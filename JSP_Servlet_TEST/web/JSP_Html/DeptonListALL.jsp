<%@ page import="java.util.List" %>
<%@ page import="bean.DeptClass" %>
<%@ page import="java.util.Objects" %><%--
  Created by IntelliJ IDEA.
  User: 24946
  Date: 2022/10/3
  Time: 18:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>部门详情</title>
</head>
<body>
<%
List<DeptClass> dept=(List<DeptClass>)request.getAttribute("dept");
    for(DeptClass deptClass : dept){


%>
<div align='center'>
<h1>部门详情</h1>
<hr>
    <p>
        部门编号：<%=deptClass.getDeptno()%>
    </p>
    <p>
        部门名称：<%=deptClass.getDname()%>
    </p>
    <p>
<%--        部门位置：<input type="text" value="<%=deptClass.getLoc()%>">--%>
        部门位置：<%=deptClass.getLoc()%>
    </p>
   <% }%>
    <input type='button' value='后退' onclick='window.history.back()'>
</div>
</body>
</html>