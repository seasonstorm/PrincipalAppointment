$(function(){
	 $('a#adminlogin').click(function(){
         getLoginForm();
     });



    $('#checknumber').click(function () {
	     if($("input[id='phonenumber']").val().length==0&&$("input[id='phonenumber']").val()==""){
             $('#phonenumbertable tbody').html('');
             layui.use('layer', function(){
                 var layer = layui.layer;
                 layer.msg('查询条件为空', {
                     icon: 2,
                     time: 1000
                 }, function(){
                 });
             });
         }else{
             $.ajax({
                 type:'get',
                 data:{
                     'phone':$("input[id='phonenumber']").val()
                 },
                 url:'/reserve/appointment/getInfoByPhone.do',
                 dataType:'json',
                 success:function(data){

                     if(data.length!=0){
                         $('#phonenumbertable tbody').html('');
                         for(var i in data){
                             $('#phonenumbertable tbody').append('<tr><th>'+data[i].schoolarea+'</th>'+'<th>'+data[i].headteacher+'</th>'+'<th>'+data[i].date+'</th>'+'<th>'+data[i].phone+'</th>'+'<th>'+data[i].appoinementpeople+'</th></tr>');
                         }

                     }
                     else{
                         $('#phonenumbertable tbody').html('');
                         layui.use('layer', function(){
                             var layer = layui.layer;
                             layer.msg('无预约记录', {
                                 icon: 2,
                                 time: 1000
                             }, function(){
                             });
                         });
                     }

                 },
                 error:function(e){
                     console.log(e);
                 }
             });
         }


     });
     $.ajax({
        url:'/reserve/appointment/getHeadMasters.do',
        dataType:'json',
        success:function(data){
            for(var i in data){

                $('#headteachers').append('<option value='+data[i].code+'>'+data[i].name+'</option>');
                layui.use('form', function(){
                    var form = layui.form;
                    form.render();
                });
            }
        },
        error:function(e){
            console.log(e);
        }
    });

    $.ajax({
        url:'/reserve/appointment/getSchools.do',
        dataType:'json',
        success:function(data){

            for(var i in data){

                $('#schoolareas').append('<option value='+data[i].schoolCode+'>'+data[i].schoolName+'</option>');
                layui.use('form', function(){
                    var form = layui.form;
                    form.render();
                });
            }
        },
        error:function(e){
            console.log(e);
        }
    });


})