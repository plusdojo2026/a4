package dto;

import java.io.Serializable;
/*
 * Serializableについて
 * インスタンスをバイト列に変換（シリアライズ）可能にする
 * 主に、ネットワーク送信やセッション保存を可能にするために必要
 */

// フィールド
// ログイン処理の際は下3つを持ってく処理にする
public class Employees implements Serializable{
	private int id; //従業員ID
	private String name; //名前
	private int age; //年齢
	private int gender; //性別　0男1女2その他
	private String phone; //電話番号
	private String address; //住所
	private int adminstrater; //管理者フラグ
	private String login_id; //ログインID
	private String password; //パスワード
	
	//コンストラクタ
	public Employees(int id, String name, int age, int gender, String phone, String address, int adminstrater,
			String login_id, String password) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.phone = phone;
		this.address = address;
		this.adminstrater = adminstrater;
		this.login_id = login_id;
		this.password = password;
	}
	
	//以下、getterとsetter
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAdminstrater() {
		return adminstrater;
	}

	public void setAdminstrater(int adminstrater) {
		this.adminstrater = adminstrater;
	}

	public String getLogin_id() {
		return login_id;
	}

	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
