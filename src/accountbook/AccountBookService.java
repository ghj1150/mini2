package accountbook;

import java.util.*;

import mini.miniUtils;

public class AccountBookService {

    private List<AccountBook> loadData = new ArrayList<AccountBook>();
	private List<AccountBook> accountBooks = new ArrayList<AccountBook>();
	private int userIdx;
	private int maxIdx;

    public AccountBookService(int userIdx){
        loadData = miniUtils.dataLoad("./src/data/accountBook.ser");
		if (loadData == null || loadData.isEmpty()) {
            loadDefaultData();
            System.out.println("기본 데이터 추가");
        }
        setting();
    }

    private void loadDefaultData() {
        loadData = new ArrayList<>();
        loadData.add(new AccountBook(1,1,"test1",1,1,"2024/09/30"));
        loadData.add(new AccountBook(2,1,"test2",1,1,"2024/09/30"));
        loadData.add(new AccountBook(3,1,"test3",1,1,"2024/01/03"));
        loadData.add(new AccountBook(4,1,"test4",1,1,"2024/01/03"));
        loadData.add(new AccountBook(5,1,"test5",1,1,"2024/01/03"));
    }

	public void setting(){
		for (AccountBook c : loadData) {
			if (c.getUserIdx() == userIdx) {
				accountBooks.add(c);
			}
		}
		if (!accountBooks.isEmpty()) {
            maxIdx = accountBooks.get(accountBooks.size()-1).getIdx();
        } else {
            maxIdx = 0;
        }
		accountBookMenu();
	}

    public void accountBookMenu(){
    	AccountCalendar ac = new AccountCalendar();
		while(true){
			ac.cal();
			int input = miniUtils.next("1.날짜변경 2.가계부 작성 3.수정 4.삭제 5.종료",Integer.class,n -> 1 <= n && n <= 5, "1~5사이의 숫자만 입력해주세요.");

			switch (input) {
				case 1:
					// 캘린더 표출 - 해야됨
		            ac.CalendarMenu();
					break;
				case 2:
					add();
					break;					
				case 3:
					
					break;					
				case 4:
					break;
				case 5:
					System.out.println("종료");
					return;			
				default:
					break;
			}
		}
	}
    
    public void add() {
    	String str = miniUtils.next("연도, 월을 입력해주세요. ex) 2024/09", String.class, n-> n!=null, "이부분 나중에 수정할것(정규화로 형식 맞춰야됨)");
    	
    	
    }
}
