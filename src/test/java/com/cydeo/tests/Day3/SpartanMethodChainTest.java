package com.cydeo.tests.Day3;

import com.cydeo.utility.SpartanAPItestBase;
import org.junit.jupiter.api.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class SpartanMethodChainTest extends SpartanAPItestBase {

    @Test
    public void getOneSpartanTest() {

        // in one shot, send request to GET/spartans/{id} with id of 1
        // log all
        // verify status code is 200
        // content type is json
        // "name" is "vitalii"

        given()
                .log().all().pathParam("id", 1).accept(ContentType.JSON)
                .when()
                .get("/spartans/{id}")
                .then()
                .statusCode(is(200))
                //.header("Content-Type", ContentType.JSON.toString())
                .contentType(ContentType.JSON)
                .body("id", is(1))
                .body("name", is("Vitalii"))

        ;


    }

    @Test
    public void testSearch() {

        // http://54.236.150.168:8000/api/spartans/search?nameContains=Ea&gender=Male
        //        verify status code is 200
//                content type is json
//                total element 3
//                json array has size of 3
//                every gender is Male
//                every name contain ignoring case "ea"


             given().log().all()  // logging everithing about request
                     .queryParam("nameContains" , "Ea")
                     .queryParam("gender", "Male")
                     .when().get("/spartans/search")

                     .then().log().all()
                     .statusCode(200)
                     .contentType(ContentType.JSON)
                     .body("totalElement", is(3))
                     .body("content", hasSize(3))
                     .body("content.name", hasItem("Sean"))
                     .body("content.gender",  everyItem(is("Male"))  )
                     .body("content.name",  everyItem(  containsStringIgnoringCase("ea")   )   )

             ;


    }



    @Test
    public void testSearchPractice (){

        given().log().uri()
                .queryParam("nameContains", "Ea")
                .queryParam("gender", "Male")
                .when()
                .get("/spartans/search").
                then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("totalElement", is(3))
                .body("content", hasSize(3))
                .body("content.name", hasItem("Sean"))
                .body("content.id[1]", is(65))
        ;
    }
}
