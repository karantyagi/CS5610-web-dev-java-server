(function() {
    $(init);
    
    
    var $staticUserName;
    var $email;
    var $phone;
    var $role;
    var $date;
    var userId = 612; // harshmeet
    
    var userService = new UserServiceClient();

    function init() {

    	$staticUserName = $("#staticUserName");
    	$email = $("#inputEmail");
        $phone = $("#inputPhone");
        $role =  $("#inputRole");
        //$date =  $("#inputDate");
        $("#updateBtn").click(updateUser);
        $("#logoutBtn").click(logoutUser);
        
        //console.log( $logoutBtn);
        
        //console.log("DATE:");
        //console.log($date);
       
       findUserById(userId);

   
        
    }
    
    function updateUser(){
    	 var user = {
    	            phone: $phone.val(),
    	            email: $email.val(),
    	            role: $role.val()
    	        };

  	        	userService
    	            .updateUser(userId, user)
    	            .then(success);
    	
    }
    
    
    function logoutUser(){
    	window.location.replace("http://localhost:8080/jquery/components/login/login.template.client.html");
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
    	$email.val(user.email);
    	$phone.val(user.phone);
    	$role.val(user.role);
    	//$date.val(user.date);
    }

})();