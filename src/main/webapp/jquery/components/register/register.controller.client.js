(function () {
	jQuery(main);
	var userService = new UserServiceClient()
	
	function main(){
    	$('#registerBtn').click(register);
    	} //end of main function

	
	function validate(){
		// read values from UI
    	var username = $('#usernameFld').val();
		var password = $('#passwordFld').val();
		var verifyPassword = $('#verifyPasswordFld').val();
		
		console.log("inside validations");
		
		// Validations
		
		if(username == ""){
			alert("Please fill username!");
			return false;
		}
		
		if(password == ""){
			alert("Please fill password!");
			return false;
		}
		
		if(verifyPassword == ""){
			alert("Please verify password!");
			return false;
		}
		
		if(password != verifyPassword){
			alert('Passwords do not match!');
			return false;
		}
		
		if(password != "" && password == verifyPassword){
			return true;
		}
		
	} // end of validate function
	
	
	function register(event) {
		
		if(validate()){
			var username = $('#usernameFld').val();
			var password = $('#passwordFld').val();
			var user = {
					username: username,
					password: password};
				//console.log("NEW USER to be registered, into database:");
				//console.log(user);
				
				//console.log("Check if username already taken ?");
				//console.log(user.username);
				userService
					.findUserByUsername(user.username)
					.then(foundSuccess);
	
		}

      
    } // end of register function
	
	 function foundSuccess(user) {
	        if(user.id == -1) {	        	
	        	
				var newUser = {
						username: $('#usernameFld').val(),
						password: $('#passwordFld').val()};
	        	
	        	userService
				.registerUser(newUser)
				.then(success);	
	        	//alert("Registration done! You can login and update your profile.");
	        } 
	        else {
	        	// Some user with the same username found..
	        	clear();
	        	alert("Please enter another username. This username already exists!");
	        	
	        }
	    } // end of foundSuccess function

	 function success(response) {
	        if(response === null) {
	            alert('Unable to register. Please try again.')
	        } else {
	            alert("Registration done! You can now login and update your profile.");
	            clear();
	            
	        }
	    } // end of success function
	 
	 function clear(){
			$('#usernameFld').val("");
			$('#passwordFld').val("");
			$('#verifyPasswordFld').val("");
	 }

})();
