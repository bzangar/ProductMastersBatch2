package medium;

public class UserData {
    public int id;
    public String name;
    public String email;

    @Override
    public String toString() {
        return "UserData{" + "id=" + id +
                ", name='" + name  +
                ", email='" + email  +
                '}';
    }

    public UserData(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
