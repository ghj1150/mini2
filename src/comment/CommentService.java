package comment;

import java.util.*;

import mini.miniUtils;

public class CommentService {

	private List<Comment> comments = new ArrayList<Comment>();

	{
		// 데이터 로드
		comments = miniUtils.dataLoad("./src/data/test.ser");
		if (comments == null || comments.isEmpty()) {
			comments = new ArrayList<>();
			comments.add(new Comment(1, "1", 1, 0, 1, null));
			comments.add(new Comment(2, "1-1", 1, 1, 1, null));
			comments.add(new Comment(3, "X", 2, 0, 2, null));
			comments.add(new Comment(4, "1-2", 1, 1, 2, null));
			comments.add(new Comment(5, "X", 2, 0, 1, null));
			comments.add(new Comment(6, "2", 1, 0, 1, null));
			comments.add(new Comment(7, "1-3", 1, 1, 1, null));
			comments.add(new Comment(8, "X", 2, 0, 2, null));
			comments.add(new Comment(9, "2-1", 1, 2, 2, null));
			comments.add(new Comment(10, "X", 2, 0, 1, null));
			System.out.println("기본 데이터 추가");
		}
		// 데이터 테스트 용
		System.out.println(comments);
		// 데이터 save
		miniUtils.dataSave("./src/data/test.ser", comments);
	}

	public void commentView(int postIdx) {
		List<Comment> tmp = new ArrayList<>();
		List<String> tmpRlt = new ArrayList<>();
		for (Comment c : comments) {
			if (c.getPostIdx() == postIdx) {
				tmp.add(c);
			}
		}
		tmp.sort((o1,o2)->o1.getCommentParentIdx()-o2.getCommentParentIdx());
		System.out.println("==============================================");
		System.out.println("                    댓 글                      ");
		System.out.println("==============================================");
		System.out.println(tmp);

		for (Comment i : tmp) {
			if(i.getCommentParentIdx()==0) {
				tmpRlt.add(i.getComment());
			}
			
			for (Comment j : tmp) {
				if(j.getCommentParentIdx()!=0 && i.getIdx()==j.getCommentParentIdx()) {
					tmpRlt.add("└" + j.getComment());
				}
			}
		}
		
		for(String i : tmpRlt) {
			System.out.println(i);
		}

	}
}
