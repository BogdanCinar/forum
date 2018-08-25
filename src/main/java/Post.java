public class Post {

    private Integer id;
    private User user;
    private Topic topic;
    private String text;
    private String title;


    public Post(Integer id, User user, Topic topic, String text, String title) {
        this.id = id;
        this.user = user;
        this.topic = topic;
        this.text = text;
        this.title = title;
    }

}
