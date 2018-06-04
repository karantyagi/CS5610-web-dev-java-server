package webdev.services;

import java.util.ArrayList;
import java.util.Comparator;
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

import webdev.models.Exam;
import webdev.models.Topic;

import webdev.models.Widget;
import webdev.repositories.ExamRepository;
import webdev.repositories.TopicRepository;
import webdev.repositories.WidgetRepository;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ExamService {
	@Autowired
	ExamRepository examRepository;

	@Autowired
	TopicRepository topicRepository;

	@Autowired
	WidgetRepository widgetRepository;
	
	
	@GetMapping("/api/exam")
	public List<Exam> findAllExams()
	{
		List<Exam> all;
		all = (List<Exam>) examRepository.findAll();
		return all;
	}
	
	@GetMapping("/api/exam/{examId}")
	public Exam findExamById(@PathVariable("examId") int examId)
	{
		Optional<Exam> data = examRepository.findById(examId);
		if(data.isPresent()) {
			return data.get();
		}
		// alternate to returning null
		Exam empty = new Exam();
		empty.setTitle("NoExamFound");
		return empty;
	}

	@GetMapping("/api/topic/{topicId}/exam")
	public List<Widget> findAllExamsForTopic(
			@PathVariable("topicId") int topicId) {
		List<Widget> empty = new ArrayList<>();
		Optional<Topic> data = topicRepository.findById(topicId);
		if(data.isPresent()) {
			Topic topic = data.get();
			List<Widget> all = topic.getWidgets();
			
			// Filter for exams from widget - dtype;
			return all;
		}
		
		return empty;		
	}
	
	
	@DeleteMapping("/api/exam/{examId}")
	public void deleteExamById(@PathVariable("examId") int examId)
	{
//		widgetRepository.deleteById(examId);
		examRepository.deleteById(examId);
	}
	
	
	@PostMapping("/api/topic/{topicId}/exam")
	public Exam createExam(
			@PathVariable("topicId") int topicId,
			@RequestBody Exam exam) {
		Optional<Topic> data = topicRepository.findById(topicId);
		Topic topic = data.get();
			exam.setTopic(topic);
			return examRepository.save(exam);
	}

}