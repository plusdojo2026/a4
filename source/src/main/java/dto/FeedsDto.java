package dto;

import java.io.Serializable;
import java.sql.Date;

/*
 * Serializableについて
 * インスタンスをバイト列に変換（シリアライズ）可能にする
 * 主に、ネットワーク送信やセッション保存を可能にするために必要
 */

// フィールド
// ログイン処理の際は下3つを持ってく処理にする

public class FeedsDto implements Serializable{
	private int feed_id;
	private Date date;
	private int increase_amount;
	private int decrease_amount;
	
	//コンストラクタ
	public FeedsDto(int feed_id, Date date, int increase_amount, int decrease_amount) {
		super();
		this.feed_id = feed_id;
		this.date = date;
		this.increase_amount = increase_amount;
		this.decrease_amount = decrease_amount;
	}
	
	//空のコンストラクタ
	public FeedsDto() {
	}

	//以下、getterとsetter
	public int getFeed_id() {
		return feed_id;
	}

	public void setFeed_id(int feed_id) {
		this.feed_id = feed_id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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

