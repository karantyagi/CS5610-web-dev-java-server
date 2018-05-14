(function() {
    $(init);
    
    var $staticEmail;
    var $staticUserName;
    var $firstName;
    var $lastName;
    var $updateBtn;
    
    var userService = new UserServiceClient();

    function init() {

    	$staticUserName = $("#staticUserName");
    	$staticEmail = $("#staticEmail");
        $firstName = $("#firstName");
        $lastName = $("#lastName");
        $updateBtn = $("#updateBtn")
           .click(updateUser);

        findUserById(12);
    }
    
    function updateUser(){
    	 var user = {
    	            firstName: $firstName.val(),
    	            lastName: $lastName.val()
    	        };

    	        userService
    	            .updateUser(12, user)
    	            .then(success);
    	
    }
    
    function success(response) {
        if(response === null) {
            alert('unable to update')
        } else {
            alert('success');
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
    	$staticEmail.val(user.email);
        $firstName.val(user.firstName);
        $lastName.val(user.lastName);
    }

})();