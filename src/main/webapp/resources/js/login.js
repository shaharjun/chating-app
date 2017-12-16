$(document).ready(function(){
	var user = null;	
	// Get data from local storage
	user = getLocalStorage("thisUser");
	// Get data from java server

	if(user === null){
		$("#email").val("");
	}
	if(getLocalStorage("sessionId") !== null || getLocalStorage("isRegistered") !== null){
		$("#email").val(user.emailId);
	}
	else{
		console.log("token does not exist");
	}
	$("#login-button").click(function(){
		var email = $("#email").val();
		var password = $("#password").val();
		if(email === ""){
			Materialize.toast("Enter email", 1000);
		}
		else if(password === ""){
			Materialize.toast("Enter password", 1000);
		}
//		else if(user.emailId === email && user.password=== password){
//		setLocalStorage("sessionId",email.hashCode());
//		window.location.href = "index.html";
//		}
//		else{
//		Materialize.toast("Incorrect email or password", 2000);
//		}
		else{

			var requestData = {
					"emailId": email,
					"password": password	
			};

			$.ajax({
				type: 'POST',
				url : 'login',
				crossDomain: true,
				data: JSON.stringify(requestData),
				contentType:'application/json; charset=utf-8',
				dataType: 'json',
				success: function(data){
					// call firebase

					// redirect to index
					if(data.userStatus == true){
						localStorage.setItem("sessionId", data.sessionId);
						localStorage.setItem("emailId", data.emailId);
						window.location.replace("index");
					}
					else{
						Materialize.toast("Incorrect email or password", 3000);
					}
					console.log(data);

				},
				error: function(error){
					console.log(error);
				}
			});

//			$.ajax({
//			type: "POST",
//			url: "login",
//			data: requestData,
//			success: function(data){
//			// call firebase

//			// redirect to index
//			if(data.userStatus == true){
//			localStorage.setItem("sessionId", data.sessionId);
//			localStorage.setItem("emailId", data.emailId);
//			window.location.replace("index");
//			}
//			else{
//			Materialize.toast("Incorrect email or password", 3000);
//			}
//			console.log(data);

//			},
//			error: function(error){
//			console.log(error);
//			},
//			dataType: json
//			});
		}
	});
	validatePassword();
});
