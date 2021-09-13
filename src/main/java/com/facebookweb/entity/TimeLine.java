package com.facebookweb.entity;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TimeLine {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) //this line is used to create value of primarykey automatically as my sql auto_increment
	private long id;
	private String message;
	private String sender;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	
}
