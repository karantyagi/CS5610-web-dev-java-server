// IIFE - immediately invoked function expression

(function () {
	jQuery(main);
	
	var tbody;
	var template;
	var updateId = -1;
	var userService = new UserServiceClient()
	
	function main(){
		
		tbody = $('tbody');
		template = $('.template');
		$('#createUser').click(createUser);
		$('#updateUser').click(updateUser);
		findAllUsers();
	} // end of main function
	
	function findAllUsers(){	
		userService
			.findAllUsers()
			.then(renderUsers);
	}
	
	function renderUsers(users){
		tbody.empty();
			for(var i=0; i<users.length; i++){
				var user = users[i];
				var clone = template.clone();
				
				clone.attr('id',user.id);
				clone.find('.delete').click(deleteUser);
				clone.find('.edit').click(editUser);
				
				
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

	function createUser(){
		var username = $('#usernameFld').val();
		var password = $('#passwordFld').val();
		var firstName = $('#firstNameFld').val();
		var lastName = $('#lastNameFld').val();
		var role = $('#role').val();

		var user = {
				username: username,
				password: password,
				firstName: firstName,
				lastName: lastName,
				role: role};
		userService
			.createUser(user)
			.then(findAllUsers);
		$('#usernameFld').val("");
		$('#passwordFld').val("");
		$('#firstNameFld').val("");
		$('#lastNameFld').val("");
		$('#role').val("FACULTY");
	}
	
	
	function deleteUser(event){
//		console.log('deleteUser');
		var deleteBtn = $(event.currentTarget);
		
		var userId = deleteBtn
		.parent()
		.parent()
		.attr('id');
		
		userService
			.deleteUser(userId)
			.then(findAllUsers);
	} // end of deleteUsers
	
	
	function editUser(event){
		console.log('editUser clicked');
		var editBtn = $(event.currentTarget);
		var userId = editBtn
		.parent()
		.parent()
		.attr('id');
		updateId = userId;
		console.log(updateId);
		findUserById(updateId);
		console.log("Selected user populated.")
		
	}
	
	function updateUser(){
		if(updateId == -1){
			alert("Select a user to update !");
		}
		else{
			var username = $('#usernameFld').val();
			var password = $('#passwordFld').val();
			var firstName = $('#firstNameFld').val();
			var lastName = $('#lastNameFld').val();
			var role = $('#role').val();
		
			var user = {
					username: username,
					password: password,
					firstName: firstName,
					lastName: lastName,
					role: role	};
			
//			console.log("updateID: ",updateId);
//			console.log("Values to be updated:")
//			console.log(user);
			
			userService
	        .updateUser(updateId, user)
	        .then(findAllUsers);
			$('#usernameFld').val("");
			$('#passwordFld').val("");
			$('#firstNameFld').val("");
			$('#lastNameFld').val("");
			$('#role').val("FACULTY");
			alert('User successfully updated!')
		}
	}
	
	function findUserById(updateId) {
        userService
            .findUserById(updateId)
            .then(selectUser);
    }
	
	// render user populates the fields
	function selectUser(user){
		//console.log("inside selectUser ..we are getting correct user");
		//console.log(user);
		$('#usernameFld').val(user.username);
		$('#passwordFld').val(user.password);
		$('#firstNameFld').val(user.firstName);
		$('#lastNameFld').val(user.lastName);
		$('#role').val(user.role);
	}
	

})();
