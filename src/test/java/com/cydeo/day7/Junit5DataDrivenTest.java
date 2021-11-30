package com.cydeo.day7;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class Junit5DataDrivenTest {

    // given these string data:
    // "Abbos", "Yevheniia","Shazia","Tugba","Mohamed","Kimberly"

    @ParameterizedTest
    @ValueSource(strings = {"Abbos", "Yevheniia", "Shazia", "Tugba", "Mohamed", "Kimberly"})
    public void testName(String eachName) {

        System.out.println("eachName = " + eachName);
        assertTrue(eachName.length() > 5, "name is shorter than 5 chars");
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/people.csv", numLinesToSkip = 1)
    public void testPerson(String nameParam, String genderParam, long phoneParam){

        System.out.println("nameParam = " + nameParam);
        System.out.println("genderParam = " + genderParam);
        System.out.println("phoneParam = " + phoneParam);
    }




}
