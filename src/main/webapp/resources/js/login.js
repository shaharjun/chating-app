$(document).ready(function(){
	var user = null;	
	// Get data from local storage
	//user = getLocalStorage("thisUser");
	// Get data from java server

	$("#login-button").click(function(){
		var email = $("#email").val();
		var password = $("#password").val();
		if(email === ""){
			Materialize.toast("Enter email", 1000);
		}
		else if(password === ""){
			Materialize.toast("Enter password", 1000);
		}
		else{

			var requestData = {
					"emailId": email,
					"password": password	
			};

			$.ajax({
				type: 'POST',
				// call CPP login API via Java backend
				url : 'login',
				// call CPP login API via Client(browser)
//				url : 'http://172.31.11.110:8192/gc/userauthservice/login/',
				crossDomain: true,
				data: JSON.stringify(requestData),
				contentType:'application/json; charset=utf-8',
				dataType: 'json',
				success: function(data){
					// call firebase

					// redirect to index
					if(data.userStatus == true){
						//localStorage.setItem("sessionId", data.sessionId);
						//localStorage.setItem("emailId", data.emailId);
						getAllUsers().then(function(users){
							var userObject = getUser(users,email);
							if(userObject != null) {
								userObject.sessionId = data.sessionId;
								setLocalStorage("thisUser",userObject);
								userObject.sessionId = ' ';
								storeLoggedInUser(userObject);
								console.log(userObject);
								window.location.replace("index");
							}
						})
					}
					if(data.userStatus == false){
						Materialize.toast("Incorrect email or password", 3000);
					}
				},
				error: function(error){
					console.log(error);
				}
			});
		}
	});
	validatePassword();
});
