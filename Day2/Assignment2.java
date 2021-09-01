public class Assignment2{
   public static void main(String args[]){
      int [][] arr = {{5, 3, 10}, {3, 9, 20}, {12, 8, 7}};
      int largestRow = 0;
      int largestCol = 0;
      int currentLargest = arr[0][0];
      
      for(int r = 0; r<arr.length; r++){
         for(int c = 0; c<arr[0].length; c++){
            if(arr[r][c]>currentLargest){
               largestRow = r;
               largestCol = c;
               currentLargest = arr[r][c];
            }
         }
      }
      System.out.println("The largest number is " + currentLargest + " at Row: " + largestRow + " Col: " + largestCol);
   }
}