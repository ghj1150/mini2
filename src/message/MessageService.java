package message;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mini.miniUtils;
@SuppressWarnings("unchecked")
public class MessageService {

//	Message msg = new Message("id", "title", "contents");
	private List<Message> msg = new ArrayList<>();
	
	private int id;
	
	public MessageService(int id){
		this.id = id;
		msg = miniUtils.dataLoad("./src/data/message.ser");
		if(msg == null || msg.isEmpty()) {
			System.out.println(msg);
			msgDefaultData();
			System.out.println(msg);
			System.out.println("메세지 데이터 추가");
		}
//		setting();
		// 데이터 테스트 용
//		 System.out.println(msg);
		// 데이터 save	
		 miniUtils.dataSave("./src/data/message.ser", msg);
		
	}
	
	private void msgDefaultData() {
		msg = new ArrayList<>();
		msg.add(new Message(1, 2, "qw", null, null));
		msg.add(new Message(1,10,"title","con","date"));
		msg.add(new Message(2,2,"title2","con2","date2"));
		msg.add(new Message(3,1,"title3","con3","date3"));
	}
	
	public void setting() {
		for(Message m : msg) {
//			if(m.getUserIdx() == )
		}
		
	}
	
	Date d = new Date();
	
	

	
	
	// 메세지 보내기
	// 상대회원(String), 제목, 내용
	// 읽음, 안읽음 표시
	// 메세지 삭제 시 
	// 내용 보기

//	for(int msgNo = 0 ; ; msgNo++)
	
//	public void messageLogin() {
//		
//		String userIdx = miniUtils.next("로그인할 아이디를 입력해 주세요", String.class);
//	}
	
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

		
		int targetId = miniUtils.next("받는사람 아이디 입력", Integer.class, n -> n != null, "없는 id입니다.");
		String title = miniUtils.next("제목", String.class);
		String contents = miniUtils.next("보낼 내용", String.class);
		String date = d.toString();
		
		
		msg.add(new Message(id, targetId, title, contents, date));

		System.out.println("전송완료");

	}
	// 메세지 삭제
	public void messageDel() {
		int tmpTarget = miniUtils.next("쪽지를 삭제할 보낸사람 id를 입력", Integer.class, n -> n != null, "없는 id입니다.");
		
		int m = findBy(tmpTarget);
//		msg.get(m);
		msg.remove(m);
	}
	
	// 송신자 찾기
	private int findBy(int id) {
		int tmp = 0;
		for(int i = 0 ; i < msg.size() ; i++) {
			if(msg.get(i).getIdx()==id) {
				tmp = i;
			}
		}
		return tmp;
	}
	// 내용보기
	public void messageCon() {
		int tmpTarget = miniUtils.next("확인할 쪽지의 보낸사람 id를 입력", Integer.class, s -> s != null, "없는 id입니다.");
		int m = findBy(tmpTarget);
		System.out.println("보낸사람: " +msg.get(m).getIdx() +  "/ 제목: " + msg.get(m).getTitle());
	
//		for(int i = 0 ; i < msg.get(m).getContents().length() ; i++ ) {
//			if(msg.get(m).getContents().length()< 10) {
//				System.out.println();
//			}
//		}
		
		System.out.println("보낸 내용: " + msg.get(m).getContents());
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
