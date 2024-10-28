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
	private String amount;
	private String years;
	private String interest;
	private Double monthlyRate;

	@Inject
	FacesContext ctx;

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getYears() {
		return years;
	}

	public void setYears(String years) {
		this.years = years;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public Double getMonthlyRate() {
		return monthlyRate;
	}


	public boolean calcRate() {
		try {
			double amount = Double.parseDouble(this.amount);
			double years = Double.parseDouble(this.years);
			double interest = Double.parseDouble(this.interest);

			monthlyRate = (amount + (amount * interest/100)) / (years * 12);

			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie", null));
			return true;
		} catch (Exception e) {
			ctx.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd podczas przetwarzania parametrów", null));
			return false;
		}
	}

	public String calc() {
		if (calcRate()) {
			return "showresult";
		}
		return null;
	}

	public String calc_AJAX() {
		if (calcRate()) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Miesięczna rata: " + monthlyRate, null));
		}
		return null;
	}
}
