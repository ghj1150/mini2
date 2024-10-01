package mini;

import java.util.*;
import java.util.function.Predicate;
import java.io.*;

public class miniUtils {
    static Scanner scanner = new Scanner(System.in);
    
    // 입력 통합
    public static <T> T next(String msg, Class<T> clazz) {
		System.out.println(msg);
		System.out.print("> ");
		String str = scanner.nextLine();
		if (clazz == Integer.class) {
			return clazz.cast(Integer.parseInt(str));
		} else if (clazz == String.class) {
			return clazz.cast(str);
		} else {
			throw new RuntimeException("잘못된 타입");
		}
	}
    // 입력 반복
    public static <T> T next(String msg, Class<T> clazz, Predicate<T> con, String errMsg) {
        while (true) {
            try {
                T t = next(msg, clazz);
                if (con.test(t)) {
                    return t;
                } else {
                    throw new IllegalArgumentException(errMsg);
                }
            } catch (NumberFormatException iae) {
                System.out.println("올바른 숫자를 입력해라");
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
            }
        }
    }

     // data 가져오기 - 수정중 groot
	@SuppressWarnings("unchecked")
	public static <T> T dataLoad(String str){
		 try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(str))) {
			 	return (T) ois.readObject();
			} catch (FileNotFoundException e) {
				System.out.println("파일 검색 실패");
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		return null;
	}
	public static <T> void dataSave(String str, T t){
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(str))) {
			oos.writeObject(t);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
