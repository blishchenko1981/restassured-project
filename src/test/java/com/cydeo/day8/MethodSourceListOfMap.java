package com.cydeo.day8;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MethodSourceListOfMap {

    @ParameterizedTest
    @MethodSource("getAllStudentInfo")
    public void testStudentDDT(Map<String, Object> studentInfo){
        System.out.println("studentInfo = " + studentInfo);
    }


    public static List<Map<String,Object>> getAllStudentInfo(){

        List<Map<String,Object>> allInfoMapList = new ArrayList<>();
        // add 3 items to this list
        Map<String,Object>  studentMap1 = new HashMap<>();
        studentMap1.put("name", "VItalii");
        studentMap1.put("gender", "Male");
        studentMap1.put("phone", 1232123321);

        Map<String,Object>  studentMap2 = new HashMap<>();
        studentMap2.put("name", "Artem");
        studentMap2.put("gender", "Male");
        studentMap2.put("phone", 321234543);

        Map<String,Object>  studentMap3 = new HashMap<>();
        studentMap3.put("name", "Milana");
        studentMap3.put("gender", "Female");
        studentMap3.put("phone", 1200123321);

        Map<String,Object> studentMap4 =  new HashMap<>() ;
        studentMap4.put("name", "Sophie") ;
        studentMap4.put("gender", "Female") ;
        studentMap4.put("phone", 332323323122L) ;


        allInfoMapList.add(studentMap1);
        allInfoMapList.add(studentMap2);
        allInfoMapList.add(studentMap3);
        allInfoMapList.add(studentMap4);
        return allInfoMapList;

    }


}
