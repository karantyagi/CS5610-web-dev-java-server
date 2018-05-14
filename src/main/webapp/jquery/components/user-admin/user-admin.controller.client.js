// IIFE - immediately invoked function expression

(function () {
	jQuery(main);
	
	var tbody;
	var template;
	var userService = new UserServiceClient()
	
	function main(){
//		var h1=jQuery('h1');
//		h1.css('color','red');
//		h1.html('User Admin Panel');
		
		tbody = $('tbody');
		template = $('.template');
		$('#createUser').click(createUser);
	
		findAllUsers();
	} // end of main function
	
	function findAllUsers(){
//		var promise = fetch('http://localhost:8080/api/user');
////		console.log(users);
//		promise.then(function (response){
//			return response.json(); 
//		}).then(renderUsers);
		
		userService
			.findAllUsers()
			.then(renderUsers);
	}
	
	function createUser(){
		var username = $('#usernameFld').val();
		var password = $('#passwordFld').val();
		var firstName = $('#firstNameFld').val();
		var lastName = $('#lastNameFld').val();
		var role = $('#role').val();
		//console.log(role);
		
		var user = {
				username: username,
				password: password,
				firstName: firstName,
				lastName: lastName,
				role: role
		};
		
		userService
			.createUser(user)
			.then(findAllUsers);
		
		console.log(user);
			
		
	}

		function renderUsers(users){
			for(var i=0; i<users.length; i++){
				var user = users[i];
				var clone = template.clone();
				clone.find('.username')
				.html(user.username);
				tbody.append(clone);
			}
			
		}
		

})();
