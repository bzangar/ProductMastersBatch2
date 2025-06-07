//package Module_7;
//
//import java.util.Scanner;
//
//public class Piramid {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("h = ");
//        int h = scanner.nextInt();
//        String s = " ";
//        String s1 = s.repeat(h-1);
//        StringBuilder space = new StringBuilder(s1);
//        String star = new String("*");
//
//        for(int i = 0; i < h; i ++){
//            String stars =  star.repeat(i*2 + 1);
//            System.out.println(space + "" + stars);
//            space.deleteCharAt(space.length() - 1);
//
//        }
//    }
//}
