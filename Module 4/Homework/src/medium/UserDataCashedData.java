package medium;

public class UserDataCashedData implements Mutable{
    UserData userData;

    @Override
    public UserData getData() {
        return userData;
    }

    @Override
    public void saveData(UserData userData) {
        this.userData = userData;

    }
}
