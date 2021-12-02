package com.cydeo.tests.day2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class My_practice {
    @BeforeAll
    public static void setup(){
        RestAssured.baseURI= "https://www.breakingbadapi.com";
        RestAssured.basePath="/api";
    }
    @AfterAll
    public static void teardown(){
        reset();
    }

    @Test
    public void getFirstMovie(){
        Response response = get("/characters");
        response.prettyPrint();
        System.out.println("response.getStatusCode() = " + response.getStatusCode());

        System.out.println("------------------------------------------------------------");

        Response response1 = get("");
        response1.prettyPrint();
    }

    @Test
    public void useQueryParam(){
        Response response = given()

               //??????????????? .queryParam("id", 2)
                     // .queryParam("category", "Breaking Bad")
                      .queryParam("name", "Walter")
                .when()
                      .get("/characters");

        System.out.println("response.statusCode() = " + response.statusCode());
        response.prettyPrint();
    }

    @Test
    public void usePathParam(){

        Response response = given()
                //???????????????????.pathParam("id", 2)
                .when()
                .get("/characters/");
        response.prettyPrint();


    }



}
