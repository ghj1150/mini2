package user;
import java.util.ArrayList;
import java.util.List;

import mini.miniUtils;
public class UserService {
	
	private List<User> users =new ArrayList<User>();
	
	
	public void UserStart() {
		while(true) {
			int input = miniUtils.next("1.로그인 2.회원가입 3.비밀번호 찾기 4.정보수정 ", Integer.class, n ->1 <=n && n <= 4, "1~4사이의 값을 입력하세요");
			switch(input) {
			case 1:
				login();
				break;
			case 2:
				add();
				break;
			case 3:
				System.out.println(users);
				break;
			case 4:
				modify();
				break;
			}
		}
	}
	
	
	public void add() {
		String userid = miniUtils.next("아이디를 입력 해주세요", String.class, n -> n != null, " 아이디를 잘못 입력 하였습니다." );
		String pw = miniUtils.next("pw", String.class, n -> n != null, " 비밀번호를 잘못 입력 하였습니다." );
		String name = miniUtils.next("name", String.class, n -> n != null, " 이름을 잘못 입력 하였습니다." );
		String phoneNumber = miniUtils.next("phoneNumber", String.class, n -> n != null, " 전화번호를 확인을 해주세요." );
		String birth = miniUtils.next("birth",String.class, n -> n != null, "생년월일 (YYYY-MM-DD)방식을 확인하여주세요." );
		
		users.add(new User(userid,pw,name,birth));
		System.out.println("회원가입 성공했습니다.");
	}	
	public void login()  {
	
		String idTmp = miniUtils.next("아이디", String.class, n-> findById(n) != null, "입력한 아이디는 존재하지 않습니다.");
		String pwTmp = miniUtils.next("비밀번호", String.class, n-> findByPw(n) != null, "입력한 비밀번호는 틀린 비밀번호니다.");

	
	
		if (idTmp == null || pwTmp == null){
			System.out.println("로그인에 실패 하였습니다.");
		}
		else {
			System.out.println("로그인 성공");
		}
	}

	public void modify() {
		User user = findById (miniUtils.next("아이디", String.class, n ->findById(n) != null, "입력한 아이디는 존재하지 않습니다."));
		String name = miniUtils.next("변경 할 이름 : ", String.class, str -> str.matches("^[가-힣]{2,4}"), "정확한 이름의 조건을 입력하세요" );
		String pw =  miniUtils.next("변경 할 비밀번호 : ", String.class, n -> n != null, " 입력하신 비밀번호는 형식에 맞지 않습니다. ");
		int phoneNumber = miniUtils.next("변경할 휴대폰번호는010과 - 빼고 기입해주세요 ", Integer.class, n ->1 <=n && n <= 11, "변경할 번호 8자리를 입력하세요");//나중에 수정해야함
	}
	
	
	
	private User findById(String userid) {
		User user = null;
		for(int i =0; i < users.size(); i++) {
			if(users.get(i).getUserId().equals(userid) ) {
				user = users.get(i);
			}
		}
		return user;
		
	}
	private User findByPw(String userpw) {
		User user = null;
		for(int i =0; i < users.size(); i++) {
			if(users.get(i).getUserId().equals(userpw) ) {
				user = users.get(i);
			}
		}
		return user;
		
	}
}
