package com.vogella.junit5;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day4Assignment4{
   public static double getSlope(double a, double b, double c, double d){
      return (c-a)/(d-b);
   }
   public static double getDistance(double a, double b, double c, double d){
      return Math.sqrt(Math.pow(c-a,2) + Math.pow(d-b,2));
   }
   public static boolean parallelTo(double a, double b, double c, double d, double e, double f, double g, double h){
      if(getSlope(a,b,c,d) == getSlope(e,f,g,h)){
         return true;
      }
      else{
         return false;
      }
   }
}

class LineTest{
   Day4Assignment4 testing;
   
   @BeforeEach
   void setup(){
      testing = new Day4Assignment4();
   }

   @Test
   void testGetSlope(){
      System.out.println("test case get slope");
      assertEquals(2, testing.getSlope(1,1,5,3));
      assertNotEquals(0, testing.getSlope(0,0,5,5));
   }

   @Test
   void testGetDistance(){
      System.out.println("test case get distance");
      assertEquals(Math.sqrt(20), testing.getDistance(1,1,5,3));
   }

   @Test
   void testParallelTo(){
      System.out.println("test case parallel to");
      assertEquals(true, testing.parallelTo(0,0,1,1, 2,2,3,3));
      assertEquals(false, testing.parallelTo(2,5,4,8, 3,1,5,2));
      assertNotEquals(true, testing.parallelTo(2,5,4,8, 3,1,5,2));

   }
}