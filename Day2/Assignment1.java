import java.util.Scanner;
public class Assignment1{
   public static void main(String[]args){
        int total = 0;
        Scanner myObj = new Scanner(System.in);
        while(myObj.hasNextInt()){
         total+=myObj.nextInt();
        }
        System.out.println(total);
   }
}