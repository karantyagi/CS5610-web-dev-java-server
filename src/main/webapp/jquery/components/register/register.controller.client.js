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
			
			user1=userService
				.findUserByUsername(username)
				
			
			
			
			
				console.log("USER");
				console.log(user);
				
//				userService
//				.registerUser(user)
//				.then(success);	
		}

      
    } // end of register function
	
	
	
	 function success(response) {
	        if(response === null) {
	            alert('Unable to register. Please try again.')
	        } else {
	            alert("Registration done! You can login and update your profile.");
	        }
	    } // end of success function

	 
	 
	 
	 
			//	alert("Please enter another username. This username already exists!");



})();
