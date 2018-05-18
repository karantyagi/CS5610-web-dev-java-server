function UserServiceClient() {
   this.createUser = createUser;
   this.findAllUsers = findAllUsers;
   this.deleteUser = deleteUser;
   this.findUserById = findUserById;
   this.findUserByUsername = findUserByUsername;
   this.updateUser = updateUser;
   this.updateUserProfile = updateUserProfile;
   this.registerUser = registerUser;
   this.loginUser = loginUser;
   this.profile = profile;
   this.getSessionAttribute = getSessionAttribute;
   this.setSessionAttribute = setSessionAttribute;
   this.invalidateSession = invalidateSession;
   
   this.sessionSet = 
	   'http://localhost:8080/api/session/set';
//	   this.sessionSet = 
//		   'https://kt-web-dev-java-server.herokuapp.com/api/session/set';
   
   this.sessionGet = 
	   'http://localhost:8080/api/session/get';
//	   this.sessionGet = 
//		   'https://kt-web-dev-java-server.herokuapp.com/api/session/get';
	   
   this.profileUrl = 
	   'http://localhost:8080/api/profile';
//	   this.profileUrl = 
//		   'https://kt-web-dev-java-server.herokuapp.com/api/profile';
	   
   this.invalidateUrl = 
	   'http://localhost:8080/api/session/invalidate';
//	   this.invalidateUrl = 
//		   'https://kt-web-dev-java-server.herokuapp.com/api/session/invalidate';
   
   
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
				credentials: 'same-origin',
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
   
   
   function profile(user){
		 return fetch(this.profileUrl,{
			 credentials: 'same-origin'
		 })
			 .then(function(response){
			       	//console.log("PROFILE  ", response);
			           return response.json();
			   });
	 }
   
   function setSessionAttribute(attr,value){
		 return fetch(this.sessionSet+'/' + attr +'/' + value,{
			 credentials: 'same-origin'
		 })
			 .then(function(response){
			           return response.text();
			   });
	 }
   
   function getSessionAttribute(attr){
		 return fetch(this.sessionGet +'/' + attr,{
			 credentials: 'same-origin'
		 })
			 .then(function(response){
			           return response.text();
			   });
	 }
   
   function invalidateSession(){
		 return fetch(this.invalidateUrl,{
			 credentials: 'same-origin'
		 })
			 .then(function(response){
			           return response.text();
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

function updateUserProfile(userId, user) {
    return fetch(self.urlregister + '/' + userId, {
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


