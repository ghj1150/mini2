package message;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

public class Main implements Serializable{
	public static void main(String[] args) throws FileNotFoundException, IOException {
//		System.out.println("hello world! 안녕 세상!");
//
//		승환 code
//		CommentService cs = new CommentService();
//		cs.commentView(1);
//		 
////		주연 code
////		UserService us = new UserService();
////		us.add();
//		 
////		현우 code
		MessageService me = new MessageService();
		
		
		me.messageMenu();
		
//		
////		규철 code
////		PostService ps = new PostService(); 
////		ps.add();
//		
	}
}