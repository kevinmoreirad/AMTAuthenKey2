<%-- 
    Document   : register
    Created on : 12 oct. 2017, 17:14:30
    Author     : kevin moreira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    <div class="card card-register mx-auto mt-5">
      <div class="card-header">Register an Account</div>
      <div class="card-body">
          <form method="POST" action="registerControl">
          <div class="form-group">
            <div class="form-row">
              <div class="col-md-6">
                <label for="exampleInputName">username</label>
                <input class="form-control" name="username" type="text" aria-describedby="nameHelp" placeholder="Enter first name">
              </div>
            </div>
          </div>
          <div class="form-group">
            <div class="form-row">
              <div class="col-md-6">
                <label for="exampleInputPassword1">Password</label>
                <input class="form-control" name="password" type="password" placeholder="Password">
              </div>
              <div class="col-md-6">
                <label for="exampleConfirmPassword">Confirm password</label>
                <input class="form-control" name="passwordBis" type="password" placeholder="Confirm password">
              </div>
            </div>
          </div>
          <button class="btn btn-primary btn-block"type="submit" >Register</button>
        </form>
        <div class="text-center">
          <a class="d-block small mt-3" href="loginControl">Login Page</a>
        </div>
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
