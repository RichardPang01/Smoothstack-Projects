import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Character.compare;
import static java.util.stream.Collectors.joining;

public class Day5Assignment1{

    public static void main(String args[]){

        //Assignment1 Lambdas Part 1
        List<String> words = new ArrayList<>();
        words.add("eggs");
        words.add("apples");
        words.add("bacon");
        words.add("pie");
        words.add("flour");

        System.out.println("This is the original order of the list.");
        printList(words);

        words.sort((w1,w2) -> w2.compareTo(w1));
        System.out.println("This is the order from shortest to longest.");
        printList(words);

        words.sort((w1,w2) -> w1.compareTo(w2));
        System.out.println("This is the order from longest to shortest.");
        printList(words);

        words.sort((w1,w2) -> compare(w1.charAt(0), w2.charAt(0)));
        System.out.println("This is the order alphabetically by first character only.");
        printList(words);

        //e first
        words.sort((w1,w2)->{
           if(w1.contains("e")){return -1;}
           else{return 1;}
        });
        System.out.println("This is the order with containing e first.");
        printList(words);

        System.out.println("Same thing but with a static helper method.");
        words.sort((w1, w2)-> containsE(w1, w2));
        printList(words);

        //Assignment 1 Lambdas Part 2
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(32);
        numbers.add(5);
        numbers.add(7);
        numbers.add(10);

        System.out.println(stringComma(numbers));

        //Assignment 1 Lambdas Part 3
        List<String> wordList = new ArrayList<>();
        wordList.add("ape");
        wordList.add("horse");
        wordList.add("ant");
        wordList.add("dog");
        wordList.add("art");
        printList(aThree(wordList));

    }
    public static String stringComma(List<Integer> list){
        return list.stream()
                .map(i -> i%2 == 0 ? "e" + i : "o" + i)
                .collect(joining(","));
    }
    public static List<String> aThree(List<String> list){
        return list.stream()
                .filter(s->s.startsWith("a"))
                .filter(s->s.length()==3)
                .collect(Collectors.toList());
    }
    public static void printList(List<String> list){
        for(String str: list){
            System.out.print(str + " ");
        }
        System.out.println();
    }
    public static int containsE(String w1, String w2){
        if(w1.contains("e")){return -1;}
        else{return 1;}
    }

    /*Date-Time API Questions and Code
    1. You would use LocalDateTime class
    2. Use the TemporalAdjusters method of previous method.
    3. ZoneId is an identifier used to represent different zones. ZoneOffset is used to track a specific offset of time.
    4. Use the ofInstant method of ZonedDateTime.
    5. Check monthsInYear
    6. Check mondaysInMonth
    7. Check fridayTheThirteenth
    */
}