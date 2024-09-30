package message;

import java.io.Serializable;

public class Message implements Serializable{
	
	 private int idx; // id
	 private int userIdx; // 내 id (보낼사람)
	 private int otherIdx; // 상대 id (받는사람)
	 private String title; // 제목
	 private String contents; // 내용
	 private String date; // 날짜
//	 private int msgNo; // 메세지 번호
	 private boolean check; // 메세지 읽음 안읽음
//	 
//	public Message() {
//
//	}
	
	public Message(int idx, int otherIdx, String title, String contents, String date) {
		this.idx = idx;
		this.otherIdx = otherIdx;
		this.title = title;
		this.contents = contents;
		this.date = date;
	}
	
	
	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getUserIdx() {
		return userIdx;
	}
	public void setUserIdx(int userIdx) {
		this.userIdx = userIdx;
	}
	
	public int getOtherIdx() {
		return otherIdx;
	}
	public void setOtherIdx(int otherIdx) {
		this.otherIdx = otherIdx;
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
		return "[ 보낸사람ID = " + idx + ",  받은사람ID = "+ otherIdx +  ", 제목 = " + title + ", 날짜 = " + date+" ]" ;
	}
	 
	 
}
