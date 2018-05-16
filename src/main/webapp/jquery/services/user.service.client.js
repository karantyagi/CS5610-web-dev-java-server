function UserServiceClient() {
   this.createUser = createUser;
   this.findAllUsers = findAllUsers;
   this.deleteUser = deleteUser;
   this.findUserById = findUserById;
   this.findUserByUsername = findUserByUsername;
   this.updateUser = updateUser;
   this.registerUser = registerUser;
   this.loginUser = loginUser;
   
   this.url =
'http://localhost:8080/api/user';
//   this.url =
//	   'https://kt-web-dev-java-server.herokuapp.com/api/user';
   
   this.urlregister =
	   'http://localhost:8080/api/register';
//   this.urlregister =
//	   'https://kt-web-dev-java-server.herokuapp.com/api/register';
   
   
   this.login =
       'http://localhost:8080/api/login';
//   this.login =
//	   'https://kt-web-dev-java-server.herokuapp.com/api/login';
   
   var self = this;
   
   function registerUser(user){
		 return fetch(this.urlregister,{
				method: 'post',
				body: JSON.stringify(user),
				headers: {
					'content-type': 'application/json'
				}
			})
			 .then(function(response){
			       	console.log(response);
			           return response.json();
			   });
	 }
   
   
   function loginUser(username, password) {
       return fetch(self.login, {
           method: 'post',
           body: JSON.stringify({username:username, password: password}),
           headers: {
               'content-type': 'application/json'
           }
       })
       .then(function(response){
       	console.log(response);
           return response.json();
   });
}
   
   
   function createUser(user){
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
    return fetch(self.urlregister + '/' + username)
			    .then(function(response){
			        return response.json();
			    });
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
        	console.log(response);
            return response.json();
    });
}

   
}


