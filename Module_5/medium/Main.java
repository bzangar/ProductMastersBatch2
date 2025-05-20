package Module_5.medium;

import Module_5.easy.DayOfWeek;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        List<DayOfWeek> daysOfWeek = new ArrayList<DayOfWeek>();
        daysOfWeek.add(DayOfWeek.MONDAY);
        daysOfWeek.add(DayOfWeek.TUESDAY);
        daysOfWeek.add(DayOfWeek.WEDNESDAY);
        daysOfWeek.add(DayOfWeek.THURSDAY);
        daysOfWeek.add(DayOfWeek.FRIDAY);
        daysOfWeek.add(DayOfWeek.SATURDAY);
        daysOfWeek.add(DayOfWeek.SUNDAY);

        for(DayOfWeek day: daysOfWeek){
            System.out.println(day);
        }

        DayOfWeek day = DayOfWeek.SATURDAY;


        System.out.println("Какой сегодня день? - " + day);
        System.out.println("Сегодня выходной? - " + isWeekend(day));
    }

    public static boolean isWeekend(DayOfWeek day){
        if(day == DayOfWeek.SUNDAY || day == DayOfWeek.SATURDAY){
            return true;
        }
        else{
            return false;
        }
    }
}
