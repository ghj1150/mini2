package accountbook;

import java.util.*;


import java.text.*;
import java.time.LocalDate;

import mini.miniUtils;


public class AccountCalendar {
    private Calendar cal = Calendar.getInstance(); 	// 2024-09-19
	private List<AccountBook> loadData = new ArrayList<>();
	private List<AccountBook> tmpDetails = new ArrayList<>();
	private String userId;
	private int maxIdx;
	
    public AccountCalendar(String userId) {
    	this.userId = userId;
		loadData = miniUtils.dataLoad("./src/data/accountBook.ser");

		if (loadData == null || loadData.isEmpty()) {
            loadDefaultData();
            System.out.println("기본 데이터 추가");
        }
		if (!loadData.isEmpty()) {
	          maxIdx = loadData.get(loadData.size()-1).getIdx();
	      } else {
	          maxIdx = 0;
	      }
		// 데이터 테스트 용
		// System.out.println(loadData);
		// 데이터 save
		 miniUtils.dataSave("./src/data/accountBook.ser", loadData);

//		setting();
	}

	private void loadDefaultData() {
        loadData = new ArrayList<>();
        loadData.add(new AccountBook(1, "test", "1", 1000, 0, "2024/09/30"));
        loadData.add(new AccountBook(2, "test1", "22", 0, 10000, "2024/10/30"));
        loadData.add(new AccountBook(3, "test2", "333", 0, 1, "2024/10/30"));
        loadData.add(new AccountBook(4, "test3", "4444", 0, 1, "2024/09/30"));
        loadData.add(new AccountBook(5, "test1", "55555", 0, 1, "2024/09/30"));
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
		    	String str = miniUtils.next("연도, 월을 입력해주세요. ex) 2024/09/30", String.class, n-> n!=null, "이부분 나중에 수정할것(정규화로 형식 맞춰야됨)");
				try {
					cal.setTime(new SimpleDateFormat("yyyy/MM").parse(str));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				break;
			case 0:
				return;
			default:
				break;
			}
			cal();
			
		}
    }
    
    
	public void add() {
    	int cost=0;
    	int input = miniUtils.next("1.수입 2.지출 (종료:0)",Integer.class,n -> 0 <= n && n <= 2, "0~2사이의 숫자만 입력해주세요.");
    	String str = miniUtils.next("내용을 입력하세요.", String.class, n-> n!=null, "이부분 나중에 수정할것(정규화로 형식 맞춰야됨)");
    	String date = miniUtils.next("연도, 월을 입력해주세요. ex) 2024/09/30", String.class, n-> n!=null, "이부분 나중에 수정할것(정규화로 형식 맞춰야됨)");
    	if (input == 1) {
    		cost = miniUtils.next("수입을 입력해주세요.",Integer.class,n -> n !=0 , "제대로 작성해주세요.");
    		loadData.add(new AccountBook(++maxIdx,userId,str,cost,0,date));
    	}else {
    		cost = miniUtils.next("지출을 입력해주세요.",Integer.class,n -> n !=0 , "제대로 작성해주세요.");
    		loadData.add(new AccountBook(++maxIdx,userId,str,0,cost,date));
    	}
    	
    	System.out.println("가계부 작성을 완료했습니다.");
    	
    }
	
    public void modify() {
    	System.out.println(cal.get(Calendar.YEAR) +"/"+ (cal.get(Calendar.MONTH)+1) + " 목록");
    	System.out.println("-----------------------------");
    	for (int i = 0; i < tmpDetails.size(); i++) {
    		int num = i+1;
    		System.out.print("["+ num +" | "+ tmpDetails.get(i).getDate() + " | " + tmpDetails.get(i).getDetail() + " | " );
			if(tmpDetails.get(i).getIncome()==0) {
				System.out.print("-"+tmpDetails.get(i).getLosses());
			}else {
				System.out.print("+"+tmpDetails.get(i).getIncome());
			}
			System.out.println("]");
    	}
    	
    	int input = miniUtils.next("수정할 가계부 목록의 번호를 선택해주세요. (종료:0)", Integer.class, n-> 0 <= n && n <= tmpDetails.size(), "번호 선택 실패");
    	if (input == 0) return;
    	input--;
    	
    	for (AccountBook i : loadData) {
    		
    		if(i.getIdx() == tmpDetails.get(input).getIdx()) {
    			int cost=0;
    	    	String str = miniUtils.next("내용을 입력하세요.", String.class, n-> n!=null, "이부분 나중에 수정할것(정규화로 형식 맞춰야됨)");
    	    	String date = miniUtils.next("연도, 월을 입력해주세요. ex) 2024/09/30", String.class, n-> n!=null, "이부분 나중에 수정할것(정규화로 형식 맞춰야됨)");
    	    	int tmp = miniUtils.next("1.수입 2.지출 (종료:0)",Integer.class,n -> 0 <= n && n <= 2, "0~2사이의 숫자만 입력해주세요.");
    	    	if (tmp == 1) {
    	    		cost = miniUtils.next("수입을 입력해주세요.",Integer.class,n -> n !=0 , "제대로 작성해주세요.");
    	    		i.setIncome(cost);
    	    		i.setLosses(0);
    	    	}else {
    	    		cost = miniUtils.next("지출을 입력해주세요.",Integer.class,n -> n !=0 , "제대로 작성해주세요.");
    	    		i.setIncome(0);
    	    		i.setLosses(cost);
    	    	}
    	    	
    	    	i.setDate(date);
    	    	i.setDetail(str); 
    	    	System.out.println("수정이 완료되었습니다.");
    	    	break;
	    	}
		}
	}
    	
    public void remove() {
    	System.out.println(cal.get(Calendar.YEAR) +"/"+ (cal.get(Calendar.MONTH)+1) + " 목록");
    	System.out.println("-----------------------------");
    	for (int i = 0; i < tmpDetails.size(); i++) {
    		int num = i+1;
    		System.out.print("["+ num +" | "+ tmpDetails.get(i).getDate() + " | " + tmpDetails.get(i).getDetail() + " | " );
			if(tmpDetails.get(i).getIncome()==0) {
				System.out.print("-"+tmpDetails.get(i).getLosses());
			}else {
				System.out.print("+"+tmpDetails.get(i).getIncome());
			}
			System.out.println("]");
    	}
    	
    	int input = miniUtils.next("수정할 가계부 목록의 번호를 선택해주세요. (종료:0)", Integer.class, n-> 0 <= n && n <= tmpDetails.size(), "번호 선택 실패");
    	if (input == 0) return;
    	input--;
    	
    	for (int i=0; i <= loadData.size(); i++) {
    		if(loadData.get(i).getIdx() == tmpDetails.get(input).getIdx()) {
    			loadData.remove(i);
    	    	break;
	    	}
		}
	}


	
	
    public Calendar cal() {
    	printCal(cal);
    	int cnt = 1;
    	System.out.println("가계부 목록");
    	System.out.println("-----------------------------");
    	for(AccountBook ac : loadData) {
    		String tmp= ac.getDate().substring(0,7);
    		if (!ac.getuserId().equals(userId)) continue;
    		if(tmp.equals(cal.get(Calendar.YEAR) +"/"+ String.format("%02d", (cal.get(Calendar.MONTH)+1)))) {
    			tmpDetails.add(ac);
    			System.out.print("["+cnt +" | "+ ac.getDate() + " | " + ac.getDetail() + " | " );
    			if(ac.getIncome()==0) {
    				System.out.print("-"+ac.getLosses());
    			}else {
    				System.out.print("+"+ac.getIncome());
    			}
    			System.out.println("]");
    			cnt++;
    		}
    	}
    	
    	System.out.println("=============================");
    	return cal;
    }
    
    
	public void printCal(Calendar cal) {
		System.out.println("=============================");
		System.out.println("         "+ cal.get(Calendar.YEAR) +" / "+ String.format("%02d", (cal.get(Calendar.MONTH)+1)));
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
