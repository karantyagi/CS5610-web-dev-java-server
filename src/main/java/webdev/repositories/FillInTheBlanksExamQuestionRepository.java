package webdev.repositories;
	
import org.springframework.data.repository.CrudRepository;
import webdev.models.question.FillInTheBlanksExamQuestion;

public interface FillInTheBlanksExamQuestionRepository
	extends CrudRepository<FillInTheBlanksExamQuestion, Integer>{
}

