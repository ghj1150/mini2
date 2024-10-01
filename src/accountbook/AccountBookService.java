package accountbook;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import mini.miniUtils;

public class AccountBookService {

	private List<AccountBook> loadData = new ArrayList<>();
	private List<AccountBook> tmpDetails = new ArrayList<>();
	private Calendar cal = Calendar.getInstance();
	private String userId;
	private int maxIdx;

    public AccountBookService(String userId){
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
		
		// 데이터 save
		miniUtils.dataSave("./src/data/accountBook.ser", loadData);
		accountBookMenu();
    }
    
    private void loadDefaultData() {
        loadData = new ArrayList<>();
        loadData.add(new AccountBook(1, "이승환", "과자", 1000, 0, "2024/09/30"));
        loadData.add(new AccountBook(2, "한주연", "참치", 0, 10000, "2024/10/30"));
        loadData.add(new AccountBook(3, "이규철", "고등어", 0, 1, "2024/10/30"));
        loadData.add(new AccountBook(4, "이현우", "카레", 0, 1, "2024/09/30"));
        loadData.add(new AccountBook(5, "이승환", "김찌", 0, 1, "2024/10/30"));
    }
    
    
    public void accountBookMenu(){
		while(true){
			printCal(cal);
			accountBookPrint();
			int input = miniUtils.next("1.날짜변경 2.가계부작성 3.수정 4.삭제 5.메인메뉴",Integer.class,n -> 1 <= n && n <= 5, "1~5사이의 숫자만 입력해주세요.");
			switch (input) {
				case 1:
		            CalendarMenu();
					break;
				case 2:
					add();
					break;					
				case 3:
					modify();
					break;					
				case 4:
					remove();
					break;
				case 5:
					System.out.println("종료");
					return;			
				default:
					break;
			}
		}
	}
    
    public void accountBookProc() {

    	for(AccountBook ac : loadData) {
    		
    		String dateTmp= cal.get(Calendar.YEAR) +"/"+ String.format("%02d", (cal.get(Calendar.MONTH)+1));
    		
    		if (!ac.getuserId().equals(userId)) continue;
    		if(ac.getDate().substring(0,7).equals(dateTmp)) {
    			tmpDetails.add(ac);
    		}
    		
    	}
    	
    	System.out.println("=============================");
    }
    
    public void accountBookPrint() {
    	accountBookProc();
    	System.out.printf("%20s",userId+"님 가계부 목록\n");
    	System.out.println("-----------------------------");
    	
    	if(tmpDetails.isEmpty() || tmpDetails == null) {
    		System.out.println();
    		System.out.printf("%22s",userId+"님 가계부 목록 비어있습니다.\n");
    		System.out.println();
    		System.out.println("-----------------------------");
    		return;
    	} 
    	
    	
    	System.out.printf("[ %2s | %-20s | %9s | %9s | %9s ]\n", "번호", "내용", "수입", "지출", "날짜");
    	for (int i = 0; i < tmpDetails.size(); i++) {
    		AccountBook tmpData = tmpDetails.get(i);
    		System.out.printf("[ %03d | %-20s | %10s | %10s | %10s ]\n", 
    				i+1, tmpData.getDetail(), tmpData.getIncome(), tmpData.getLosses(), tmpData.getDate());
    	}
    	
//    	System.out.print("["+cnt +" | "+ ac.getDate() + " | " + ac.getDetail() + " | " );
//		if(ac.getIncome()==0) {
//			System.out.print("-"+ac.getLosses());
//		}else {
//			System.out.print("+"+ac.getIncome());
//		}
		System.out.println("]");
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
			accountBookPrint();
			
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


    
    
    
    
    
    
    

}
