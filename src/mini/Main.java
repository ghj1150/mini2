package mini;

import java.io.Serializable;

import comment.*;

public class Main implements Serializable{
	public static void main(String[] args) {
		System.out.println("hello world! 안녕 세상!");

		// 승환 code
		 CommentService cs = new CommentService();
		 cs.commentView(1);

		
	}
}
