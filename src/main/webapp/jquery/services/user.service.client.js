function UserServiceClient() {
   this.createUser = createUser;
   this.findAllUsers = findAllUsers;
   this.deleteUser = deleteUser;
   this.findUserById = findUserById;
   this.updateUser = updateUser;
   
  // this.register = register;
   
   this.url =
'http://localhost:8080/api/user';
   
   this.login =
       'http://localhost:8080/api/login';
   
   var self = this;
   
 //  function register(username, password){}
   
   function login(username, password) {
       return fetch(self.login, {
           method: 'post',
           body: JSON.stringify({username:username, password: password}),
           headers: {
               'content-type': 'application/json'
           }
       });
   }
   
   
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


function findUserById(userId) {
    return fetch(self.url + '/' + userId)
        .then(function(response){
            return response.json();
        });
}


function findUserByUsername(username) {
    return fetch(self.url + '/' + username);
}


function updateUser(userId, user) {
    return fetch(self.url + '/' + userId, {
        method: 'put',
        body: JSON.stringify(user),
        headers: {
            'content-type': 'application/json'
        }
    })
    .then(function(response){
        if(response.bodyUsed) {
        	console.log(response);
            return response.json();
        } else {
            return null;
        }
    });
}

   
}


