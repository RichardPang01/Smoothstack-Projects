import java.time.Month;
import java.time.Year;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.Scanner;
public class fridayTheThirteenth{
    public static void main(String args[]){
        Scanner myObj = new Scanner(System.in);
        System.out.println("Write a month:");
        Month month = Month.valueOf(myObj.next().toUpperCase());
        System.out.println("Write a day:");
        int day = myObj.nextInt();
        System.out.println("Write a year:");
        int year = myObj.nextInt();
        LocalDate date = Year.of(year).atMonth(month).atDay(day);
        if(date.get(ChronoField.DAY_OF_MONTH)==13&&date.get(ChronoField.DAY_OF_WEEK)==5){
            System.out.println("It is Friday the Thirteenth on " + date);
        }
        else{
            System.out.println("It is not Friday the Thirteenth on " + date);
        }
    }
}