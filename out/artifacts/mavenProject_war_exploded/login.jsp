<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<base href="<%=basePath%>">
<html>
<head>
    <title>登录首页</title>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%--自适应--%>
    <script type="application/javascript" src="Bootstrap4.3.1/js/jquery-2.1.0.min.js"></script>
    <script type="text/javascript" src="https://cdn.bootcss.com/bluebird/3.5.3/bluebird.min.js"></script>
    <link rel="stylesheet" href="Bootstrap4.3.1/css/bootstrap.min.css">
</head>
<body style="background-image: url('img/timg.jpg')">
<div class="container" style="padding-top: 350px;padding-left: 450px">
    <div class="row clearfix">
        <form class="form-horizontal" role="form" id="bootForm">
            <div class="form-group">
                <label class="form-inline">账号：<input id="loginName" type="text" class="form-control"/></label><%--autocomplete="off" 不让提示已经输入过的账号--%>
            </div>
            <div class="form-group">
                <label class="form-inline">密码：<input id="loginPassWord" type="password" class="form-control"/></label>
            </div>
            <div class="form-group">
                <div class="form-inline">
                    <div class="checkbox">
                        <label><input type="checkbox"/>记住密码</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="form-inline">
                    <button type="submit" class="btn btn-outline-primary" id="bootZc"">注册</button>
                    <button type="submit" class="btn btn-outline-primary" id="bootSubmit">登录</button>
                    <button type="button" class="btn btn-outline-primary" id="DicomToJpg" onclick="getJpg()">获取Jpg
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
<script type="application/javascript">
    $("#bootSubmit").click(function () {
        var username = $("#loginName").val();
        var password = $("#loginPassWord").val();
        console.log(username);
        if (username == "" || password == "") {
            alert("用户名或密码不能为空");
        } else {
            $.ajax({
                url: "pro/login.do",
                data: JSON.stringify({
                    name: username,
                    password: password
                }),
                type: "POST",
                contentType: 'application/json',//传到后台的数据形式
                dataType: "json",//期望后台返回的数据
                success: function (data) {
                    if (data.success) {
                        alert(data.content);
                        // window.location.href = "index.jsp";
                        window.location.href = "index.jsp";
                    } else {
                        alert(data.content);
                    }
                }
            });
        }
    });

    $("#bootZc").click(function () {
        var username = $("#loginName").val();
        var password = $("#loginPassWord").val();
        if (username == "" || password == "") {
            alert("请输入要注册的用户名或密码");
        } else {
            $.ajax({
                url: "pro/register.do",
                data: {
                    name: username,
                    password: password
                },
                type: "POST",
                // contentType: 'application/json',//传到后台的数据形式
                dataType: "json",//期望后台返回的数据
                success: function (data) {
                    if (data.success) {
                        alert(data.content);
                        location.href = "login.jsp";
                    } else {
                        alert(data.content);
                    }
                },
                error: function (e) {
                    var da = JSON.stringify(e);
                    alert(da);
                }
            });
        }
    });

    function getJpg() {
        $.get("pro/dicomToJpg.do");
    }
</script>
</body>

</html>
