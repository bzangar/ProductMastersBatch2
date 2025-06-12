package Module_7;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        int nums[] = {0,0,1,1,1,2,2,3,3,4};

        Set<Integer> n = new HashSet<>();

        for(Integer a: nums){
            n.add(a);
        }
        System.out.println(n);
        System.out.println(n.size());

    }
}
