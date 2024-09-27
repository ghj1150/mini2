package message;

public class Message {
	 private String id;
	 private String title;
	 private String contents;
	 private int msgNo;
	 private boolean check;
	 
	public Message() {

	}
	public Message(String id, String title, String contents) {
		this.id = id;
		this.title = title;
		this.contents = contents;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
		return "[보낸회원=" + id + ", 제목=" + title + "]";
	}
	 
	 
}
