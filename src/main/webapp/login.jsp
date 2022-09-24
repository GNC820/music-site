<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link href="https://fonts.googleapis.com/css?family=ZCOOL+XiaoWei" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container">
            <div class="regbox box">
                <img class="avatar" src="img/music-sign.jpg">
                <h1>
                   Login</h1>
                 <!-- here the /LoginUser servet is called when the Login button  is pressed-->
                <form action="./LoginUser" method="post">
                    <p>
                        Username</p>
                    <input type="text" placeholder="Username" name="username" required>
         
                    <p>
                        Password</p>
                    <input type="password" placeholder="Password" name="password" required>
                    
                    <input type="submit" value="Login">
                    <a href="register.jsp">Don't have an Account?</a>
                </form>
            </div>
        </div>
    </body>
</html>
