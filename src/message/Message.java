package message;

public class Message {
	
	 private String userId; // 내 id (보낼사람)
	 private String otherId; // 상대 id (받는사람)
	 private String title; // 제목
	 private String contents; // 내용보기
	 private int msgNo; // 메세지 번호
	 private boolean check; // 메세지 읽음 안읽음
	 
	public Message() {

	}
	
	public Message(String userId, String otherId, String title, String contents) {
		this.userId = userId;
		this.otherId = otherId;
		this.title = title;
		this.contents = contents;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getOtherId() {
		return otherId;
	}
	public void setOtherId(String otherId) {
		this.otherId = otherId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getMsgNo() {
		return msgNo;
	}
	public void setMsgNo(int msgNo) {
		this.msgNo = msgNo;
	}
	public boolean isCheck() {
		return check;
	}
	public void setCheck(boolean check) {
		this.check = check;
	}
	@Override
	public String toString() {
		return "[보낸사람ID= " + userId + ",  받은사람ID= "+ otherId +  " , 제목=" + title + "]";
	}
	 
	 
}
