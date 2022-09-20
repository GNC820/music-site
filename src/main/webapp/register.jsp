<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Account</title>
        <link href="https://fonts.googleapis.com/css?family=ZCOOL+XiaoWei" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container">
            <div class="regbox box ">
                <img class="avatar" src="img/music-sign.jpg">
                <h1>
                    Register Account</h1>
                <form action="./RegisterUser" method="post">
                    <p>
                        Username</p>
                    <input type="text" placeholder="Username" name="username" required>
                    <p>
                        Email</p>
                    <input type="text" placeholder="Useremail" name="email" required>
                    <p>
                        Password</p>
                    <input type="password" placeholder="Password" name="password" required>
                    <p>
                        Is Admin?</p>
                    <input style="width: 10%" type="radio" name="isAdmin" value="Yes"> Yes<BR>
                    <input style="width: 10%" type="radio" name="isAdmin" value="No"> No<BR>
                    <input type="submit" value="Register">
                    <a href="login.jsp">Already have an Account?</a>
                </form>
            </div>
        </div>
    </body>
</html>
