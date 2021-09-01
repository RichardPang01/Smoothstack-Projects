public class Assignment1{
   public static void main(String [] args){
      System.out.println("1)");
      for(int i = 1; i < 5; i++){
         for(int r = i; r > 0; r--){System.out.print("*");}
         System.out.print("\n");
      }
      System.out.println(".........");
      System.out.println("2)");
      System.out.println(".........");
      for(int i = 1; i < 5; i++){
         for(int r = 5-i; r > 0; r--){System.out.print("*");}
         System.out.print("\n");
      }
      System.out.println("3)");
      for(int i = 1; i < 5; i++){
         for(int space = 6 - i; space>0; space--){System.out.print(" ");}
         for(int star = i*2-1; star>0; star--){System.out.print("*");}
         System.out.print("\n");
      }
      System.out.println("...........");
      System.out.println("4)");
      System.out.println("...........");
      for(int i = 4; i > 0; i--){
         for(int space = 6 - i; space>0; space--){System.out.print(" ");}
         for(int star = i*2-1; star>0; star--){System.out.print("*");}
         System.out.print("\n");
      }
   }
}
