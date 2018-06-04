package webdev.models.question;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "JOINED_MULTIPLE_CHOICE_QUESTION")
public class MultipleChoiceExamQuestion
	extends BaseExamQuestion {
	@Column(name = "CORRECT_CHOICE", nullable = false)
	private String correctChoice;
	
	private String choiceA;
	private String choiceB;
	private String choiceC;
	private String choiceD;
	
	public String getCorrectChoice() {
		return correctChoice;
	}
	public void setCorrectChoice(String correctChoice) {
		this.correctChoice = correctChoice;
	}
	public String getChoiceA() {
		return choiceA;
	}
	public void setChoiceA(String choiceA) {
		this.choiceA = choiceA;
	}
	public String getChoiceB() {
		return choiceB;
	}
	public void setChoiceB(String choiceB) {
		this.choiceB = choiceB;
	}
	public String getChoiceC() {
		return choiceC;
	}
	public void setChoiceC(String choiceC) {
		this.choiceC = choiceC;
	}
	public String getChoiceD() {
		return choiceD;
	}
	public void setChoiceD(String choiceD) {
		this.choiceD = choiceD;
	}
		
}
