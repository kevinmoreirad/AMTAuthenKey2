<%-- 
    Document   : pageView
    Created on : 9 oct. 2017, 10:26:23
    Author     : kevin moreira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>Auth Key </title>
  <!-- Bootstrap core CSS-->
  <link href="pages/unrestricted/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <!-- Custom fonts for this template-->
  <link href="pages/unrestricted/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
  <!-- Page level plugin CSS-->
  <link href="pages/unrestricted/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
  <!-- Custom styles for this template-->
  <link href="pages/unrestricted/css/sb-admin.css" rel="stylesheet">
  
  <link rel="stylesheet" href="pages/unrestricted/css/trHover.css">
</head>

<body class="fixed-nav sticky-footer bg-dark" id="page-top">
  <!-- Navigation-->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
    <a class="navbar-brand">AuthentificationKeys</a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
      <ul class="navbar-nav navbar-sidenav" id="exampleAccordion">
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Tables">
          <a class="nav-link" href="mainControl">
            <i class="fa fa-fw fa-table"></i>
            <span class="nav-link-text">Tables</span>
          </a>
        </li>
      </ul>
      <ul class="navbar-nav sidenav-toggler">
        <li class="nav-item">
          <a class="nav-link text-center" id="sidenavToggler">
            <i class="fa fa-fw fa-angle-left"></i>
          </a>
        </li>
      </ul>
      <ul class="navbar-nav ml-auto">
        <li class="nav-item">
          <a class="nav-link" data-toggle="modal" data-target="#exampleModal">
            <i class="fa fa-fw fa-sign-out"></i>Logout</a>
        </li>
      </ul>
    </div>
  </nav>
  <div class="content-wrapper">
    <div class="container-fluid">
      <!-- Example DataTables Card-->
      <div class="card mb-3">
        <div class="card-header" style=" display: flex;  flex-direction: row; justify-content: space-between;">
            <div><i class="fa fa-table"></i> Authentification Keys Table</div>
            <div>
                <form method="POST" action="mainControl">
                    <SELECT name="numberRows"  onchange="this.form.submit()">
                    <OPTION value="" selected disabled hidden>${nbRows}</OPTION>
                    <OPTION> 10
                    <OPTION> 50
                    <OPTION> 100
                    <OPTION> 500
                    </SELECT>
                </form>
            </div>
        </div>  
        <div class="card-body">
          <div>
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
              <thead>
                <tr>
                  <th>
                    <form method="POST" action="mainControl">
                        <input name ="orderBy" type="hidden" value="auth">
                        <button style="background: none; border: none;  font-weight: bold;" type="submit">Authentification Key</button>
                    </form>
                  </th>
                  <th>
                    <form method="POST" action="mainControl">
                        <input name ="orderBy" type="hidden" value="owner">
                        <button style="background: none; border: none;  font-weight: bold;" type="submit">Owner</button>
                    </form>
                  </th>
                  <th>
                    <form method="POST" action="mainControl">
                        <input name ="orderBy" type="hidden" value="start">
                        <button style="background: none; border: none;  font-weight: bold;" type="submit">Start Date</button>
                    </form>
                  </th>
                  <th>
                    <form method="POST" action="mainControl">
                        <input name ="orderBy" type="hidden" value="end">
                        <button style="background: none; border: none;  font-weight: bold;" type="submit">End Date</button>
                    </form>
                  </th>
                </tr>
              </thead>
              <tfoot>
                  <tr>
                  <th>Authentification Key</th>
                  <th>Owner</th>
                  <th>Start Date</th>
                  <th>End Date</th>
                </tr>
              </tfoot>
              <tbody>         
                <c:forEach items = "${authKeys}" var="currAuthKey">
                    <tr class="trHover" onClick="modifyRow('${currAuthKey.authKey}')" style="cursor: pointer;">
                      <td>${currAuthKey.authKey}</td>
                      <td>${currAuthKey.owner}</td>
                      <td>${currAuthKey.startDate}</td>
                      <td>${currAuthKey.endDate}</td> 
                    </tr>
                </c:forEach>                                           
              </tbody>
            </table>
          </div>
        </div>
          
          <div style=" display: flex;  flex-direction: row; justify-content: space-between;">
          <div>
          <a class="nav-link" data-toggle="modal" data-target="#addkeyModal" style="cursor: pointer;">
            <img src="pages/unrestricted/img/plusOne.png" style="width: 25px;" alt="Adding one key"> Add Key</a>    
          </div>
          <div>
            <a class="nav-link" data-toggle="modal" data-target="#addMultikeyModal" style="cursor: pointer;">
            <img src="pages/unrestricted/img/plusOne.png" style="width: 25px;" alt="Adding one key"> Add Multiple Keys</a>    
          </div>
              <div style="display: flex;  flex-direction: row;">    
                <div>
                    <form method="POST" action="mainControl">
                        <button style="background: none; border: none;" type="submit">
                            <input name="changePage" style="width: 25px;" type="image" 
                                   src="pages/unrestricted/img/left_arrow.png" value="left"/></button>
                  </form>
                </div>
                    <div>
                        ${currentPage}
                    </div>
                <div>
                  <form method="POST" action="mainControl">
                      <button type="submit" style="background: none; border: none;">
                          <input  name="changePage" style="width: 25px;" type="image"
                                  src="pages/unrestricted/img/right_arrow.png" value="right" /></button> 
                  </form>
                </div>
          </div>           
              <div style="float: right;">
                           ${nbRows}/${nbKeys} Authentification Keys    
          </div>
          </div>
    </div>
    </div>
    <!-- /.container-fluid-->
    <!-- /.content-wrapper-->
    <footer class="sticky-footer">
      <div class="container">
        <div class="text-center">
          <small>Copyright © AuthTables 2017</small>
        </div>
      </div>
    </footer>
    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
      <i class="fa fa-angle-up"></i>
    </a>
    <!-- Logout Modal-->
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">×</span>
            </button>
          </div>
          <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
          <div class="modal-footer">
            <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
            <form method="POST" action="mainControl">
                <input name ="logout" type="hidden" value="logout">
                 <button class="btn btn-primary" type="submit">Logout</button>
            </form>
          </div>
        </div>
      </div>
    </div>
        <!-- add Key Modal-->
    <div class="modal fade" id="addkeyModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Add One key Authentification</h5>
            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">×</span>
            </button>
          </div>
          <div class="modal-body">${username} : Add a new key</div>
          <div class="modal-footer">
            
            <form method="POST" action="mainControl">
                <div>
                    <label>Start date</label>
                    <input name ="startDate" type="date"placeholder="Enter start date"> 
                </div>
                <div>
                    <label>End Date&nbsp</label>
                    <input name ="endDate" type="date" placeholder="Enter end date">     
                </div>
                 <input name ="newUniqueKey" type="hidden" value="newUniqueKey"> 
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                <button class="btn btn-primary" type="submit">Add Now!</button>
            </form>
          </div>
        </div>
      </div>
    </div>
          
           <!-- modify Key Modal-->
    <div class="modal fade" id="modifykeyModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Modify Key Authentification</h5>
            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">×</span>
            </button>
          </div>
            <div class="modal-body">${username}:modify the dates of the key <div id="authNumber"></div></div>
          <div class="modal-footer">
            
            <form method="POST" action="mainControl">
                <div>
                    <label>Start date</label>
                    <input name ="startDate" type="date"placeholder="Enter start date"> 
                </div>
                <div>
                    <label>End Date&nbsp</label>
                    <input name ="endDate" type="date" placeholder="Enter end date">     
                </div>
                 <input id="modifyKeyId" name ="modifyKey" type="hidden"> 
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                <button class="btn btn-primary" type="submit">Modify Now!</button>
            </form>
              <form method="POST" action="mainControl">
                   <input id="deleteKeyId" name ="deleteKey" type="hidden"> 
                   <button class="btn btn-primary" style="background-color: #240,52,52" type="submit">Delete the key!</button>
              </form>
          </div>
        </div>
      </div>
    </div>
            <!-- add multi Key Modal-->
    <div class="modal fade" id="addMultikeyModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Add Multiple Keys Authentification</h5>
            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">×</span>
            </button>
          </div>
          <div class="modal-body">${username}: add Multiple keys</div>
          <div class="modal-footer">
            
            <form method="POST" action="mainControl">
                <div>
                    <label>Number of random Keys to add</label>
                    <input name ="numberKeys" type="number" max="1234567" placeholder="Enter number keys"> 
                </div>
                <input name ="newMultiKey" type="hidden" value="newMultiKey"> 
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                <button class="btn btn-primary" type="submit">Add Now!</button>
            </form>
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
    <!-- Custom scripts for all pages-->
    <script src="pages/unrestricted/js/sb-admin.min.js"></script>
    <!-- Custom scripts for this page-->
    <script src="pages/unrestricted/js/sb-admin-datatables.min.js"></script>
    <!-- script for modify an row by clicking in it--> 
    <script type="text/javascript">
        function modifyRow(param)
        {
            document.getElementById("authNumber").innerHTML = param;
            document.getElementById("modifyKeyId").value = param;
            document.getElementById("deleteKeyId").value = param;
            $('#modifykeyModal').modal('show');
        };
</script>
  </div>
</body>
</html>