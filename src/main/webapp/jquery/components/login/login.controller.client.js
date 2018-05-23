(function () {
    var $usernameFld, $passwordFld;
    var $loginBtn;
    var userService = new UserServiceClient();
    $(main);

    function main(){
    	$('#loginBtn').click(login);
    } // end of main function
    
    function login(){
    	//console.log("Login button clicked");
    	if(validate()){
    		var user = {
					username: $('#usernameFld').val(),
					password: $('#passwordFld').val()};
    	//console.log(user);	
    	validateUser(user);
    	   
    	}
    	
    } // end of login function
    
   
    function validateUser(){
    	
    	//console.log("user find user by credentials...coming up...")
    	var user = {
					username: $('#usernameFld').val(),
					password: $('#passwordFld').val()};
    	
    	userService
    		.loginUser(user.username,user.password)
    		.then(findUser);
    }
    
    function findUser(users) {
    	console.log(users.length);
        if(users.length == 0) {
            alert('Username or password incorrect.')
        } else {
        	
        	userService
			.setSessionAttribute("id", users[0].id)
			.then(loginSuccess);
        	
           
        }
    } // end of success function
    
    function loginSuccess(sessionInfo) {
        console.log("SET SESSION :", sessionInfo);
        alert("You are logged in !");  
        window.location.replace("/jquery/components/profile/profile.template.client.html");
       // window.location.replace("https://kt-web-dev-java-server.herokuapp.com/jquery/components/profile/profile.template.client.html");
    }
    
    function validate(){
		// read values from UI
    	var username = $('#usernameFld').val();
		var password = $('#passwordFld').val();
		
		//console.log("inside validations");
		
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
