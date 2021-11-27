package com.cydeo.day6;

import com.cydeo.pojo.Character;
import com.cydeo.pojo.Driver;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;


public class BreakingBad_Deserializatation_Practice_Test {

//https://www.breakingbadapi.com/api/characters

    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "https://www.breakingbadapi.com" ;
        RestAssured.basePath = "/api" ;

    }
    @AfterAll
    public static void cleanup(){
        RestAssured.reset();
    }

    @Test
    public void testCharacters(){

        JsonPath jp = get("/characters")
                .prettyPeek().jsonPath();

        // get first character and deserilize it into Character 200;

        Character ch1 = jp.getObject("[0]", Character.class);
        System.out.println("ch1 = " + ch1);

        List<Character> allCharacters = jp.getList("", Character.class);
        System.out.println("allCharacters = " + allCharacters);

    }

}
