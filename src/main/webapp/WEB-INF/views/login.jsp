<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="msapplication-tap-highlight" content="no">
<meta name="description" content="">
<meta name="keywords" content="">
<title>Login</title>
<link rel="stylsheet" href='<c:url value="/resources/css/bootstrap.min.css" />'>
<!-- Favicons-->
<link rel="icon" href="images/favicon/favicon-32x32.png" sizes="32x32">
<!-- Favicons-->
<link rel="apple-touch-icon-precomposed"
	href="images/favicon/apple-touch-icon-152x152.png">
<!-- For iPhone -->
<meta name="msapplication-TileColor" content="#00bcd4">
<meta name="msapplication-TileImage"
	content="images/favicon/mstile-144x144.png">
<!-- For Windows Phone -->


<!-- CORE CSS-->
<link href="resources/css/materialize.css" type="text/css" rel="stylesheet"
	media="screen,projection">
<link href="resources/css/style.css" type="text/css" rel="stylesheet"
	media="screen,projection">
<!-- Custome CSS-->
<link href="resources/css/custom/custom.min.css" type="text/css" rel="stylesheet"
	media="screen,projection">
<link href="resources/css/layouts/page-center.css" type="text/css"
	rel="stylesheet" media="screen,projection">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">

<!-- INCLUDED PLUGIN CSS ON THIS PAGE -->
<link href="resources/js/plugins/prism/prism.css" type="text/css" rel="stylesheet"
	media="screen,projection">
<link href="resources/js/plugins/perfect-scrollbar/perfect-scrollbar.css"
	type="text/css" rel="stylesheet" media="screen,projection">
<script src="https://www.gstatic.com/firebasejs/4.6.2/firebase-app.js"></script>
<script
	src="https://www.gstatic.com/firebasejs/4.6.2/firebase-database.js"></script>
<script
	src="https://www.gstatic.com/firebasejs/4.6.2/firebase-messaging.js"></script>
<script src="https://www.gstatic.com/firebasejs/4.8.0/firebase.js"></script>
<script>
	// Initialize Firebase
	var config = {
		apiKey : "AIzaSyAC7vCACafVTaQZ_uyyxSRmoWNofhX185Q",
		authDomain : "gconnect-4524f.firebaseapp.com",
		databaseURL : "https://gconnect-4524f.firebaseio.com",
		projectId : "gconnect-4524f",
		storageBucket : "gconnect-4524f.appspot.com",
		messagingSenderId : "792119929745"
	};
	firebase.initializeApp(config);
</script>
<script src='resources/js/firebase.js'></script>

</head>

<body class="customColor">
	<!-- Start Page Loading -->
	<div id="loader-wrapper">
		<div id="loader"></div>
		<div class="loader-section section-left"></div>
		<div class="loader-section section-right"></div>
	</div>
	<!-- End Page Loading -->



	<div id="login-page" class="row">
		<div class="col s12 z-depth-4 card-panel">
			<form class="login-form">
				<div class="row">
					<div class="input-field col s12 center">
						<img src="resources/images/login-logo.png" alt=""
							class="circle responsive-img valign profile-image-login">
						<p class="center login-form-text">Global Connect</p>
					</div>
				</div>
				<div class="row margin">
					<div class="input-field col s12">
						<i class="material-icons prefix">email</i> <input
							id="email" type="email" class="validate" required="required"> <label
							for="email" class="center-align" data-error="Enter valid email" data-success="Valid">Email</label>
					</div>
				</div>
				<div class="row margin">
					<div class="input-field col s12">
						<i class="material-icons prefix">security</i> <input id="password"
							type="password" class="validate" required="required"> <label for="password"
							data-error="Min 6 characters" data-success="Valid">Password</label>
					</div>
				</div>
				<div class="row">
					<div class="input-field col s12">
						<a id="login-button" class="btn waves-effect waves-light col s12 customColor">Login</a>
					</div>
				</div>
				<div class="row">
					<p style="text-align: center;" class="margin medium-small">
						<a href="register.html">Register Now!</a>
					</p>
				</div>

			</form>
		</div>
	</div>



	<!-- ================================================
    Scripts
    ================================================ -->

	<!-- jQuery Library -->
	<script src='resources/js/plugins/jquery-2.2.4.min.js'></script>
	<!--materialize js-->
	<script type="text/javascript" src="resources/js/plugins/materialize.js"></script>
	<!--prism-->
	<script type="text/javascript" src="resources/js/plugins/prism/prism.js"></script>
	<!--scrollbar-->
	<script type="text/javascript"
		src="resources/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js"></script>

	<!--plugins.js - Some Specific JS codes for Plugin Settings-->
	<script type="text/javascript" src="resources/js/plugins/plugins.min.js"></script>
	<!--custom-script.js - Add your own theme custom JS-->
	<script type="text/javascript" src="resources/js/apiCalls.js"></script>
	<script type="text/javascript" src="resources/js/util.js"></script>
	<script type="text/javascript" src="resources/js/login.js"></script>
</body>

</html>