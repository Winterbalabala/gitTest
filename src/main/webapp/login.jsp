<%--
  Created by IntelliJ IDEA.
  User: 15738
  Date: 2020/9/4
  Time: 9:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
<%--    <script type="javascript">--%>
<%--        function getCookie(c_name){--%>
<%--            if (document.cookie.length >0){--%>
<%--                var c_start = document.cookie.indexOf(c_name+"=");--%>
<%--                c_start = c_start + c_name.length+1;--%>
<%--                var c_end = document.cookie.indexOf(";",c_start);--%>
<%--                if (c_end == -1) c_end = document.cookie.length;--%>
<%--                return unescape(document.cookie.substring(c_start,c_end));--%>
<%--            }--%>
<%--        }--%>
<%--        window.onload=function () {--%>
<%--            var form = document.getElementById("loginform");--%>
<%--            var username = document.getElementById("username");--%>
<%--            if (getCookie("userkey")!="" && getCookie("userkey")!=null && getCookie("ssid")!="" && getCookie("ssid")!=null){--%>
<%--                username.value = getCookie("userKey");--%>
<%--                form.submit();--%>
<%--            }--%>
<%--        }--%>
<%--    </script>--%>
</head>
<body>
    <br>
    <br>
    <%if (request.getAttribute("note")!=null){%>
    <span style="color: red; font-weight: bolder;"><%=request.getAttribute("note")%></span>
    <%}%>
    <br>
    <form action="<%=request.getContextPath()%>/login.udo" method="get">
        用户名：<input  type="text" name="username" value=""/>
        <br><br><br>
        密  码：<input type="text" name="pasword"/>
        <br><br><br>
        记住我:7天<input type="radio" name="expiredays" value="7"/>30天<input type="radio" name="expiredays" value="30"/>永久<input type="radio" name="expiredays" value="100"/>
        <br><br><br>
        <input type="submit" value="登录" />
    </form>
</body>
</html>
