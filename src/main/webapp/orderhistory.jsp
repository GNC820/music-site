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
                         <!-- here the /ViewAllSongs servlet is called when the Songs tab  is pressed-->
                        <li class="nav-item active"> <a class="nav-link" href="./ViewAllSongs">Songs</a> </li>
                         <!-- here the /OrderHistory servlet is called when the Order history tab  is pressed-->
                        <li class="nav-item active"> <a class="nav-link" href="./OrderHistory">Order history</a> </li>
                    </ul>
                </div>
                <i class="mdi mdi-logout" ></i>
                 <!-- here the /Logout servlet is called when the Logout button is pressed-->
                <a  class="btn btn-warning" href="./Logout">Logout</a>
            </nav>
        </header>
        <br>

        <div class="row">
            <div class="container">
                <h3 class="text-center">Order history</h3>
                <hr>
                <br>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>User Id</th>
                            <th>Song Id</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Total</th>
                        </tr>
                    </thead>
                    <tbody>

                        <c:forEach var="order" items="${allOrders}">

                            <tr>
                                <td><c:out value="${order.id

                                           }" /></td>
                                <td><c:out value="${order.songId}" /></td>
                                <td><c:out value="${order.userId}" /></td>
                                <td><c:out value="£ ${order.price}" /></td>
                                <td><c:out value="${order.quantity}" /></td>
                                <td><c:out value="£ ${order.total}" /></td>

                            </tr>
                        </c:forEach>

                    </tbody>

                </table>
            </div>
        </div>
    </body>
</html>
