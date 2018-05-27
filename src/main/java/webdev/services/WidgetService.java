package webdev.services;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import webdev.models.Lesson;
import webdev.models.Topic;
import webdev.models.Widget;
import webdev.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class WidgetService {
	
	@Autowired
	WidgetRepository widgetRepository;
	
@GetMapping("/api/widget")
	public List<Widget> findAllWidgets()
	{
		return (List<Widget>) widgetRepository.findAll();
	}


//@PostMapping("/api/widget/save")
//public Widget saveWidget(@RequestBody Widget widget) {
//	return widgetRepository.save(widget);
//}

//@PostMapping(("/api/widget/")
//public Topic createTopic(
//		@PathVariable("lessonId") int lessonId,
//		@RequestBody Topic newTopic) {
//	Optional<Lesson> data = lessonRepository.findById(lessonId);
//	
//	if(data.isPresent()) {
//		Lesson lesson = data.get();
//		newTopic.setLesson(lesson);
//		return topicRepository.save(newTopic);
//	}
//	return null;		
//}


@DeleteMapping("/api/widget/{widgetId}")
public void deleteWidget(@PathVariable("widgetId") int widgetId)
{
	widgetRepository.deleteById(widgetId);
}


}