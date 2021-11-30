package com.cydeo.day8;

import com.cydeo.pojo.Place;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class ZipcodeMethodSourceTest {

    /**
     * method return all zip code for Fairfox VA
     * using  GET https://api.zippopotam.us/us/va/fairfax
     * return List<String>
     */
    @BeforeAll
    public static void setup() {
        baseURI = "https://api.zippopotam.us/";
        basePath = "/us";
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

        List<String> allZip = get("/va/'indian trail'").path("places.'post code'");
        return allZip;
    }

}
