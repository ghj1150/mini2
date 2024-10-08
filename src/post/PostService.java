package post;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import comment.CommentService;
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
			posts.add(new Post(1, "test1", "평화통일", "평화통일정책의 수립에 관한 대통령의 자문에 응하기 위하여 민주평화통일자문회의를 둘 수 있다. 국민의 자유와 권리는 헌법에 열거되지 아니한 이유로 경시되지 아니한다.", "09/26"));
			posts.add(new Post(2, "test2", "제목2", "호호호", "09/26"));
			posts.add(new Post(3, "test3", "제목3", "허허허", "09/26"));
			posts.add(new Post(4, "test4", "제목4", "후후후", "09/26"));

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

			int input = miniUtils.next("1. 게시글 보기 2. 게시글 작성하기(종료:0)", Integer.class, n -> n >= 0 && n <= 2,
					"0~2사이의 값을 입력하세요");

			switch (input) {
			case 1:
				choicePost = findByIdx(
						miniUtils.next("몇번째글 보실래요?", Integer.class, n -> findByIdx(n) != null, "게시글이 없습니다"));
				readpost(choicePost);
				CommentService cs = new CommentService(userId, choicePost.getIdx());

				modifymenu(cs);
//				cs.commentMenu(input); // input 값에 값 넣으면 번호 출력

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

		String title = miniUtils.next("제목을 작성해주세요", String.class);
		String post = miniUtils.next("게시글을 작성해주세요", String.class);

		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("MM/dd");
		String createDate = format.format(now);

		posts.add(new Post(++maxIdx, userId, title, post, createDate));
	}

	// 목록보기

	public void list() {

		miniUtils.markPrint("=", "게시판");
//		System.out.printf(" %1s| %7s | %8s  | %8s \n", "번호", "아이디", "제목", "날짜");
		test("번호","아이디","L제목","날짜");
		miniUtils.markPrint("-");

		for (int i = 0; i < posts.size(); i++) {
			System.out.println(posts.get(i));
		}
		miniUtils.markPrint("-");

	}

	// 게시글 내용
	public void readpost(Post post) {

		miniUtils.markPrint("=", "제목 : " + post.getTitle());

//		System.out.println("내용 : " + post.getPost() + "\n");
		int beforidx = 0;

		int p = post.getPost().length() / 25;

		for (int i = 1; i < p + 2; i++) {
			int j = i * 25;

			if (i == p + 1) {
				System.out.println(post.getPost().substring(beforidx));
			} else {
				System.out.println(post.getPost().substring(beforidx, j));
			}
			beforidx = j;
		}

		System.out.printf("%70s", "날짜 : " + post.getCreateDate() + "\n");
//		miniUtils.markPrint("-");

	}
	
	 public void test(String... strArray){
			List<String> strList = Arrays.asList(strArray);
			int tmpInterval = 0;

			for (String str : strList){
				if(str.substring(0,1)=="L") continue;
				tmpInterval += str.length()+2;
			}

			int maxInterval = 72 - tmpInterval - strList.size() - 1;

			for (String str : strList){

				String print = (str==strList.get(0)) ? "%-" : "|%-";

				if(str.substring(0,1).equals("L")) {
					System.out.printf(print+maxInterval+"s"," "+str.substring(1)+" ");
				}else {
					System.out.printf(print+str.length()+"s"," "+str+" ");
				}
			}
			System.out.println();


		}

	// 게시글을 작성한 회원만 수정 삭제 가능하게
	public void modifymenu(CommentService cs) {
		while (true) {

			if (userId.equals(choicePost.getUserId())) {
				int input = miniUtils.next("1.수정하기 2.삭제하기 3.댓글쓰기 (종료:0)", Integer.class, n -> n >= 0 && n < 4,
						"0~2사이의 값을 입력하세요");
				switch (input) {
				case 0:
					return;
				case 1:
					modify();
//				readpost(choicePost);
					return;
				case 2:
					remove();
					return;
				case 3:
					cs.commentMenu();
					return;
				default:
					break;
				}
			} else {
				int input = miniUtils.next("1.댓글 (종료:0)", Integer.class, n -> n >= 0 && n < 2, "0~2사이의 값을 입력하세요");
				if (input == 0) {
					return;
				} else {
					cs.commentMenu();
					return;
				}

//				readpost(choicePost);
//				return;
			}
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