package comment;

import java.util.*;

import mini.miniUtils;

@SuppressWarnings("unchecked")
public class CommentService {

	private List<Comment> loadData = new ArrayList<Comment>();
	private List<Comment> comments = new ArrayList<Comment>();
	private int userIdx;
	private int postIdx;
	private int maxIdx;

	public CommentService(int userIdx,int postIdx){
		this.userIdx = userIdx;
		this.postIdx = postIdx;
		loadData = miniUtils.dataLoad("./src/data/comment.ser");

		if (loadData == null || loadData.isEmpty()) {
            loadDefaultData();
            System.out.println("기본 데이터 추가");
        }

		// 데이터 테스트 용
		// System.out.println(loadData);
		// 데이터 save
		// miniUtils.dataSave("./src/data/comment.ser", comments);

		setting();
	}

	private void loadDefaultData() {
        loadData = new ArrayList<>();
        loadData.add(new Comment(1, "1", 1, 0, 1, null));
        loadData.add(new Comment(2, "1-1", 1, 1, 1, null));
        loadData.add(new Comment(3, "X", 2, 0, 2, null));
        loadData.add(new Comment(4, "1-2", 1, 1, 2, null));
        loadData.add(new Comment(5, "X", 2, 0, 1, null));
        loadData.add(new Comment(6, "2", 1, 0, 1, null));
        loadData.add(new Comment(7, "1-3", 1, 1, 1, null));
        loadData.add(new Comment(8, "X", 2, 0, 2, null));
        loadData.add(new Comment(9, "2-1", 1, 6, 2, null));
        loadData.add(new Comment(10, "X", 2, 0, 1, null));
    }

	public void setting(){
		// 게시글에 맞는 데이터 추출
		for (Comment c : loadData) {
			if (c.getPostIdx() == postIdx) {
				comments.add(c);
			}
		}
		if (!comments.isEmpty()) {
            maxIdx = comments.get(comments.size()-1).getIdx();
        } else {
            maxIdx = 0; // 댓글이 없는경우
        }

		commentMenu();
	}

	public void commentMenu(){
		Comment tmp;

		while(true){
			view((List<String>) viewProc(true));
			int input = miniUtils.next("1.댓글 작성 2.대댓글작성 3.댓글수정 4.댓글삭제 5.종료",Integer.class,n -> 1 <= n && n <= 5, "1~5사이의 숫자만 입력해주세요.");

			switch (input) {
				case 1:
					write(0);
					break;
				case 2:
					tmp = targetComment(true);
					if(tmp == null) return;
					write(tmp.getIdx());
					break;					
				case 3:
					tmp = targetComment(false);
					if(tmp == null) return;
					modify(tmp);
					break;					
				case 4:
					remove(postIdx);
					break;
				case 5:
					System.out.println("종료");
					break;										
			
				default:
					break;
			}
		}
	}
	// 대댓글 선택
	// public void write(List<Comment> targetList){

	// 	for (int i =0;i<targetList.size(); i++){
	// 		if(targetList.get(i).getIdx() == target){
	// 			target = i;
	// 			break;
	// 		}
	// 	}
	// 	System.out.println(target);
	// 	// write(target);
	// }

	public void write(int parentIdx){
		String str = miniUtils.next("댓글을 작성해주세요. (종료:0)",  String.class, (n) -> n != null, "댓글 작성 오류");
		if(str.equals("0")) return;

		comments.add(new Comment(++maxIdx, str, postIdx, parentIdx, userIdx, null));

		System.out.println("댓글 작성 완료");
	}

	public Comment targetComment(boolean ck){
		if(ck){
			// 대댓
			List<Comment> targetList = (List<Comment>) viewProc(false);
			int target = miniUtils.next("댓글 선택 (종료 : 0)",  Integer.class, (n) -> 0 <= n && n <= targetList.size(), "제대로 선택해");		
			if(target == 0) return null;
			return targetList.get(target-1);
		}else{

			// 자기가 글쓴 데이터만 수정 삭제 가능하게 리스트 뽑아서 진행해야됨
			// 수정/삭제
			int target = miniUtils.next("댓글 선택 (종료 : 0)",  Integer.class, (n) -> 0 <= n && n <= comments.size(), "제대로 선택해");		
			if(target == 0) return null;
			return comments.get(target-1);
		}
	}

	public void modify(Comment target){
		System.out.println(target);
		
	}
	public void remove(int postIdx){
		
	}

	
	public List<?> viewProc(Boolean ckReturn) {
		List<String> strList = new ArrayList<>();
		List<Comment> parentIdx0 = new ArrayList<>();
		// 선택한 게시글 댓글 추출 및 부모 댓글 순으로 sort

		comments.sort((o1,o2)->o1.getparentIdx()-o2.getparentIdx());
		
		System.out.println(comments);
		// 데이터 가공
		int cnt=1;
		for (Comment i : comments) {
			if(ckReturn){
				// 대댓글 출력
				if(i.getparentIdx()==0) {
					strList.add(cnt + ". " + i.getComment());
					cnt++;
				}
				for (Comment j : comments) {
					if(j.getparentIdx()!=0 && i.getIdx()==j.getparentIdx()) {
						strList.add("  └" + j.getComment());
					}
				}
			} else {
				// 메인 객체 추출
				if(i.getparentIdx()==0) {	
					parentIdx0.add(i);
				}
			}
		}	
		return ckReturn == true ? strList : parentIdx0; 
	}

	public void view(List<String> strList){
		// 출력
		System.out.println("==============================================");
		System.out.println("                    댓 글                      ");
		System.out.println("==============================================");
		for(String i : strList) {
			System.out.println(i);
		}
	}
}
