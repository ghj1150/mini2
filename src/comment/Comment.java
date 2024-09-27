package comment;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Comment implements Serializable{
    private int idx;
    private String comment;
    private int userId;
    private int postIdx;
    private int commentParentIdx;
    private String createDate;
    // private int good; // 좋아요
    
    public Comment() {	

    }

    public Comment(int idx, String comment, int postIdx, int commentParentIdx, int userId, String createDate) {
        this.idx = idx;
        this.comment = comment;
        this.postIdx = postIdx;
        this.commentParentIdx = commentParentIdx;
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

	public int getCommentParentIdx() {
		return commentParentIdx;
	}

	public void setCommentParentIdx(int commentParentIdx) {
		this.commentParentIdx = commentParentIdx;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String toString() {
		return String.format("%4d %4s %6d %7d %7d %7s\n", idx, comment, userId, postIdx, commentParentIdx, createDate);
	}
}
