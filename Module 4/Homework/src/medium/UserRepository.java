package medium;

public class UserRepository implements DataSource{
    private final UserDataCloudDataSource userDataCloudDataSource;
    private final UserDataCashedData userDataCashedData;

    public UserRepository(UserDataCloudDataSource userDataCloudDataSource,
                          UserDataCashedData userDataCashedData) {
        this.userDataCloudDataSource = userDataCloudDataSource;
        this.userDataCashedData = userDataCashedData;
    }

    @Override
    public UserData getData() {
        UserData result = userDataCashedData.getData();

        if(result == null){
            System.out.println("В кэше нет данных");
            result = userDataCloudDataSource.getData(); //если кэш пуста берем данные из сети
            userDataCashedData.saveData(result); // потом храним данные в кэщ
        } else{
            System.out.println("Данные взяты из кэша: ");

        }
        return result;
    }
}
