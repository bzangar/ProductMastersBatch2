package medium;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository(new UserDataCloudDataSource(),
                new UserDataCashedData());

        UserData userData = userRepository.getData();
        System.out.println(userData);
        System.out.println();

        userData = userRepository.getData();
        System.out.println(userData);
    }
}
