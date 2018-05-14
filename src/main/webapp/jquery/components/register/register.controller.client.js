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

				console.log("USER");
				console.log(user);
				
		}
      
    } // end of register function
	
	
	

			//	alert("Please enter another username. This username already exists!");

//				userService.register(user);
//				var username = $('#usernameFld').val();
//				var password = $('#passwordFld').val();
//				var user = {
//						username: username,
//						password: password
//				};
				
//				alert("Registration done! You can login to complete your profile.");




})();
