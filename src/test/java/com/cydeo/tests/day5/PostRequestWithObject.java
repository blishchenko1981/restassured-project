package com.cydeo.tests.day5;

import com.cydeo.utility.SpartanAPItestBase;
import com.cydeo.utility.SpartanUtil;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class PostRequestWithObject extends SpartanAPItestBase {


    /**
     * POST /spartans
     * content type is json
     * body is
     * {
     * "name":"API POST",
     * "gender":"Male",
     * "phone":1231231231
     * }
     **/

    @Test
    public void testPostWithMap() {
        Map<String, Object> bodyMap = new LinkedHashMap<>();
        bodyMap.put("name", "API POST");
        bodyMap.put("gender", "Male");
        bodyMap.put("phone", "1231231231");

        System.out.println("bodyMap = " + bodyMap);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(bodyMap).
                when()
                .post("/spartans").
                then()
                .log().all()
                .statusCode(201);
        // using the Map


    }

    @Test
    public void testPostWithMapWithRandomData() {

//        Faker faker = new Faker();
//        Map<String, Object> bodyMap = new LinkedHashMap<>() ;
//        bodyMap.put("name",   faker.name().firstName()       );
//        bodyMap.put("gender", faker.demographic().sex()     ); // Male or Female
//        // get a number between 5000,000,000 - 999,999,9999
//        bodyMap.put("phone",  faker.number().numberBetween(5000000000L,9999999999L));
        // having utility, so we can just call a method as below
        // 1. create a class under utility package with name SpartanUtil
        // 2. create a public static method with return type of Map<String, Object>
        // 3. add above code you already wrote for method body and return the bodyMap from the method
        // 4. call the method here to get the random body
        Map<String, Object> bodyMap = SpartanUtil.getRandomSpartanBody();
        System.out.println("bodyMap = " + bodyMap);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(bodyMap).
                when()
                .post("/spartans").
                then()
                .log().all()
                .statusCode(201);
    }

    // Send request to PUT /spartans/{id} sith Random Map body

    @Test
    public void testUpdate1DataWithRandomBody(){

        int lastID = get("/spartans").path("id[-1]");

        System.out.println("lastID = " + lastID);

        Map<String,Object> updateBodyMap = SpartanUtil.getRandomSpartanBody();
        given().log().all()
                .pathParam("id",lastID)
                .contentType(ContentType.JSON)
                .body(updateBodyMap).
                when()
                .put("/spartans/{id}").
                then()
                .log().all()
                .statusCode(204);

    }




}

