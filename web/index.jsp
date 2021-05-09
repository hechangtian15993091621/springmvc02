<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/4/28
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
<%--  <spring:eval expression="user.birthday"></spring:eval>--%>
  <form action="/download1">
    <input type="submit" value="下载">
  </form>
  <hr>
<h1>单线程多文件上传</h1>
<form action="${pageContext.request.contextPath}/load" method="post" enctype="multipart/form-data">
  <input type="text" name="desc"/>文件描述<br>
  <input type="file" name="myFile" multiple accept="image/*"/>提交文件<br>
  <input type="submit" value="上传"/>
</form>
  <h1>多线程多文件上传</h1>
<form action="${pageContext.request.contextPath}/load01" method="post" enctype="multipart/form-data">
  <input type="text" name="desc"/>文件描述<br>
  <input type="file" name="myFile" multiple accept="image/*"/>提交文件<br>
  <input type="submit" value="上传"/>
</form>
  </body>
</html>
