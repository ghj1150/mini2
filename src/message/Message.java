package message;

public class Message {
	 private int id;
	 private String title;
	 private String contents;
	 private boolean check;
	 
	public Message() {
	}
	public Message(int id, String title, String contents) {
		this.id = id;
		this.title = title;
		this.contents = contents;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public boolean isCheck() {
		return check;
	}
	public void setCheck(boolean check) {
		this.check = check;
	}
	 
	 
}
