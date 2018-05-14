(function () {
	jQuery(main);
	
	var tbody;
	var template;
	var userService = new UserServiceClient()
	
	function main(){
    	$('#registerUser').click(registerUser);
    	}
	
	function registerUser(){
		var username = $('#usernameFld').val();
		var password = $('#passwordFld').val();
		var verifyPassword = $('#verifyPasswordFld').val();
		
			
		if(username == ""){
			alert("Please fill username!");
		}
			
		
		if(password == ""){
			alert("Please fill password!");
		}
		
		if(verifyPassword == ""){
			alert("Please verify password!");
		}
		
		if(password != verifyPassword){
			alert('Password does not match!');
		}
		
		if(password != "" && password == verifyPassword){
			var user = {
					username: username,
					password: password};
			// if username is not already taken
			
			
			
			console.log("now check if");

//			
//			if(searchUserName(username)!=null){
//				alert("Please enter another username. This username already exists!");
//			}
						
				userService.register(user);
				var username = $('#usernameFld').val();
				var password = $('#passwordFld').val();
				var user = {
						username: username,
						password: password
				};
				userService
				.findUserByUsername(username);
				alert("Registration done! You can login to complete your profile.");
			

		}

   		} // end of register function


})();
