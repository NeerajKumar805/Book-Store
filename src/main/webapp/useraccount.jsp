<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="ISO-8859-1">
        <link rel="stylesheet" href="style.css">
        </link>
        <title>Book Store - User Account</title>
        <style>
            body{
                background-color: #b6f1b6;
            }
            .my-profile{
                margin: 20px;
            }
            .my-profile-con{
                text-align: center;
                /* border: 1px solid red; */
                box-shadow: 0 0 5px 5px black;
                width: fit-content;
                padding: 20px 40px;
                margin: auto;
            }
            .profile-img{
            	/* border: 1px solid red; */
            }
            .profile-img img{
            	/* border: 1px solid aqua; */
            }
            .profile-deatils p{
                font-size: 18px;
                font-weight: 700;
                color: #7807fa;
                padding: 10px;
                /* border-bottom: 1px solid red; */
            }
            .edit-button{
                background-color: blue;
                padding: 5px;
                border-radius: 8px;
            }
        </style>
    </head>

    <body>
        <nav>
            <div class="logo">
                <img src="./images/book-round.png" width="100px" alt="">
            </div>
            <div class="logo-name">
                <h1>Book Store</h1>
            </div>
            <div class="nav-items">
                <!-- <p><a href="#">Home</a></p>
            	 <p><a href="#bb">Browse</a></p>
            	 <p><a href="index.jsp">Log Out</a></p> -->

                <div class="dropdown">
                    <button onclick="myFunction()" class="dropbtn">Welcome Back</button>
                    <div id="myDropdown" class="dropdown-content">
                        <a href="useraccount.jsp">My Account</a>
                        <a href="#">My Orders</a>
                        <a href="#">My Address</a>
                        <a href="logout">Log Out</a>
                    </div>
                </div>
            </div>
        </nav>
        <section class="my-profile">
            <div class="my-profile-con">
                <h1>My Profile</h1>
                <div class="profile-img">
                    <img src="./images/my-account-profile.png" alt="">
                    <h2>Neeraj Kumar</h2>
                </div>
                <div class="profile-deatils">
                    <p>Name : Neraj</p>
                    <p>Email : neeraj@gmail.com</p>
                    <p>Password : 1353344</p>
                    <p>Phone : 687687687</p>
                </div>
                <div class="edit-button">
                    <a href="edituserdetails">Edit Details</a>
                </div>
            </div>
        </section>
        <script>
            /* When the user clicks on the button,
            toggle between hiding and showing the dropdown content */
            function myFunction() {
                document.getElementById("myDropdown").classList.toggle("show");
            }

            // Close the dropdown menu if the user clicks outside of it
            window.onclick = function (event) {
                if (!event.target.matches('.dropbtn')) {
                    var dropdowns = document.getElementsByClassName("dropdown-content");
                    var i;
                    for (i = 0; i < dropdowns.length; i++) {
                        var openDropdown = dropdowns[i];
                        if (openDropdown.classList.contains('show')) {
                            openDropdown.classList.remove('show');
                        }
                    }
                }
            }
        </script>
    </body>

    </html>