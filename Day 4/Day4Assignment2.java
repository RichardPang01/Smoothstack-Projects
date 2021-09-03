public class Day4Assignment2{

   public static void main(String args[]){
      String str1 = "first";
      String str2 = "second";

      Thread t1 = new Thread(new Runnable() {
         @Override
         public void run() {
            while(true){
               synchronized (str1){
                  synchronized (str2){
                     System.out.println(str1+str2);
                  }
               }
            }
         }
      });

      Thread t2 = new Thread(new Runnable() {
         @Override
         public void run() {
            while(true){
               synchronized (str2){
                  synchronized (str1){
                     System.out.println(str2+str1);
                  }
               }
            }
         }
      });
      t1.start();
      t2.start();


   }

}