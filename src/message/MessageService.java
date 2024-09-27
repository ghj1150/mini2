package message;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import comment.Comment;
import mini.miniUtils;
import student.Student;

public class MessageService {

//	Message msg = new Message("id", "title", "contents");
	private List<Message> msg = new ArrayList<Message>();

	// 메세지 보내기
	// 상대회원(String), 제목, 내용
	// 읽음, 안읽음 표시
	// 메세지 삭제 시 
	// 내용 보기

//	for(int msgNo = 0 ; ; msgNo++)
	
	
	public void messageMenu() throws FileNotFoundException, IOException {
		while (true) {
			int input = miniUtils.next("1.쪽지함 2.보내기 3.나가기", Integer.class, n -> n >= 1 && n <= 3, "1~3 사이 값만 입력");
			switch (input) {
			case 1:
				messageBox();
				break;
			case 2:
				messageSend();
				break;
			case 3:
				;
				break;
			default:
				break;
			}
		}
	}
	// 쪽지함
	public void messageBox() {
		System.out.println("===================================================");
		System.out.println("                     쪽지함");
		System.out.println("===================================================");
		
		for(int i = 0 ; i < msg.size() ; i++) {
			
			System.out.println(msg.get(i));
		}
		int input = miniUtils.next("1.쪽지삭제 2.내용보기 3.나가기", Integer.class, n -> n >= 1 && n <= 2, "1~2 사이 값만 입력");
		switch(input) {
		case 1:
			messageDel();
			break;
		case 2:
			messageCon();
			break;
		}
	}
	// 메세지 보내기
	public void messageSend() {


		String targetId = miniUtils.next("쪽지를 보낼 상대방의 id를 입력", String.class, n -> n != null, "없는 id입니다.");
		String title = miniUtils.next("제목", String.class);
		String contents = miniUtils.next("보낼 내용", String.class);

		msg.add(new Message(targetId, title, contents));

		System.out.println("전송완료");

	}
	// 메세지 삭제
	public void messageDel() {
		String tmpTarget = miniUtils.next("쪽지를 삭제할 상대방의 id", String.class);
		
		int m = findBy(tmpTarget);
//		msg.get(m);
		msg.remove(m);
	}
	private int findBy(String id) {
		int tmp = 0;
		for(int i = 0 ; i < msg.size() ; i++) {
			if(msg.get(i).getId().equals(id)) {
				tmp = i;
			}
		}
		return tmp;
	}
	
	public void messageCon() {
		String mc = miniUtils.next("확인할 쪽지의 회원 id를 입력", String.class);
		
	}
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
