package Module_6.hard;

public class Main {
    public static void main(String[] args) {
        SafeList<String> list = new SafeList<>();
        list.add("banana");
        list.add("apple");
        list.add("apple"); //не добавляется
        list.add(null); //не добавляется
        System.out.println(list); //[banana, apple]

        System.out.println(list.get(0)); // banana
        System.out.println(list.get(100)); // null
    }
}
