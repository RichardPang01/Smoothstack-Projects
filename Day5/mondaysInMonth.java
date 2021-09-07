import java.time.Month;
import java.time.Year;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Scanner;

public class mondaysInMonth{
    public static void main(String args[]){
        Scanner myObj = new Scanner(System.in);
        System.out.println("Write a month:");
        String month = myObj.next();
        Month currentMonth = Month.valueOf(month.toUpperCase());
        LocalDate date = Year.now().atMonth(currentMonth).atDay(1);

        Month month1 = date.getMonth();
        while(month1==currentMonth){
            System.out.println(date);
            date = date.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
            month1 = date.getMonth();
        }

    }
}