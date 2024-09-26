package post;

import java.util.ArrayList;
import java.util.List;
import mini.miniUtils;

public class PostService {
	private List<Post> posts = new ArrayList<Post>();

	{
		posts.add(new Post(1, 1, null, null, null));
		posts.add(new Post(2, 2, null, null, null));
		posts.add(new Post(3, 3, null, null, null));
		posts.add(new Post(4, 4, null, null, null));

	}

	public void add() {
		System.out.println(posts);
//		int idx = miniUtils.next("", Integer.class, null, null);
	}

	public void list() {

	}

	public void modify() {

	}

	public void remove() {

	}
}
