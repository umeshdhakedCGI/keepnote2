package com.stackroute.keepnote.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.swing.*;
import java.time.LocalDateTime;

/*
 * The class "Note" will be acting as the data model for the note Table in the database. Please
 * note that this class is annotated with @Entity annotation. Hibernate will scan all package for 
 * any Java objects annotated with the @Entity annotation. If it finds any, then it will begin the 
 * process of looking through that particular Java object to recreate it as a table in your database.
 */
@Entity
public class Note {

	@Id
	int Id;
	String Title;
	String Content;
	String Status;
	LocalDateTime CreatedAt;



	public Note() {

	}

	public Note(int i, String string, String string2, String string3, LocalDateTime localDate) {
this.Id=i;
this.Title=string;
this.Content=string2;
this.Status=string3;
this.CreatedAt=localDate;
	}

	public int getNoteId() {

		return Id;
	}

	public String getNoteTitle() {

		return Title;
	}

	public String getNoteContent() {

		return Content;
	}

	public String getNoteStatus() {

		return Status;
	}

	public void setNoteId(int parseInt) {
	this.Id=parseInt;
	}

	public void setNoteTitle(String parameter) {
		this.Title=parameter;

	}

	public void setNoteContent(String parameter) {
		this.Content=parameter;

	}

	public void setNoteStatus(String parameter) {
		this.Status=parameter;

	}

	public void setCreatedAt(LocalDateTime now) {
		this.CreatedAt=now;
	}

}
