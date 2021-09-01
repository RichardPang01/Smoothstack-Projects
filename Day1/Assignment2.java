import java.util.Scanner;
import java.lang.Math;

public class Assignment2{
   public static void main(String args[]){
      int rand = (int)(Math.random() * 100);
      Scanner myObj = new Scanner(System.in);
      int i;
      for(i = 0; i < 5; i++){
         System.out.println("Guess a number:");
         int guess = myObj.nextInt();
         if(Math.abs(rand-guess)<10){
            System.out.println("The correct answer was " + rand);
            break;
         }
      }
      if(i==5){System.out.println("Sorry. The answer was " + rand);}
   }
}
