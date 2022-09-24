
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
    <div class="collapse navbar-collapse">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active"> <a class="nav-link" href="./ViewAllSongs">Songs</a> </li>
            <li class="nav-item active"> <a class="nav-link" href="./OrderHistory">Order history</a> </li>
        </ul>
        <div class="col-9">
            <form action="./SearchSong" method="post" class="form-inline my-2 my-lg-0"> <input style="width: 40%;" class="form-control mr-sm-3" name="search" type="text" placeholder="Search by artist of title"> <button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button> </form>
        </div>



        <i class="mdi mdi-logout" ></i>
        <a  class="btn btn-warning" href="./Logout">Logout</a>

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

                        <form action="./BuySong" method="post">
                            <div class="card"  style="width: 15rem; margin-bottom: 20px; margin-top: 20px; min-height:700px" >
                                <img class="card-img-top img-fluid"
                                     src="./img/music-sign.jpg"  alt=""
                                     style="width: 30%;
                                     margin: 0 auto;">
                                <div class="card-body">

                                    <input type="hidden" name="id" value="<c:out value='${event.id}' />" />
                                    <h4 class="card-title text-center">${event.title}</h4>
                                    <hr>
                                    <p class="card-text" style="min-height: 30px">
                                        ${event.description}
                                    </p>
                                    <p class="card-text">
                                        <b><i class="far fa-calendar-alt"></i> Artist:</b> ${event.artist}
                                    </p>
                                    <p class="card-text">
                                        <input type="hidden" name="price" value="<c:out value='${event.price}' />" />
                                        <b><i class="fa fa-user"></i> Price:</b>
                                        ${event.price}
                                    </p>

                                    <p class="card-text">
                                        <b><i class="fa fa-user"></i> Sales:</b>
                                        ${event.salesAmount}
                                    </p>
                                </div>
                                <hr/>
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-6">
                                            <p class="card-text"><strong>Quantity:</strong></p>
                                        </div>
                                        <div class="col-6" >

                                            <input style="width:70%" type="text"value="0" name="quantity" required>
                                        </div>
                                    </div>
                                </div>
                                <div style="margin:16px 0" class="col text-center">
                                    <button style="width: 100px" type="submit" class="btn btn-warning">Buy</button>
                                </div>
                            </div>
                        </form>
                    </div>

                </c:forEach>
            </div>
        </div>
    </div>
</div>
</html>


