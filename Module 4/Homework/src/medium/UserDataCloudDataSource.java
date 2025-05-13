package medium;

public class UserDataCloudDataSource implements DataSource{

    @Override
    public UserData getData() {
        return new UserData(1, "Zangar", "zangar@mail.com");
    }
}
