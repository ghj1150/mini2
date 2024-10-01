package user;

import java.io.Serializable;

public class User  implements Serializable{

	// private id, pw, 이름, 생년월일 
	private String userId;
	private String userPw;
	private String name;
	private String birth;
	private int lostMoney;
	private int phoneNumber;
	//생성자로 
	public User( String userId, String userPw, String name, String birth) {
		super();
	
		this.userId = userId;
		this.userPw = userPw;
		this.name = name;
		this.birth = birth;
		this.lostMoney = 0;
		this.phoneNumber = 0;
	}

	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public int getLostMoney() {
		return lostMoney;
	}
	public void setLostMoney(int lostMoney) {
		this.lostMoney = lostMoney;
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Override
	public String toString() {
		return "User [ userId=" + userId + ", userPw=" + userPw + ", name=" + name + ", birth=" + birth
				+ ", lostMoney=" + lostMoney + ", phoneNumber=" + phoneNumber + "]";
	}
	
}
