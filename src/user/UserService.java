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
	
//	Scanner scanner = new Scanner(System.in);
	private List<User> users = new ArrayList<User>();// 어플리케이션에 저장된 모든 회원들에 대한 정보
	User loginUser; // 현재 로그인한 사용자
	// null >> 비로그인, not null/로그아웃이 편함

	public UserService() {
		users = miniUtils.dataLoad("./src/data/user.ser");

		if (users == null || users.isEmpty()) {
			loadDefaultData();
			// System.out.println("기본 데이터 추가");
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
		users.add(new User("test1", "12312", "제갈제니", "2012/01/20"));
		users.add(new User("test2", "123123", "김라율", "2003/05/20"));
		users.add(new User("test3", "23452", "곽두팔", "1975/08/17"));
		users.add(new User("test4", "77554", "곽한구", "1982/02/19"));
	}

//userid, pw, name, birth
	public int UserStart() {
		
		System.out.println(users); // 나중에 지울 코드
		while (true) {
			miniUtils.markPrint("=", "로그인");
			int input = miniUtils.next("1.로그인 2.회원가입 3.비밀번호찾기 (종료:0)", Integer.class, n -> 0 <= n && n <= 3,"0번~ 3번 사이의 값을 입력 해주세요.");
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
			case 0:
				System.out.println("종료 되었습니다.");
				return 0;
			default:
				break;
			}
			if (loginUser != null) break;
		}
		return 1;
	}

	public User loginStart() {
		while (loginUser != null) {
			miniUtils.markPrint("=", "회원정보");
			int input1 = miniUtils.next("1.로그아웃 2. 탈퇴 3.회원정보수정 (종료:0)", Integer.class, n -> 0<= n && n <= 3,"0번~ 3번 사이의 값을 입력 해주세요.");
			switch (input1) {
			case 1:
				logout();
				return null;
			case 2:
				remove();
				return null;
			case 3:
				modify();
				break;
			case 0:
				return loginUser;
			default:
				break;
			}
		}
		return loginUser;
	}

	public void login() {

		String id = miniUtils.next("아이디를 입력 해주세요 >", String.class, n -> findById(n) != null," 다시 입력 해주세요. >");
		User u = findById(id);
		if (u == null) {
			System.out.println("입력한 회원의 아이디가 존재하지 않습니다.");
			return;
		}

		String pw =miniUtils.next("비밀번호 >", String.class,	n -> n != null , "형식에 맞게 비밀번호를 작성 해주세요");
		this.loginUser = u;

		if (!pw.equals(loginUser.getUserPw()) ) {
			System.out.println("입력한 회원의 비밀번호가 틀렸습니다.");
			this.loginUser =null;
			return;
		}
		System.out.println("\n");
		System.out.println(loginUser.getName() + "님 환영합니다!");
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
		String userId = miniUtils.next("아이디를 입력 해주세요 >", String.class, n -> findById(n) == null &&  n.matches("[a-zA-Z]+"), " 영문이 아니거나 존재하는 ID 입니다. 다시 입력 해주세요.");
		User u = findById(userId);
//		String.class, str -> str.matches("[a-zA-Z]+"),
//		if (u != null) {
//			System.out.println("입력한 회원의 아이디가 존재합니다.");
//			return;
		
		String name = miniUtils.next("이름 >", String.class, n -> n != null, " 형식에 맞게 이름을 작성해주세요");
		String pw = miniUtils.next("비밀번호 >", String.class,	n -> n != null , "형식에 맞게 비밀번호를 작성 해주세요");

		String dateInput = miniUtils.next("연월일을 입력해주세요. ex) 20240930", String.class,
				n -> n != null && n.matches("\\d{8}"), "양식에 맞게 작성해주세요");
		String date = dateInput.substring(0, 4) + "/" + dateInput.substring(4, 6) + "/" + dateInput.substring(6);

		users.add(new User(userId, pw, name, date));
	}

	public void modify() {
		String pw = "";
		String name = "";
		int lostMoney = 0;
		String lostDay = "";
		String fixDay = "";
		int fixIncome = 0;
		
		while (true) {
			
			int input1 = miniUtils.next("1.비밀번호수정 2.이름수정 3.고정지출수정 4.고정수입수정 5.전체수정 (종료:0)", Integer.class, n -> 0<= n && n <= 5,"0번~ 5번 사이의 값을 입력 해주세요.");
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
				lostDay = miniUtils.next("매달 고정지출값이 나가는 날짜를 입력 해주세요 ex) 20240930\" ", String.class,
						n -> n != null && n.matches("\\d{8}"),  " 날짜 형식을 확인 해주세요 ");
				String date = lostDay.substring(0, 4) + "/" + lostDay.substring(4, 6) + "/" + lostDay.substring(6);
				lostMoney = miniUtils.next("매달 고정지출값이 나가는 금액을 입력 해주세요", Integer.class, n -> n != null, " 금액을 입력해주세요 ");
				
				loginUser.setLostDay(date);
				loginUser.setLostMoney(lostMoney);
				
				System.out.println(" 고정 지출일이 등록되었습니다. ");
				return;
			case 4:
				fixDay= miniUtils.next("매달 고정수입이 들어오는 날짜를 입력 해주세요 ", String.class,n -> n != null && n.matches("\\d{8}"),  " 날짜 형식을 확인 해주세요 ");
				fixIncome= miniUtils.next("매달 고정수입이 들어오는 금액을 입력 해주세요", Integer.class, n -> n != 0, " 금액을 입력 해주세요");
				 
				 loginUser.setFixDay(fixDay);
				 loginUser.setFixIncome(fixIncome);			 
				 
				 
				 System.out.println(" 고정 수입이 등록되었습니다. ");
				
				return;
			case 5:	
				pw = miniUtils.next("변경 할 비밀번호 : ", String.class, n -> n != null, " 입력하신 비밀번호는 형식에 맞지 않습니다. ");
				loginUser.setUserPw(pw);
				System.out.println("비밀번호 변경이 완료되었습니다.");
				 name =miniUtils.next("변경 할 이름 : ", String.class, str -> str.matches("^[가-힣]{2,4}"),"정확한 이름의 조건을 입력하세요");
				loginUser.setName(name);
				System.out.println("이름 변경이 완료 되었습니다.");
				lostDay = miniUtils.next("매달 고정지출값이 나가는 날짜를 입력 해주세요", String.class, n -> n != " ", " 날짜를 입력해주세요 ");
				lostMoney = miniUtils.next("매달 고정지출값이 나가는 금액을 입력 해주세요", Integer.class, n -> n != null, " 금액을 입력해주세요 ");
				loginUser.setLostDay(lostDay);
				loginUser.setLostMoney(lostMoney);
				System.out.println(" 고정 지출일이 등록되었습니다. ");
				fixDay= miniUtils.next("매달 고정수입이 들어오는 날짜를 입력 해주세요", String.class, n -> n != null, " 날짜를 입력 해주세요");
				fixIncome= miniUtils.next("매달 고정수입이 들어오는 금액을 입력 해주세요", Integer.class, n -> n != 0, " 금액을 입력 해주세요");
				 loginUser.setFixDay(fixDay);
				 loginUser.setFixIncome(fixIncome);	
				 System.out.println(" 고정 수입이 등록되었습니다. ");
				return;
//				 lostDay= miniUtils.next("매달 고정수입을 입력 해주세요", Integer.class, n -> n != 0, " 수입을 입력해주세요");
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
		String pwTmp = miniUtils.next("비밀번호를 입력해주세요.", String.class, n -> findByPw(n) != null, "입력한 비밀번호는 틀렸습니다. 다시 입력해주세요.");

		if (pwTmp != null) {
			User user = findByPw(pwTmp);
			users.remove(user);
			users.remove(loginUser);
			System.out.println("회원탈퇴 완료되었습니다.");
		} else {
			System.out.println("회원정보를 정확히 입력하세요.");
		}
//		System.out.println(users);
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
	private User findByBirthDay(String birth) {
//		User user = null;
		for (User u : users) {
			  if (u.getBirth().equals(birth)) {
		            return u;
			}
		}
		return null;
	}
	

	public void findUser() {
		// 아이디에 맞는 유저 찾기
	    User user = findById(miniUtils.next("아이디", String.class, n -> findById(n) != null, "입력한 아이디는 존재하지 않습니다."));
	    if (user == null) {
	        System.out.println("해당 아이디가 존재하지 않습니다.");
	        return;
	    }
	    
	    // 생년월일 입력 및 검증
	    String birth = miniUtils.next("생년월일을 입력해주세요. ex) 20240930", String.class,
	                                  n -> n != null && n.matches("\\d{8}"), "양식에 맞게 작성해주세요");
	    String formattedBirth = birth.substring(0, 4) + "/" + birth.substring(4, 6) + "/" + birth.substring(6);
	    
	    if (!user.getBirth().equals(formattedBirth)) {
	        System.out.println("입력한 생년월일이 맞지 않습니다.");
	        return;
	    }
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
