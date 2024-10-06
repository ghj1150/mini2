package post;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Post implements Serializable {
	private int idx; // id
	private String userId; // 회원 id
	private String title; // 제목
	private String post; // 게시글
	private String createDate; // 날짜
//	private int like; // 좋아요
//	private int view; // 뷰

	public Post() {

	}

	public Post(int idx, String userId, String title, String post, String createDate) {
		this.idx = idx;
		this.userId = userId;
		this.title = title;
		this.post = post;
		this.createDate = createDate;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String toString() {
		return String.format(" %2d. %10s %9s %10s", idx, userId, title, createDate);
	}

}
