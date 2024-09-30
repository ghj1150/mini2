package comment;

import java.io.Serializable;


public class Comment implements Serializable{
    private int idx;
    private String comment;
    private int postIdx;
    private int parentIdx;
	private int userId;
    private String createDate;
    // private int good; // 좋아요 클래스로 다시 만든다.
    
    public Comment(int idx, String comment, int postIdx, int parentIdx, int userId, String createDate) {
        this.idx = idx;
        this.comment = comment;
        this.postIdx = postIdx;
        this.parentIdx = parentIdx;
        this.userId = userId;
        this.createDate = createDate;
    }

    public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getPostIdx() {
		return postIdx;
	}

	public void setPostIdx(int postIdx) {
		this.postIdx = postIdx;
	}

	public int getparentIdx() {
		return parentIdx;
	}

	public void setparentIdx(int parentIdx) {
		this.parentIdx = parentIdx;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String toString() {
		return String.format("%4d %4s %6d %7d %7d %7s\n", idx, comment, postIdx, parentIdx, userId, createDate);
	}
}
