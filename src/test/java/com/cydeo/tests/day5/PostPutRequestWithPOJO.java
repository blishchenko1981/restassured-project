package com.cydeo.tests.day5;

import com.cydeo.pojo.Spartan;
import org.junit.jupiter.api.Test;
import com.cydeo.utility.SpartanAPItestBase;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class PostPutRequestWithPOJO extends SpartanAPItestBase{
    /**
     POST /spartans
     content type is json
     body is
     {
     "name":"API POST",
     "gender":"Male",
     "phone":1231231231
     }
     We learned how to represent json body using Map
     and let Jackson data-bind library to serialize it into json when sending to the server

     Another common way of representing json data is using
     special purpose Java Object created from A class
     that have fields matched to json keys
     and have getters and setters
     This type of Object , sole purpose is to represent data ,
     known as POJO , plain old java object
     The class to create POJO known as POJO class
     It's used for creating POJO , just like you create any other object
     **/

@Test
    public void testPostWithPojoBody(){

    Spartan sp1 = new Spartan("Jim", "Male", 555000454544L);
    System.out.println("sp1 = " + sp1);

    given()
            .log().all()
            .contentType(ContentType.JSON)
            .body(sp1).
            when()
            .post("/spartans").
            then().log().all().statusCode(201);






}



}
