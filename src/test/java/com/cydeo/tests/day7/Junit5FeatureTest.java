package com.cydeo.tests.day7;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

// CLASS TO EXPLORE JUNIT 5 FEATURES

@DisplayName("Explore Junit5 features")
public class Junit5FeatureTest {

    @DisplayName("Testing addition functionality")
    @Test
    public void testAdd() {
        assertEquals(101, 5 + 5, "Expected amount is different!!! Shoud be 10");
        // providing additioanal error massage when assertions fail
        assertEquals(17, 17+10 , "Hey !!! It wasnt 17!!!!!1");


    }

    @DisplayName("Testing substraction functionality")
    @Test
    public void testSubstract() {
        assertEquals(9, 10 - 1);
    }


    @Disabled("ignore this test until it will fixed")
    @DisplayName("checking Disable functionality")
    @Test
    public void testDisabled() {

        System.out.println("check if disabled is working");
    }

    @DisplayName("Assert All together then result")
    @Test
    public void multipleAssertions() {
        assertEquals(8, 10 - 2);
        assertEquals(9, 10 - 1);
        assertEquals(5, 10 - 5);

        assertAll(

                () -> assertEquals(81, 10 - 2),
                () -> assertEquals(92, 10 - 1),
                () -> assertEquals(53, 10 - 5)
        );

    }

    @ParameterizedTest
    @ValueSource(ints = {12,53,81,53,66,43,22})
    public void testNumberMore10(int num){
        //assert that num2,5,8,5,66,43,22 more than 10;

        System.out.println("number for this iteration " + num);
       // int num = 11;
        assertTrue(num>10, "hey it wass more than 10");
    }

}
