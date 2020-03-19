$(function () {
    $("#check").click(function () {
        passwordCheck();
    });
    $("#addSchool").click(function () {
        addSchool();
    });
    $("#addSchoolmaster").click(function () {
        addSchoolmaster();
    });
    getSchool();
    getSchoolmaster();
});

//两次输入密码是否一致
function passwordCheck() {
    var pas1=$("#password1").val();
    var pas2=$("#password2").val();
    if(pas1 =='' || pas2 ==''){
        $("#error").text("密码不能为空");
        $("#error").removeClass("layui-hide");
    }else if( pas1 != pas2){
        $("#error").text("两次输入的密码不一致");
        $("#error").removeClass("layui-hide");
    } else{
        $("#error").addClass("layui-hide");
        changePassword();
    }
}

//密码修改
function changePassword() {
    $.ajax({
        url:"/reserve/admin/updatePassword.do",
        dataType:"json",
        type:"post",
        data:{"newPassword":$("#password1").val()},
        success:function (data) {
            alert("密码修改成功");
        },
        error:function(jqXHR){
            alert("密码修改失败");
        }
    });
}

//校区查询
function getSchool() {
    $.ajax({
        url:"/reserve/appointment/getSchools.do",
        dataType:"json",
        type:"get",
        success:function (data) {
            var obj=eval(data);
            $.each(obj,function (i,item){
                var t1='<tr>';
                t1 += '<td>' + item.schoolName + '</td>';
                t1 +='<td>';
                t1 +='<div class="layui-table-cell laytable-cell-1-0-10">';
                t1 +='<button class="layui-btn layui-btn-xs" id="edit'+i+'" value="'+item.schoolCode+'">修改</button>';
                t1 +='<button class="layui-btn layui-btn-danger layui-btn-xs" id="del'+i+'" value="'+item.schoolCode+'">删除</button>';
                t1 +='</div>';
                t1 +='</td>';
                t1 +='</tr>';
                $("#text1").append(t1);
            });
            for(var i=0;i<obj.length;i++){
            (function (index) {
                    $("#edit"+index).click(function () {
                        changeSchool(index);
                    });
                    $("#del"+index).click(function () {
                        delSchool(index);
                    });
            })(i);}
        },
        error:function(jqXHR){
            alert("校区查询失败");
        }
    });
}

//查询校长名字
function getSchoolmaster() {
    $.ajax({
        url:"/reserve/appointment/getHeadMasters.do",
        dataType:"json",
        type:"get",
        success:function (data) {
            var obj=eval(data);
            $.each(obj,function (i,item){
                var t1='<tr>';
                t1 += '<td>' + item.name + '</td>';
                t1 +='<td>';
                t1 +='<div class="layui-table-cell laytable-cell-1-0-10">';
                t1 +='<button class="layui-btn layui-btn-xs" id="edit2'+i+'" value="'+item.code+'">修改</button>';
                t1 +='<button class="layui-btn layui-btn-danger layui-btn-xs" id="del2'+i+'"  value="'+item.code+'">删除</button>';
                t1 +='</div>';
                t1 +='</td>';
                t1 +='</tr>';
                $("#text2").append(t1);
            });
            for(var i=0;i<obj.length;i++){
                (function (index) {
                $("#edit2"+index).click(function () {
                    changeSchoolmaster(index);
                });
                $("#del2"+index).click(function () {
                    delSchoolmaster(index);
                });
            })(i);}
        },
        error:function(jqXHR){
            alert("校长查询请求失败");
        }
    });
}

//添加新校区
function addSchool() {
    layui.use('layer', function () { //独立版的layer无需执行这一句
        var $ = layui.jquery, layer = layui.layer;
        layer.open({
            id: 'LAY_layuipro3' //设定一个id，防止重复弹出
            , shade: 0.3
            , title: false //不显示标题栏
            , closeBtn: false
            , area: ['400px', '300px'],
            type: 1,
            content:
            '<form class="layui-form" style="background-color:rgb(1,151,137);height:240px;text-align: center;overflow: hidden">' +

            '<div class="layui-form-item">' +
            '<div class="layui-inline">' +
            '<h2 style="color:white;padding-top:25px;">添加新校区</h2>' +
            '</div></div><br><br><br><br>' +


            '<div class="layui-form-item">' +
            '<div class="layui-inline">' +
            '<label class="layui-form-label" style="color:white;">校区名：</label>' +
            '<div class="layui-input-inline">' +
            '<input type="text"  required lay-verify="required" id="schoolName"  class="layui-input">' +
            '</div></div></div>' +

            '</form>',
            btnAlign: 'c',
            btn: ['提交', '取消']
            , yes: function (index, layero) {
                layui.use(['layer', 'jquery'], function () {
                    var layer = layui.layer,
                        $ = layui.jquery;
                    if ($('#schoolName').val().length == 0 && $($('#schoolName').val() == "")) {
                        layer.msg('校区名为空', {
                            icon: 2,
                            time: 1000
                        }, function () {
                        });
                    } else {
                        $.ajax({
                            dataType: 'text',
                            url: '/reserve/admin/addSchool.do',
                            type: 'post',
                            data: {'schoolName': $('#schoolName').val()},
                            success: function (data) {
                                alert("添加新校区成功");
                                layer.close(index);
                                $("#text1").html("");
                                getSchool();
                            },
                            error: function (jqXHR) {
                                alert("添加新校区失败");
                            }
                        })

                    }
                })


            }
            , success: function (index, layero) {
                layui.use('form', function () {
                    var form = layui.form;
                });
            }
        });
    });
}

//修改校区
function changeSchool(a) {
    layui.use('layer', function () { //独立版的layer无需执行这一句
        var $ = layui.jquery, layer = layui.layer;
        layer.open({
            id: 'LAY_layuipro' //设定一个id，防止重复弹出
            , shade: 0.3
            , title: false //不显示标题栏
            , closeBtn: false
            , area: ['400px', '300px'],
            type: 1,
            content:
            '<form class="layui-form" style="background-color:rgb(1,151,137);height:240px;text-align: center;overflow: hidden">' +

            '<div class="layui-form-item">' +
            '<div class="layui-inline">' +
            '<h2 style="color:white;padding-top:25px;">修改校区</h2>' +
            '</div></div><br><br><br>' +


            '<div class="layui-form-item">' +
            '<div class="layui-inline">' +
            '<label class="layui-form-label" style="color:white;">校区名：</label>' +
            '<div class="layui-input-inline">' +
            '<input type="text"  required lay-verify="required" id="schoolName"  class="layui-input">' +
            '</div></div></div>' +

            '</form>',
            btnAlign: 'c',
            btn: ['提交', '取消']
            , yes: function (index, layero) {
                layui.use(['layer', 'jquery'], function () {
                    var layer = layui.layer,
                        $ = layui.jquery;
                    if ($('#schoolName').val().length == 0 && $($('#schoolName').val() == "")) {
                        layer.msg('校区名为空', {
                            icon: 2,
                            time: 1000
                        }, function () {
                        });
                    } else {
                        $.ajax({
                            dataType: 'text',
                            url: '/reserve/admin/modifySchoolByCode.do',
                            type: 'post',
                            data: {'newSName': $('#schoolName').val(),'sCode': $("#edit"+a).val()},
                            success: function (data) {
                                alert("修改校区成功");
                                layer.close(index);
                                $("#text1").html("");
                                getSchool();
                            },
                            error: function (jqXHR) {
                                alert("修改校区失败");
                            }
                        })

                    }
                })


            }
            , success: function (index, layero) {
                layui.use('form', function () {
                    var form = layui.form;
                });
            }
        });
    });
}

//删除校区
function delSchool(a) {
    layui.use('layer', function () { //独立版的layer无需执行这一句
        var $ = layui.jquery, layer = layui.layer;
        layer.open({
            id: 'LAY_layuipro4' //设定一个id，防止重复弹出
            , shade: 0.3
            , title: false //不显示标题栏
            , closeBtn: false
            , area: ['400px', '200px'],
            type: 1,
            content:
            '<form class="layui-form" style="background-color:rgb(1,151,137);height:100px;text-align: center;overflow: hidden">' +
            '<div class="layui-form-item">' +
            '<div class="layui-inline">' +
            '<h2 style="color:white;padding-top:45px;">是否删除该校区</h2>' +
            '</div></div>'+
            '</form>',
            btnAlign: 'c',
            btn: ['是', '否']
            , yes: function (index, layero) {
                layui.use(['layer', 'jquery'], function () {
                    var layer = layui.layer,
                        $ = layui.jquery;
                        $.ajax({
                            dataType: 'text',
                            url: '/reserve/admin/deleteSchoolByCode.do',
                            type: 'post',
                            data: {'sCode': $("#del"+a).val()},
                            success: function (data) {
                                alert("删除该校区成功");
                                layer.close(index);
                                $("#text1").html("");
                                getSchool();
                            },
                            error: function (jqXHR) {
                                alert("删除该校区失败");
                            }
                        })
                })


            }
            , success: function (index, layero) {
                layui.use('form', function () {
                    var form = layui.form;
                });
            }
        });
    });
}

//添加校长
function addSchoolmaster() {
    layui.use('layer', function () { //独立版的layer无需执行这一句
        var $ = layui.jquery, layer = layui.layer;
        layer.open({
            id: 'LAY_layuipro2' //设定一个id，防止重复弹出
            , shade: 0.3
            , title: false //不显示标题栏
            , closeBtn: false
            , area: ['400px', '300px'],
            type: 1,
            content:
            '<form class="layui-form" style="background-color:rgb(1,151,137);height:240px;text-align: center;overflow: hidden">' +

            '<div class="layui-form-item">' +
            '<div class="layui-inline">' +
            '<h2 style="color:white;padding-top:25px;">添加校长</h2>' +
            '</div></div><br><br><br><br>' +


            '<div class="layui-form-item">' +
            '<div class="layui-inline">' +
            '<label class="layui-form-label" style="color:white;">校长名：</label>' +
            '<div class="layui-input-inline">' +
            '<input type="text"  required lay-verify="required" id="schoolmasterName"  class="layui-input">' +
            '</div></div></div>' +

            '</form>',
            btnAlign: 'c',
            btn: ['提交', '取消']
            , yes: function (index, layero) {
                layui.use(['layer', 'jquery'], function () {
                    var layer = layui.layer,
                        $ = layui.jquery;
                    if ($('#schoolmasterName').val().length == 0 && $($('#schoolmasterName').val() == "")) {
                        layer.msg('校长为空', {
                            icon: 2,
                            time: 1000
                        }, function () {
                        });
                    } else {
                        $.ajax({
                            dataType: 'text',
                            url: '/reserve/admin/addHeadMaster.do',
                            type: 'post',
                            data: {'headMasterName': $('#schoolmasterName').val()},
                            success: function (data) {
                                alert("添加校长成功");
                                layer.close(index);
                                $("#text2").html("");
                                getSchoolmaster();
                            },
                            error: function (jqXHR) {
                                alert("添加校长失败");
                            }
                        })

                    }
                })


            }
            , success: function (index, layero) {
                layui.use('form', function () {
                    var form = layui.form;
                });
            }
        });
    });
}

//修改校长
function changeSchoolmaster(a) {
    layui.use('layer', function () { //独立版的layer无需执行这一句
        var $ = layui.jquery, layer = layui.layer;
        layer.open({
            id: 'LAY_layuipro5' //设定一个id，防止重复弹出
            , shade: 0.3
            , title: false //不显示标题栏
            , closeBtn: false
            , area: ['400px', '300px'],
            type: 1,
            content:
            '<form class="layui-form" style="background-color:rgb(1,151,137);height:240px;text-align: center;overflow: hidden">' +

            '<div class="layui-form-item">' +
            '<div class="layui-inline">' +
            '<h2 style="color:white;padding-top:25px;">修改校长</h2>' +
            '</div></div><br><br><br><br>' +


            '<div class="layui-form-item">' +
            '<div class="layui-inline">' +
            '<label class="layui-form-label" style="color:white;">校长名：</label>' +
            '<div class="layui-input-inline">' +
            '<input type="text"  required lay-verify="required" id="schoolmasterName"  class="layui-input">' +
            '</div></div></div>' +

            '</form>',
            btnAlign: 'c',
            btn: ['提交', '取消']
            , yes: function (index, layero) {
                layui.use(['layer', 'jquery'], function () {
                    var layer = layui.layer,
                        $ = layui.jquery;
                    if ($('#schoolmasterName').val().length == 0 && $($('#schoolmasterName').val() == "")) {
                        layer.msg('校长名为空', {
                            icon: 2,
                            time: 1000
                        }, function () {
                        });
                    } else {
                        $.ajax({
                            dataType: 'text',
                            url: '/reserve/admin/modifyHeadMasterByCode.do',
                            type: 'post',
                            data: {'newHName': $('#schoolmasterName').val(),'hCode': $("#edit2"+a).val()},
                            success: function (data) {
                                alert("修改校长名成功");
                                layer.close(index);
                                $("#text2").html("");
                                getSchoolmaster();
                            },
                            error: function (jqXHR) {
                                alert("修改校长名成功");
                            }
                        })

                    }
                })


            }
            , success: function (index, layero) {
                layui.use('form', function () {
                    var form = layui.form;
                });
            }
        });
    });
}

//删除校长
function delSchoolmaster(a) {
    layui.use('layer', function () { //独立版的layer无需执行这一句
        var $ = layui.jquery, layer = layui.layer;
        layer.open({
            id: 'LAY_layuipro6' //设定一个id，防止重复弹出
            , shade: 0.3
            , title: false //不显示标题栏
            , closeBtn: false
            , area: ['400px', '200px'],
            type: 1,
            content:
            '<form class="layui-form" style="background-color:rgb(1,151,137);height:100px;text-align: center;overflow: hidden">' +
            '<div class="layui-form-item">' +
            '<div class="layui-inline">' +
            '<h2 style="color:white;padding-top:45px;">是否删除该校长</h2>' +
            '</div></div>'+
            '</form>',
            btnAlign: 'c',
            btn: ['是', '否']
            , yes: function (index, layero) {
                layui.use(['layer', 'jquery'], function () {
                    var layer = layui.layer,
                        $ = layui.jquery;
                    $.ajax({
                        dataType: 'text',
                        url: '/reserve/admin/deleteHeadMasterByCode.do',
                        type: 'post',
                        data: {'hCode': $("#del2"+a).val()},
                        success: function (data) {
                            alert("删除该校长成功");
                            layer.close(index);
                            $("#text2").html("");
                            getSchoolmaster();
                        },
                        error: function (jqXHR) {
                            alert("删除该校长失败");
                        }
                    })
                })


            }
            , success: function (index, layero) {
                layui.use('form', function () {
                    var form = layui.form;
                });
            }
        });
    });
}