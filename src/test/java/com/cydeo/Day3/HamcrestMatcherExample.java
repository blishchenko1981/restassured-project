package com.cydeo.Day3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestMatcherExample {
    /**
     * Hamcrest is a framework for writing matcher objects allowing ‘match’ rules
     * to be defined declaratively.
     * It is an assertion library that can be used additionally to make assertion
     * readable and it comes with a lot of pre-written matchers to make it easier to write and read
     * <p>
     * The method chain of RestAssured then section use the hamcrest matcher library
     * extensively to assert the response components in one chain
     * RestAssured dependency already contains Hamcrest Assertion library
     * so no separate dependency needed
     * All we need is to add static imports and start using it's matchers
     * to make the assertions great again
     */
    @Test
    public void testNumbers() {
// in junit5 assertions 3+6=9
        Assertions.assertEquals(9, 3 + 6);

        // Hamcrest it can be written in this way

        assertThat(3 + 6, equalTo(9));
        assertThat(4 + 6, is(10));

        assertThat(5 + 6, greaterThan(10));

        assertThat(10 + 10, lessThanOrEqualTo(21));
    }


    @Test
    public void testString() {

        String msg = "B23 is Excelent!";
        assertThat(msg, is("B52 is Excelent"));


        assertThat(msg, startsWithIgnoringCase("b23"));

        // check if string is blank: has only spases
        // one Matcher can be  wrapped inside another Matcher

        assertThat(msg, not(blankString()));
    }

    @Test
    public void testCollection() {

        List<Integer> numberList = Arrays.asList(3, 77, 66, 69, 21, 100);

        // list size of 6
        assertThat(numberList, hasSize(6));

        // list has 77
        assertThat(numberList, hasItem(77));

        //list has 77 and 66
        assertThat(numberList, hasItems(21, 100));

        // list has item greater then 50
        assertThat(numberList, hasItems(greaterThan(50)));

        // check if every item is greater then ZERO
        assertThat(numberList, everyItem(greaterThan(0)));


    }


}
