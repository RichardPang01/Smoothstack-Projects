class Singleton{
   private static Singleton single_instance = null;

   private Singleton(){}

   public static Singleton getInstance(){
      if(single_instance == null){
         synchronized (Singleton.class){
            if(single_instance == null){
               single_instance = new Singleton();
            }
         }
      }
      return single_instance;
   }
}

public class Day4Assignment1{
   public static void main(String args[]){
      Singleton s1 = Singleton.getInstance();
   }
}