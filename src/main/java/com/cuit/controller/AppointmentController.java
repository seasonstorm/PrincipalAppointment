package com.cuit.controller;

import com.cuit.entity.Appointment;
import com.cuit.entity.School;
import com.cuit.service.impl.AppointmentServiceImpl;
import com.cuit.util.tools.RandomCodeImageBuilder;
import com.cuit.util.tools.UUIDBuilder;
import com.google.gson.Gson;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

@Controller
@RequestMapping(value = "/appointment")

public class AppointmentController {

    @Autowired
    private AppointmentServiceImpl appointmentService;

    @RequestMapping(value = "/getHeadMasters.do")
    public void getHeadMasters(HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json");

        String jsonData = new Gson().toJson(appointmentService.selectHeadMasters());
        try {
            response.getWriter().write(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("AppointmentController#getHeadMasters:出现异常");
        }
    }

    @RequestMapping(value = "/getSchools.do")
    public void getSchools(HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json");
        String jsonData = new Gson().toJson(appointmentService.selectSchools());
        try {
            response.getWriter().write(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("AppointmentController#getSchools:出现异常");
        }
    }

    @RequestMapping(value = "/getChoiceDateAppointmentInfo.do")
    public void getChoiceDateAppointmentInfo(@Param("hCode") int hCode,
                                             @Param("sCode") int sCode,
                                             @Param("choiceDate") String choiceDate,
                                             HttpServletResponse response, HttpServletRequest request) {

        String choiceMonth = choiceDate + "-01";

        List<Appointment> appointmentList = appointmentService.selectChoiceDateAppointmentInfo(choiceMonth, hCode, sCode);
        List<Map> list = new ArrayList<>();
        for (Appointment appointment : appointmentList) {
            Map<String, String> map = new LinkedHashMap<>();
            if (appointment.getAppointmentTime().equals("0")) {
                map.put("appointmentDate",appointment.getAppointmentDate() + "0");
            } else {
                map.put("appointmentDate",appointment.getAppointmentDate() + "1");
            }
            list.add(map);
        }

        String jsonData = new Gson().toJson(list);

        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json");
            response.getWriter().write(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("AppointmentController#getChoiceDateAppointmentInfo:出现异常");
        }
    }

    @RequestMapping(value = "/appointmentDeal.do")
    public void appointmentDeal(@Param("hCode") int hCode,
                                @Param("sCode") int sCode,
                                @Param("name") String name,
                                @Param("tel") String tel,
                                @Param("appointmentDate") String appointmentDate,
                                @Param("appointmentTime") String appointmentTime,
                                HttpServletResponse response) {
        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(appointmentDate);
        appointment.setAppointmentTime(appointmentTime);
        appointment.setCode(new UUIDBuilder().BuildUUID(8));
        appointment.setName(name);
        appointment.setPhone(tel);
        appointment.setSchoolCode(sCode);
        appointment.setHeadmasterCode(hCode);
        appointment.setRecordTime(new Date());

        int status = appointmentService.insertAppointmentInfo(appointment);

        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/plain");
            if (status == 1) {
                response.getWriter().write("1");
            } else {
                response.getWriter().write("0");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("AppointmentController#appointmentDeal:出现异常");
        }
    }

    @RequestMapping(value = "/getCodeImage.do")
    public void getCodeImage(HttpServletResponse response, HttpSession session) {
        //设置response头信息
        //禁止缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        RandomCodeImageBuilder builder = new RandomCodeImageBuilder();
        String testCode = builder.getCode();
        session.setAttribute("testCode", testCode.toLowerCase());
        BufferedImage image = builder.getBufferedImage(testCode);

        try {
            ImageIO.write(image, "JPEG", response.getOutputStream());
            response.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("AppointmentController#getCodeImage:出现异常");
        }
    }

    @RequestMapping(value = "testCodeData.do")
    public void testCodeData(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");

        String testCode = (String) request.getSession().getAttribute("testCode");
        String userCode = request.getParameter("testCode");

        try {
            if (userCode.toLowerCase().equals(testCode)) {
                response.getWriter().write("1");
            } else {
                response.getWriter().write("0");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("AppointmentController#testCodeData:出现异常");
        }
    }

    @RequestMapping(value = "/getInfoByPhone.do")
    public void getInfoByPhone(@Param("phone") String phone,  HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json");

        List<Appointment> appointmentList = appointmentService.selectByPhone(phone);
        List<Map> list = new ArrayList<>();
        for (int i = 0;i < appointmentList.size();i++) {
            Appointment appointment = appointmentList.get(i);
            Map<String, String> map = new LinkedHashMap<>();
            //校区名
            map.put("schoolarea", appointmentService.selectSchoolNameByCode(appointment.getSchoolCode()));
            //校长名
            map.put("headteacher", appointmentService.selectHeadMasterNameByCode(appointment.getHeadmasterCode()));
            map.put("date", getShowTime(appointment));
            map.put("phone", appointment.getPhone());
            map.put("appoinementpeople", appointment.getName());
            list.add(map);
        }

        String jsonData = new Gson().toJson(list);

        try {
            response.getWriter().write(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("AppointmentController#getInfoByPhone:出现异常");
        }
    }

    private String getShowTime(Appointment appointment) {
        String dateStr = appointment.getAppointmentDate();
        dateStr.replaceFirst("-", "年");
        dateStr.replaceFirst("-", "月");
        if (appointment.getAppointmentTime().equals("0")) {
            return dateStr + "日上午";
        }
        return dateStr + "日下午";
    }
}

