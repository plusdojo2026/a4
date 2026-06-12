package dto;

import java.time.LocalDate;

// フィールド
public class AllMoneyDto {
	private int feed_id;
	private LocalDate date;
	private int increase_amount;
	private int decrease_amount;
	
	// 空コンストラクタ
	public AllMoneyDto() {
	}
	
	// コンストラクタ
	public AllMoneyDto(int feed_id, LocalDate date, int increase_amount, int decrease_amount) {
		super();
		this.feed_id = feed_id;
		this.date = date;
		this.increase_amount = increase_amount;
		this.decrease_amount = decrease_amount;
	}

	// 以下、getterとsetter
	public int getFeed_id() {
		return feed_id;
	}


	public void setFeed_id(int feed_id) {
		this.feed_id = feed_id;
	}


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}


	public int getIncrease_amount() {
		return increase_amount;
	}


	public void setIncrease_amount(int increase_amount) {
		this.increase_amount = increase_amount;
	}


	public int getDecrease_amount() {
		return decrease_amount;
	}


	public void setDecrease_amount(int decrease_amount) {
		this.decrease_amount = decrease_amount;
	}
	
	
}

