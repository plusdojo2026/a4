package dto;

import java.io.Serializable;
import java.sql.Date; //java.sql.Dateを使う

// フィールド
public class AllMoneyDto implements Serializable{
	private int money_id;
	private int income;
	private int expense;
	private Date date;
	private String reason; 
	
	// 空コンストラクタ
	public AllMoneyDto() {
	}
	
	// コンストラクタ
	public AllMoneyDto(int money_id, int income, int expense, Date date, String reason) {
		super();
		this.money_id = money_id;
		this.income = income;
		this.expense = expense;
		this.date = date;
		this.reason = reason;
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

	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
	
}

