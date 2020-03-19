package com.cuit.controller;

import com.cuit.entity.Appointment;
import com.cuit.entity.HeadMaster;
import com.cuit.entity.School;
import com.cuit.service.impl.AppointmentServiceImpl;
import com.cuit.service.impl.SchoolAndHeadMasterServiceImpl;
import com.google.gson.Gson;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin")
public class HeadMasterSelectController {

    @Autowired
    private SchoolAndHeadMasterServiceImpl schoolAndHeadMasterService;

    @Autowired
    private AppointmentServiceImpl appointmentService;

    @RequestMapping(value = "/updatePassword.do")
    public void updatePassword(@Param("newPassword") String newPassword, HttpServletResponse response) {
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");

        int result = schoolAndHeadMasterService.updatePassword(newPassword);

        try {
            if (result == 1) {
                response.getWriter().write("1");
            } else {
                response.getWriter().write("0");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("HeadMasterSelectController#updatePassword:出现异常");
        }
    }

    @RequestMapping(value = "/addHeadMaster.do")
    public void addHeadMaster(@Param("headMasterName") String headMasterName,
                              HttpServletResponse response) {
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");

        int result = schoolAndHeadMasterService.insertHeadMaster(headMasterName);

        try {
            if (result == 1) {
                response.getWriter().write("1");
            } else {
                response.getWriter().write("0");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("HeadMasterSelectController#addHeadMaster:出现异常");
        }
    }

    @RequestMapping(value = "/addSchool.do")
    public void addSchool(@Param("schoolName") String schoolName,
                          HttpServletResponse response) {
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");

        int result = schoolAndHeadMasterService.insertSchool(schoolName);

        try {
            if (result == 1) {
                response.getWriter().write("1");
            } else {
                response.getWriter().write("0");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("HeadMasterSelectController#addSchool:出现异常");
        }
    }

    @RequestMapping(value = "/modifyHeadMasterByCode.do")
    public void modifyHeadMasterByCode(@Param("newHName") String newHName,
                                       @Param("hCode") int hCode,
                                       HttpServletResponse response) {

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");

        HeadMaster headMaster = new HeadMaster();
        headMaster.setCode(hCode);
        headMaster.setName(newHName);
        int result = schoolAndHeadMasterService.updateHeadMasterByCode(headMaster);

        try {
            if (result == 1) {
                response.getWriter().write("1");
            } else {
                response.getWriter().write("0");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("HeadMasterSelectController#modifyHeadMasterByCode:出现异常");
        }
    }

    @RequestMapping(value = "/modifySchoolByCode.do")
    public void modifySchoolByCode(@Param("newSName") String newSName,
                                   @Param("sCode") int sCode,
                                   HttpServletResponse response) {

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");

        School school = new School();
        school.setSchoolCode(sCode);
        school.setSchoolName(newSName);

        int result = schoolAndHeadMasterService.updateSchoolByCode(school);

        try {
            if (result == 1) {
                response.getWriter().write("1");
            } else {
                response.getWriter().write("0");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("HeadMasterSelectController#modifySchoolByCode:出现异常");
        }
    }

    @RequestMapping(value = "/deleteHeadMasterByCode.do")
    public void deleteHeadMasterByCode(@Param("hCode") int hCode, HttpServletResponse response) {

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");

        int result = schoolAndHeadMasterService.deleteHeadMasterByCode(hCode);

        try {
            if (result == 1) {
                response.getWriter().write("1");
            } else {
                response.getWriter().write("0");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("HeadMasterSelectController#deleteHeadMasterByCode:出现异常");
        }
    }

    @RequestMapping(value = "/deleteSchoolByCode.do")
    public void deleteSchoolByCode(@Param("sCode") int sCode, HttpServletResponse response) {

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");

        int result = schoolAndHeadMasterService.deleteSchoolByCode(sCode);

        try {
            if (result == 1) {
                response.getWriter().write("1");
            } else {
                response.getWriter().write("0");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("HeadMasterSelectController#deleteSchoolByCode:出现异常");
        }
    }

    @RequestMapping(value = "/login.do")
    public void login(@Param("username") String username,
                      @Param("password") String password,
                      HttpServletRequest request,
                      HttpServletResponse response) {

        int result = schoolAndHeadMasterService.loginCheck(username, password);

        try {
            if (result == 1) {
                request.getSession().setAttribute("user", "1");
                response.getWriter().write("1");
            } else {
                response.setContentType("text/plain");
                response.setCharacterEncoding("utf-8");
                response.getWriter().write("0");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("HeadMasterSelectController#login:出现异常");
        }
    }

    @RequestMapping(value = "/getRangeAppointmentInfo.do")
    public void getRangeAppointmentInfo(@Param("startDate") String startDate,
                                        @Param("endDate") String endDate,
                                        HttpServletResponse response) {
        List<HeadMaster> headMasters = appointmentService.selectHeadMasters();

        int startYear = Integer.parseInt(startDate.substring(0,4));
//        System.out.println(startYear);
        int endYear = Integer.parseInt(endDate.substring(0,4));
//        System.out.println(endYear);
        int startMonth = Integer.parseInt(startDate.substring(5));
//        System.out.println(startMonth);
        int endMonth = Integer.parseInt(endDate.substring(5));
//        System.out.println(endMonth);

        int monthNumber = 12 * (endYear - startYear) + (endMonth - startMonth) + 1;

//        System.out.println("查询月个数" + monthNumber);

        List<Map> infoList = new ArrayList<>();
        String selectDate;
        int selectYear = startYear;
        int selectMonth = startMonth;
        for (int i = 0;i < monthNumber;i++) {
            int countSum = 0;
            Map<String, String> map = new LinkedHashMap<>();
            selectDate = selectYear + "-" + selectMonth;
            map.put("date",selectDate);
            selectDate += "-01";
            for (HeadMaster headMaster : headMasters) {
                int count =
                        schoolAndHeadMasterService
                                .selectAppointmentNumberByHCode(selectDate,
                                        headMaster.getCode());
                map.put("code" + headMaster.getCode(),count + "");
                countSum += count;
            }
            if (selectMonth == 12) {
                selectMonth = 1;
                selectYear += 1;
            } else {
                selectMonth += 1;
            }
            map.put("sum", countSum + "");
            infoList.add(map);
        }

        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json");
            response.getWriter().write(new Gson().toJson(infoList));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("HeadMasterSelectController#getRangeAppointmentInfo:出现异常");
        }
    }

    @RequestMapping(value = "/getInfoByChoiceDateAndCode.do")
    public void getInfoByChoiceDateAndCode(@Param("hCode") int hCode,
                                           @Param("sCode") int sCode,
                                           @Param("choiceDate") String choiceDate,
                                           HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json");

        String choiceMonth = choiceDate + "-01";

        List<Appointment> appointmentList = appointmentService.selectChoiceDateAppointmentInfo(choiceMonth, hCode, sCode);
        List<Map> list = new ArrayList<>();
        for (Appointment appointment : appointmentList) {
            Map<String, String> infoMap = new LinkedHashMap<>();
            //校区名
            infoMap.put("schoolarea",appointmentService.selectSchoolNameByCode(appointment.getSchoolCode()));infoMap.put("date", getShowTime(appointment));
            //校长名
            infoMap.put("headteacher",  appointmentService.selectHeadMasterNameByCode(appointment.getHeadmasterCode()));
            infoMap.put("phone", appointment.getPhone());
            infoMap.put("appoinementpeople", appointment.getName());
            list.add(infoMap);
        }

        String jsonData = new Gson().toJson(list);

        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json");
            response.getWriter().write(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("AppointmentController#getInfoByChoiceDateAndCode:出现异常");
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
