package hard;
import medium.UserData;

public class Main {
    public static void main(String[] args) {
        Box<String> box1 = new Box<>();
        box1.setItem("Hello");
        Box<UserData> box2 = new Box<>();
        box2.setItem(new UserData(1, "Zangar", "zangar@mail.com"));
        Box<Integer> box3 = new Box<>();
        box3.setItem(123);

        Box [] boxes = {box1, box2, box3};
        for(Box box: boxes){
            System.out.println(box.getItem()); //выводим содержание боксов через форич
        }

        System.out.println();

        for(Box box: boxes){
            box.showType(); //выводим тип боксов
        }
    }
}
