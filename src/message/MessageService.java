package message;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import comment.Comment;
import mini.miniUtils;

public class MessageService {
	Message msg = new Message(123, "title", "contents");
	
	MessageService ms = new MessageService();
	// 메세지 보내기
	// 상대회원(int), 제목, 내용
	// 읽음, 안읽음 표시
	public void MessageMenu() throws FileNotFoundException, IOException {
		int input = miniUtils.next("1.쪽지함 2.보내기 3.나가기",Integer.class, n -> n >= 1 && n <= 3, "1-3사이값을입력"	);
		switch (input) {
		case 1: 
			ms.messageBox();
			break;
		case 2:
			ms.messageSend();
			break;
		case 3:
			;
			break;
		default:
			break;
		}
	}
	
	
	public void messageBox() {
		System.out.println("쪽지함");
	}
	
	
	public void messageSend()throws FileNotFoundException, IOException {
		int id = miniUtils.next("쪽지를 보낼 id",Integer.class,  n -> findBy(n) == null, "없는 id입니다.");
		String title = miniUtils.next("제목",String.class);
		String contents = miniUtils.next("보낼 내용",String.class);

	}
	

//	private List<Message> message = new ArrayList<Message>();
//	{
//	message = miniUtils.dataLoad("./src/data/test.ser");
//	
//	if(message == null) {
//		message.add(new Message(123, "title", "contents"));
//	
//	
//	}
//	
//	}
	
//	public void list() {
////		System.out.println("list()");
//		int input = next("1. 입력순 2. 학번순 3. 이름순 4. 석차순", Integer.class, i -> i <= 4 && i >= 1 , "1- 4사이값을 입력하세요");
//		switch (input) {
//		case 1:
//			students;
//			break;
//		case 2:
//			tmp = noSortedStudents;
//			break;
//		case 3:
//			tmp = nameSortedStudents;
//			break;
//		case 4:
//			tmp = totalSortedStudents;
//			break;
//		default:
//			System.out.println("????");
//			break;
//		}
//		System.out.println("학번   이름    국어    영어    수학    총점    평균");
//		System.out.println("===================================================");
//		for(int i = 0 ; i < tmp.size() ; i++) {
//	
//			System.out.println(tmp.get(i));
//		}
//	}
	

}