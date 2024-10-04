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
		System.out.println("  _    _               _____                   ");
        System.out.println(" | |  | |             |  __ \\                  ");
        System.out.println(" | |__| | __ _ _ __   | |  | | ___  _ __    ");
        System.out.println(" |  __  |/ _` | '_ \\  | |  | |/ _ \\| '_ \\ ");
        System.out.println(" | |  | | (_| | | | | | |__| | (_) | | | |  ");
        System.out.println(" |_|  |_|\\__,_|_| |_| |_____/ \\___/|_| |_|");
		UserService us = new UserService();
		
		while(true) {
			// 로그인
			int exit = us.UserStart();
			if (exit == 0) return;
			// 로그인 성공
			User loginUser = us.getLoginUser();

			while(loginUser != null){
				AccountBookService ac = new AccountBookService(loginUser);
				PostService ps = new PostService(loginUser.getUserId()); 
				MessageService me = new MessageService(loginUser.getUserId());

				int input = miniUtils.next("1.가계부 2.게시판 3.쪽지함 4.회원정보", Integer.class, n-> 0 < n && n <= 4, "1~4번중에 선택해주세요.");
				switch (input) {
					case 1:
						ac.accountBookMenu();
						break;
					case 2:
						ps.postMenu();
						break;
					case 3:
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
			}
		}
		
		
		
	}
}
