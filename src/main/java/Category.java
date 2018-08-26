import java.util.List;
import java.util.Objects;

public class Category {
    private Integer id;
    private List<Topic> topics;
    private User user;
    private String subject;

    public Category(Integer id, User user, String subject) {
        this.id = id;
        this.user = user;
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    @Override
    public String toString() {
        return "Category{" +
                "#" + id +
                ", user #" + user +
                ", subject='" + subject + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(subject, category.subject);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, topics, user, subject);
    }
}
