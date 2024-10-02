package user;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import mini.miniUtils;

@SuppressWarnings("unchecked")
public class UserService {
	Scanner scanner = new Scanner(System.in);
	private List<User> users = new ArrayList<User>();// 어플리케이션에 저장된 모든 회원들에 대한 정보
	User loginUser; // 현재 로그인한 사용자
	// null >> 비로그인, not null/로그아웃이 편함

	public UserService() {
		users = miniUtils.dataLoad("./src/data/user.ser");

		if (users == null || users.isEmpty()) {
			loadDefaultData();
			System.out.println("기본 데이터 추가");
		}
//	
//	// 데이터 테스트 용
//	 System.out.println(users);
		// 데이터 save
//	 miniUtils.dataSave("./src/data/user.ser", users);
//
////회원가입,로그인 
	}

	private void loadDefaultData() {
		users = new ArrayList<>();
		users.add(new User("test2", "123123", "김라율", "2003-05-20"));
		users.add(new User("test3", "23452", "곽두팔", "1975-08-17"));
		users.add(new User("test4", "77554", "곽한구", "1982-02-19"));
	}

//userid, pw, name, birth
	public void UserStart() {
		while (true) {
			System.out.println(users); // 나중에 지울 코드

			System.out.println("1. 로그인 2. 회원가입 3.비밀번호찾기");
			int input = Integer.parseInt(scanner.nextLine());
			switch (input) {
			case 1:
				login();
				break;
			case 2:
				add();
				break;
			case 3:
				findUser();
				break;
			default:
				break;
			}

			if (loginUser != null)
				break;

		}
		loginStart();
	}

	public User loginStart() {
		while (loginUser != null) {
			System.out.println("1.로그아웃 2. 탈퇴 3.회원정보수정 (종료:0)");
			int input1 = Integer.parseInt(scanner.nextLine());
			switch (input1) {
			case 1:
				logout();
				return null;
			case 2:
				remove();
				return null;
			case 3:
				modify();
			case 0:
				return loginUser;
			default:
				break;
			}
		}
		return loginUser;
	}

	public void login() {
		System.out.println("아이디>");
		String id = scanner.nextLine();
		User u = findById(id);
		if (u == null) {
			System.out.println("입력한 회원의 아이디가 존재하지 않습니다.");
			return;
		}
		System.out.println("비밀번호 >");

		String pw = scanner.nextLine();
		this.loginUser = u;

		if (pw == loginUser.getUserPw()) {
			System.out.println("입력한 회원의 비밀번호가 틀렸습니다.");
		}
		System.out.println("로그인 성공");
	}

	public void logout() {
		loginUser = null;
		System.out.println("로그아웃 되었습니다.");
	}

	//
//		
//		String userid = miniUtils.next("아이디를 입력 해주세요", String.class, n -> findById(n) == null,
//				" 존재하는 아이디 입니다. 다시 입력 해주세요.");
//		String pw = miniUtils.next("pw", String.class, n -> n != null, " 비밀번호를 잘못 입력 하였습니다.");
//		String name = miniUtils.next("name", String.class, n -> n != null, " 이름을 잘못 입력 하였습니다.");
//		String phoneNumber = miniUtils.next("phoneNumber", String.class, n -> n != null, " 전화번호를 확인을 해주세요.");
//		String birth = miniUtils.next("birth", String.class, n -> n != null, "생년월일 (YYYY-MM-DD)방식을 확인하여주세요.");
//
//		users.add(new User(userid, pw, name, birth));
//		System.out.println("회원가입 성공했습니다.");
//	

	public void add() {
		System.out.println("아이디 > ");
		String userId = scanner.nextLine();
		User u = findById(userId);
		if (u != null) {
			System.out.println("입력한 회원의 아이디가 존재합니다.");
			return;
		}
		System.out.println("이름 >");
		String name = scanner.nextLine();
		System.out.println("비밀번호 >");
		String pw = scanner.nextLine();

		String dateInput = miniUtils.next("연월일을 입력해주세요. ex) 20240930", String.class,
				n -> n != null && n.matches("\\d{8}"), "양식에 맞게 작성해주세요");
		String date = dateInput.substring(0, 4) + "/" + dateInput.substring(4, 6) + "/" + dateInput.substring(6);

		users.add(new User(userId, pw, name, date));
	}

	public void modify() {
		String pw = "";
		String name = "";
		int lostMoney = 0;
		int lostDay = 0;
		
		while (true) {
			System.out.println("1.비밀번호수정 2.이름수정 3.고정지출수정 4.고정수입수정 5.전체수정 (종료:0)");
			int input1 = Integer.parseInt(scanner.nextLine());
			switch (input1) {
			case 1: 
				pw = miniUtils.next("변경 할 비밀번호 : ", String.class, n -> n != null, " 입력하신 비밀번호는 형식에 맞지 않습니다. ");
				loginUser.setUserPw(pw);
				System.out.println("비밀번호 변경이 완료되었습니다.");
				return;
			case 2 :
				 name =miniUtils.next("변경 할 이름 : ", String.class, str -> str.matches("^[가-힣]{2,4}"),"정확한 이름의 조건을 입력하세요");
				loginUser.setName(name);
				System.out.println("이름 변경이 완료 되었습니다.");
				return ;
			case 3: 
				 lostMoney = miniUtils.next("매달 고정지출을 입력 해주세요", Integer.class, n -> n != 0, " 지출을 입력해주세요 ");
				System.out.println("고정 지출이 등록되었습니다.");
			case 4:
				 lostDay= miniUtils.next("매달 고정수입을 입력 해주세요", Integer.class, n -> n != 0, " 수입을 입력해주세요");
			case 5:	
				pw = miniUtils.next("변경 할 비밀번호 : ", String.class, n -> n != null, " 입력하신 비밀번호는 형식에 맞지 않습니다. ");
				loginUser.setUserPw(pw);
				System.out.println("비밀번호 변경이 완료되었습니다.");
				 name =miniUtils.next("변경 할 이름 : ", String.class, str -> str.matches("^[가-힣]{2,4}"),"정확한 이름의 조건을 입력하세요");
				loginUser.setName(name);
				System.out.println("이름 변경이 완료 되었습니다.");
				 lostMoney = miniUtils.next("매달 고정지출을 입력 해주세요", Integer.class, n -> n != 0, " 지출을 입력해주세요 ");
				System.out.println("고정 지출이 등록되었습니다.");
				 lostDay= miniUtils.next("매달 고정수입을 입력 해주세요", Integer.class, n -> n != 0, " 수입을 입력해주세요");
			case 0 :
				return ;
			default:
				break;
			}	
			
		}
		
		
		
//		User user = findById(miniUtils.next("아이디", String.class, n -> findById(n) != null, "입력한 아이디는 존재하지 않습니다."));
//		String name = miniUtils.next("변경 할 이름 : ", String.class, str -> str.matches("^[가-힣]{2,4}"),
//				"정확한 이름의 조건을 입력하세요");
//		String pw = miniUtils.next("변경 할 비밀번호 : ", String.class, n -> n != null, " 입력하신 비밀번호는 형식에 맞지 않습니다. ");
//		String lostMoney = miniUtils.next("고정지출", null, null, pw)
	}

	public void remove() {
		users.remove(loginUser);
		logout();

//		
		String idTmp = miniUtils.next("아이디", String.class, n -> findById(n) != null, "입력한 아이디는 존재하지 않습니다.");

		if (idTmp != null) {
			User user = findById(idTmp);
			users.remove(user);
			System.out.println("회원탈퇴 완료되었습니다.");
		} else {
			System.out.println("회원정보를 정확히 입력하세요.");
		}
		System.out.println(users);
	}

	private User findById(String userid) {
//		User user = null;
		for (User u : users) {
			if (u.getUserId().equals(userid)) {
				return u;
			}
		}
		return null;
	}

	private User findByPw(String userpw) {
		User user = null;
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getUserPw().equals(userpw)) {
				user = users.get(i);
			}
		}
		return user;
	}

	public void findUser() {
		// 아이디에 맞는 유저 찾기
		User user = findById(miniUtils.next("아이디", String.class, n -> findById(n) != null, "입력한 아이디는 존재하지 않습니다."));
		// 비밀번호 받기
		String pwTmp = miniUtils.next("변경 할 비밀번호 : ", String.class, n -> n != null, " 입력하신 비밀번호는 형식에 맞지 않습니다. ");
		// 비밀번호 변경 적용
		user.setUserPw(pwTmp);
		// 성공 표출
		System.out.print(user.getUserId() + " 님 ");
		System.out.println("비밀번호 변경이 완료되었습니다.");

	}

	public User getLoginUser() {
		return loginUser;
	}

}
