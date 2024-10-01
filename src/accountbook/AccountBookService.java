package accountbook;

import java.util.*;

import mini.miniUtils;

public class AccountBookService {

    private List<AccountBook> loadData = new ArrayList<AccountBook>();
	private List<AccountBook> accountBooks = new ArrayList<AccountBook>();
	private AccountCalendar ac;

    public AccountBookService(String userId){
    	ac = new AccountCalendar(userId);
    	accountBookMenu();
    }

    public void accountBookMenu(){
    	
		while(true){
			ac.cal();
			int input = miniUtils.next("1.날짜변경 2.가계부 작성 3.수정 4.삭제 5.종료",Integer.class,n -> 1 <= n && n <= 5, "1~5사이의 숫자만 입력해주세요.");

			switch (input) {
				case 1:
					// 캘린더 표출 - 해야됨
		            ac.CalendarMenu();
					break;
				case 2:
					ac.add();
					break;					
				case 3:
					ac.modify();
					break;					
				case 4:
					ac.remove();
					break;
				case 5:
					System.out.println("종료");
					return;			
				default:
					break;
			}
		}
	}
    
    

}
