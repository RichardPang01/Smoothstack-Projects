import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Day3Assignment3{
   public static void main(String args[]) throws IOException{
      int count = 0;
      Scanner myObj = new Scanner(System.in);
      System.out.print("Enter a char: ");
      String c = myObj.next();
      char character = c.charAt(0);

      File file = new File(".\\testFiles\\test_file.txt");
      FileReader fr = new FileReader(file);
      
      BufferedReader br = new BufferedReader(fr);

      int read;
      while((read=br.read()) != -1){
         if(((char)read) == character){
            count++;
         }
      }
      System.out.println("The character " + character + " appears " + count + " times.");
      fr.close();
      br.close();
   }
}