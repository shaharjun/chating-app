<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html class=''>
<head>
<meta charset='UTF-8'>
<meta name="robots" content="noindex">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>	
<link href="<c:url value="/resources/css/reset.min.css" />" rel="stylesheet"></link>
<link rel="stylsheet" href='<c:url value="/resources/css/bootstrap.min.css" />'> <!-- CDN for bootstrap.min.css -->
<link href='' rel='stylesheet' type='text/css'><!-- FONTS from CDN -->

<link href="<c:url value="/resources/css/reset.min.css" />" rel="stylesheet"/>
<link href="<c:url value="/resources/css/materialize.min.css" />" rel='stylesheet'> <!-- Materialize CDN link -->

<link href="<c:url value="/resources/css/reset.min.css" />" rel="stylesheet"></link>
<link rel='stylesheet prefetch'
	href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.2/css/font-awesome.min.css'>
<link rel='stylesheet' href='<c:url value="/resources/css/chatStyle.css" />'>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
<!--Import Google Icon Font-->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<!--Import materialize.css-->
<link type="text/css" rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css"
	media="screen,projection" />
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.5.1/jquery.min.js"
	type="text/javascript"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"
	type="text/javascript"></script>
<script src='../js/plugins/jquery-2.2.4.min.js'></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
<script src='../js/populate.js'></script>
<script src='../js/main.js'></script>
<script src='../js/poll.js'></script>
<link rel="stylesheet" href="../css/croppie.css" />
<script src="../js/croppie.js"></script>
</head>
<body id="mainBody">
	<div id="frame">
		<div id="sidepanel">
			<div id="profile">
				<div class="wrap">
					<img id="profile-img" src="" class="online" alt="" />
					<p></p>
					<i class="fa fa-chevron-down expand-button" aria-hidden="true"></i>
					<div id="status-options">
						<ul>
							<li id="status-online" class="active"><span
								class="status-circle"></span>
								<p>Online</p></li>
							<li id="status-away"><span class="status-circle"></span>
								<p>Away</p></li>
							<li id="status-busy"><span class="status-circle"></span>
								<p>Busy</p></li>
							<li id="status-offline"><span class="status-circle"></span>
								<p>Offline</p></li>
							<li onclick="logout()" id="logout"><i id="logout-button"
								class="fa fa-sign-out" aria-hidden="true"></i>
								<p>Logout</p></li>
						</ul>
					</div>
					<div id="expanded">
						<ul>

							<li></li>
							<li></li>
							<li></li>

						</ul>
					</div>
				</div>
			</div>

			<div class="wrap" id="myProfileOuterDiv">
				<div align="left" class="starred" id="myProfileInnerDiv">
					<!-- class name kept same with starred, may need to change -->
					&nbsp&nbsp&nbsp<i class="fa fa-user" aria-hidden="true"></i><span>&nbsp&nbspMy
						Profile</span>
				</div>
			</div>
			<div class="wrap">
				<div align="left" class="starred" onclick="showStarred()">
					&nbsp&nbsp&nbsp<i style="color: gold;" class="fa fa-star"
						aria-hidden="true"></i><span>&nbsp&nbspStarred Messages</span>
				</div>
			</div>

			<div id="search">

				<label><i onclick="searchContact()" class="fa fa-search"
					aria-hidden="true"></i></label> <input type="text" style="width: 93%"
					class="browser-default" placeholder="Search contacts..."
					id="searchContactText" /> <label><i
					id="clearSearchContactBar" class="fa fa-times" aria-hidden="true"></i></label>

			</div>
			<div id="contacts">
				&nbsp &nbsp
				<ul>
				</ul>
			</div>
			<div id="bottom-bar">
				<button id="addcontact" href="#addContactModal"
					class="modal-trigger">
					<i class="fa fa-user-plus fa-fw" aria-hidden="true"></i> <span>Add
						contact</span>
				</button>
				<button id="settings" onclick="random()" id="remider"
					href="#addreminder" class="modal-trigger">
					<i class="fa fa-bell" aria-hidden="true"></i> <span>Reminder</span>
				</button>
				<button id="addGroup">
					<i class="fa fa-users" aria-hidden="true"></i><span>Create
						Group</span>
				</button>
				<button id="addPoll" href="#viewPoll" class="modal-trigger">
					<i class="fa fa-bar-chart" aria-hidden="true"></i><span>Create
						Poll</span>
				</button>
			</div>
		</div>


		<!-- my profile page -->
		<div id="uprof" style="position: absolute; z-index: -10"
			class="content ">
			<div class="card " id="pcard">

				<div id="wrappie">

					<!-- Exit button -->
					<div class="exit-card">
						<a class="btn-flat" onclick="backHomeFromMyProfile()"><i
							class="material-icons">close</i></a> <a class="btn-flat"
							onclick="showEditMyProfile()"><i class="material-icons">edit</i></a>
					</div>

					<!-- Card Profile image -->
					<div class="card-image">
						<img src="" class="display-pic">
					</div>

					<!-- Card Profile content -->
					<div class="card-content">
						<div id="userName">
							<p id="userNameValue">userName</p>
						</div>
						<div id="userEmail">
							<p class="cardLabel">Email</p>
							<p id="userEmailValue">kamlesh@gmail.com</p>
						</div>
						<div id="userPhone">
							<p class="cardLabel">Phone No.</p>
							<p id="userPhoneValue">9676433245</p>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- Edit profile page -->
		<div id="eprof" style="position: absolute; z-index: -10"
			class="content ">
			<div class="card " id="pcard">

				<div id="wrappie">

					<!-- Exit button -->
					<div class="exit-card">
						<a class="btn-flat" onclick="backHomeFromMyProfile()"><i
							class="material-icons">close</i></a>
					</div>

					<form>
						<img src="" id="upload-demo" />
					</form>

					<!-- Card Profile content -->
					<div class="card-content">
						<div class="card-action">
							<input type='file' onchange="readURL(this);"
								accept="image/jpeg, image/png"
								style="margin-left: 10%; color: green;" /><br> <br>
						</div>
						<div id="userName" class="input-field">
							<p class="cardLabel">Full Name</p>
							<input id="userNameValue" type="text">
						</div>
						<div id="userEmail" class="input-field">
							<p class="cardLabel">Email</p>
							<input id="userEmailValue" type="email" class="validate">
						</div>
						<div id="userPhone" class="input-field">
							<p class="cardLabel">Phone No.</p>
							<input id="phone" type="text" class="validate">
						</div>
						<div class="card-action">
							<a id="updateProfileBtn" href="#">Update Profile</a>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- Contact profile page -->
		<div id="cprof" style="position: absolute; z-index: -10"
			class="content ">
			<div class="card " id="pcard">

				<div id="wrappie">

					<!-- Exit button -->
					<div class="exit-card">
						<a class="btn-flat" onclick="backHomeFromContactProfile()"><i
							class="material-icons">close</i></a>
					</div>

					<!-- Card Profile image -->
					<div class="card-image">
						<img src="images/profile.png" class="display-pic">
					</div>

					<!-- Card Profile content -->
					<div class="card-content">
						<div id="userName">
							<p id="userNameValue"></p>
						</div>
						<div id="userEmail">
							<p class="cardLabel">Email</p>
							<p id="userEmailValue"></p>
						</div>
						<div id="userPhone">
							<p class="cardLabel">Phone No.</p>
							<p id="userPhoneValue"></p>
						</div>
					</div>

				</div>
			</div>
		</div>

		<!-- starred message display -->
		<div id="stardisplay" style="position: absolute; z-index: -10"
			class="content ">
			<div class="card" style="background: #E6EAEA;" id="pcard">

				<div id="wrappie">

					<!-- Exit button -->
					<div class="exit-card">
						<a class="btn-flat" onclick="backHomeFromMyProfile()"><i
							class="material-icons">close</i></a>
					</div>
					<!-- display starred -->
					<div id="starmessages" class="messages" style="max-height: 600px;">
						<ul>

						</ul>
					</div>

				</div>
			</div>
		</div>

		<!-- Background page -->
		<div id="background" class="content"
			style="position: absolute; z-index: 300; background: #E6EAEA"></div>

		<!--Contact Chat page -->
		<div id="chat" class="content" style="z-index: -10">
			<div class="contact-profile">

				<img src="images/profile.png" alt="" />
				<p></p>



			</div>
			<div id="messages" class="messages">
				<ul>

				</ul>
			</div>
			<div class="message-input">
				<div class="wrap" style="display: flex">
					<input id='chatBox' type="text" class="browser-default"
						style="width: 100%" placeholder="Write your message..." />

					<button class="submit" id="sb">

						<i class="fa fa-paper-plane" aria-hidden="true"></i>
					</button>
				</div>
			</div>
		</div>

		<div id="addreminder" class="modal">
			<div class="modal-content">
				<div class="rowreminder">
					<form class="col s12">
						<div class="rowreminder">
							<div class="input-field col s6">
								<i class="material-icons prefix">mode_edit</i>
								<textarea id="remaindermessage" class="materialize-textarea"></textarea>
								<label for="icon_prefix2">Enter Message</label>
							</div>
						</div>

					</form>
				</div>
				<div class="input-field col s6">
					<i class="material-icons prefix">perm_contact_calendar</i> <input
						type="text" id="remainderdate" class="datepicker"> <label
						for="icon_prefix2">Enter Date</label>
				</div>
				<div class="input-field col s6">
					<div class="input_fields_wrap">
						<p>
							<i class="material-icons prefix">contacts</i> <label
								for="icon_prefix2"></label> <label id="getcontact"
								style="visibility: hidden">></label> <a class="btn-floating"
								id="createlist"
								style="background: #32465a; color: white; float: right"> <i
								class="material-icons">add</i></a>
						</p>
					</div>
					<div>
						<i class="material-icons prefix"></i><input type="text"
							id="remindercontact" placeholder="Select Contact"
							onkeypress="return false" />
					</div>

					<div id="remindercreate">
						<div id="rsearchUserResult">
							<ul id="rsearchUserResultList">
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<a href="#!" style="background: #32465a; color: white;"
					class="modal-action modal-close btn-flat" id="addrem"
					onClick='sendReminderChat()'>Add Reminder</a> <a href="#!"
					style="background: #32465a; color: white;"
					class="modal-action modal-close btn-flat">Close</a>
			</div>

		</div>

		<div id="addContactModal" class="modal">
			<div class="modal-content">
				<input type="text" id="searchText"
					placeholder="Search by name, e-mail id" /> <input type="submit"
					id="searchUserButton" class=" btn-flat" value="Search"
					style="background: #32465a; color: white;" />


				<div id="searchUserResult">
					<ul id="searchUserResultList">
					</ul>
				</div>

			</div>
			<div class="modal-footer">

				<a href="#!" style="background: #32465a; color: white;"
					class="modal-action modal-close btn-flat"
					id="closeAddContactButton">Close</a>
			</div>
		</div>
		<div id="viewPoll" class="modal">
			<div class="modal-content">
				<!-- <div class="row"> -->
				<div class="row">
					<div class="col s10">
						<span class="card-image waves-effect waves-block waves-light">
							<div id="create">
								<a class="btn-floating btn-large waves-effect waves-light red">
									<i class="material-icons">add</i>
								</a> <font face="verdana" color="black" size="5">Create </font>

							</div>
						</span>
					</div>
					<div class="col s2" id="closePoll">
						<span> <a href="#!"
							class="modal-action modal-close 
													waves-effect waves-green btn-flat">
								<i class="material-icons">close</i>
						</a>

						</span>
					</div>
				</div>
				<div class="row">
					<div class="col s12" id="popUp_1">
						<form>
							<input type="text" name="question" id="questionPoll"
								placeholder="Enter your question for Poll" /><br>
							<div class="input_fields_wrap">
								<p>
									<i class="material-icons prefix">contacts</i> Add Contacts <a
										class="btn-floating" id="addPollContact" style="float: right;">
										<i class="material-icons">add</i>
									</a>
								</p>
								<div>
									<input type="text" name="mytext" id="addContactPoll1">
								</div>
							</div>
							<br> <input type="button" id="sendPoll"
								value="Send the Poll" class="waves-effect waves-light btn">
						</form>
					</div>
				</div>
				<!-- </div> -->
				<hr>
				<div class="row col s12 m8 offset-m2 l6 offset-l3"
					id="view_questions">
					<div class='card-panel grey lighten-5 z-depth-1' id='viewQuestions'>
						<div class="content_wrapper" id="questions"></div>
					</div>
				</div>
			</div>
		</div>

	</div>
	<script src='../js/datepicker.js'></script>
</body>
</html>