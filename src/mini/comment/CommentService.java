package mini.comment;

import java.util.*;
import mini.comment.Comment;

public class CommentService {
    
    private List<Comment> comments = new ArrayList<Comment>();

    {
        comments.add(new Comment(1, "tet1",1, 0, 1, null));
        comments.add(new Comment(2, "tet2",1, 1, 1, null));
        comments.add(new Comment(3, "tet3",2, 0, 1, null));
        System.out.println(comments);
    }

    public void commentView(int postIdx){
        for(Comment c : comments){
            if(c.getPost_idx()==postIdx){
                System.out.println(c.getComment());
            }
        }
        
    }
}
