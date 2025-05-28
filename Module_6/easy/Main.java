package Module_6.easy;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        ListClass lc = new ListClass();
        lc.numbers = Arrays.asList(1, 3, 5, 7, 9, 2, 3);

        System.out.println("Заданный список: " + lc.numbers);

        //1
        System.out.println("Минимальный элемент списка: " + lc.findMin());

        //2
        System.out.println("Максимальный элемент списка: " + lc.findMax());

        //3
        System.out.println("Новый список отсортированный по возростанию: " + lc.sortList());

        //4
        System.out.println("Новый список отсортированный по убыванию: " + lc.sortListReverse());


        //5
        int a = 5;
        System.out.println("Есть ли число " + a + " в списке: " + lc.findElement(a));

        //6
        int b = 3;
        System.out.println("Новый массив больше числа " + b + ": " + lc.filterList(b));

        //7
        System.out.println("Сумма всех чисел списка: " + lc.sum());




    }
}
