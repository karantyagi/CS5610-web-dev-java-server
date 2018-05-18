(function() {
    $(init);
    
    var username;
    var $staticUserName;
    var $firstName;
    var $lastName;
    var $email;
    var $phone;
    var $role;
    var $date;
    var userId;
   console.log("userID: ",userId);

    var userService = new UserServiceClient();

    function init() {
    	
    	// TO DO: Check if some session is being maintained.... ???
    	userService
    		.getSessionAttribute("id")
    		.then(checkSession)		
    }
    
    function checkSession(sessionId){
    	
    	console.log("GETTING SESSION ID :", sessionId);

    	 if(sessionId == null)
    		{	
    	     //$('form').css("filter", "blur(4px)");
    	    	alert("\nYou are currently not logged in !\nLogin / Sign in to view or update profile !")
    	    	//window.location.replace("http://localhost:8080/jquery/components/login/login.template.client.html");
    	    	window.location.replace("https://kt-web-dev-java-server.herokuapp.com/jquery/components/login/login.template.client.html");
    	    	}
    	 else{

    	userId = sessionId;
    	
    	$staticUserName = $("#staticUserName");
    	$firstName= $("#inputFirstName");
    	$lastName= $("#inputLastName");
    	$email = $("#inputEmail");
        $phone = $("#inputPhone");
        $role =  $("#inputRole");
        $date =  $("#inputDate");
        $("#updateBtn").click(updateUser);
        $("#logoutBtn").click(logoutUser);
        
        $("#updateDateBtn").click(updateDate);
        
        //console.log( $logoutBtn);
        //console.log("DATE:");
        //console.log($date);
        findUserById(userId);
    	 }
  
    }
    
    function updateUser(){
    	 var user = {
    			 	firstName: $firstName.val(),
    			 	lastName: $lastName.val(),
    	            phone: $phone.val(),
    	            email: $email.val(),
    	            role: $role.val(),
    	            dateOfBirth: $date.val()
    	        };

  	        	userService
    	            .updateUserProfile(userId, user)
    	            .then(success);	
    }
    
    function updateDate(){
    	$date =  $("#inputDate");
        console.log("USER's NEW DOB :  ",$date.val(), " ... update this !");
    	
    }
    
    
    function logoutUser(){
    	userService
    	.invalidateSession()
    	.then(logout);	
    }
    
    function logout(message) {
        if(message == "session invalidated") {
            alert('Successfully logged out!')
          //  window.location.replace("http://localhost:8080/jquery/components/login/login.template.client.html");
    	window.location.replace("https://kt-web-dev-java-server.herokuapp.com/jquery/components/login/login.template.client.html");
            
        } else {
            alert('Error! Try logging out again.');
        }
    }

    
    
    
    function success(response) {
        if(response === null) {
            alert('unable to update')
        } else {
            alert('Profile updated successfully!');
        }
    }


    function findUserById(userId) {
        userService
            .findUserById(userId)
            .then(renderUser);
    }
    
    function renderUser(user){
    	console.log(user);
    	$staticUserName.val(user.username);
    	if(user.firstName != null){$firstName.val(user.firstName); }
    	if(user.lastName != null){$lastName.val(user.lastName); }
    	if(user.email != null){$email.val(user.email); }
    	if(user.phone != null){$phone.val(user.phone);}
    	if(user.role != null){$role.val(user.role);}
    	if(user.dateOfBirth != null)
    		{ 
    			//console.log(user.dateOfBirth.substring(0,10)); 
    			$date.val(user.dateOfBirth.substring(0,10)); }
//    	else{
//            document.querySelector("#inputDate").valueAsDate = new Date();
//            $date =  $("#inputDate");
//            console.log("USER's DOB is null so showing today's date", $date.val());
//    	}
    }

})();