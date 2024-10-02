package mini;

import java.io.IOException;
import java.io.Serializable;

import accountbook.AccountBookService;
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

			while(loginUser != null){
				int input = miniUtils.next("1.가계부 2.게시판 3.쪽지함 4.회원정보 (종료:0)", Integer.class, n-> 0 <= n && n <= 4, "번호 선택 실패");
				switch (input) {
					case 1:
						new AccountBookService(loginUser.getUserId());
						break;
					case 2:
						PostService ps = new PostService(loginUser.getUserId()); 
						ps.postMenu();
						break;
					case 3:
						MessageService me = new MessageService(loginUser.getUserId());
						try {
							me.messageMenu();
						} catch (IOException e) {
							e.printStackTrace();
						}
						break;
					case 4:
						loginUser = us.loginStart();
						break;
					default:
						break;
				}
    			if (input == 0) return;
			}
		}
		
		
		
	}
}
