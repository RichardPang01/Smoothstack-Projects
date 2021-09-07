import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Character.compare;
import static java.lang.Character.lowSurrogate;
import static java.util.stream.Collectors.joining;

public class Day5Assignment2{
    public static void main(String args []){

        List<Integer> numList1 = new ArrayList<Integer>();
        numList1.add(51);
        numList1.add(22);
        numList1.add(3);
        numList1.add(20);
        printNumList(getRight(numList1));

        List<Integer> numList2 = new ArrayList<Integer>();
        numList2.add(4);
        numList2.add(6);
        numList2.add(8);
        numList2.add(12);
        printNumList(doubling(numList2));

        List<String> wordList = new ArrayList<>();
        wordList.add("axe");
        wordList.add("boxes");
        wordList.add("faxxing");
        wordList.add("pax");
        wordList.add("xxcxf");
        printStringList(noX(wordList));

        int[]numArr1 = {2,4,8};
        System.out.println(groupSumClump(0, numArr1, 10));//should print true
        int[]numArr2 = {1,2,4,8,1};
        System.out.println(groupSumClump(0, numArr2, 14));//should print true
        int[]numArr3 = {2,4,4,8};
        System.out.println(groupSumClump(0, numArr3, 14));//should print false

    }


    public static List<Integer> getRight(List<Integer> numbers){
        numbers.replaceAll(n -> n%10);
        return numbers;
    }
    public static List<Integer> doubling(List<Integer> numbers){
        numbers.replaceAll(n->n*2);
        return numbers;
    }
    public static List<String> noX(List<String> words){
        words.replaceAll(n->n.replace("x", ""));
        return words;
    }
    public static boolean groupSumClump(int counter, int[] arr, int target){
        if(counter>=arr.length){return target == 0;}

        int i = counter;
        int total = 0;
        while(i<arr.length && arr[counter] == arr[i]){
            total = total + arr[i];
            i++;
        }
        if(groupSumClump(i, arr, target - total)){return true;}
        if(groupSumClump(i, arr, target)){return true;}
        return false;
    }
    public static void printStringList(List<String> list){
        for(String str: list){
            System.out.print(str + " ");
        }
        System.out.println();
    }
    public static void printNumList(List<Integer> list){
        for(Integer num: list){
            System.out.print(num + " ");
        }
        System.out.println();
    }
}