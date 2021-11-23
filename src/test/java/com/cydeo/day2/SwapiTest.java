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

public class SwapiTest {
    @BeforeAll
    public static void setup() {

        RestAssured.baseURI = "https://swapi.dev";
        RestAssured.basePath = "/api";


    }

    @AfterAll
    public static void teardown() {
        reset();
    }

    @Test
    public void testSwapi() {
        Response response = get("/people");
        //response.prettyPrint();

        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertEquals(ContentType.JSON.toString(), response.getContentType());

        Assertions.assertEquals(82, (int) response.path("count"));
        System.out.println("response.path(\"count\") = " + response.path("count"));

        Assertions.assertEquals("Luke Skywalker", response.path("results[0].name"));
        System.out.println("response.path(\"results[0].name\") = " + response.path("results[0].name"));

        Assertions.assertEquals("none", response.path("results[3].hair_color"));
        System.out.println("response.path(\"results[3].hair_color\") = " + response.path("results[3].hair_color"));


       // System.out.println("response.path(\"results[].birth_year\") = " + response.path("results[9].birth_year"));

        List<String> allNames = response.path("results.name");
        System.out.println("allNames = " + allNames);
        Assertions.assertEquals(10, allNames.size());
        System.out.println("response.path(\"results[last].birth_year\") = "
                + response.path("results["+ (allNames.size()-1)+"].birth_year"));
        Assertions.assertEquals("57BBY", response.path("results["+ (allNames.size()-1)+"].birth_year"));

        // response return Stirng so we assign result to List<String>
        List<String> allHeights = response.path("results.height");
        System.out.println("allHeights = " + allHeights);

        // by wrapper class convert elements of list to Integer

        int maxHeight = Integer.parseInt(allHeights.get(0));

        for (String eachHeight : allHeights) {
            int each = Integer.parseInt(eachHeight);
            if (each > maxHeight) {
                maxHeight = each;
            }
        }
        System.out.println("maxHeight = " + maxHeight);


    }


    @Test
    public void testWithQueryParam(){

        Response response = given()
                .queryParam("page", 2)
                .when()
                .get("/people");
      //  response.prettyPrint();

        System.out.println("Name of the 4th person on the second page is - " + response.path("results[3].name"));

        Assertions.assertEquals("Yoda", response.path("results[8].name"));
        System.out.println("Name of the 9th person is - " + response.path("results[8].name"));
    }


    @Test
    public void testWithPathParam(){

        Response response = given()
                .pathParam("id", 3)
                .when()
                .get("/films/{id}");
        System.out.println("response.statusCode() = " + response.statusCode());
        Assertions.assertEquals(200, response.statusCode());

        System.out.println("response.contentType() = " + response.contentType());
        Assertions.assertEquals(ContentType.JSON.toString(), response.contentType());

        response.prettyPrint();  //Return of the Jedi

        System.out.println("response.path(\"title\") = " + response.path("title"));
        Assertions.assertEquals("Return of the Jedi", response.path("title"));


        List<String> allCharacters = response.path("characters");
        System.out.println("allCharacters.size() = " + allCharacters.size());
        Assertions.assertEquals(20, allCharacters.size());

    }



}
