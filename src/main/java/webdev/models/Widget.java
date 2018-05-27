package webdev.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Widget {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	 @ManyToOne
	  @JsonIgnore
	  private Topic topic;
	  
	private String text;
	private String name; 
	private String className;
	private String style;
	private String width;
	private String height;
	private String order;
 
	 
	 	  
}
