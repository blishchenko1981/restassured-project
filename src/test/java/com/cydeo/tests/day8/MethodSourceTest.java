package com.cydeo.tests.day8;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MethodSourceTest {

    @ParameterizedTest
    //@MethodSource("return10nums")
    @MethodSource("return10nums")
    public void testWithMethodSource(int num){
        System.out.println("num = " + num);

    }


    // static method that return List of 10 random integers
    public static List<Integer> return10nums (){
        List<Integer> listOfNums = new ArrayList<>();
        listOfNums = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        return listOfNums;
    }


   @ParameterizedTest
   @MethodSource("com.cydeo.day8.MethodSourceUtil#getSomeNames")
    public void testNamesWithMethodSourceDDT(String name){
       System.out.println("name = " + name);

   }

}
