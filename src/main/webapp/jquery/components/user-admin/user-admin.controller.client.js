// IIFE - immediately invoked function expression

(function () {
	jQuery(main);
	
	var tbody;
	var template;
	var userService = new UserServiceClient()
	
	function main(){
		
		tbody = $('tbody');
		template = $('.template');
		$('#createUser').click(createUser);
	
		findAllUsers();
	} // end of main function
	
	function findAllUsers(){	
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
		
		console.log(user);
		
		userService
			.createUser(user)
			.then(findAllUsers);	
	}

	
	function renderUser(user){
		var clone = template.clone();
		
		clone.attr('id',user.id);
		clone.find('.delete').click(deleteUser);
		clone.find('.edit').click(updateUser);
		
		
		clone.find('.username')
		.html(user.username);
	
		clone.find('.password')
		.html(user.password.replace(/[a-z0-9]/g , "*"));
		
		clone.find('.firstName')
		.html(user.firstName);
		
		clone.find('.lastName')
		.html(user.lastName);
		
		clone.find('.role')
		.html(user.role);

		tbody.append(clone);		
}
	
	
	function renderUsers(users){
		tbody.empty();
			for(var i=0; i<users.length; i++){
				var user = users[i];
				var clone = template.clone();
				
				clone.attr('id',user.id);
				clone.find('.delete').click(deleteUser);
//				clone.find('.edit').click(updateUser);
				
				
				clone.find('.username')
				.html(user.username);
			
				clone.find('.password')
				.html(user.password.replace(/[a-z0-9]/g , "*"));
				
				clone.find('.firstName')
				.html(user.firstName);
				
				clone.find('.lastName')
				.html(user.lastName);
				
				clone.find('.role')
				.html(user.role);

				tbody.append(clone);
			}
			
		} // end of renderUsers
	
	function deleteUser(event){
//		console.log('deleteUser');
//		console.log(event);
		var deleteBtn = $(event.currentTarget);
		var userId = deleteBtn
		.parent()
		.parent()
		.attr('id');
		userService
			.deleteUser(userId)
			.then(findAllUsers);
	} // end of deleteUsers
	
	function updateUser(event){
		console.log('editUser');
		console.log(event);
		var editBtn = $(event.currentTarget);
		var userId = editBtn
		.parent()
		.parent()
		.attr('id');
		
	//	
//		userService
//			.updateUser(userId)
//			.then(findAllUsers);
		}

	function findUserById(userId){
		userService.
			findUserById(userId)
	}


})();