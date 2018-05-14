(function () {
	jQuery(main);
	
	var tbody;
	var template;
	var userService = new UserServiceClient()
	
	function main(){
		
		
    	$('#registerUser').click(register);
    	
    	
    	
    	}
	
	
	function register(user) {
        userService
            .findUserById(userId)
            .then(renderUser);
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

				console.log("now check if....");

			//	alert("Please enter another username. This username already exists!");

//				userService.register(user);
//				var username = $('#usernameFld').val();
//				var password = $('#passwordFld').val();
//				var user = {
//						username: username,
//						password: password
//				};
				
//				alert("Registration done! You can login to complete your profile.");


		}

   		} // end of register function


})();
