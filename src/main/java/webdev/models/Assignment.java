package webdev.models;

import javax.persistence.Entity;



@Entity
public class Assignment extends Widget {
	
	private String title;
	private String description;
	private int points;
	private String essayAnswer;
	private String filename;
	private String link;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public String getEssayAnswer() {
		return essayAnswer;
	}
	public void setEssayAnswer(String essayAnswer) {
		this.essayAnswer = essayAnswer;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
}