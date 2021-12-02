package com.cydeo.day8;

import com.cydeo.utility.LibraryAPIUtil;
import com.cydeo.utility.LibraryAPI_BaseTest;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class LibraryLoginTest extends LibraryAPI_BaseTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/library_credentials.csv", numLinesToSkip = 1)
    public void testLogin(String username, String password) {

        System.out.println("---------------------------------------------------------");
        System.out.println("username = " + username);
        System.out.println("password = " + password);

        given()
                .log().all()
                .contentType(ContentType.URLENC)
                .formParam("email", username)
                .formParam("password", password).
                when()
                .post("/login").then()
                .log().all().statusCode(200)
        ;


    }

    @DisplayName("Librarian should be able to add book")
    @Test
    public void testAddBook() {

        // first get library token by sending POST /login request
        // and save it(might make a method)


        // if you have many form parameters as body
        // you can use form params method and pass map object  instead

        // send request to POST/ add book and verify the body
        String libraryToken = LibraryAPIUtil.getToken();
        Map<String, Object> bookMap = LibraryAPIUtil.getRandomBookMap();


        given()
                .log().all()
                .header("X-LIBRARY-TOKEN", libraryToken)
                .contentType(ContentType.URLENC)
                .formParams( bookMap ).
                when().post("/add_book").
                then().log().all().
        body("message", is("The book has been created."));

    }


}
