public class Category {
    private Integer id;
   // private List<Topic> categories;
    private User user;
    private String subject;

    public Category(Integer id, User user, String subject) {
        this.id = id;
        this.user = user;
        this.subject = subject;
    }


    @Override
    public String toString() {
        return "Category{" +
                "subject='" + subject + '\'' +
                '}';
    }
}
