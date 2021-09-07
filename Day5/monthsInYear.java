import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.Scanner;

public class monthsInYear{
    public static void main(String args[]){
        Scanner myObj = new Scanner(System.in);
        System.out.println("Write a year:");
        int year = myObj.nextInt();
        Year givenYear = Year.of(year);
        for(Month month: Month.values()){
            YearMonth ym = YearMonth.of(year,month);
            System.out.println(month + ": " + ym.lengthOfMonth() + " days\n");
        }
    }
}