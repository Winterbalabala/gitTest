<%--
  Created by IntelliJ IDEA.
  User: 15738
  Date: 2020/6/18
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ADD</title>
    <style>
        tr{height: 25px;}
        td{padding: 10px}
    </style>
</head>
<body>
<form action="<%=request.getContextPath()%>/add.udo" method="post">
    <table style="margin-left: 100px; padding: 50px;border: 1px #ccc solid;width: 400px;">
        <tr>
            <td style="text-align: right;">用戶名：</td>
            <td style="text-align: left"><input type="text" name="username" /></td>
        </tr>
        <tr>
            <td style="text-align: right;">密码：</td>
            <td style="text-align: left"><input type="text" name="pasword" /></td>
        </tr>

        <tr>
            <td colspan="2" style="text-align: center"><input type="submit"  value="注册用戶"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
