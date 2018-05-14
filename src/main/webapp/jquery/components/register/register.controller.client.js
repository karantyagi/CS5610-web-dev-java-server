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
		
		console.log(searchUserName(username));
		
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
		
		if(password == verifyPassword){
			var user = {
					username: username,
					password: password
			};
			
			console.log(user);
			
//			userService
//				.createUser(user);	
		}

   		}
	
	function searchUserName(username){
			userService
					.findUserByUsername(username);
	}

})();
