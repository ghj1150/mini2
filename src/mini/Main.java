package mini;

import java.io.Serializable;

import comment.*;
import message.MessageService;
import post.PostService;
import user.User;
import user.UserService;

public class Main implements Serializable{
	public static void main(String[] args) {
		
		UserService us = new UserService();
		
		while(true) {
			
			// 로그인
			us.UserStart();
			// 로그인 성공
			User loginUser = us.getLoginUser();
			// 
			
		}
		
		
		
	}
}
