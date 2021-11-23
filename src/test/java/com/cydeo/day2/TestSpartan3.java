package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;


public class TestSpartan3 {

    // set up and teardown
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


    // send request to GET /spartans and chech=k status code 200 and content type json

    @Test
    public void testAllSpartans() {

        Response response = get("/spartans");
        System.out.println("response.statusCode() = " + response.statusCode());
        // response.prettyPrint();

        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertEquals(ContentType.JSON.toString(), response.getContentType());


        System.out.println("response.path(\"[0].gender\") = " + response.path("[0].gender"));
        System.out.println("response.path(\"gender[0]\") = " + response.path("gender[0]"));

        // Get all the id(instead of just one ) and store it into List<Integer>

        List<Integer> listOfID = response.path("id");
        System.out.println("listOfID = " + listOfID);

    }


// Send request to GET/spartans and provide accept header

    @Test
    public void testGetXMLResponse() {
        // restAssured use method chaining extensively to combine all part of requests
        // and verify the response inone shot
        // here since we need to provide additional header info to get xml

        Response response = given()
                // .header("Accept", "application/xml")
                .header("Accept", ContentType.XML)
               // .accept(ContentType.XML)
                .when()
                .get("/spartans");

        response.prettyPrint();
        // write assertions

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(ContentType.XML.toString(), response.contentType());


    }

    // SEND REQUEST TO GET http://54.236.150.168:8000/api/spartans/search?nameContains=Ea&gender=Male
    @Test
    public void testSearch() {


        Response response = given()
                .queryParam("nameContains", "V")
                .queryParam("gender", "Male")
                .when()
                .get("/spartans/search");

        response.prettyPrint();

        // totalelement value

        System.out.println("response.path(\"totalElement\") = " + response.path("totalElement"));

        // get the first person name
        System.out.println("response.path(\"content[0]name\") = " + response.path("content[0].name"));

        System.out.println("response.path(\"content.name[0]\") = " + response.path("content.name[0]"));

        // get the second person Id
        System.out.println("response.path(\"content[1].name\") = " + response.path("content[1].name"));
        System.out.println("response.path(\"content[1].id\") = " + response.path("content[1].id"));

        // store all the names into the list
        List<String> allNames = response.path("content.name");
        System.out.println("allNames = " + allNames);


    }


    @Test
    public void testOneSpartanPathParam (){

        Response response = given()
                .pathParam("id", 3)
                .log().all() // this will log everything about the request
                .when()
                .get("/spartans/{id}");

        response.prettyPrint();
    }
    @Test
    public void addHeaders(){

        Response response = given()
               // .header("Accept" , "application/xml")
                //.header("Accept", ContentType.JSON)
                .accept(ContentType.XML)
                .when()
                .get("/spartans");

        System.out.println("response.getStatusCode() = " + response.getStatusCode());
        System.out.println("response.contentType() = " + response.contentType());
        System.out.println("response.asPrettyString() = " + response.asPrettyString());


    }




}












