<%--
  Created by IntelliJ IDEA.
  User: 季节-风暴
  Date: 2018/9/13
  Time: 19:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>跳转</title>
</head>
<body>
<%
    response.sendRedirect(request.getContextPath() + "/headteacher/content/main.html");
%>
</body>
</html>
