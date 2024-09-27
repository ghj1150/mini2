package post;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Post implements Serializable {
	private int idx; // id
	private int user_id; // 회원 id
	private String title; // 제목
	private String post; // 게시글
	private String create_date; // 날짜
//	private int like; // 좋아요
//	private int view; // 뷰

	public Post() {

	}

	public Post(int idx, int user_id, String title, String post, String create_date) {
		this.idx = idx;
		this.user_id = user_id;
		this.title = title;
		this.post = post;
		this.create_date = create_date;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
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

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public String toString() {
		return String.format("%4d %10s %10s", user_id, title, create_date);
	}

}
