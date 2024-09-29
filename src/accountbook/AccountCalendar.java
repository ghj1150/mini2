package accountbook;

import java.util.*;
import java.text.*;

public class AccountCalendar {
    static Scanner scanner = new Scanner(System.in);
    static void calendar() {
		
		Calendar cal = Calendar.getInstance(); 	// 2024-09-19
		
		cal.add(Calendar.MONTH, 1);
		
		// 연, 월, 마지막 날짜, 1일의 요일
		int year = cal.get(Calendar.YEAR); 		// 2024
		int month = cal.get(Calendar.MONTH);  	// 8 
		
		System.out.println(year);
		System.out.println(month);
		printCal(cal);
		while(true) {
			System.out.println("1.이전달  2.다음달  3.이전해  4.다음해  5.특정 연월  6.종료");
			switch (scanner.nextInt()) {
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
//				System.out.println("연도 입력 > ");
//				cal.add(Calendar.YEAR, scanner.nextInt());				
//				System.out.println("월 입력 > ");
//				cal.add(Calendar.YEAR, scanner.nextInt());
				System.out.println("연도, 월을 입력해주세요. ex) 2024/09");
				String str = scanner.nextLine();
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
	
	static void printCal(Calendar cal) {
		System.out.println(cal.get(Calendar.YEAR) + "/" + (cal.get(Calendar.MONTH)+1));
		int lastDate = cal.getActualMaximum(Calendar.DATE);
		cal.set(Calendar.DATE, 1);				// 1일로 초기화
		int startDate = cal.get(Calendar.DAY_OF_WEEK); // 1 : 일요일
		
		for(int i = 1 - startDate +1; i <= lastDate; i++) {
			if (i > 0) {
				System.out.printf("%4d",i);				
			}else {
				System.out.print("    ");
			}
			if(i % 7 == startDate) {
				System.out.println();
			}
		}
		System.out.println("\n");
	}
}
