package com.cydeo.homeworks;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class OpenWeather {

    @BeforeAll
    public static void setup() {
        baseURI = "https://api.openweathermap.org";
        basePath = "/data/2.5/weather";
    }

    @AfterAll
    public static void teardown() {
        reset();
    }

    @Test
    public void weatherRequest() {

       JsonPath jp =  given()
                .log().all()
                .queryParam("q", "Charlotte")
                .queryParam("appid","f1fb538c23485a8d67a254164ef47809")
                .when()
                .get("")
                .prettyPeek().jsonPath()
        ;

       String minTemp = jp.getString("main.temp_min");
        System.out.println("minTemp = " + minTemp);

        String maxTemp = jp.getString("main.temp_max");
        System.out.println("maxTemp = " + maxTemp);


        System.out.println("jp.getJsonObject(\"main\").toString() = " + jp.getJsonObject("main").toString());



    }
}
