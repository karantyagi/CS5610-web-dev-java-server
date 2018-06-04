package webdev.models.question;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "JOINED_ESSAY_QUESTION")
public class EssayExamQuestion
	extends BaseExamQuestion {
	@Column(name = "ANSWER")
	private String answer;

	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
}
