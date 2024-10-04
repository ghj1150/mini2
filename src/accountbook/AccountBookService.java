package accountbook;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import static java.util.Calendar.*;
import mini.miniUtils;

public class AccountBookService {

	private List<AccountBook> loadData = new ArrayList<>();
	private List<AccountBook> tmpDetails = new ArrayList<>();
	 private List<Analyze> rankList = new ArrayList<>();
	private Calendar cal = Calendar.getInstance();
	private String userId;
	private int maxIdx;

    public AccountBookService(String userId){
    	cal.set(DATE, 1);
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
		analyze();
    }
    
    private void loadDefaultData() {
        loadData = new ArrayList<>();
        loadData.add(new AccountBook(1, "이승환", "과자", 1000, 0, "2024/09/30"));
        loadData.add(new AccountBook(2, "한주연", "참치", 0, 10000, "2024/10/30"));
        loadData.add(new AccountBook(3, "이규철", "고등어", 0, 1, "2024/10/30"));
        loadData.add(new AccountBook(4, "이현우", "카레", 0, 1, "2024/09/30"));
        loadData.add(new AccountBook(5, "이승환", "김찌", 0, 1, "2024/10/30"));
		loadData.add(new AccountBook(5, "이승환", "김참", 3, 0, "2024/10/24"));
		loadData.add(new AccountBook(5, "이승환", "김ㅋ", 2, 0, "2024/10/10"));
		loadData.add(new AccountBook(5, "이승환", "김ㄷ", 5, 0, "2024/10/20"));
    }


     public void analyze(){
	 	List<AccountBook> tmpList = new ArrayList<>();
//	 	List<Analyze> analyzeList = new ArrayList<>();
	 	
	 	String dateTmp= cal.get(Calendar.YEAR) +"/"+ String.format("%02d", (cal.get(Calendar.MONTH)+1)); //현재 달력 날짜
	 	for (int i=0; i < loadData.size(); i++){
	 		if (dateTmp.equals(loadData.get(i).getDate().substring(0, 7))){
	 			tmpList.add(loadData.get(i));
	 		}
	 	}
	 	
	 	if(tmpList.isEmpty() || tmpList == null) return;
	 	tmpList.sort((o1,o2)->(o1.getuserId()).compareTo(o2.getuserId()));
		
		int cnt = -1;
	 	for (int i = 0; i < tmpList.size(); i++){
	 		int totalIncome=0;
	 		int totalLosses=0;
	 		int remainMoney=0;
	 		int expenseIncomeRate=0;
	 		
	 		
	 		
	 		 
	 		
	 		if(i!=0 && tmpList.get(i).getuserId().equals(tmpList.get(i-1).getuserId())){
	 			Analyze tmp = rankList.get(cnt);  
	 			
	 			tmp.setTotalIncome(tmp.getTotalIncome() + tmpList.get(i).getIncome());
	 			tmp.setTotalLosses(tmp.getTotalLosses() + tmpList.get(i).getLosses());
	 			tmp.setRemainMoney(tmp.getTotalIncome() - tmp.getTotalLosses());
	 			
	 			if(tmp.getTotalIncome()==0){
	 				tmp.setExpenseIncomeRate(tmp.getRemainMoney());
	 			}else{
	 				tmp.setExpenseIncomeRate( (int)((tmp.getRemainMoney() / (double)tmp.getTotalIncome())*100) );
	 			}
	 			
	 		}else{
	 			totalIncome = tmpList.get(i).getIncome();
	 			totalLosses = tmpList.get(i).getLosses();
	 			remainMoney = totalIncome-totalLosses;
	 			
	 			if(totalIncome==0){
	 				expenseIncomeRate = (int)(remainMoney/1);
	 			}else{
	 				expenseIncomeRate = (int)((remainMoney / (double)totalIncome)*100);
	 			}

	 			rankList.add(new Analyze(tmpList.get(i).getuserId(), totalIncome, totalLosses, remainMoney, expenseIncomeRate));
	 			cnt++;
	 			continue;
	 		}
	 	}
	 	analyzeRankPrint();
	 	
	 }
     
     public void analyzeRankPrint(){
    	 
//    	 if()
    	 
    	 rankList.sort((o1,o2)->(o2.getExpenseIncomeRate() - o1.getExpenseIncomeRate()));
    	 System.out.println(rankList);
    	 miniUtils.markPrint("=", "★저축 랭킹★");
    	 
    	 for(int i =0; i< rankList.size(); i++) {
    		 String name = "";
    		 if(i==0) {
    			 name = "🜲"+rankList.get(i).getUserId()+"🜲";
    		 }else {
    			 name = rankList.get(i).getUserId()+"   ";
    		 }
    		 System.out.printf("%20d. %10s    %15d %10s\n",i+1,name, rankList.get(i).getExpenseIncomeRate(), "%" );
    	 }
     }
     
    // 메인 메뉴
    public void accountBookMenu(){
		while(true){
			printCal();
			accountBookProc();
			accountBookPrint();
			int input = miniUtils.next("1.날짜변경 2.가계부작성 3.수정 4.삭제 5.메인메뉴", Integer.class,n -> 1 <= n && n <= 5, "1~5사이의 숫자만 입력해주세요.");
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
    // 달력 메뉴
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
				String dateInput = miniUtils.next("연월일을 입력해주세요. ex) 202409", String.class,n -> n != null && n.matches("\\d{6}"), "양식에 맞게 작성해주세요");
				String date = dateInput.substring(0, 4) + "/" + dateInput.substring(4);
				try {
					cal.setTime(new SimpleDateFormat("yyyy/MM").parse(date));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				break;
			case 0:
				return;
			default:
				break;
			}
			printCal();
			accountBookProc();
			accountBookPrint();
			
		}
    }
    
    public void accountBookPrint() {

		miniUtils.markPrint("-", userId+"님 가계부 목록");
    	
    	if(tmpDetails.isEmpty() || tmpDetails == null) {
    		System.out.println();
    		System.out.printf("%22s",userId+"님 가계부 목록 비어있습니다.\n");
    		System.out.println();
    		miniUtils.markPrint("-");
    		return;
    	} 
    	
    	System.out.printf("[ %2s | %-20s | %8s | %8s | %8s ]\n", "번호", "내용", "수입", "지출", "날짜");
    	miniUtils.markPrint("-");
    	for (int i = 0; i < tmpDetails.size(); i++) {
    		AccountBook tmpData = tmpDetails.get(i);
    		System.out.printf("[ %04d | %-20s | %10s | %10s | %10s ]\n", 
    				i+1, tmpData.getDetail(), tmpData.getIncome(), tmpData.getLosses(), tmpData.getDate());
    	}
    	miniUtils.markPrint("-");
    }
    
    public void accountBookProc() {
    	tmpDetails = new ArrayList<>();
    	for(AccountBook ac : loadData) {
    		
    		String dateTmp= cal.get(Calendar.YEAR) +"/"+ String.format("%02d", (cal.get(Calendar.MONTH)+1));
    		
    		if (!ac.getuserId().equals(userId)) {
    			continue;
    		} else if(ac.getDate().substring(0,7).equals(dateTmp)) {
    			tmpDetails.add(ac);
    		}
    		
    	}
    }
    
	public void printCal() {
		
		miniUtils.markPrint("=");
		System.out.printf("%40s\n",cal.get(Calendar.YEAR) +" / "+ String.format("%02d", (cal.get(Calendar.MONTH)+1)));
		miniUtils.markPrint("=");
		int lastDate = cal.getActualMaximum(Calendar.DATE);
		int startDay = cal.get(Calendar.DAY_OF_WEEK);
		System.out.printf("%20s","");
		for(int i = 1 - startDay + 1 ; i <= lastDate ; i++) {
			
			if(i > 0) {
				System.out.printf("%4d", i);
			}
			else {
				System.out.print("    ");
			}
			if(i % 7 == (7 - startDay + 1) % 7) {
				System.out.printf("\n%20s","");
			}
		}
		System.out.println();
		miniUtils.markPrint("=");
		
	}
    
    public void add() {
    	int income = 0;
    	int losses = 0;
    	
    	String str = miniUtils.next("가계부에 쓸 내용을 입력하세요.(MAX : 11 글자)", String.class, n -> n.length() < 12 && n != null, "11 글자 이하의 내용으로 작성해주세요");
    	String dateInput = miniUtils.next("연월일을 입력해주세요. ex) 20240930", String.class,n -> n != null && n.matches("\\d{8}"), "양식에 맞게 작성해주세요");
    	String date = dateInput.substring(0, 4) + "/" + dateInput.substring(4, 6) + "/" + dateInput.substring(6);
    	
    	System.out.println("수입/지출을 입력해주세요.");
    	String cost = miniUtils.next("ex_) 1000 또는 -1000",String.class,n -> n != null && n.matches("-?\\d+"), "제대로 작성해주세요.");
    	
    	if(cost.charAt(0)!='-'){
    		income = Integer.parseInt(cost);
    	}else {
    		losses = Integer.parseInt(cost.substring(1));
    	}
    	
    	loadData.add(new AccountBook(++maxIdx, userId, str, income, losses, date));
    	miniUtils.dataSave("./src/data/accountBook.ser", loadData);
    	System.out.println("가계부 작성을 완료했습니다.");
    }
	
    public void modifyAndRemoveProc() {
    	tmpDetails = new ArrayList<>();
    	for(AccountBook ac : loadData) {
    		if (ac.getuserId().equals(userId)) tmpDetails.add(ac);
    	}
    	
    	System.out.println(tmpDetails);
    }
    
    
    public void modify() {
    	int removeInt = 0;
    	AccountBook target = null;
    	
    	modifyAndRemoveProc();
    	accountBookPrint();
    	
    	int idx = miniUtils.next("수정할 가계부 목록의 번호를 선택해주세요. (종료:0)", Integer.class, n-> 0 <= n && n <= tmpDetails.size(), "번호 선택 실패");
    	if (idx-- == 0) return;
  
    	int input = miniUtils.next("1.내용수정 2.수입/지출수정 3.날짜수정 4.전체수정 (종료:0)", Integer.class, n-> 0 <= n && n <= 4, "번호 선택 실패");
    	if (input == 0) return;
    	
    	
    	for (AccountBook i : loadData) {
    		if(i.getIdx() == tmpDetails.get(idx).getIdx()) {
    			removeInt = idx;
    			target = i;
    		}
    	}
    	
    	switch (input) {
		case 1: 
			String str = miniUtils.next("수정할 내용을 입력하세요.(MAX : 11 글자)", String.class, n -> n.length() < 12 && n != null, "11 글자 이하의 내용으로 작성해주세요");
			target.setDetail(str);
			break;
		case 2:
			System.out.println("수입/지출을 입력해주세요.");
			String cost = miniUtils.next("ex_) 1000 또는 -1000",String.class,n -> n != null && n.matches("-?\\d+"), "제대로 작성해주세요.");
	    	
	    	if(cost.charAt(0)!='-'){
	    		target.setIncome(Integer.parseInt(cost));
	    	}else {
	    		target.setLosses(Integer.parseInt(cost.substring(1)));
	    	}
	    	break;
		case 3:
			String dateInput = miniUtils.next("연월일을 입력해주세요. ex) 20240930", String.class,n -> n != null && n.matches("\\d{8}"), "양식에 맞게 작성해주세요");
			String date = dateInput.substring(0, 4) + "/" + dateInput.substring(4, 6) + "/" + dateInput.substring(6);
	    	target.setDate(date);
	    	break;
		case 4:
			tmpDetails.remove(removeInt);
			add();
	    	break;
		}
    	miniUtils.dataSave("./src/data/accountBook.ser", loadData);
    	System.out.println("수정이 완료 되었습니다.");
	}
    	
    public void remove() {
    	modifyAndRemoveProc();
    	accountBookPrint();
    	
    	int idx = miniUtils.next("삭제할 가계부 목록의 번호를 선택해주세요. (종료:0)", Integer.class, n-> 0 <= n && n <= tmpDetails.size(), "번호 선택 실패");
    	if (idx-- == 0) return;
    	
    	for (AccountBook i : loadData) {
    		if(i.getIdx() == tmpDetails.get(idx).getIdx()) {
    			tmpDetails.remove(idx);
    		}
    	}
    	miniUtils.dataSave("./src/data/accountBook.ser", loadData);
    	System.out.println("삭제가 완료 되었습니다.");
    }
    
}
