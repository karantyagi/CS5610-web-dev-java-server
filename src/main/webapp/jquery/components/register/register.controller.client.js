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

	 function success(user) {
	            console.log("ID :", user.id);
	            alert("Registration done!");
	            
	           
	            userService.profile()
	           .then(checkProfile);
	            
	 } // end of success function
	 
	 function checkProfile(user){
	            if(user.username != "NULL"){
	        	   console.log("USER IN SESSION:  ",user.username,"  ", user.id,"\n",user);
	        	   console.log("Now set session and goto profile page");
	        	   userService
					.setSessionAttribute("id", user.id)
					.then(loginSuccess);	        	   
	           } 
	            else{
	            	 console.log("USER SESSION:  Current user is null, that means no session is being maintained");
	            	 console.log("...the above else SHOULD HAVE NEVER BEEN FIRED ... error ")
	            }
	            
	            
	            //clear();

	    }
	 
	 function loginSuccess(sessionInfo) {
         console.log("SET SESSION :", sessionInfo);
         alert("\nYou are logged in as you just registered.\n You can now update your profile.");
      // window.location.replace("https://kt-web-dev-java-server.herokuapp.com/jquery/components/profile/profile.template.client.html");
      window.location.replace("/jquery/components/profile/profile.template.client.html");
         
} // end of loginSuccess function
	 
	 function clear(){
			$('#usernameFld').val("");
			$('#passwordFld').val("");
			$('#verifyPasswordFld').val("");
	 }

})();
