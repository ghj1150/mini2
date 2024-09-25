package mini.comment;

public class Comment {
    private int idx;
    private String comment;
    private int user_id;
    private int post_idx;
    private int comment_parent_idx;
    private String create_date;
    // private int good; // 좋아요
    
    public Comment() {	

    }

    public Comment(int idx, String comment, int post_idx, int comment_parent_idx, int user_id, String create_date) {
        this.idx = idx;
        this.comment = comment;
        this.post_idx = post_idx;
        this.comment_parent_idx = comment_parent_idx;
        this.user_id = user_id;
        this.create_date = create_date;
    }

	public int getIdx() {
		return this.idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getPost_idx() {
		return this.post_idx;
	}

	public void setPost_idx(int post_idx) {
		this.post_idx = post_idx;
	}

	public int getComment_parent_idx() {
		return this.comment_parent_idx;
	}

	public void setComment_parent_idx(int comment_parent_idx) {
		this.comment_parent_idx = comment_parent_idx;
	}

	public int getUser_id() {
		return this.user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getCreate_date() {
		return this.create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

    public String toString() {
		return String.format("%4d %4s %6d %7d %7d %7s", idx, comment, user_id, post_idx, comment_parent_idx, create_date);
	}
}
