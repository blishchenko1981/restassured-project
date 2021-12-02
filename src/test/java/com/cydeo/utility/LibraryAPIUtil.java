package com.cydeo.utility;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class LibraryAPIUtil {

    // create a method to get token
    public static String getToken(){

        String libraryToken = given()
                .log().all()
                .contentType(ContentType.URLENC)
                .formParam("email", "librarian47@library")
                .formParam("password", "Sdet2022*").
                when()
                .post("/login").path("token");

       return libraryToken;
    }

    // crate random book
    public static Map<String,Object> getRandomBookMap(){
        Faker faker = new Faker();

        Map<String, Object> bookMap = new LinkedHashMap<>();
        bookMap.put("name", faker.book().title());
        bookMap.put("isbn", faker.numerify("######"));
        bookMap.put("year", faker.number().numberBetween(1000, 2021));
        bookMap.put("author", faker.book().author());
        bookMap.put("book_category_id", faker.number().numberBetween(1, 20));
        bookMap.put("description", faker.chuckNorris().fact());

        return bookMap;
    }



}
