package message;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mini.miniUtils;
@SuppressWarnings("unchecked")
public class MessageService {

//   Message msg = new Message("id", "title", "contents");
   private List<Message> msg = new ArrayList<>();
   
   private String userId;
   
   public MessageService(String userId){
      this.userId = userId;
      msg = miniUtils.dataLoad("./src/data/message.ser");
      if(msg == null || msg.isEmpty()) {
         System.out.println(msg);
         msgDefaultData();
         System.out.println(msg);
         System.out.println("메세지 데이터 추가");
      }
//      setting();
      // 데이터 테스트 용
//       System.out.println(msg);
      // 데이터 save   
//       miniUtils.dataSave("./src/data/message.ser", msg);
      
   }
   
   private void msgDefaultData() {
      msg = new ArrayList<>();
      msg.add(new Message(1,"가","A", "제목1","내용","2024-01-01"));
      msg.add(new Message(2,"나","B", "제목1","내용","2024-01-01"));
      msg.add(new Message(3,"다","C", "제목1","내용","2024-01-01"));
//      msg.add(new Message(2,10,"title","con","date"));
//      msg.add(new Message(3,2,"title2","con2","date2"));
//      msg.add(new Message(4,1,"title3","con3","date3"));
   }
   
   public void setting() {
      for(Message m : msg) {
//         if(m.getUserIdx() == )
      }
   }
   // 메세지 보내기
   // 상대회원(String), 제목, 내용
   // 읽음, 안읽음 표시
   // 메세지 삭제 시 
   // 내용 보기

   public void messageMenu() throws FileNotFoundException, IOException {
      
      
      while (true) {
         int input = miniUtils.next("===================================================\n                      메뉴\n"
         		+ "===================================================\n"+"1.쪽지함 2.보내기 3.나가기", Integer.class, n -> n >= 1 && n <= 3, "1~3 사이 값만 입력");
         switch (input) {
         case 1:
            messageBox();
            break;
         case 2:
            messageSend();
            break;
         case 3:
        	miniUtils.dataSave("./src/data/message.ser", msg);
        	System.out.println("종료");
            return;
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
      
 

      if(msg.size() == 0) {
    	  System.out.println("쪽지함이 비었습니다.");
    	  return;
      }
      
      
      
      for(int i = 0 ; i < msg.size() ; i++) {
    	  //	for(int j = 0 ; j < -- 만드는중
    	  //    	  if(userId == msg.get(i).getOtherId())  유저아이디와 일치 시 자기 자신에게 온 메세지만 확인 가능하게 구현 -- 만드는중
         System.out.println((i+1) +". "+msg.get(i));
      }
      int input = miniUtils.next("1.쪽지삭제 2.내용보기 3.뒤로가기", Integer.class, n -> n >= 1 && n <= 3, "1~3 사이 값만 입력");
      switch(input) {
      case 1:
         messageDel();
         break;
      case 2:
         messageCon();
         break;
      case 3:
          return;
       default:
          break;
      }
   }
   // 메세지 보내기
   public void messageSend() {

      
	  String targetId = miniUtils.next("받는사람 아이디 입력", String.class, n -> n != null, "없는 id입니다.");
      String title = miniUtils.next("제목", String.class);
      String contents = miniUtils.next("보낼 내용", String.class);
      Date today = new Date();
      SimpleDateFormat format = new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm:ss a"); 
      String date = format.format(today);
      
      
      msg.add(new Message(1, userId, targetId, title, contents, date));

      System.out.println("전송완료");

   }
   // 메세지 삭제
   public void messageDel() {
      int tmpTarget = miniUtils.next("삭제할 쪽지의 번호를 입력", Integer.class, n -> 0 < n && n <= msg.size(), "없는 번호입니다.");

      Message m = findByIdx(tmpTarget - 1);

      if (m != null) {
         msg.remove(m);
      }
      else {
    	  System.out.println("없는 번호입니다.");
    	  return;
      }
      
      
   }

   // 내용보기
   public void messageCon() {
      int tmpTarget = miniUtils.next("확인할 쪽지의 번호를 입력", Integer.class, n -> 0 < n && n <= msg.size(), "없는 번호입니다.");
      Message m = findByIdx(tmpTarget - 1);

      if (m != null) {
         System.out.println("보낸사람: " +m.getUserId() +  "/ 제목: " + m.getTitle());
         System.out.println("보낸 내용: " + m.getContents());
      }
      else {
    	 System.out.println("없는 번호입니다.");
    	 return;
      }
   }

   // 송신자 찾기
   private Message findById(int id) {
      for(Message m : msg) {
         if(m.getIdx() == id) {
            return m;
         }
      }
      return null;
   }
   
   private Message findByIdx(int idx) {
	   return msg.get(idx);
   }
}

