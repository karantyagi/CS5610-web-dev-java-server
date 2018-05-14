function UserServiceClient() {
   this.createUser = createUser;
   this.findAllUsers = findAllUsers;
   this.deleteUser = deleteUser;
   
   
//   this.findUserById = findUserById;
//   this.updateUser = updateUser;
   this.url =
'http://localhost:8080/api/user';
   var self = this;
   
   
   function createUser(user){
//	   fetch('http://localhost:8080/api/user'
		 return fetch(this.url,{
				method: 'post',
				body: JSON.stringify(user),
				headers: {
					'content-type': 'application/json'
				}
			});
	 }
   
   
   function findAllUsers(user){
		return fetch(self.url)
		   .then(function (response){
				return response.json(); 
			});
   }
   
function deleteUser(userId){
	return fetch(self.url + '/' + userId, {
		method: 'delete'
	});
}
   
}


