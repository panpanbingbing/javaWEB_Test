<%@ page import="bean.DeptClass" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 24946
  Date: 2022/10/3
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>修改部门</title>
</head>
<body>

<%
    List<DeptClass> dept=(List<DeptClass>)request.getAttribute("dept");
    for(DeptClass deptClass : dept){


%>
<h1>修改部门</h1>
<hr>
<form action="<%=request.getContextPath()%>/dept/update" method="post">
    <p>
        部门编号：<input type="text" value="<%=deptClass.getDeptno()%>" name="depton"  readonly=“readonly” >
    </p>
    <p>
        部门名称：<input type="text" value="<%=deptClass.getDname()%>" name="dname">
    </p>
    <p>
        部门位置：<input type="text" value="<%=deptClass.getLoc()%>" name="loc">
    </p>
    <%}%>
    <input type="submit" value="修改">

<%--    <a href="<%= request.getContextPath()%>/JSP_Html/DeptonList.jsp">返回</a>--%>
    <input type='button' value='后退' onclick='window.history.back()'>
</form>
</body>
</html>
