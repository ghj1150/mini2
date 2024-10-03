package user;

import java.io.Serializable;

public class User  implements Serializable{

	// private id, pw, 이름, 생년월일 
	private String userId;
	private String userPw;
	private String name;
	private String birth;
	private String lostDay; 
	private int lostMoney;
	private int fixIncome;
	private String fixDay;
	
	
	public User(String userId, String userPw, String name, String birth) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.name = name;
		this.birth = birth;
		this.lostDay = "";
		this.lostMoney = 0;
		this.fixIncome = 0;
		this.fixDay = "";
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
	public String getLostDay() {
		return lostDay;
	}
	public void setLostDay(String lostDay) {
		this.lostDay = lostDay;
	}
	public int getLostMoney() {
		return lostMoney;
	}
	public void setLostMoney(int lostMoney) {
		this.lostMoney = lostMoney;
	}
	public int getFixIncome() {
		return fixIncome;
	}
	public void setFixIncome(int fixIncome) {
		this.fixIncome = fixIncome;
	}
	public String getFixDay() {
		return fixDay;
	}
	public void setFixDay(String fixDay) {
		this.fixDay = fixDay;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userPw=" + userPw + ", name=" + name + ", birth=" + birth + ", lostDay="
				+ lostDay + ", lostMoney=" + lostMoney + ", fixIncome=" + fixIncome + ", fixDay=" + fixDay + "]\n";
	}
	
	
}
