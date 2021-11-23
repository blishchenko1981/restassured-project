package com.cydeo.utility;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.reset;

public class SpartanAPItestBase {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://54.90.169.134:8000";
        RestAssured.basePath = "/api";
    }

    @AfterAll
    public static void teardown() {
        // method to clean our basePath and baseURI after all tests in current class

        //  RestAssured.reset();
        reset();

    }


}
