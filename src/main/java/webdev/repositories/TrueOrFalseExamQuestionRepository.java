package webdev.repositories;

import org.springframework.data.repository.CrudRepository;
import webdev.models.question.TrueOrFalseExamQuestion;

public interface TrueOrFalseExamQuestionRepository
extends CrudRepository<TrueOrFalseExamQuestion, Integer>{
}

