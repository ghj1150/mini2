package accountbook;

import java.util.*;


import java.text.*;
import mini.miniUtils;


public class AccountCalendar {
    private Calendar cal = Calendar.getInstance(); 	// 2024-09-19
	private List<AccountBook> loadData = new ArrayList<>();
	private List<AccountBook> tmpDetails = new ArrayList<>();
	private int userIdx;
	private int maxIdx;
	
    public AccountCalendar() {
		cal.add(Calendar.MONTH, 1);
		this.userIdx = userIdx;
		loadData = miniUtils.dataLoad("./src/data/accountBook.ser");

		if (loadData == null || loadData.isEmpty()) {
            loadDefaultData();
            System.out.println("기본 데이터 추가");
        }

		// 데이터 테스트 용
		// System.out.println(loadData);
		// 데이터 save
		 miniUtils.dataSave("./src/data/accountBook.ser", loadData);

//		setting();
	}

	private void loadDefaultData() {
        loadData = new ArrayList<>();
        loadData.add(new AccountBook(1, 1, "1", 1000, 0, "2024/09/30"));
        loadData.add(new AccountBook(2, 1, "22", 0, 10000, "2024/10/30"));
        loadData.add(new AccountBook(3, 1, "333", 0, 1, "2024/10/30"));
        loadData.add(new AccountBook(4, 2, "4444", 0, 1, "2024/09/30"));
        loadData.add(new AccountBook(5, 1, "55555", 0, 1, "2024/09/30"));
    }

		
		

	
    public void CalendarMenu() {
    	while(true) {
			int input = miniUtils.next("1.이전달  2.다음달  3.이전해  4.다음해  5.특정 연월  (종료:0)", Integer.class, n-> 0 <= n && n<6, "0~5번 작성");
			
			switch (input) {
			case 1: 
				cal.add(Calendar.MONTH,-1);
				break;
			case 2: 
				cal.add(Calendar.MONTH,+1);
				break;
			case 3: 
				cal.add(Calendar.YEAR,-1);
				break;
			case 4: 
				cal.add(Calendar.YEAR,+1);
				break;
			case 5:
				String str = miniUtils.next("연도, 월을 입력해주세요. ex) 2024/09", String.class, n-> n!=null, "이부분 나중에 수정할것(정규화로 형식 맞춰야됨)");
				try {
					cal.setTime(new SimpleDateFormat("yyyy/MM").parse(str));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				break;
			default:
				break;
			}
			printCal(cal);
			
		}
    }
    
    public Calendar cal() {
    	printCal(cal);
    	int cnt = 1;
    	System.out.println("가계부 목록");
    	System.out.println("-----------------------------");
    	for(AccountBook ac : loadData) {
    		String tmp= ac.getDate().substring(0,7);
    		if(tmp.equals(cal.get(Calendar.YEAR) +"/"+ (cal.get(Calendar.MONTH)+1))) {
    			tmpDetails.add(ac);
    			System.out.print(cnt +". "+ ac.getDate() + ": " + ac.getDetail());
    			if(ac.getIncome()==0) {
    				System.out.println("   -"+ac.getLosses());
    			}else {
    				System.out.println("   +"+ac.getIncome());
    			}
    			cnt++;
    		}
    	}
    	System.out.println("=============================");
    	return cal;
    }
    
    
	public void printCal(Calendar cal) {
		System.out.println("=============================");
		System.out.println("         "+ cal.get(Calendar.YEAR) +" / "+ (cal.get(Calendar.MONTH)+1));
		System.out.println("=============================");
		int lastDate = cal.getActualMaximum(Calendar.DATE);
		int startDay = cal.get(Calendar.DAY_OF_WEEK);
		for(int i = 1 - startDay + 1 ; i <= lastDate ; i++) {
			if(i > 0) {
				System.out.printf("%4d", i);
			}
			else {
				System.out.print("    ");
			}
			if(i % 7 == (7 - startDay + 1) % 7) {
				System.out.println();
			}
		}
		System.out.println();
		System.out.println("=============================");
		
	}
}
