package dto;

import java.io.Serializable;
/*
 * Serializableについて
 * インスタンスをバイト列に変換（シリアライズ）可能にする
 * 主に、ネットワーク送信やセッション保存を可能にするために必要
 */

// フィールド
// ログイン処理の際は下3つを持ってく処理にする
public class EmployeesDto implements Serializable{
	private int id; //従業員ID
	private String name; //名前
	private int age; //年齢
	private int gender; //性別　0男1女2その他
	private String phone; //電話番号
	private String address; //住所
	private String admin; //管理者フラグ
	private String login_id; //ログインID
	private String password; //パスワード
	private String strGender; // Stringのgender
	
	// コンストラクタ
	public EmployeesDto(int id, String name, int age, int gender, String phone, String address, String admin,
			String login_id, String password) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.phone = phone;
		this.address = address;
		this.admin = admin;
		this.login_id = login_id;
		this.password = password;
	}
	// 空コンストラクタ
	public EmployeesDto() {
    }
	// login用１
	public EmployeesDto(String login_id,String password) {
		this.login_id = login_id;
		this.password = password;
	}
	// login用2 String,Stringで被っちゃうのでint num 追加
	public EmployeesDto(String name,String admin,int num) {
		this.name = name;
		this.admin = admin;
	}
	// regist用　
	public EmployeesDto(int id, String name, int age, int gender, String phone, String address) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.phone = phone;
		this.address = address;
	}
	// List用
	public EmployeesDto(int id, String name, int gender, String phone, String address) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.phone = phone;
		this.address = address;
	}
	
	//以下、getterとsetter
	public String getStrGender() {
		return strGender;
	}

	public void setStrGender(String strGender) {
		this.strGender = strGender;
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

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
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
