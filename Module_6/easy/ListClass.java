package Module_6.easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListClass {
    List<Integer> numbers;

    int findMin(){
        return Collections.min(numbers);
    }

    int findMax(){
        return Collections.max(numbers);
    }

    List<Integer> sortList(){
        List<Integer> result = new ArrayList<>(numbers);
        Collections.sort(result);
        return result;

    }

    List<Integer> sortListReverse(){
        List<Integer> result = new ArrayList<>(numbers);
        Collections.sort(result);
        Collections.reverse(result);
        return result;
    }

    boolean findElement(int a){
        return numbers.contains(a);
    }

    List<Integer> filterList(int a){
        List<Integer> result = new ArrayList<>();

        for(int b: numbers){
            if(b > a){
                result.add(b);
            }
        }
        return result;
    }

    int sum(){
        int sum = 0;

        for(int a: numbers){
            sum += a;
        }

        return sum;
    }

 }
