package com.jsfcourse.calc;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@Named
@RequestScoped
public class CalcCredBB {
	private Integer years;
	private Double amount;
	private Double interest;
	private Double monthlyRate;

	@Inject
	FacesContext ctx;

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getYears() {
		return years;
	}

	public void setYears(Integer years) {
		this.years = years;
	}

	public Double getInterest() {
		return interest;
	}

	public void setInterest(Double interest) {
		this.interest = interest;
	}

	public Double getMonthlyRate() {
		return monthlyRate;
	}


	public void calcRate() {
		monthlyRate = (amount + (amount * interest/100)) / (years * 12);
		monthlyRate = (double) Math.round(monthlyRate * 100)/100;
		
	
		ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie", null));
	}

	public String calc() {
		calcRate();
		return "showresult";
	}

	public String calc_AJAX() {
		calcRate();
		ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Miesięczna rata: " + monthlyRate + " zł", null));	
		return null;
	}
}
