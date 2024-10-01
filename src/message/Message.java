package message;

import java.io.Serializable;

public class Message implements Serializable{
	
	 private int idx; // id
	 private String userId; // 내 id (보낼사람)
	 private String otherId; // 상대 id (받는사람)
	 private String title; // 제목
	 private String contents; // 내용
	 private String date; // 날짜
//	 private int msgNo; // 메세지 번호
	 private boolean check; // 메세지 읽음 안읽음

	 

	public Message(int idx, String userId, String otherId, String title, String contents, String date) {
		this.idx = idx;
		this.userId = userId;
		this.otherId = otherId;
		this.title = title;
		this.contents = contents;
		this.date = date;
		this.check = false;
	}
	
	
	
	public int getIdx() {
		return idx;
	}



	public void setIdx(int idx) {
		this.idx = idx;
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



	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}



	//	public int getMsgNo() {
//		return msgNo;
//	}
//	public void setMsgNo(int msgNo) {
//		this.msgNo = msgNo;
//	}
	public boolean isCheck() {
		return check;
	}
	public void setCheck(boolean check) {
		this.check = check;
	}
	@Override
	public String toString() {
		return "[ 보낸사람ID = " + userId + ",  받은사람ID = "+ otherId +  ", 제목 = " + title + ", 보낸날짜 = " + date +" ]" ;
	}
	 
	 
}
