<%@ page import="java.util.List" %>
<%@ page import="cn.mvc.session.model.User" %>
<%--
  Created by IntelliJ IDEA.
  User: 15738
  Date: 2020/6/13
  Time: 8:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <style>
      tr{height: 25px;}
      td{padding: 10px}
    </style>
  </head>
  <body>
<%--  实现了未登录状态下无法进入其他页面，对用户登陆状态进行判断--%>
<%--  <%--%>
<%--      String username = (String) request.getSession().getAttribute("user");--%>
<%--      if (username == null || "".equals(username)){--%>
<%--        response.sendRedirect(request.getContextPath() + "/login.jsp");--%>
<%--      }--%>
<%--  %>--%>

<%--  实现了未登录状态下无法进入其他页面，对用户登陆状态进行判断--%>
<%--<c:if test = "${empty sessionScope.user}">--%>
<%--  <c:redirect url = "/login.jsp"/>--%>
<%--</c:if>--%>


  <form action="<%=request.getContextPath()%>/query.udo" method="post">
    <table style="margin-left: 100px; padding: 50px;border: 1px #ccc solid;width: 400px;">
      <tr>
        <td style="text-align: right;">用戶名：</td>
        <td style="text-align: left"><input type="text" name="username" /></td>
      </tr>
      <tr>
        <td colspan="2" style="text-align: center"><input type="submit"  value="查詢用戶"/>
        </td>
      </tr>
      <tr>
        <td colspan="2" style="text-align: center">
          <a href="<%=request.getContextPath()%>/add.jsp">注册</a>
        </td>
      </tr>
      <tr>
        <td colspan="2" style="text-align: center">
          <a href="<%=request.getContextPath()%>/logout.udo">注销</a>
        </td>
      </tr>

    </table>
  </form>
  <table style="margin-left: 100px; padding:50px "border="1" cellpadding="0" cellspacing="0">
    <tr>
      <td>用戶id</td>
      <td>用戶名</td>
      <td>用戶密码</td>
      <td>时间</td>
      <td>操作记录</td>
    </tr>
    <%
    List<User> list = (List<User>) request.getAttribute("userList");
    if (list != null && list.size()>0){
      for (User user:list){
    %>
    <tr>
      <td><%=user.getId()%></td>
      <td><%=user.getUsername()%></td>
      <td><%=user.getPasword()%></td>
      <td><%=user.getRegDate()%></td>
      <td><a href="<%=request.getContextPath()%>/update.udo?id=<%=user.getId()%>">编辑 </a ><a href="<%=request.getContextPath()%>/delete.udo?id=<%=user.getId()%>"> 删除</a></td>
    </tr>
    <%
      }
    }
    %>
  </table>
  </body>
</html>