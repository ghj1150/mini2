package user;

public class User {
	// private id, pw, 이름, 생년월일 
	private String userIdx;
	private String userPw;
	private String name;
	private String birth;
	private int lostMoney;
	private int phoneNumber;
	//생성자로 
	public User(String id, String userPw, String name, String birth ) {
		super();
		this.userIdx = id;
		this.userPw = userPw;
		this.name = name;
		this.birth = birth;
		this.lostMoney = 0;
		this.phoneNumber = phoneNumber;
	}
	public String getUserId() {
		return userIdx;
	}
	public void setUserId(String userId) {
		this.userIdx = userId;
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
	@Override
	public String toString() {
		return "User [userId=" + userIdx + ", userPw=" + userPw + ", name=" + name + ", birth=" + birth + ", lostMoney="
				+ lostMoney + "]";
	}

}
