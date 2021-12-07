package com.cydeo.tests.day11;

import com.cydeo.utility.SpartanAPItestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;


@DisplayName("Json Schema Validation Tests")
public class JsonSchemaValidationPracticeTest extends SpartanAPItestBase {

    @DisplayName("Test GET /spartans/search schema")
    @Test
    public void searchSpartanSchemaTest(){

        // when more then couple key- value  pairs
        // like meny headers or meny query params, form params
        // restAssured provide method to let you pass it as a map in one shot

        Map<String, Object> queryMap = new LinkedHashMap<>();
        queryMap.put("nameContains", "Ea" ) ;
        queryMap.put("gender", "Male" ) ;

        given().log().all()
                .queryParams(queryMap).
        when()
                .get("/spartans/search")
                .then().log().all()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("SearchSpartanSchema.json"))

        ;


    }


}
