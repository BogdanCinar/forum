import java.util.Objects;

public class User {
    private Integer id;
    private String username;
    private String password;
    private String mail;

    public User(Integer id, String username, String password, String mail) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.mail = mail;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return
                Objects.equals(username, user.username) &&
                        Objects.equals(mail, user.mail);
    }

    @Override
    public int hashCode() {

        return Objects.hash(username);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }

    public boolean isThisPasswordMine(String password) {
        if (this.password.equals(password)) {
            return true;
        }
        else{
            return false;
        }
    }
}
