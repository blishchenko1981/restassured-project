package com.cydeo.day6;

import com.cydeo.pojo.Movie;
import org.junit.jupiter.api.BeforeAll;

import com.cydeo.pojo.SpartanWithID;
import com.cydeo.utility.SpartanAPItestBase;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;
import com.cydeo.utility.SpartanAPItestBase;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class MovieAPI_HomeworkTest {

    @BeforeAll
    public static void setup(){

        baseURI="http://www.omdbapi.com";

    }

    @AfterAll
    public static void tearDown(){
        reset();
    }

    @Test
    public  void testMovies(){
     JsonPath jp  =   given()
                .log().uri()
                .queryParam("apikey", "25d8fdf1")
                .queryParam("s", "The Mandalorian").
        when()
                .get("")
                .prettyPeek()
                .jsonPath()
        ;
// what is the json path to get the first object; Search[0];

Movie m1 = jp.getObject("Search[0]", Movie.class);
        System.out.println("m1 = " + m1);


    }


}
