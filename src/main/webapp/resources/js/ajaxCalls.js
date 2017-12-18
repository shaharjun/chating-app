function updateUser(fullName, email, phoneNo, base64){
	var requestData = {
			"fullName": fullName,
			"emailId": email,
			"phoneNo": phoneNo,
			"profilePictureUrl": base64
	};
	$.ajax({
		type: 'POST',
		url : 'updateUser',
		crossDomain: true,
		data: JSON.stringify(requestData),
		contentType:'application/json; charset=utf-8',
		dataType: 'json',
		success: function(data){
			// call firebase

			// redirect to index
			if(data.userStatus == true){
				Materialize.toast("Profile Updated. Refreshing Page.", 4000);
	            location.reload();
			}
			if(data.userStatus == false){
				Materialize.toast("Could not update profile", 3000);
			}
		},
		error: function(error){
			console.log(error);
		}
	});
}
