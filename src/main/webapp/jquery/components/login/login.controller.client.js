(function () {
    var $usernameFld, $passwordFld;
    var $loginBtn;
    var userService = new UserServiceClient();
    $(main);

    function main(){
    	$('#loginBtn').click(login);
    } // end of main function
    
    function login(){
    	console.log("Login button clicked");
    	if(validate()){
    		var user = {
					username: $('#usernameFld').val(),
					password: $('#passwordFld').val()};
    	console.log(user);	
    	validateUser(user);
    	   
    	}
    	
    } // end of login function
    
   
    function validateUser(){
    	
    	console.log("user find user by credentials...coming up...")
    }
    
    
    
    function validate(){
		// read values from UI
    	var username = $('#usernameFld').val();
		var password = $('#passwordFld').val();
		
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
		
		return true;
		
	} // end of validate function 
    

})();
