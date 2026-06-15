package dto;

import java.io.Serializable;
import java.math.BigDecimal;

/*
 * Serializableについて
 * インスタンスをバイト列に変換（シリアライズ）可能にする
 * 主に、ネットワーク送信やセッション保存を可能にするために必要
 */

public class CowsDto implements Serializable{
	private int id; //牛のID
	private String name; //名前
	private int gender; //性別 
	private String birth_day; //生年月日
	private String status; //生死
	private String photo;//写真
	private String updatedate;//変更日
	private String cause;//原因 
	private String regist_day;//登録日
	
	private BigDecimal weight;//体重
	private int milkquality;//乳の質
	private BigDecimal bacterialCount;//細菌数
	private BigDecimal milk_fat_content;//乳脂肪分
	private int somatic_cell_count;//体細胞数
	private String day;//日付
	private String temperature;//体温
	
	public CowsDto() {
		
	}
	
	public CowsDto(int id, String name, int gender, String birth_day, String status, String photo, String updatedate,
			String cause, String regist_day, BigDecimal weight, int milkquality, BigDecimal bacterialCount,
			BigDecimal milk_fat_content, int somatic_cell_count, String day, String temperature, int appetite,
			int drinking, int manure, int health) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.birth_day = birth_day;
		this.status = status;
		this.photo = photo;
		this.updatedate = updatedate;
		this.cause = cause;
		this.regist_day = regist_day;
		this.weight = weight;
		this.milkquality = milkquality;
		this.bacterialCount = bacterialCount;
		this.milk_fat_content = milk_fat_content;
		this.somatic_cell_count = somatic_cell_count;
		this.day = day;
		this.temperature = temperature;
		this.appetite = appetite;
		this.drinking = drinking;
		this.manure = manure;
		this.health = health;
	}
	private int appetite;//食欲
	private int drinking;//飲水量
	private int manure;//排便の様子
	private int health; //健康状態
	
	//CowsDailyDaoで使うコンストラクタ
	public CowsDto(int id, String day, String temperature, int appetite, int drinking, int manure, int health) {
		super();
		this.id = id;
		this.day = day;
		this.temperature = temperature;
		this.appetite = appetite;
		this.drinking = drinking;
		this.manure = manure;
		this.health = health;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getBirth_day() {
		return birth_day;
	}
	public void setBirth_day(String birth_day) {
		this.birth_day = birth_day;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
	public String getRegist_day() {
		return regist_day;
	}
	public void setRegist_day(String regist_day) {
		this.regist_day = regist_day;
	}
	public BigDecimal getWeight() {
		return weight;
	}
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	public int getMilkquality() {
		return milkquality;
	}
	public void setMilkquality(int milkquality) {
		this.milkquality = milkquality;
	}
	public BigDecimal getBacterialCount() {
		return bacterialCount;
	}
	public void setBacterialCount(BigDecimal bacterialCount) {
		this.bacterialCount = bacterialCount;
	}
	public BigDecimal getMilk_fat_content() {
		return milk_fat_content;
	}
	public void setMilk_fat_content(BigDecimal milk_fat_content) {
		this.milk_fat_content = milk_fat_content;
	}
	public int getSomatic_cell_count() {
		return somatic_cell_count;
	}
	public void setSomatic_cell_count(int somatic_cell_count) {
		this.somatic_cell_count = somatic_cell_count;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public int getAppetite() {
		return appetite;
	}
	public void setAppetite(int appetite) {
		this.appetite = appetite;
	}
	public int getDrinking() {
		return drinking;
	}
	public void setDrinking(int drinking) {
		this.drinking = drinking;
	}
	public int getManure() {
		return manure;
	}
	public void setManure(int manure) {
		this.manure = manure;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	
	
	
}
	
	
	

