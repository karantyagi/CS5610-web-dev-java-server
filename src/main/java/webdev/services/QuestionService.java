package webdev.services;


import webdev.repositories.TopicRepository;
import webdev.repositories.WidgetRepository;
import webdev.models.Exam;
import webdev.models.Topic;
import webdev.models.question.BaseExamQuestion;
import webdev.models.question.FillInTheBlanksExamQuestion;
import webdev.models.question.MultipleChoiceExamQuestion;
import webdev.models.question.EssayExamQuestion;
import webdev.models.question.TrueOrFalseExamQuestion;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import webdev.repositories.TrueOrFalseExamQuestionRepository;
import webdev.repositories.BaseExamQuestionRepository;
import webdev.repositories.EssayExamQuestionRepository;
import webdev.repositories.ExamRepository;
import webdev.repositories.FillInTheBlanksExamQuestionRepository;
import webdev.repositories.MultipleChoiceExamQuestionRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class QuestionService {
	@Autowired
	MultipleChoiceExamQuestionRepository choiceRepo;
	
	@Autowired
	EssayExamQuestionRepository essayRepo;
	
	@Autowired
	TrueOrFalseExamQuestionRepository trueRepo;
	
	@Autowired
	FillInTheBlanksExamQuestionRepository fillRepo;
	
	@Autowired
	BaseExamQuestionRepository baseRepo;
	
	@Autowired
	TopicRepository topicRepository;

	@Autowired
	ExamRepository examRepository;
	
	@GetMapping("/api/exam/{examId}/choice")
	public MultipleChoiceExamQuestion createChoiceQue
	(@PathVariable("examId") int examId,
	@RequestBody MultipleChoiceExamQuestion que) 
	{
		Optional<Exam> data = examRepository.findById(examId);
		Exam exam = data.get();
		que.setExam(exam);
		return choiceRepo.save(que);
	}
	
	@GetMapping("/api/exam/{examId}/essay")
	public EssayExamQuestion createEssayQue
	(@PathVariable("examId") int examId,
	@RequestBody EssayExamQuestion que) 
	{
		Optional<Exam> data = examRepository.findById(examId);
		Exam exam = data.get();
		que.setExam(exam);
		return essayRepo.save(que);
	}

	
	@GetMapping("/api/exam/{examId}/truefalse")
	public TrueOrFalseExamQuestion createTrueFalseQue
	(@PathVariable("examId") int examId,
	@RequestBody TrueOrFalseExamQuestion que) 
	{
		Optional<Exam> data = examRepository.findById(examId);
		Exam exam = data.get();
		que.setExam(exam);
		return trueRepo.save(que);
	}
	
	@GetMapping("/api/exam/{examId}/blanks")
	public FillInTheBlanksExamQuestion createFillInBlanksQue
	(@PathVariable("examId") int examId,
	@RequestBody FillInTheBlanksExamQuestion que) 
	{
		Optional<Exam> data = examRepository.findById(examId);
		Exam exam = data.get();
		que.setExam(exam);
		return fillRepo.save(que);
	}

		
	
}