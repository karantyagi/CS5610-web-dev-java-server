package webdev.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import webdev.models.User;
import webdev.repositories.UserRepository;

@RestController
public class UserService {
	@Autowired
	UserRepository repository;
	List<User> users = new ArrayList<User>();
	
	@GetMapping("/api/user")
	public List<User> findAllUsers() {
		return (List<User>) repository.findAll();
	}
	
	@PostMapping("/api/user")
	public User createUser(@RequestBody User user) {
		return repository.save(user);
	}
	
//	@PostMapping("/api/register")
//	public User registerUser(@RequestBody User user) {
//		return repository.save(user);
//	}
	
	@PostMapping("/api/register")
	public User registerUser(@RequestBody User user,
			HttpSession session) {
		session.setAttribute("currentUser", user);
		users.add(user);
		// repository.save(user);
		System.out.println("\n\n\n======== ALL USERS ======= \n");
		for(User u: users) {
			System.out.println(u.getUsername());
		}
		System.out.println("\n\n\n");
		return repository.save(user);
	}
	
	// Retrieving the profile from the session
	
	@GetMapping("/api/profile")
	public User profile(HttpSession session) {
	User currentUser = (User)
	session.getAttribute("currentUser");
	if(currentUser == null) {
		User nullUser = new User();
		nullUser.setUsername("NULL");
		return nullUser;
	}
	return currentUser;
	}

	
	
	
	@DeleteMapping("/api/user/{userId}")
	public void deleteUser(@PathVariable("userId") int id) {
		repository.deleteById(id);
	}
	
	@GetMapping("/api/user/{userId}")
	public User findUserById(@PathVariable("userId") int userId) {
		Optional<User> data = repository.findById(userId);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@GetMapping("/api/register/{username}")
	public User findUserByUsername(@PathVariable("username") String username) {
		Optional<User> data = repository.findUserByUsername(username);
		if(data.isPresent()) {
			System.out.println("ID: "+data.get().getId());
			return data.get();
		}
		else {
			System.out.println("ID: -1 {NO USER FOUND}");
			User none = new User();
			none.setId(-1);
			none.setUsername(null);
			none.setPassword(null);
			none.setDateOfBirth(null);
			none.setEmail(null);
			none.setFirstName(null);
			none.setLastName(null);
			none.setRole(null);
			none.setPhone(null);
			return none;
		}
		
	}
	
	@PutMapping("/api/user/{userId}")
	public User updateUser(@PathVariable("userId") int userId, @RequestBody User newUser) {
		Optional<User> data = repository.findById(userId);
		if(data.isPresent()) {
			User user = data.get();
			user.setUsername(newUser.getUsername());
			user.setPassword(newUser.getPassword());
			user.setEmail(newUser.getEmail());
			user.setPhone(newUser.getPhone());
			user.setFirstName(newUser.getFirstName());
			user.setLastName(newUser.getLastName());
			user.setRole(newUser.getRole());
			user.setDateOfBirth(newUser.getDateOfBirth());
			//System.out.println("\n.\n.\n.\n.\nUSER DOB:"+user.getDateOfBirth()+"\n.\n.\n.\n.");
			repository.save(user);
			return user;
		}
		return null;
	}
	
	@PutMapping("/api/register/{userId}")
	public User updateUserProfile(@PathVariable("userId") int userId, @RequestBody User newUser) {
		Optional<User> data = repository.findById(userId);
		if(data.isPresent()) {
			User user = data.get();
			user.setEmail(newUser.getEmail());
			user.setPhone(newUser.getPhone());
			user.setFirstName(newUser.getFirstName());
			user.setLastName(newUser.getLastName());
			user.setRole(newUser.getRole());
			user.setDateOfBirth(newUser.getDateOfBirth());
			//System.out.println("\n.\n.\n.\n.\nUSER DOB:"+user.getDateOfBirth()+"\n.\n.\n.\n.");
			repository.save(user);
			return user;
		}
		return null;
	}
	
	@PostMapping("/api/login")
	public List<User> loginUser(@RequestBody User user) {
		return (List<User>) repository.findUserByCredentials(user.getUsername(), user.getPassword());
	}
	
	// HttpSession
	// 30 min by default for timeout
	
	@GetMapping("api/session/set/{attr}/{value}")
	public String setSessionAttribute(
			@PathVariable("attr") String attr,
			@PathVariable("value") String value,    // value can be anything, String or User.
			HttpSession session) {					// injection session
		session.setAttribute(attr,value);
		return attr + "=" + value;
	}
	
	@GetMapping("/api/session/get/{attr}")
	public String getSessionAttribute(
			@PathVariable ("attr") String attr,
			HttpSession session) {
		return (String) session.getAttribute(attr);  // cast it back to User or String as required
	}
	
	
	// to logout - invalidate the old cookie
	
	@GetMapping("/api/session/invalidate")
	public String invalidateSession(
	HttpSession session) {
		session.invalidate();
	return "session invalidated";
	}

	
	
}