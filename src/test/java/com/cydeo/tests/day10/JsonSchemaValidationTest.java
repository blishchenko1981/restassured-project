package com.cydeo.tests.day10;

import com.cydeo.utility.SpartanAPItestBase;
import com.cydeo.utility.SpartanAPItestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sun.security.pkcs11.P11Util;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class JsonSchemaValidationTest extends SpartanAPItestBase {

    @DisplayName("testing GET / spartan/{id} responce schema")
    @Test
    public void testSingleSpartanSchema() {

        int firstIdInSystem = get("/spartans").path("id[0]");


        given()
                .pathParam("id", firstIdInSystem)
                .log().uri()
                .when().get("/spartans/{id}")
                .then()
                .log().all()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("SingleSpartanSchema.json"))
        ;


    }
}
