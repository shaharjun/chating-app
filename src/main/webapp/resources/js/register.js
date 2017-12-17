$(document).ready(function () {
	//  if (getLocalStorage("generatedId") == null) {
	//  	setLocalStorage("generatedId", 1);
	//  	console.log('user id set');
	//  }
	$("#register-button").click(function () {
		var formComplete = true;
		var fullname = $("#fullname").val();
		var phone = $("#phone").val();
		var email = $("#email").val();
		var password = $("#password").val();
		var isFormComplete = validateRegisterForm(formComplete, fullname, phone, email, password);
		if (isFormComplete) {
//			var thisUser = null;
			//thisUser = getLocalStorage("thisUser");
//			if (thisUser !== null) {
//			//var registeredUser = getLocalStorage("thisUser");
//			var emailToCheck = registeredUser.emailId;
//			if (email === emailToCheck) {
//			Materialize.toast("User already registered", 4000);
//			}
//			} else {
			var requestData = {
					"emailId": email,
					"password": password,
					"phoneNo": phone,
					"fullName": fullname
			};

			$.ajax({
				type: 'POST',
				// call CPP login API via Java backend
				url : 'register',
				// call CPP login API via Client(browser)
//				url : 'http://172.31.11.110:8192/gc/userauthservice/login/',
				crossDomain: true,
				data: JSON.stringify(requestData),
				contentType:'application/json; charset=utf-8',
				dataType: 'json',
				success: function(data){

					if(data.globalErrorMessage.length > 1){
						Materialize.toast(data.globalErrorMessage, 3000);
					}

					if(data.globalSuccessMessage.length > 1){
						Materialize.toast(data.globalSuccessMessage, 3000);
						// call firebase
						var user = new User();
						user.emailId = email;
						user.password = password;
						user.phoneNo = phone;
						user.fullName = fullname;
						var id;
						firebase.database().ref('idGenerator/uId').once('value').then(function(snapshot){
							id = snapshot.val();
							user.userId = id;
							//localStorage.setItem("thisUser",JSON.stringify(user));
							createUser(user);
							id++;
							firebase.database().ref('idGenerator').update({'uId':id}).then(function(){
							
								window.location.replace("login");
							});
						});	

					}

				},
				error: function(error){
					console.log(error);
				}
			});

			// var generatedId = parseInt(getLocalStorage("generatedId"));
			// console.log(generatedId);
			//user.userId = ++generatedId;
			// create user method is in userUtil, registers user in localStorage

			//setLocalStorage("generatedId", generatedId);
			setLocalStorage("isRegistered", true);
			//window.location.href = "login.html";
		}
		else{
			return false;
		}
	});
	validatePassword();
});