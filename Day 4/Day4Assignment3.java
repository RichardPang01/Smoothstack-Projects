public class Day4Assignment3{
   public static void main(String args[]) throws InterruptedException{

      Producer_Consumer pc = new Producer_Consumer();

      Thread t1 = new Thread(new Runnable() {
         @Override
         public void run() {
            try {
               pc.produce();
            }
            catch(InterruptedException e){
               e.printStackTrace();
            }
         }
      });
      Thread t2 = new Thread(new Runnable() {
         @Override
         public void run() {
            try {
               pc.consume();
            }
            catch(InterruptedException e){
               e.printStackTrace();
            }
         }
      });

      t1.start();
      t2.start();

      t1.join();
      t2.join();

   }

   public static class Producer_Consumer{
      int[] buffer = new int[5];
      int current = 0;
      int value = 0;

      public void produce() throws InterruptedException{
         while(true){
            synchronized (this){
               while(current==5)
                  wait();
               System.out.println("Producer has produced " + value);
               buffer[current++] = value++;

               notify();
               Thread.sleep(1000);
            }
         }
      }
      public void consume() throws InterruptedException{
         while(true){
            synchronized (this)
            {
               while(current == 0)
                  wait();
               System.out.println("Consumer has consumed " + buffer[--current]);
               notify();
               Thread.sleep(1000);
            }
         }
      }

   }
}