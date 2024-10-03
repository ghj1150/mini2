package post;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import mini.miniUtils;

public class PostService {
	private List<Post> posts = new ArrayList<Post>();
	private Post choicePost;
	private String userId;
	private int maxIdx;

	public PostService(String userId) {
		this.userId = userId;

		posts = miniUtils.dataLoad("./src/data/post.ser");

		if (posts == null || posts.isEmpty()) {
			posts = new ArrayList<Post>();
			posts.add(new Post(1, "치똥이", "제목1", "히히히", "09/26"));
			posts.add(new Post(2, "개똥이", "제목2", "호호호", "09/26"));
			posts.add(new Post(3, "새똥이", "제목3", "허허허", "09/26"));
			posts.add(new Post(4, "길똥이", "제목4", "후후후", "09/26"));

			System.out.println("기본 데이터 추가");
		}
		setting();

	}

	public void setting() {

		if (!posts.isEmpty()) {
			maxIdx = posts.get(posts.size() - 1).getIdx();
		} else {
			maxIdx = 0;
		}
//		postMenu();
	}

	public void postMenu() {

		while (true) {
			list();

			int input = miniUtils.next("1. 게시글 보기 2. 글쓰기(종료 0)", Integer.class, n -> n >= 0 && n <= 2,
					"0~2사이의 값을 입력하세요");

			switch (input) {
			case 1:
				choicePost = findByIdx(miniUtils.next("몇번째글?", Integer.class, n -> findByIdx(n) != null, "게시글이 없습니다"));
				readpost(choicePost);
				break;
			case 2:
				add();
				break;
			case 0:
				System.out.println("종료");
				return;
			default:
				break;

			}
			miniUtils.dataSave("./src/data/post.ser", posts);
		}
	}

	// 글쓰기
	public void add() {

		String title = miniUtils.next("제목", String.class);
		String post = miniUtils.next("게시글", String.class);

		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("MM/dd");
		String createDate = format.format(now);

		posts.add(new Post(++maxIdx, userId, title, post, createDate));
	}

	// 목록보기

	public void list() {
		System.out.println("                게시판 목록                   ");
		System.out.println("==============================================");

		for (int i = 0; i < posts.size(); i++) {
			System.out.println(posts.get(i));
		}

	}

	// 게시글 내용
	public void readpost(Post post) {

		miniUtils.markPrint("=", "제목 : " + post.getTitle());

		System.out.println("내용 : " + post.getPost() + "\n");

		System.out.printf("%70s", "날짜 : " + post.getCreateDate() + "\n");
		miniUtils.markPrint("-");

	}

	public void modifymenu() {
		while (true) {

			// 게시글을 작성한 회원만 수정 삭제 가능하게
//			if (userId.equals(choicePost.getUserId())) {
			int input = miniUtils.next("1.수정하기 2. 삭제하기(종료:0)", Integer.class, n -> n >= 0 && n < 3, "0~2사이의 값을 입력하세요");
			switch (input) {
			case 0:
//				list();
				return;
			case 1:
				modify();
				readpost(choicePost);
				return;
			case 2:
				remove();
				break;
			default:
				break;
			}
//			}
			// 종료
//			int input = miniUtils.next("종료하시려면(0)", Integer.class, n -> n < 1, "0을 입력하세요");
//			switch (input) {
//			case 0:
//				return;
//			default:
//				break;
//			}
		}

	}

	// 수정
	// 수정하기 눌렀을때 1. 제목 2. 게시글
	public void modify() {

		while (true) {
			int input = miniUtils.next("1.제목수정 2.게시글수정 (종료:0)", Integer.class, n -> n >= 0 && n <= 2,
					"0~2사이의 값을 입력하세요");
			if (input == 0)
				return;

			if (input == 1) {
				choicePost.setTitle(miniUtils.next("수정할 제목을 작성해주세요.", String.class));
			} else {
				choicePost.setPost(miniUtils.next("수정할 게시글을 작성해주세요.", String.class));
			}
			//////// 수정 완료된 게시글표출 ///////
			readpost(choicePost);

//			System.out.println("수정완료");
		}

	}

	// 삭제
	public void remove() {
		posts.remove(choicePost);
		System.out.println("삭제완료");
	}

//	private Post findById(String userId) {
//		Post post = null;
//		for (int i = 0; i < posts.size(); i++) {
//			if (posts.get(i).getUserId().equals(userId)) {
//				post = posts.get(i);
//			}
//		}
//
//		return post;
//	}

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