package user;

public class User {
	// private id, pw, 이름, 생년월일 
	private String userId;
	private String userPw;
	private String name;
	private String birth;
	//생성자로 
	public User(String id, String userPw, String name, String birth) {
		super();
		this.userId = id;
		this.userPw = userPw;
		this.name = name;
		this.birth = birth;
	}
	public String getId() {
		return userId;
	}
	public void setId(String id) {
		this.userId = id;
	}
	public String getPw() {
		return userPw;
	}
	public void setPw(String pw) {
		this.userPw = pw;
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
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userPw=" + userPw + ", name=" + name + ", birth=" + birth + "]";
	}
	
	
}
