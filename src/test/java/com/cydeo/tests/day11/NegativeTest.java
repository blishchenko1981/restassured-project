package com.cydeo.tests.day11;

import com.cydeo.pojo.Spartan;
import com.cydeo.utility.SpartanAPItestBase;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.module.jsv.JsonSchemaValidator.*;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.* ;

public class NegativeTest extends SpartanAPItestBase {

    /**
     * Post /spartans request json payload have below requirements
     * name  : 2-15 chars
     * gender : Male or Female
     * phone  : 10 - 13 digit
     *
     * Knowing that we already tested all positive scenario
     * make sure it generates error with proper json body to reflect the error
     * according to the negative test data you provided.
     *
     * for example :
     * {
     *     "name":"A",
     *     "gender":"Male",
     *     "phone":1231231231
     * }
     * we should expect 400 response
     * {
     *     "message": "Invalid Input!",
     *     "errorCount": 1,
     *     "errors": [
     *         {
     *             "errorField": "name",
     *             "rejectedValue": "A",
     *             "reason": "name should be at least 2 character and max 15 character"
     *         }
     *     ]
     * }
     * error count should be 1
     * "message": "Invalid Input!",
     * "errorField": "name"
     *  from the response
     */

    @DisplayName("POST / spartans  payload should be valid")
    @Test
    public void testadd1DataNegativeScenario(){
        // prepare test body
        Spartan invalidBody = new Spartan("A","Male",1231231231L) ;

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(invalidBody).
                when().post("/spartans")
                .then().log().all()
                .statusCode(400)
                .body("message", is("Invalid Input!"))
                .body("errorCount", equalTo(1))
                .body("errors[0].errorfield", is("name"))
        ;
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/negativePostData.csv", numLinesToSkip = 1)

    public void testNegativePostPayload(String name, String gender, long phone, int expectedCount){

        Spartan invalidBody = new Spartan(name,gender,expectedCount) ;

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(invalidBody).
                when().post("/spartans")
                .then().log().all()
                .statusCode(400)
              //  .body("message", is("Invalid Input!"))
               // .body("errorCount", equalTo(expectedCount))
               // .body("errors[0].errorfield", is("name"))
        ;


        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);
        System.out.println("expectedCount = " + expectedCount);


    }


    @Test
    public void testPutRequestNegativeScenario(){

        int lastId = get("/spartans").path("id[-1]");
        System.out.println("lastId = " + lastId);

        // prepare body, wrong phone number
        Faker faker = new Faker();
        long phone15digits = faker.number().randomNumber(15,true);
        System.out.println("phone15digits = " + phone15digits);

        Spartan bodyWithInvalidPhone = new Spartan("Alex", "Male", phone15digits);

        System.out.println("BodyWithInvalidPhone = " + bodyWithInvalidPhone);
        given()
                .log().uri()  // just to see what id i used for logging purpose
                .pathParam("id" , lastId)
                .contentType(ContentType.JSON)
                .body(bodyWithInvalidPhone).
                when()
                .put("/spartans/{id}").
                then()
                .log().ifValidationFails()
                .statusCode(400) ;
    }



}
