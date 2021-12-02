package com.cydeo.tests.day8;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class ZipcodeMethodSourceTest {

    /**
     * method return all zip code for Fairfox VA
     * using  GET https://api.zippopotam.us/us/va/fairfax
     * return List<String>
     */
    @BeforeAll
    public static void setup() {
        baseURI = "https://api.zippopotam.us/";
        basePath = "us";
    }

    @AfterAll
    public static void teardown() {
        reset();
    }

    @ParameterizedTest
    @MethodSource("getAllZipCodes")
    public void testAllZipCode(String zipParam){

        System.out.println("zipParam = " + zipParam);
    }


    public static List<String> getAllZipCodes() {

        // send request to get and store and store all zipcodes
        // we cant use baseURI for this since its not a @Test
        // we need to provide full url directly oruse baseURI (base uri) in given() section

        Response response = get("https://api.zippopotam.us/us/nc/indian trail").prettyPeek();
        List<String> allZip = response.path("places.'post code'");
        return allZip;
    }

}
