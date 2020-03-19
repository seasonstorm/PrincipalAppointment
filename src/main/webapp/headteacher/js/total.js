//日期范围
layui.use(['laydate'], function(){
    var laydate = layui.laydate;

    var start = laydate.render({
        elem: '#start_time'
        ,type:'month'
        ,format: 'yyyy年MM月'
        ,value:'2018年01月',
        btns: ['clear', 'confirm'],
        done: function(value, date){
            endMax = end.config.max;
            end.config.min = date;
            end.config.min.month = date.month -1;
        }
    });

    var end = laydate.render({
        elem: '#end_time'
        ,type:'month'
        ,format: 'yyyy年MM月'
        ,value:'2018年02月',
        done: function(value, date){
            if($.trim(value) == ''){
                var curDate = new Date();
                date = {'date': curDate.getDate(), 'month': curDate.getMonth()+1, 'year': curDate.getFullYear()};
            }
            start.config.max = date;
            start.config.max.month = date.month -1;
        }
    });
});

//表格生成
$(function () {
    getSchollmaster();
    $("#search").click(function () {
        $("#text").html("");
        getForm();
    });
});
//校长姓名查询
function getSchollmaster() {
    $.ajax({
        url:"/reserve/appointment/getHeadMasters.do",
        dataType:"json",
        type:"get",
        success:function (data) {
            var obj=eval(data);
            var t1='<tr>'+'<th>时间</th>';
            $.each(obj,function (i,item){
                t1 += '<th>' + item.name + '</th>';
            });
            t1 +='<th>总计</th>'+ '</tr>';
            $("thead").append(t1);
        },
        error:function(jqXHR){
            alert("请求失败");
        }
    });
}

//查询请求提交
function getForm() {
    $.ajax({
        url:"/reserve/admin/getRangeAppointmentInfo.do",
        dataType:"json",
        type:"post",
        data:{"startDate":timeRange("start_time"),"endDate":timeRange("end_time")},
        success:function (data) {
            var s=[['总计']];
            var obj=eval(data);
            $.each(obj,function (i,item){
                var t2='<tr>';
                var k=0;
                $.each(item,function (j,val) {
                    t2 += '<td>' + val + '</td>';
                    if(k) {
                        if(i==0)s.push('0');

                        s[k]=Number(s[k])+Number(val);
                    }
                    k++;
                });
                t2 += '</tr>';
                $("tbody").append(t2);
            });

            //所有数据总计
            var t3='<tr>';
            $.each(s,function (j,val) {
                t3 += '<td>' + val + '</td>';
            });
            t3 +='</tr>';
            $("tbody").append(t3);
        },
        error:function(jqXHR){
            alert("查询失败");
        }
    });
}

//日期格式修改
function timeRange(a) {
    var date;
    date=$("#"+a).val().substring(0,4);
    date+="-";
    date+=$("#"+a).val().substring(5,7);
    return date;
}
