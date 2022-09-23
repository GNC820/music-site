
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
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: black"> <a class="navbar-brand" style="color: yellow"> HitTastic! - an online music site</a></button>
        <div class="collapse navbar-collapse" id="navbarColor02">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active"> <a class="nav-link" href="#" data-abc="true">Home <span class="sr-only">(current)</span></a> </li>
            </ul>
            <div class="col-6">
            <form onsubmit="event.preventDefault()" class="form-inline my-2 my-lg-0"> <input class="form-control mr-sm-3" type="text" placeholder="Search by artist of title"> <button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button> </form>
            </div>
              
              <div class="col-1">
                <i class="mdi mdi-basket"></i>
                <a class="btn btn-warning" href="../basket/check-out">Basket</a>
              </div>
        
              <div class="col-1" >
                <i class="mdi mdi-logout" ></i>
                <a  class="btn btn-warning" href="../user/login">Logout</a>
              </div>
        </div>
    </nav>
    <div class="tab-content" id="myTabContent">
        <div class="tab-pane fade show active" id="All" role="tabpanel"
             aria-labelledby="home-tab">
            <c:if test="${allSongs.size() == 0}" var="event" scope="session">
                <h4 class="text-center py-3 empty-events">
                    <i class="far fa-folder-open"></i> No songs
                </h4>
            </c:if>
            <div class="container">
                <div class="row">
                    <c:forEach var="event" items="${allSongs}">
                        <div class="col-4 col-sm-3">
                            <br>
                            <div class="card"  style="width: 15rem; margin-bottom: 20px" >
                                <img class="card-img-top img-fluid"
                                     src="./img/music-sign.jpg"  alt=""
                                     style="width: 30%;
                                     margin: 0 auto;">
                                <div class="card-body">
                                    <h4 class="card-title text-center">${event.title}</h4>
                                    <hr>
                                    <p class="card-text" style="min-height: 30px">
                                        ${event.description}
                                    </p>
                                    <p class="card-text">
                                        <b><i class="far fa-calendar-alt"></i> Artist:</b> ${event.artist}
                                    </p>
                                    <p class="card-text">
                                        <b><i class="fa fa-user"></i> Price:</b>
                                        ${event.price}
                                    </p>
                                </div>
                                <div class="col text-center">
                                    <a href="eventDetails?id=${event.id}" class="btn btn-warning" style="width:50%"><i
                                            class="fas fa-external-link-alt"></i> Buy</a>
                                </div>
                                <br>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</html>


