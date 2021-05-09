<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/5/7
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/hello" method="post">
    <input type="text" name="birthday" value=""/>生日${errors.birthday}<br>
    <input type="submit" value="提交">
</form>

<%--<form action="${pageContext.request.contextPath}/test" method="get">--%>
<%--    <input type="text" name="username" value=""/>用户名<br>--%>
<%--    <input type="password" name="psw" value=""/>密码<br>--%>
<%--    <input type="submit" value="提交">--%>
<%--</form>--%>

</body>
</html>
