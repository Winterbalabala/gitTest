<%@ page import="cn.mvc.session.model.User" %><%--
  Created by IntelliJ IDEA.
  User: 15738
  Date: 2020/6/18
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        tr{height: 25px;}
        td{padding: 10px}
    </style>
</head>
<body>
<%User user = (User) request.getAttribute("we"); %>

<form action="<%=request.getContextPath()%>/updatedo.udo" method="post">
    <input type="hidden" name="id" value="<%=user.getId() %>">
    <table style="margin-left: 100px; padding: 50px;border: 1px #ccc solid;width: 400px;">
        <%
            String note = (String) request.getAttribute("note");
            if(note!=null && !"".equals(note)){

        %>
        <tr>
            <td colspan="2" style="text-align: right; color: crimson; font-weight: bolder"><%=note%></td>
        </tr>
        <%}%>
        <tr>
            <td style="text-align: right;">用戶名：</td>
            <td style="text-align: left"><input type="text" name="name" value="<%=user.getUsername()%>"/></td>
        </tr>
        <tr>
            <td style="text-align: right;">密码：</td>
            <td style="text-align: left"><input type="text" name="age" value="<%=user.getPasword()%>"/></td>
        </tr>
        <tr>　

            <td style="text-align: right;">时间：</td>
            <td style="text-align: left"><input type="text" name="gender" value="<%=user.getRegDate()%>"/></td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center"><input type="submit"  value="修改信息"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
