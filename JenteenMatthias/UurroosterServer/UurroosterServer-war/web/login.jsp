<%-- 
    Document   : login
    Created on : 21-nov-2017, 10:01:27
    Author     : student
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <title>Login</title>
        <style>
            /* Bordered form */
            form {
                border: 3px solid #f1f1f1;
                width: 20%;
                position: absolute;
                margin-left: 40%;
                margin-top: 15%;
            }
            /* Full-width inputs */
            input[type=text], input[type=password] {
                width: 100%;
                padding: 12px 20px;
                margin: 8px 0;
                display: inline-block;
                border: 1px solid #ccc;
                box-sizing: border-box;
            }
            .container {
                padding: 16px;
            }
            /* Center the avatar image inside this container */
            .imgcontainer {
                text-align: center;
            }

            /* Avatar image */
            img.avatar {
                width: 100%;
            }
        </style>
        <script>
            function myFunction() {
                var x = document.getElementById("password");
                if (x.type === "password") {
                    x.type = "text";
                } else {
                    x.type = "password";
                }
            }
        </script>
    </head>
    <body style="background-color: #DDE7F0;">
        <form method=post action="j_security_check">
            <div class="imgcontainer">
                <img src="https://upload.wikimedia.org/wikipedia/commons/4/49/KU_Leuven_logo.svg" alt="Avatar" class="avatar">
            </div>
            <div class="containter">
                <label><b>Username</b></label>
                <input placeholder="Enter Username" required type="text" name="j_username" />
                <label><b>Password</b></label>
                <input placeholder="Enter Password" required type="password" name="j_password" id="password" />
                <input type="checkbox" onclick="myFunction()">Show Password
                <button type="submit" class="w3-button w3-large w3-blue w3-card-4" style="width: 100%">Log in</button>
            </div>
        </form>
    </body>
</html>