package webdev.models.question;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "JOINED_FILL_IN_THE_BLANK_QUESTION")
public class FillInTheBlanksExamQuestion
	extends BaseExamQuestion {

	private String blank;

	public String getBlank() {
		return blank;
	}

	public void setBlank(String blank) {
		this.blank = blank;
	}
	
	
}