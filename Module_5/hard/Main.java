package Module_5.hard;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Сколько чисел нужно добавить в лист? (n = ): ");
        int n = scanner.nextInt();

        ArrayList<Integer> list = new ArrayList<Integer>();

        for(int i = 0; i < n; i++){

            System.out.print("Введите цифры: ");
            int number = scanner.nextInt();
            list.add(number);
        }

        System.out.println(list);
        System.out.println();

        removeDublicates(list);
        System.out.println("Лист после удаления дубликатов: ");
        System.out.println(list);
    }

    public static void removeDublicates(ArrayList<Integer> list){
        for(int i = 0; i < list.size() - 1; i++){
            for(int j = i+1; j < list.size(); j++){
                if(list.get(i).equals(list.get(j))){
                    list.remove(j);
                    j--;
                }
            }
        }
    }
}


