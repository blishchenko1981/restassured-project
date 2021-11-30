package com.cydeo.day8;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.reset;
public class BaseAuthTest {
    // set up and teardown
    @BeforeAll
    public static void setup(){
        RestAssured.baseURI = "http://54.236.150.168:7000" ;
        RestAssured.basePath = "/api";
    }

    @AfterAll
    public static void teardown(){
        reset();
    }

    @Test
    public void testPublicUser(){

        given().log().uri().
                when().get("/spartans").
                then().log().all()
                .statusCode(401)
        ;

    }

    @Test
    public void testAuthenticatedUser(){

        given().log().all()
                .auth().basic("user", "user")
                .when().get("/spartans").
                then().log().ifValidationFails()
                .statusCode(200);
    }




}
