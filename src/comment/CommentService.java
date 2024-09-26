package comment;

import java.util.*;

import mini.miniUtils;

public class CommentService {
    
    private List<Comment> comments = new ArrayList<Comment>();

    {
    	// 데이터 로드
    	comments = miniUtils.dataLoad("./src/data/test.ser");
    	
        if(comments==null) {
        	comments.add(new Comment(1, "tet1",1, 0, 1, null));
            comments.add(new Comment(2, "tet2",1, 1, 1, null));
            comments.add(new Comment(3, "tet3",2, 0, 1, null));
            System.out.println("기본 데이터 추가");
		}
        // 데이터 테스트 용
        System.out.println(comments);
        // 데이터 save
    	miniUtils.dataSave("./src/data/test.ser",comments);
    }

    public void commentView(int postIdx){
        for(Comment c : comments){
            if(c.getPost_idx()==postIdx){
                System.out.println(c.getComment());
            }
        }
        
    }
}
