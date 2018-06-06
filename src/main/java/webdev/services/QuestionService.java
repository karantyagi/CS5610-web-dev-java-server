package webdev.services;


import webdev.repositories.TopicRepository;
import webdev.repositories.WidgetRepository;
import webdev.models.Exam;
import webdev.models.Lesson;
import webdev.models.Topic;
import webdev.models.question.BaseExamQuestion;
import webdev.models.question.FillInTheBlanksExamQuestion;
import webdev.models.question.MultipleChoiceExamQuestion;
import webdev.models.question.EssayExamQuestion;
import webdev.models.question.TrueOrFalseExamQuestion;

import java.util.ArrayList;
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
	
	
	@GetMapping("/api/question")
	public List<BaseExamQuestion> findAllQuestions()
	{
		List<BaseExamQuestion> all;
		all = (List<BaseExamQuestion>) baseRepo.findAll();
		return all;
	}
	
	@GetMapping("/api/question/{questionId}")
	public BaseExamQuestion findQuestionById(@PathVariable("questionId") int questionId)
	{
		Optional<BaseExamQuestion> data = baseRepo.findById(questionId);
		if(data.isPresent()) {
			return data.get();
		}
		// alternate to returning null
		BaseExamQuestion empty = new BaseExamQuestion();
		empty.setTitle("NoQuestionFound");
		return empty;
	}
	
	@PutMapping("/api/question/{questionId}/essay")
	public EssayExamQuestion updateEssayQuestion
	(@PathVariable("questionId") int questionId, @RequestBody EssayExamQuestion newQuestion) {
		Optional<EssayExamQuestion> data = essayRepo.findById(questionId);
		if(data.isPresent()) {
			EssayExamQuestion question = data.get();
			
			question.setTitle(newQuestion.getTitle());
			question.setPoints(newQuestion.getPoints());
			question.setDescription(newQuestion.getDescription());
			
			essayRepo.save(question);
			return question;
		}
		return null;
	}
	
	@PutMapping("/api/question/{questionId}/choice")
	public MultipleChoiceExamQuestion updateMultipleChoiceQuestion
	(@PathVariable("questionId") int questionId, @RequestBody MultipleChoiceExamQuestion newQuestion) {
		Optional<MultipleChoiceExamQuestion> data = choiceRepo.findById(questionId);
		if(data.isPresent()) {
			MultipleChoiceExamQuestion question = data.get();
			
			question.setTitle(newQuestion.getTitle());
			question.setPoints(newQuestion.getPoints());
			question.setDescription(newQuestion.getDescription());
			question.setCorrectChoice(newQuestion.getCorrectChoice());
			question.setChoiceA(newQuestion.getChoiceA());
			question.setChoiceB(newQuestion.getChoiceB());
			question.setChoiceC(newQuestion.getChoiceC());
			question.setChoiceD(newQuestion.getChoiceD());
			
			choiceRepo.save(question);
			return question;
		}
		return null;
	}
	
	@PutMapping("/api/question/{questionId}/fb")
	public FillInTheBlanksExamQuestion updateFillBLankQuestion
	(@PathVariable("questionId") int questionId, @RequestBody FillInTheBlanksExamQuestion newQuestion) {
		Optional<FillInTheBlanksExamQuestion> data = fillRepo.findById(questionId);
		if(data.isPresent()) {
			FillInTheBlanksExamQuestion question = data.get();
			
			question.setTitle(newQuestion.getTitle());
			question.setPoints(newQuestion.getPoints());
			question.setDescription(newQuestion.getDescription());
			question.setBlank(newQuestion.getBlank());
			fillRepo.save(question);
			return question;
		}
		return null;
	}
	
	@PutMapping("/api/question/{questionId}/tf")
	public TrueOrFalseExamQuestion updateTrueFalseQuestion
	(@PathVariable("questionId") int questionId, @RequestBody TrueOrFalseExamQuestion newQuestion) {
		Optional<TrueOrFalseExamQuestion> data = trueRepo.findById(questionId);
		if(data.isPresent()) {
			TrueOrFalseExamQuestion question = data.get();
			
			question.setTitle(newQuestion.getTitle());
			question.setPoints(newQuestion.getPoints());
			question.setDescription(newQuestion.getDescription());
			question.setIsTrue(newQuestion.getIsTrue());
			
			trueRepo.save(question);
			return question;
		}
		return null;
	}
	
	@GetMapping("/api/question/choice")
	public List<MultipleChoiceExamQuestion> findAllChoiceQuestions()
	{
		List<MultipleChoiceExamQuestion> all;
		all = (List<MultipleChoiceExamQuestion>) choiceRepo.findAll();
		return all;
	}
	
	@GetMapping("/api/question/fb")
	public List<FillInTheBlanksExamQuestion> findAllFillInBlankQuestions()
	{
		List<FillInTheBlanksExamQuestion> all;
		all = (List<FillInTheBlanksExamQuestion>) fillRepo.findAll();
		return all;
	}
	
	@GetMapping("/api/question/tf")
	public List<TrueOrFalseExamQuestion> findAlltrueFalseQuestions()
	{
		List<TrueOrFalseExamQuestion> all;
		all = (List<TrueOrFalseExamQuestion>) trueRepo.findAll();
		return all;
	}
	
	@GetMapping("/api/question/essay")
	public List<EssayExamQuestion> findAllEssayQuestions()
	{
		List<EssayExamQuestion> all;
		all = (List<EssayExamQuestion>) essayRepo.findAll();
		return all;
	}
	
	@GetMapping("/api/exam/{examId}/question")
	public List<BaseExamQuestion> findAllQuestionsForExam(
			@PathVariable("examId") int examId) {
		Optional<Exam> data = examRepository.findById(examId);
		List<BaseExamQuestion> empty = new ArrayList<>();
		if(data.isPresent()) {
			Exam exam = data.get();
			return exam.getQuestions();
		}
		return empty;		
	}
	
	
	
	@PostMapping("/api/exam/{examId}/choice")
	public MultipleChoiceExamQuestion createChoiceQue
	(@PathVariable("examId") int examId,
	@RequestBody MultipleChoiceExamQuestion que) 
	{
		Optional<Exam> data = examRepository.findById(examId);
		Exam exam = data.get();
		que.setExam(exam);
		return choiceRepo.save(que);
	}
	
	@PostMapping("/api/exam/{examId}/essay")
	public EssayExamQuestion createEssayQue
	(@PathVariable("examId") int examId,
	@RequestBody EssayExamQuestion que) 
	{
		Optional<Exam> data = examRepository.findById(examId);
		Exam exam = data.get();
		que.setExam(exam);
		return essayRepo.save(que);
	}

	
	@PostMapping("/api/exam/{examId}/truefalse")
	public TrueOrFalseExamQuestion createTrueFalseQue
	(@PathVariable("examId") int examId,
	@RequestBody TrueOrFalseExamQuestion que) 
	{
		Optional<Exam> data = examRepository.findById(examId);
		Exam exam = data.get();
		que.setExam(exam);
		return trueRepo.save(que);
	}
	
	@PostMapping("/api/exam/{examId}/blanks")
	public FillInTheBlanksExamQuestion createFillInBlanksQue
	(@PathVariable("examId") int examId,
	@RequestBody FillInTheBlanksExamQuestion que) 
	{
		Optional<Exam> data = examRepository.findById(examId);
		Exam exam = data.get();
		que.setExam(exam);
		return fillRepo.save(que);
	}
	
	
	@DeleteMapping("/api/question/{questionId}")
	public void deleteQuestionById(@PathVariable("questionId") int qId)
	{
//		widgetRepository.deleteById(qId);
		baseRepo.deleteById(qId);
	}
	
	

		
	
}