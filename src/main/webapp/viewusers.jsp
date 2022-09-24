<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>Hit Tastic</title>
        <link rel="stylesheet"
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
              crossorigin="anonymous">
    </head>
    <body>

        <header>
            <nav class="navbar navbar-expand-md navbar-dark"
                 style="background-color: black">
                <div class="collapse navbar-collapse">
                    <a class="navbar-brand"  style="color: yellow">
                        HitTastic! - an online music site </a>
                    <ul class="navbar-nav mr-auto">
                        <!-- here the /ViewAllUsers servet is called when the Users tab is pressed-->
                        <li class="nav-item active"> <a class="nav-link" href="./ViewAllUsers">Users</a> </li>
                    </ul>
                </div>
                <i class="mdi mdi-logout" ></i>
                <a  class="btn btn-warning" href="./Logout">Logout</a>
            </nav>
        </header>
        <br>

        <div class="row">
            <div class="container">
                <h3 class="text-center">List of Users</h3>
                <hr>
                <br>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Username</th>
                            <th>Email</th>
                            <th>Is Admin</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>

                        <c:forEach var="user" items="${listUser}">

                            <tr>
                                <td><c:out value="${user.id

                                           }" /></td>
                                <td><c:out value="${user.username}" /></td>
                                <td><c:out value="${user.email}" /></td>
                                <td><c:out value="${user.isAdmin}" /></td>
                                <!-- here the /EditUser servet is called when the Edit tab is pressed-->
                                <!-- here the /DeleteUser servet is called when the Delete tab is pressed-->
                                <td><a class="btn btn-warning"  href="./EditUser?id=<c:out value='${user.id}' />">Edit</a>
                                    &nbsp;&nbsp;&nbsp;&nbsp; <a  class="btn btn-warning" 
                                                                 href="./DeleteUser?id=<c:out value='${user.id}' />">Delete</a></td>
                            </tr>
                        </c:forEach>

                    </tbody>

                </table>
            </div>
        </div>
    </body>
</html>