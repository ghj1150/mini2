package post;

import java.util.ArrayList;
import java.util.List;
import mini.miniUtils;

public class PostService {
	private List<Post> posts = new ArrayList<Post>();
	private Post choicePost;
//	public int userId = 1;

	{
		posts = miniUtils.dataLoad("./src/data/post.ser");

		if (posts == null) {
			posts = new ArrayList<Post>();
			posts.add(new Post(1, 1, "제목", "히히히", "09/26"));
			posts.add(new Post(2, 2, "제목", "호호호", "09/26"));
			posts.add(new Post(3, 3, "제목", "허허허", "09/26"));
			posts.add(new Post(4, 4, "제목", "후후후", "09/26"));

			System.out.println("기본 데이터 추가");
		}
//		System.out.println(posts);

		miniUtils.dataSave("./src/data/post.ser", posts);

	}

	public void postMenu() {

		while (true) {

			int input = miniUtils.next("1.목록보기 2. 글쓰기 3. 종료", Integer.class, n -> n > 0 && n <= 3, "1~3사이의 값을 입력하세요");

			switch (input) {
			case 1:
				list();
				break;
			case 2:
				add();
				break;
			case 3:
				System.out.println("종료");
				return;
			default:
				break;

			}
		}
	}

	// 글쓰기
	public void add() {
//		System.out.println(posts);

		int idx = miniUtils.next("id", Integer.class, n -> n > 0, "");
		int userId = miniUtils.next("회원 id", Integer.class, n -> n > 0, "");
		String title = miniUtils.next("제목", String.class);
		String post = miniUtils.next("게시글", String.class);
		String create_date = miniUtils.next("날짜", String.class);

		posts.add(new Post(idx, userId, title, post, create_date));
	}

	// 목록보기

	public void list() {

		System.out.println("                게시판 목록                   ");
		System.out.println("==============================================");
		for (int i = 0; i < posts.size(); i++) {
			System.out.println(posts.get(i));
		}

//		int t = miniUtils.next("몇번?", Integer.class);
//
//		Post post = null;
//		for (int i = 0; i < posts.size(); i++) {
//			if (posts.get(i).getIdx() == t) {
//				post = posts.get(i);
//			}
//		}
//		if (post == null) {
//			System.out.println("게시글이 없습니다");
//			return;
//		}
//
		choicePost = findByIdx(miniUtils.next("아이디?", Integer.class, n -> findByIdx(n) != null, "게시글이 없습니다"));

		readpost(choicePost);
	}

	// 게시글 내용
	public void readpost(Post post) {

		System.out.println("==============================================");
		System.out.println("                제목 : " + post.getTitle() + "                   ");
		System.out.println("==============================================");
		System.out.println();
		System.out.println("내용 : " + post.getPost() + "\n");

		System.out.println("                               날짜 : " + post.getCreateDate());
		System.out.println("---------------------------------------------");

		// 종료 버튼
		// 수정 삭제는 post를 작성한 user만 수정 삭제 가능하게

		while (true) {
			int input = miniUtils.next("1.수정하기 2. 삭제하기 3. 종료", Integer.class, n -> n > 0 && n <= 3, "1~3사이의 값을 입력하세요");

			switch (input) {
			case 1:
				modify();
				return;
			case 2:
				remove();
				return;
			case 3:
				return;
			default:
				break;
			}
		}
	}

	// 수정
	public void modify() {

		String title = miniUtils.next("제목", String.class);
		String post = miniUtils.next("게시글", String.class);
		choicePost.setTitle(title);
		choicePost.setPost(post);

		System.out.println("수정완료");
	}

	// 삭제
	public void remove() {
		posts.remove(choicePost);
		System.out.println("삭제완료");
	}

	private Post findById(int userId) {
		Post post = null;
		for (int i = 0; i < posts.size(); i++) {
			if (posts.get(i).getUserId() == userId) {
				post = posts.get(i);
			}
		}

		return post;
	}

	private Post findByIdx(int idx) {
		Post post = null;
		for (int i = 0; i < posts.size(); i++) {
			if (posts.get(i).getIdx() == idx) {
				post = posts.get(i);
			}
		}
		return post;
	}
}