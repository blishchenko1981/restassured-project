package com.cydeo.tests.day12;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class MockAPIEndpointTest {

    @BeforeAll
    public static  void setUp(){

        baseURI = "https://f19855de-c8d1-4272-bf08-e1ee0ee7cf19.mock.pstmn.io";


    }

    @AfterAll
    public static void tearDown(){
        reset();
    }

    @Test
    public void testNemo(){
given().log().all().
         get("/nemo").prettyPeek()
        .then()
        .log().all()
        .statusCode(200)
        .contentType(ContentType.JSON)
        .body("type", is("fish"))
          ;

    }

    @Test
    public void testMultiPartFormDataFileUpload(){

        given()
                .log().all()
                .contentType(ContentType.MULTIPART)
                        .multiPart(new File("src/test/java/com/cydeo/tests/day12/Gherkin.png")).
                when()
                .post("/upload")
                .then()
                .statusCode(200)
        ;



    }



}
