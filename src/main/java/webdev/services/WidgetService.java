package webdev.services;



import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import webdev.models.Topic;
import webdev.models.Widget;

import webdev.repositories.TopicRepository;
import webdev.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class WidgetService {
	
	@Autowired
	TopicRepository topicRepository;

	@Autowired
	WidgetRepository widgetRepository;
	
@GetMapping("/api/widget")
	public List<Widget> findAllWidgets()
	{
		List<Widget> all;
		all = (List<Widget>) widgetRepository.findAll();
		all.sort(Comparator.comparing(Widget::getWidgetOrder));
//		all.sort(Comparator.comparing(Widget::getWidgetOrder).reversed());
		return all;
	}


@GetMapping("/api/topic/{topicId}/widget")
public List<Widget> findAllWidgetsForTopic(
		@PathVariable("topicId") int topicId) {
	Optional<Topic> data = topicRepository.findById(topicId);
	if(data.isPresent()) {
		Topic topic = data.get();
		return topic.getWidgets();
		
	}
	return null;		
}

@GetMapping("/api/widget/{widgetId}")
public Widget findWidgetById(@PathVariable("widgetId") int widgetId) {
	Optional<Widget> data = widgetRepository.findById(widgetId);
	if(data.isPresent()) {
		return data.get();
	}
	Widget notPresent = new Widget();
	notPresent.setId(0);
	return notPresent;
}

@PostMapping("/api/topic/{topicId}/widget")
public Widget createWiget(
		@PathVariable("topicId") int topicId,
		@RequestBody Widget newWidget) {
	Optional<Topic> data = topicRepository.findById(topicId);
	
	if(data.isPresent()) {
		Topic topic = data.get();
		newWidget.setTopic(topic);
		return widgetRepository.save(newWidget);
	}
	return null;		
}




@DeleteMapping("/api/widget/{widgetId}")
public void deleteWidget(@PathVariable("widgetId") int widgetId)
{
	widgetRepository.deleteById(widgetId);
}

//
//@PutMapping("/api/user/{userId}")
//public User updateUser(@PathVariable("userId") int userId, @RequestBody User newUser) {
//	Optional<User> data = repository.findById(userId);
//	if(data.isPresent()) {
//		User user = data.get();
//		user.setUsername(newUser.getUsername());
//		user.setPassword(newUser.getPassword());
//		user.setEmail(newUser.getEmail());
//		user.setPhone(newUser.getPhone());
//		user.setFirstName(newUser.getFirstName());
//		user.setLastName(newUser.getLastName());
//		user.setRole(newUser.getRole());
//		user.setDateOfBirth(newUser.getDateOfBirth());
//		//System.out.println("\n.\n.\n.\n.\nUSER DOB:"+user.getDateOfBirth()+"\n.\n.\n.\n.");
//		repository.save(user);
//		return user;
//	}
//	return null;
//}
//
//@PutMapping("/api/register/{userId}")
//public User updateUserProfile(@PathVariable("userId") int userId, @RequestBody User newUser) {
//	Optional<User> data = repository.findById(userId);
//	if(data.isPresent()) {
//		User user = data.get();
//		user.setEmail(newUser.getEmail());
//		user.setPhone(newUser.getPhone());
//		user.setFirstName(newUser.getFirstName());
//		user.setLastName(newUser.getLastName());
//		user.setRole(newUser.getRole());
//		user.setDateOfBirth(newUser.getDateOfBirth());
//		//System.out.println("\n.\n.\n.\n.\nUSER DOB:"+user.getDateOfBirth()+"\n.\n.\n.\n.");
//		repository.save(user);
//		return user;
//	}
//	return null;
//}


}