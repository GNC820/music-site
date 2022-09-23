
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
                        <li class="nav-item active"> <a class="nav-link" href="./ViewAllUsers">Users</a> </li>
                    </ul>

                </div>
                <i class="mdi mdi-logout" ></i>
                <a  class="btn btn-warning" href="./Logout">Logout</a>
            </nav>
        </header>
        <br>
        <div class="container col-md-5" >
            <div class="card" >
                <div class="card-body">
                    <form action="./UpdateUser" method="post">

                        <caption>
                            <h2>
                                Edit User 
                            </h2>
                        </caption>
                        <input type="hidden" name="id" value="<c:out value='${user.id}' />" />

                        <fieldset class="form-group">
                            <label>Userame</label> <input type="text"
                                                          value="<c:out value='${user.username}' />" class="form-control"
                                                          name="username" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Email</label> <input type="text"
                                                        value="<c:out value='${user.email}' />" class="form-control"
                                                        name="email">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Is Admin</label> <input type="text"
                                                           value="<c:out value='${user.isAdmin}' />" class="form-control"
                                                           name="isAdmin">
                        </fieldset>

                        <button type="submit" class="btn btn-warning">Save</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
