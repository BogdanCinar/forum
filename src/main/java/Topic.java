import java.util.List;

public class Topic {
    private Integer id;
    private User user;
    private List<Post> posts;
    private Category category;
    private String subject;


    public String getSubject() {
        return subject;
    }
}
