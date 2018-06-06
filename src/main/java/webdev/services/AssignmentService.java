package webdev.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import webdev.models.Assignment;
import webdev.models.Topic;

import webdev.models.Widget;
import webdev.repositories.AssignmentRepository;
import webdev.repositories.TopicRepository;
import webdev.repositories.WidgetRepository;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AssignmentService {
	@Autowired
	AssignmentRepository assignmentRepo;

	@Autowired
	TopicRepository topicRepository;

	@Autowired
	WidgetRepository widgetRepository;
	
	
	@GetMapping("/api/assignment")
	public List<Assignment> findAllAssignments()
	{
		List<Assignment> all;
		all = (List<Assignment>) assignmentRepo.findAll();
		return all;
	}
	
	@GetMapping("/api/assignment/{assignmentId}")
	public Assignment findAssignmentById(@PathVariable("assignmentId") int aId)
	{
		Optional<Assignment> data = assignmentRepo.findById(aId);
		if(data.isPresent()) {
			return data.get();
		}
		// alternate to returning null
		Assignment empty = new Assignment();
		empty.setTitle("NoExamFound ERROR");
		return empty;
	}

	@GetMapping("/api/topic/{topicId}/assignment")
	public List<Widget> findAllAssignmentsForTopic(
			@PathVariable("topicId") int topicId) {
		List<Widget> empty = new ArrayList<>();
		Optional<Topic> data = topicRepository.findById(topicId);
		if(data.isPresent()) {
			Topic topic = data.get();
			List<Widget> all = topic.getWidgets();
			// Filtering for assignments from widgets
			all = all.stream()
				    .filter(w -> w.getWidgetType().equals("Assignment")).collect(Collectors.toList());
			return all;
		}
		
		return empty;		
	}
	
	
	@DeleteMapping("/api/assignment/{assignmentId}")
	public void deleteExamById(@PathVariable("assignmentId") int aId)
	{
//		widgetRepository.deleteById(aId);
		assignmentRepo.deleteById(aId);
	}
	
	
	@PostMapping("/api/topic/{topicId}/assignment")
	public Assignment createAssignment(
			@PathVariable("topicId") int topicId,
			@RequestBody Assignment assignment) {
		Optional<Topic> data = topicRepository.findById(topicId);
		Topic topic = data.get();
		assignment.setTopic(topic);
			return assignmentRepo.save(assignment);
	}
	
	@PutMapping("/api/assignment/{aId}")
	public Assignment updateAssignment(@PathVariable("aId") int aId, @RequestBody Assignment newAssignment) {
		Optional<Assignment> data = assignmentRepo.findById(aId);
		if(data.isPresent()) {
			Assignment assignment = data.get();
			assignment.setTitle(newAssignment.getTitle());
			assignment.setDescription(newAssignment.getDescription());
			assignment.setPoints(newAssignment.getPoints());
			assignment.setWidgetOrder(newAssignment.getWidgetOrder());
			assignment.setWidgetType(newAssignment.getWidgetType());
			assignmentRepo.save(assignment);
			return assignment;
		}
		return null;
	}

}