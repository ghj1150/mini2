package comment;

import java.util.*;

import mini.miniUtils;

public class CommentService {

	private List<Comment> comments = new ArrayList<Comment>();

	{
		// 데이터 로드
		comments = miniUtils.dataLoad("./src/data/comment.ser");
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
		// System.out.println(comments);
		// 데이터 save
		miniUtils.dataSave("./src/data/comment.ser", comments);
	}

	public void commentMenu(int postIdx){

		while(true){
			int input = miniUtils.next("1.댓글 작성 2.댓글수정 3.댓글삭제 4.종료",Integer.class,n -> 1 <= n && n <= 4, "1~4사이의 숫자만 입력해주세요.");
			switch (input) {
				case 1:
					comWrite(postIdx);
					break;
				case 2:
					comModify(postIdx);
					break;					
				case 3:
					comRemove(postIdx);
					break;
				case 4:
					System.out.println("종료");
					break;										
			
				default:
					break;
			}
		}
	}
	public void comWrite(int postIdx){
		List<Comment> targetList = new ArrayList<Comment>();
		String str = "";
		int tmp=0;

		targetList = commentView(postIdx);
		int maxIdx = comments.get(comments.size()-1).getIdx(); 
		int choise = miniUtils.next("1.댓글작성  2.대댓글작성 3.종료", Integer.class, n -> 0<n && n <=3  , "1~3번만 눌러주세요.");

		switch (choise) {
			
			case 2:	
				tmp=0;
				System.out.println(targetList);
				int target = miniUtils.next("댓글 선택 (종료 : 0)",  Integer.class, (n) -> n != 0, "댓글 작성 오류");
				if(target == 0) return;

				for (int i =0;i<targetList.size(); i++){
					if(targetList.get(i).getIdx() == target){
						tmp = i;
						break;
					}
				}
			case 1:
				str = miniUtils.next("댓글 입력 (종료 : 0)", String.class, n -> n != null , "댓글 작성 오류");
				if(str == "0") return;
				break;
			case 3:
				return;
			
						
			default:
				break;
		}

		comments.add(new Comment(maxIdx, str, postIdx, tmp, 1, null));

	}

	public void comModify(int postIdx){
		
	}
	public void comRemove(int postIdx){
		
	}
	public List<Comment> commentView(int postIdx) {
		
		List<Comment> tmp = new ArrayList<>();
		List<String> rltTmp = new ArrayList<>();
		List<Comment> returnTmp = new ArrayList<>();
		
		// 데이터 가공
		tmp = comProc(postIdx);

		// 대댓글 추출
		for (Comment i : tmp) {
			if(i.getCommentParentIdx()==0) {
				rltTmp.add(i.getComment());
				returnTmp.add(i);
			}
			for (Comment j : tmp) {
				if(j.getCommentParentIdx()!=0 && i.getIdx()==j.getCommentParentIdx()) {
					rltTmp.add("└" + j.getComment());
				}
			}
		}
		// 출력
		System.out.println("==============================================");
		System.out.println("                    댓 글                      ");
		System.out.println("==============================================");
		for(String i : rltTmp) {
			System.out.println(i);
		}
		return returnTmp;
	}
	// 댓글 가공
	public List<Comment> comProc(int postIdx){
		List<Comment> tmp = new ArrayList<>();

		// 선택한 게시글 댓글 추출
		for (Comment c : comments) {
			if (c.getPostIdx() == postIdx) {
				tmp.add(c);
			}
		}
		// 부모 댓글 순으로 sort
		tmp.sort((o1,o2)->o1.getCommentParentIdx()-o2.getCommentParentIdx());

		return tmp;
	}
}
