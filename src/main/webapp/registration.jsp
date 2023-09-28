<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>BookStore/SignUpPage?12227?56</title>

<!-- Font Icon -->
<link rel="stylesheet"
	href="fonts/material-icon/css/material-design-iconic-font.min.css">

<!-- Main css -->
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<input type = "hidden" id = "status" value = "<%= request.getAttribute("status") %>">
	<div class="main">
		<!-- Sign up form -->
		<section class="signup">
			<div class="container">
				<div class="signup-content">
					<div class="signup-form">
						<h2 class="form-title"style="text-align:center">Sign up</h2>
					
						<form method="POST" action="userreg" class="register-form"
							id="register-form">
							<div class="form-group">
								<label for="name"><i
									class="zmdi zmdi-account material-icons-name"></i></label> <input
									type="text" name="name" id="name" placeholder="Enter Name" />
							</div>
							<div class="form-group">
								<label for="email"><i class="zmdi zmdi-email"></i></label> <input
									type="email" name="email" id="email" placeholder="Enter Email" />
							</div>
							<div class="form-group">
								<label for="address"><i class="zmdi zmdi-home"></i></label> <input
									type="text" name="address" id="address" placeholder="Enter Locality" />
							</div>
							<div class="form-group">
								<label for="city"><i class="zmdi zmdi-local-mall"></i></label> <input
									type="text" name="city" id="city" placeholder="Enter City" />
							</div>
							<div class="form-group">
								<label for="state"><i class="zmdi zmdi-local-library"></i></label> <input
									type="text" name="state" id="state" placeholder="Enter State" />
							</div>
							<div class="form-group">
								<label for="pin"><i class="zmdi zmdi-lock-outline"></i></label> <input
									type="number" name="pin" id="pin" placeholder="Enter Pin Code" />
							</div>
							<div class="form-group">
								<label for="pass"><i class="zmdi zmdi-lock"></i></label> <input
									type="password" name="pwd" id="pwd" placeholder="Enter Password" />
							</div>
							<div class="form-group">
								<label for="re-pass"><i class="zmdi zmdi-confirmation-number"></i></label>
								<input type="password" name="re_pwd" id="re_pwd"
									placeholder="Confirm Password" />
							</div>
							<div class="form-group">
								<label for="phone"><i class="zmdi zmdi-smartphone-android"></i></label>
								<input type="number" name="phone" id="phone"
									placeholder="Contact no" />
							</div>
							<div class="form-group">
								<input type="checkbox" name="agree-term" id="agree-term"
									class="agree-term" /> <label for="agree-term"
									class="label-agree-term"><span><span></span></span>I
									agree all statements in <a href="#" class="term-service">Terms
										of service</a></label>
							</div>
							<div class="form-group form-button">
								<input type="submit" name="signup" id="signup"
									class="form-submit" value="Register" />
							</div>
						</form>
					</div>
					<div class="signup-image">
						<figure>
							<img src="images/signup-image.jpg" alt="sing up image">
						</figure>
						<a href="login.jsp" class="signup-image-link">I am already
							member</a>
					</div>
				</div>
			</div>
		</section>


	</div>
	<!-- JS -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="js/main.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<link rel="stylesheet" href="alert/dist/sweetalert.css">
	
	<script type="text/javascript">
		var status  = document.getElementById("status").value;
		if(status == "success")
			swal("Congrats","Account created successfully, Kindly Login!","success");
		/* else
			swal("Sorry","Account creation failed","OOPs!!!"); */
	</script>
</body>
</html>