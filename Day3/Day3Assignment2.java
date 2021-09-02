import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Day3Assignment2{
   public static void main(String args[]){
        String filePath = "./test_file.txt";
        String appendText = "This will be appended.";
        File file = new File(filePath);
        FileWriter fr = null;
        BufferedWriter br = null;
        
        try{
            fr = new FileWriter(file, true);
            br = new BufferedWriter(fr);
            br.write(appendText);
        } catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                 br.close();
                 fr.close();
            } catch(IOException e){
               e.printStackTrace();
            }
         }
   }
}
