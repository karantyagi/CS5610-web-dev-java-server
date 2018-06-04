package webdev.repositories;

import org.springframework.data.repository.CrudRepository;

import webdev.models.question.BaseExamQuestion;

public interface BaseExamQuestionRepository
	extends CrudRepository<BaseExamQuestion, Integer>{
}



