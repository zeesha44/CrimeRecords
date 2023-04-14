package com.crimerecord.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "criminals")
public class Criminals {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	Integer id;
	
	String firstName;
	
	String lastName;
	
	String offence;
	
	//@Column()
	Integer sentence;
	
	String state;
	
	String status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getOffence() {
		return offence;
	}

	public void setOffence(String offence) {
		this.offence = offence;
	}

	public Integer getSentence() {
		return sentence;
	}

	public void setSentence(Integer sentence) {
		this.sentence = sentence;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Criminals() {
	}

	public Criminals(String firstName, String lastName, String offence, Integer sentence, String state, String status) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.offence = offence;
		this.sentence = sentence;
		this.state = state;
		this.status = status;
	}

	@Override
	public String toString() {
		return "Criminals [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", offence=" + offence
				+ ", sentence=" + sentence + ", state=" + state + ", status=" + status + "]";
	}
	
	

}
