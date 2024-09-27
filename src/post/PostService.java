package post;

import java.util.ArrayList;
import java.util.List;
import mini.miniUtils;

public class PostService {
	private List<Post> posts = new ArrayList<Post>();

	{
		posts = miniUtils.dataLoad("./src/data/post.ser");
		if (posts == null) {

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

			int input = miniUtils.next("1.목록보기 2. 글쓰기 3. 수정 4. 삭제", Integer.class, n -> n > 0 && n <= 4, "");
			switch (input) {
			case 1:
				list();
				break;
			case 2:
				add();
				break;
			case 3:
				modify();
			case 4:
				remove();
			default:
				break;
			}
		}

	}

	public void add() {
		System.out.println(posts);
		int idx = miniUtils.next("id", Integer.class, n -> n > 0, "");
		int user_id = miniUtils.next("회원 id", Integer.class, n -> n > 0, "");
		String title = miniUtils.next("제목", String.class, null, null);
		String post = miniUtils.next("게시글", String.class, null, null);
		String create_date = miniUtils.next("날짜", String.class, null, null);

		posts.add(new Post(idx, user_id, title, post, create_date));
	}

	public void list() {

		System.out.println("                   게시판                     ");
		System.out.println("==============================================");
		for (int i = 0; i < posts.size(); i++) {
			System.out.println(posts.get(i));
		}
		
		int t = miniUtils.next("몇번?", Integer.class);

		Post post = null;
		for (int i = 0; i < posts.size(); i++) {
			if (posts.get(i).getIdx() == t) {
				post = posts.get(i);
			}
		}
		if(post == null) {
			System.out.println("게시글이 없습니다");
			return;
		}
		
		readpost(post);
	}

	public void readpost(Post post) {
		
	}

	public void modify() {

	}

	public void remove() {

	}
}
