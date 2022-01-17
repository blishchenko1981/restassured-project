package com.cydeo.tests.day13;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class FruitShopPracticeTest {
    /**
     * FruitShop Exercise :
     * <p>
     * 1. Get All customers
     * - verify you get 200
     * - then extract first customer id
     * <p>
     * 2. Get All the orders of that customer
     * - verify status 200
     * - then extract first order id
     */
    @BeforeAll
    public static void setup() {
        baseURI = "https://api.predic8.de:443/shop";

    }

    @AfterAll
    public static void teardown() {
        reset();
    }


    @Test
    public void testExtract() {
        Response response = get("/customers/");
        Assertions.assertEquals(200, response.statusCode());

        String firstCustomerURL = response.path("customers.customer_url[0]");
        System.out.println("firstCustomerURL = " + firstCustomerURL);

        // to extract data after then() section we can use EXTRACT() method

        String firstUrl = given()
                .log().uri().
                when()
                .get("/customers/").
                then().statusCode(200).
                extract()  // from here it will stop verifying and will extract response data
                .response()
                .path("customers.coustomer_url[0]");

        System.out.println("firstUrl = " + firstUrl);

    }

    @Test
    public void testBreakingTheMethodChain() {

        given()
                .log().uri().
                contentType(ContentType.JSON).
                when()
                .get("/customers/").
                then()
                .statusCode(200)
        ;

        RequestSpecification givenPart = given().log().uri().contentType(ContentType.JSON);

        Response response = givenPart.when().get("/customers/");

        // now lets try to get then part of the chain
        ValidatableResponse thenPart = response.then().statusCode(200);

        //now lets veriry
        thenPart.contentType(ContentType.JSON);



    }


}

