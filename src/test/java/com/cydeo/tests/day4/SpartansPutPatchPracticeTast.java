package com.cydeo.tests.day4;

import com.cydeo.utility.SpartanAPItestBase;
import com.sun.javaws.security.AppContextUtil;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SpartansPutPatchPracticeTast extends SpartanAPItestBase {


    String updatedBody = "{\n" +
            "    \"name\" : \"New Body\",\n" +
            "    \"gender\" : \"Male\",\n" +
            "   \"phone\" : 5555555599\n " +
            "}";

    String nums = "" + 12345;

    @Test
    public void testUpdate() {
        System.out.println("nums = " + nums);
        given().log().all().
                pathParam("id", 5)
                .contentType(ContentType.JSON)
                .body(updatedBody).
                when().put("/spartans/{id}").
                then().log().all()
                .statusCode(equalTo(204));

    }

    String updatedBodyPartialy = "{\n" +
            "    \"name\" : \"NewNamePatch\"\n" +
            "}";

    @Test
    public void testPartialUpdate() {
        given().log().all().
                pathParam("id", 5)
                .contentType(ContentType.JSON)
                .body(updatedBodyPartialy).
                when()
                .patch("/spartans/{id}").
                then().log().all()
                .statusCode(equalTo(204));

    }

    @Test
    public void deleteSpartan() {

        Response response = given().log().all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(updatedBody)
                .when().post("/spartans")
                ;
       response.then().statusCode(201).log().all();
        int newId = response.path("data.id");
        System.out.println("newId = " + newId);


        int lastexistedId = get("/spartans").path("id[-1]");
        System.out.println("existedId = " + lastexistedId);
        given().log().all().
                pathParam("id", lastexistedId)
                .when().delete("/spartans/{id}").
                then().log().all()
                .statusCode(204);
    }


}
