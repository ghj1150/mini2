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
        loadData.add(new AccountBook(1,1,1,1,"test1"));
        loadData.add(new AccountBook(2,2,1,1,"test2"));
        loadData.add(new AccountBook(3,3,1,1,"test3"));
        loadData.add(new AccountBook(4,4,1,1,"test4"));
        loadData.add(new AccountBook(5,5,1,1,"test5"));
    }

	public void setting(){
		// 게시글에 맞는 데이터 추출
		for (AccountBook c : loadData) {
			if (c.getUserIdx() == userIdx) {
				accountBooks.add(c);
			}
		}
		if (!accountBooks.isEmpty()) {
            maxIdx = accountBooks.get(accountBooks.size()-1).getIdx();
        } else {
            maxIdx = 0; // 댓글이 없는경우
        }
		accountBookMenu();
	}

    public void accountBookMenu(){
		while(true){
			// 캘린더 표출 - 해야됨
            AccountCalendar ac = new AccountCalendar();
            ac.calendar();
			int input = miniUtils.next("1.날짜변경 2.가계부 작성 3.수정 4.삭제 5.종료",Integer.class,n -> 1 <= n && n <= 5, "1~5사이의 숫자만 입력해주세요.");

			switch (input) {
				case 1:
					
					break;
				case 2:
					
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
}
