package user;
import java.util.ArrayList;
import java.util.List;

import mini.miniUtils;
public class UserService {
	
	private List<User> users =new ArrayList<User>();
	
	
	public void add() {
		String userid = miniUtils.next("id", String.class, n -> n != null, " 아이디를 입력 해주세요" );
		String pw = miniUtils.next("pw", String.class, n -> n != null, " 비밀번호를 입력 해주세요" );
		String name = miniUtils.next("name", String.class, n -> n != null, " 이름을 입력 해주세요" );
		String birth = miniUtils.next("birth",String.class, n -> n != null, " 생년월일을 입력 해주세요" );
		
		users.add(new User(userid,pw,name,birth));
		list();
	}	
	public void list()  {
	List<User> tmp = null;
System.out.println(users);
	
	}
}
