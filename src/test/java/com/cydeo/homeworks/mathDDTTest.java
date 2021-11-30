package com.cydeo.homeworks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


public class mathDDTTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/math.csv", numLinesToSkip = 1)

    public void testSum(int num1, int num2, int sum){

        Assertions.assertEquals(num1+num2, sum);
        assertThat(num1+num2, equalTo(sum));


    }

}
