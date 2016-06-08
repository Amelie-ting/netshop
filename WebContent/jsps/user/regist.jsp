<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>欢迎来到注册界面</title>

    <!-- Bootstrap core CSS -->
    <link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="signin.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="//v3.bootcss.com/assets/js/ie-emulation-modes-warning.js"></script>

    
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <style type="text/css">
		.container{
		   
		   width:400px;
		   position:relative;
		   top:200px;
		}
	</style>
    
  </head>

  <body>
	
    <div class="container">

      <form class="form-signin" action="<%= request.getContextPath() %>/registerServlet" method=post>
        <h2 class="form-signin-heading">欢迎您注册</h2>
        <br>
        <label for="inputEmail" class="sr-only">请输入账号</label>
        <input type="text" id="inputEmail" name="userid" class="form-control" placeholder="请输入账号" required autofocus>
        <br>
        <label for="inputPassword" class="sr-only">请输入密码</label>
        <input type="password" id="inputPassword" name="password1" class="form-control" placeholder="请输入密码" required>
        <br>
        <br>
        <label for="inputEmail" class="sr-only">请输入账号</label>
        <input type="password" id="inputEmail" name="password2" class="form-control" placeholder="请再次输入密码" required autofocus>
        <br>
        <label for="inputPassword" class="sr-only">请输入密码</label>
        <input type="text" id="inputPassword" name="phone" class="form-control" placeholder="请输入昵称" required>
        <br>
        <button class="btn btn-lg btn-primary btn-block" type="submit">注册</button>
        <td><label class="error" id="msg">${msg }</label></td>
        <br>
        <strong><a href="jsps/user/login.jsp">前往登录界面</a></strong>
      </form>
    </div> 


    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="//v3.bootcss.com/assets/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>