package dto;

import java.io.Serializable;

public class ShiftDto implements Serializable{
	private int id; //従業員ID
	private int intime; //入る時間
	private String date; //日付
	
	public ShiftDto(int id, int intime, String date) {
		super();
		this.id = id;
		this.intime = intime;
		this.date = date;
	}

	//空のコンストラクタ
	public ShiftDto() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIntime() {
		return intime;
	}

	public void setIntime(int intime) {
		this.intime = intime;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}
