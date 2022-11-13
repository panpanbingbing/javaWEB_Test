<%--
  Created by IntelliJ IDEA.
  User: 24946
  Date: 2022/10/4
  Time: 21:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>用户注册</title>
    <Style type="text/css">
        span {
            color: brown;

        }
    </Style>
</head>
<body>
<script text="text/javascript">

    window.onload=function(){
        var user = document.getElementById("user");
        var span1 = document.getElementById("span1");
        // var uesrzheng= /^[a-zA-Z][\u4e00-\u9fa5a-zA-Z0-9]{6,12}$/
        var uesrzheng= /^[\u4e00-\u9fa5a-zA-Z0-9]{2,12}$/
        //失去光标是
        user.onblur=function(){
            if(!uesrzheng.test(user.value)){
                span1.innerText= "用户名包含2-12位中文、大小写字母、和数字"
            }
        }
        user.onfocus=function(){
            span1.innerText= ""
        }
        var pass=document.getElementById("pass")
        var passzheng=/^[a-zA-Z]\w{5,17}$/
        var pass2 = document.getElementById("pass2")
        var span2 = document.getElementById("span2");
        var span3 = document.getElementById("span3");
        pass.onblur=function(){
            if(!passzheng.test(pass.value)){
                span2.innerText="以字母开头，长度在6~18之间，只能包含字符、数字和下划线"
            }
            if(pass.value != pass2.value){
                span3.innerText="密码不一致"
            }
        }
        pass.onfocus=function(){
            span2.innerText= ""
            span3.innerText= ""
        }
        pass2.onblur=function(){
            if(pass.value != pass2.value){
                span3.innerText="密码不一致"
            }
        }
        pass2.onfocus=function(){
            span3.innerText= ""
        }
        var email =document.getElementById("email");
        var emalzheng = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/
        var span4 = document.getElementById("span4")
        email.onblur=function(){
            if(!emalzheng.test(email.value)){
                span4.innerText="邮箱格式错误"
            }
        }
        email.onfocus=function(){
            span4.innerText=""
        }
        var deng = document.getElementById("deng")
        var qing = document.getElementById("qing")
        var userform = document.getElementById("userform")
        deng.onclick=function(){
            user.focus();
            user.blur();

            pass.focus();
            pass.blur();

            pass2.focus();
            pass2.blur();

            email.focus();
            email.blur();




            if( span1.innerText==="" && span2.innerText===""&&span4.innerText==="" &&span3.innerText==="" ){
                userform.submit();
            }

        }
        qing.onclick=function(){
            span1.innerText=""
            span2.innerText=""
            span3.innerText=""
            span4.innerText=""
        }
    }
</script>
<div align='center'>
    <h1>用户注册</h1>
    <hr>
<form id ="userform" action="<%=request.getContextPath()%>/user/register" method="post">
    用户名：  <input type="text"  id="user" name="username">   <span id="span1"></span><br>
    密码：    <input type="password"  id="pass" name="password">   <span id="span2"></span><br>
    确定密码：<input type="password"   id="pass2">                 <span id="span3"></span><br>
    邮箱：    <input type="text"  id="email" name="email">       <span id="span4"></span><br>
    <input type="button" id="deng" value="注册">
    <input type="reset" id="qing" value="清空">
</form>
</div>
</body>
</html>