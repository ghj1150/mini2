package mini;

import java.io.Serializable;

import comment.*;
import user.UserService;

public class Main implements Serializable{
	public static void main(String[] args) {
		System.out.println("hello world! 안녕 세상!");

		// 승환 code
//		 CommentService cs = new CommentService();
//		 cs.commentView(1);
		 
		 // 주연 code
		 UserService us = new UserService();
		
	}
}
