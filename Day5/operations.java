import java.io.*;
import java.util.*;

interface operate {
    boolean check(int a);
}
class calculations{
    public static boolean checker(operate p, int num) {
        return p.check(num);
    }

    public static operate isOdd()
    { return x -> x % 2 != 0; }
    public static operate isPalindrome()
    { return x -> x == Integer.parseInt(new StringBuilder(String.valueOf(x)).reverse().toString()) ? true : false; }
    public static operate isPrime(){
        return a ->{ for (int i = 2; i * i <= a; i++) { if (a % i == 0) { return false; } }
            return true;
        };
    }

}
public class operations {
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int counter = Integer.parseInt(br.readLine());
        calculations calc = new calculations();
        while(counter-->0){
            String str = br.readLine().trim();
            StringTokenizer st = new StringTokenizer(str);
            int ch = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());

            switch(ch){
                case 1:
                    if(calc.checker(calc.isOdd(), num))
                        System.out.println("ODD");
                    else
                        System.out.println("EVEN");
                    break;
                case 2:
                    if(calc.checker(calc.isPrime(), num))
                        System.out.println("PRIME");
                    else
                        System.out.println("NOT PRIME");
                    break;
                case 3:
                    if(calc.checker(calc.isPalindrome(), num))
                        System.out.println("PALINDROME");
                    else
                        System.out.println("NOT PALINDROME");
                    break;
                default:
                    System.out.println("Unrecognized input. Please try again.");

            }
        }
    }
}