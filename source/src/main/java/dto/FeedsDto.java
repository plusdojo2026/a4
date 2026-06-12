package dto;

import java.io.Serializable;
import java.time.LocalDate;

/*
 * Serializableについて
 * インスタンスをバイト列に変換（シリアライズ）可能にする
 * 主に、ネットワーク送信やセッション保存を可能にするために必要
 */

// フィールド
// ログイン処理の際は下3つを持ってく処理にする

public class FeedsDto implements Serializable{
	private int money_id;
	private int income;
	private int expense;
	private LocalDate date;
	
	//コンストラクタ
	public FeedsDto(int money_id, int income, int expense, LocalDate date) {
		super();
		this.money_id = money_id;
		this.income = income;
		this.expense = expense;
		this.date = date;
	} 
	
	//空のコンストラクタ
	public FeedsDto() {
	}
	
	
	//以下、getterとsetter
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
