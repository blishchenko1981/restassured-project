package com.cydeo.tests.day4;
import org.junit.jupiter.api.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public class LibraryApp_Test {

    /**
     * This is the link for doc : https://library2.cybertekschool.com/rest/v1/#/User/post_login
     *
     * POST https://library2.cybertekschool.com/rest/v1/login
     * content type : application/x-www-form-urlencoded
     * body :
     *   email      : librarian52@library
     *   password   : Sdet2022*
     *
     * According to the doc
     *  we get 200 status code
     *  json body with 2 key  : token  , redirect_url
     *  content-type json
     */

    @BeforeAll
    public static void setup(){
        // base URI https://library2.cybertekschool.com
        // basePath : /rest/v1

        baseURI = "https://library2.cybertekschool.com";
        basePath = "/rest/v1";
    }

    @AfterAll
    public static void teardown(){
        reset();
    }

    @Test
    public void testLogin(){

        given()
                .log().all()
               // .header("Content-Type", "application/x-www-form-urlencoded")
                .contentType(ContentType.URLENC)   // this line same with line above
                .formParam("email","librarian52@library")
                .formParam("password", "Sdet2022*")
                .when()
                .post("login")
                .then()
                .log().all()
                .statusCode(200);
    }

// "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjp7ImlkIjoiMjgwIiwiZnVsbF9uYW1lIjoiVGVzdCBMaWJyYXJpYW4gNTIiLCJlbWFpbCI6ImxpYnJhcmlhbjUyQGxpYnJhcnkiLCJ1c2VyX2dyb3VwX2lkIjoiMiJ9LCJpYXQiOjE2MzczNDAxMTUsImV4cCI6MTYzOTkzMjExNX0.3Tl-NJt2uE8pXDtfCMw2TGXMbCc2sagYWEbtvOY0MXM",
//    "redirect_uri": "//library2.cybertekschool.com/redirect.html?t=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjp7ImlkIjoiMjgwIiwiZnVsbF9uYW1lIjoiVGVzdCBMaWJyYXJpYW4gNTIiLCJlbWFpbCI6ImxpYnJhcmlhbjUyQGxpYnJhcnkiLCJ1c2VyX2dyb3VwX2lkIjoiMiJ9LCJpYXQiOjE2MzczNDAxMTUsImV4cCI6MTYzOTkzMjExNX0.3Tl-NJt2uE8pXDtfCMw2TGXMbCc2sagYWEbtvOY0MXM"



    @Test
    public void testWithToken() {
        // In separate test , make a request to POST /login one more time
        // no need for assertion , only save the json value of token key in the response
        // send a request to GET /dashboard_stat
        // provide a header with name x-library-token , value is the value you saved from previous request
        // verify you get 200.
Response response =  given()
        .log().all()
        // .header("Content-Type", "application/x-www-form-urlencoded")
        .contentType(ContentType.URLENC)   // this line same with line above
        .formParam("email","librarian52@library")
        .formParam("password", "Sdet2022*")
        .when()
        .post("login");

String tokenFromRes = response.path("token");
        System.out.println(tokenFromRes);


    }
}
