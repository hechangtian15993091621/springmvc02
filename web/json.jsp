<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/5/7
  Time: 22:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://cdn.staticfile.org/jquery/1.10.0/jquery.min.js"></script>
    <script type="application/javascript">
        $(function () {
            $("#btn01").click(function () {
                $.ajax({
                    url:"${pageContext.request.contextPath}/json",
                    method:"post",
                    contentType:"application/json",
                    dataType:"json",
                    data:"小明",
                    success:function (res) {
                        alert(res.name)
                    }
                })
            })

            $("#btn02").click(function () {
                $.ajax({
                    url:"${pageContext.request.contextPath}/json01",
                    method:"post",
                    contentType:"application/json",
                    dataType:"json",
                    data:JSON.stringify({'id':1,'name':'李四'}),
                    success:function (res) {
                        alert(res.name)
                    }
                })
            })

            $("#btn03").click(function () {

                var arr = new Array();
                var user1 = {"id":"1","name":"王五"};
                var user2 = {"id":"2","name":"赵六"};
                arr.push(user1)
                arr.push(user2)

                $.ajax({
                    url:"${pageContext.request.contextPath}/json02",
                    method:"post",
                    contentType:"application/json",
                    dataType:"json",
                    data:JSON.stringify(arr),
                    success:function (res) {
                        alert(res.name)
                    }
                })
            })
        })
    </script>
</head>
<body>
<input type="button" value="提交单个值" id="btn01">
<input type="button" value="提交一个对象" id="btn02">
<input type="button" value="提交一个List" id="btn03">
</body>
</html>
