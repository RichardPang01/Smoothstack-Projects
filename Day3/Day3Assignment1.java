import java.io.File;

public class Day3Assignment1{
   public static void main(String args[]){
      File directory = new File(".");
      File[] listOfFiles = directory.listFiles();
      exploreDirectory(listOfFiles);
   }
   public static void exploreDirectory(File [] listOfFiles){
      for (int i = 0; i < listOfFiles.length; i++) {
         if (listOfFiles[i].isFile()) {
            System.out.println("File " + listOfFiles[i].getName());
         } else if (listOfFiles[i].isDirectory()) {
            System.out.println("Directory " + listOfFiles[i].getName());
            exploreDirectory(listOfFiles[i].listFiles());
         }
      }
   }
}