<%--
  Created by IntelliJ IDEA.
  User: 24946
  Date: 2022/10/3
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>新增部门</title>
</head>
<body>
<div align='center'>
    <h1>新增部门</h1>
    <hr>
    <form action="<%=request.getContextPath()%>/dept/add" method="post" >
        <p>
            部门编号：<input type="text" name="depton">
        </p>
        <p>
            部门名称：<input type="text" name="dname">
        </p>
        <p>
            部门位置：<input type="text" name="loc">
        </p>

        <input type="submit" value="保存">
        <input type='button' value='后退' onclick='window.history.back()'>
<%--        <a href="<%= request.getContextPath()%>/JSP_Html/DeptonList.jsp">返回</a>--%>
    </form>
</div>
</body>