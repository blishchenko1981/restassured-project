package com.cydeo.homeworks;

import com.cydeo.utility.SpartanAPItestBase;
import com.cydeo.utility.SpartanUtil;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class AdminAuthTest extends SpartanAPItestBase {

    @ParameterizedTest
    @ValueSource(strings = {"admin", "editor", "user"})
    public void testCreateByAdmin(String role) {

        given()
                .log().all()
                .auth().basic(role, role)
                .contentType(ContentType.JSON)
                .body(SpartanUtil.getRandomSpartanBody())
                .when().post("/spartans")
                .then()
                .log().ifValidationFails()
                .statusCode(201);
    }

    @Test
    public void testRead() {

        given()
                .auth().basic("admin", "admin")
                .accept(ContentType.JSON)
                .when()
                .get("/spartans")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(200);

    }

    @ParameterizedTest
    @ValueSource(ints = {23})
    public void testUpdate(int param) {

        given()
                .log().all()
                .pathParam("id", param).
                auth().basic("admin", "admin").
                body(SpartanUtil.getRandomSpartanBody()).
                contentType(ContentType.JSON).
                when()
                .put("/spartans/{id}").
                then().log().all()
                .statusCode(204);


    }

    @ParameterizedTest
    @ValueSource(ints = {25})
    public void testDelete(int param) {

        given()
                .log().all()
                .auth().basic("admin", "admin")
                .pathParam("id", param)
                .when().
                delete("/spartans/{id}")
                .then().log().all()
                .statusCode(204)
                .header("Keep-Alive", "hi");


    }
}