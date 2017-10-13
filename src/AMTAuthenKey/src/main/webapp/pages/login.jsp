<%-- 
    Document   : login
    Created on : 9 oct. 2017, 17:29:07
    Author     : kevin moreira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
   <head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>SB Admin - Start Bootstrap Template</title>
  <!-- Bootstrap core CSS-->
  <link href="pages/unrestricted/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <!-- Custom fonts for this template-->
  <link href="pages/unrestricted/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
  <!-- Custom styles for this template-->
  <link href="pages/unrestricted/css/sb-admin.css" rel="stylesheet">
</head>

<body class="bg-dark">
  <div class="container">
    <div class="card card-login mx-auto mt-5" style ="height: 320px;">
        <div class="card-header">Login</div>
      <div class="card-body">
        <form method="POST" action="loginControl" >
          <div class="form-group">
            <label for="exampleInputEmail1">Email address</label>
            <input class="form-control" name ="inputUsername" id="inputUsername"
                   type="text" aria-describedby="emailHelp" placeholder="Enter email">
            </div>
          <div class="form-group">
            <label for="exampleInputPassword1">Password</label>
            <input class="form-control" name ="inputPassword" id="inputPassword"
                   type="password" placeholder="Password">
          </div>
          <button class="btn btn-primary btn-block" style ="height: 40px;" type="submit">Login</a>
         <div class="text-center">
        </div>
        </form>
          <form method="POST" action="loginControl">  
              <input type="hidden" name="register" value="register">
                <button style ="height: 10px; border:none; background: none;" type="submit">register</a>
          </form>
      </div>
    </div>
  </div>
  <!-- Bootstrap core JavaScript-->
  <script src="pages/unrestricted/vendor/jquery/jquery.min.js"></script>
  <script src="pages/unrestricted/vendor/popper/popper.min.js"></script>
  <script src="pages/unrestricted/vendor/bootstrap/js/bootstrap.min.js"></script>
  <!-- Core plugin JavaScript-->
  <script src="pages/unrestricted/vendor/jquery-easing/jquery.easing.min.js"></script>
</body>

</html>
