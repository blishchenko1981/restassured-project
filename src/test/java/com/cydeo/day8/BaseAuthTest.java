package com.cydeo.day8;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.reset;
public class BaseAuthTest {
    // set up and teardown
    @BeforeAll
    public static void setup(){
        RestAssured.baseURI = "http://54.236.150.168:7000" ;
        RestAssured.basePath = "/api";
    }

    @AfterAll
    public static void teardown(){
        reset();
    }

    @Test
    public void testPublicUser(){

        given().log().uri().
                when().get("/spartans").
                then().log().all()
                .statusCode(401)
        ;

    }

    @Test
    public void testAuthenticatedUser(){

        given().log().all()
                .auth().basic("user", "user")
                .when().get("/spartans").
                then().log().ifValidationFails()
                .statusCode(200);
    }


         /**
         * Role Based Access Control Test , also known as RBAC
         * is a type of testing to ensure authenticated user can
         * perform everything they have authorized to
         * in the meantime can not perform anything that they are not authorized to perform
         *
         * admin :  create , read , update , delete
         * editor:  create , read , update
         * user  :  read
         */


    @DisplayName("Editor should not be able to delete")
    @Test
    public void testEditorDeleteData(){
        given()
                .auth().basic("editor", "editor")
                .pathParam("id", 123).
                when()
                .delete("/spartans/{id}").then()
                .log().ifValidationFails()
                .statusCode(403);
    }

    @ParameterizedTest
   @ValueSource(strings={"admin", "editor", "user"})
    public void testAllUsersGetAllSpartans(String role){
        given()
                .auth().basic(role,role)  // in Spartan api password and user same
                .when().get("/spartans").
                then()
                .log().ifValidationFails().statusCode(200)

        ;


    }



}
