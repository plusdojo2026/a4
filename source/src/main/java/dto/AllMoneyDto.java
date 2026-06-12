package dto;

import java.io.Serializable;
import java.time.LocalDate;

// フィールド
public class AllMoneyDto implements Serializable{
	private int money_id;
	private int income;
	private int expense;
	private LocalDate date;
	
	// 空コンストラクタ
	public AllMoneyDto() {
		
	}
	
	// コンストラクタ
	public AllMoneyDto(int money_id, int income, int expense, LocalDate date) {
		super();
		this.money_id = money_id;
		this.income = income;
		this.expense = expense;
		this.date = date;
	}
	
	// getterとsetter
	public int getMoney_id() {
		return money_id;
	}

	public void setMoney_id(int money_id) {
		this.money_id = money_id;
	}

	public int getIncome() {
		return income;
	}

	public void setIncome(int income) {
		this.income = income;
	}

	public int getExpense() {
		return expense;
	}

	public void setExpense(int expense) {
		this.expense = expense;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	
}

