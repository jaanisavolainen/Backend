package com.example.kyselyBack.domain;



import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;


import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonBackReference;



@Entity
public class UserAnswer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long userAnswerId;
	
	private String textAnswer;
	private int type;
	

	private int refAnswerId;
	private String refQuestionString;
	

	@JsonBackReference
	@ManyToOne
	private Question refAnswerQuestion;
	
	@OneToMany(mappedBy = "answerId")
	private List<AnswerSet> answerSet;
	

	
	public UserAnswer() {
		super();
	}

	public UserAnswer(String textAnswer, int type,  Question refAnswerQuestion) {
		super();
		this.textAnswer = textAnswer;
		this.type = type;
		this.refAnswerQuestion = refAnswerQuestion;
	}

	public Long getUserAnswerId() {
		return userAnswerId;
	}

	public void setUserAnswerId(Long userAnswerId) {
		this.userAnswerId = userAnswerId;
	}

	public String getTextAnswer() {
		return textAnswer;
	}

	public void setTextAnswer(String textAnswer) {
		this.textAnswer = textAnswer;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Question getRefAnswerQuestion() {
		return refAnswerQuestion;
	}
	
	

	public void setRefAnswerQuestion(Question refAnswerQuestion) {
		this.refAnswerQuestion = refAnswerQuestion;
	}
	

	public Long getRefQuestionId() {
		return refAnswerQuestion.getId();
	}

	public String getRefQuestionString() {
		return refAnswerQuestion.getQuestion();
	}

	public void setRefQuestionString(String refQuestionString) {
		this.refQuestionString = refQuestionString;
	}


}
