<%@ page import="java.util.List" %>
<%@ page import="bean.DeptClass" %>
<%@ page import="java.util.Objects" %><%--
  Created by IntelliJ IDEA.
  User: 24946
  Date: 2022/10/3
  Time: 18:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang='en'>
<head>
    <meta charset='UTF-8'>
    <title>list</title>
</head>
<body>

<script type='text/javascript'>

    function dels(tepton) {

        if (window.confirm('亲，确定删除吗？')) {
            document.location.href = '<%=request.getContextPath()%>/dept/delete?tepton=' + tepton
        }
    }
</script>


<h1 align='center'>部门列表</h1>
<h3>欢迎<%=session.getAttribute("username")%>登录成功</h3><br>
<a href="<%=request.getContextPath()%>/user/out">[退出登录]</a>
<hr>
<table border='1px' align='center' width='50%'>
    <tr>
        <th>序号</th>
        <th>部门编号</th>
        <th>部门名称</th>
        <th>操作</th>
    </tr>

    <%

        List<DeptClass> dept = (List<DeptClass>) request.getAttribute("dept");
        int i = 0;
        for (DeptClass deptClass : dept) {
    %>
            <tr>
                <td>
                <%=++i%>
                </td>
                <td>
                    <%=deptClass.getDeptno()%>
                </td>
                <td>
                    <%=deptClass.getDname()%>
                </td>
                <td>
                    <a href='javascript:viod(0)' onclick='dels(<%=deptClass.getDeptno()%>)'>删除</a>
                    <a href="<%=request.getContextPath()%>/dept/listall?f=update&tepton=<%=deptClass.getDeptno()%>">修改</a>
                    <a href="<%=request.getContextPath()%>/dept/listall?f=listall&tepton=<%=deptClass.getDeptno()%>">详情</a>
                </td>
            <tr>

                    <%
           }
            %>


</table>
<hr>
<a href="<%=request.getContextPath()%>/JSP_Html/DeptonAdd.jsp">新增部门</a>
</body>
</html>
