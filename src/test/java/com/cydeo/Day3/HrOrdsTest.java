package com.cydeo.Day3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;


public class HrOrdsTest {
    @BeforeAll
    public static void setup() {
        baseURI = "http://54.236.150.168:1000";
        basePath = "/ords/hr/";
    }

    @AfterAll
    public static void teardown() {
        reset();
    }

    @Test
    public void testGetAllJobs() {

        Response response = given()
                .log().all()  // this will print everiting about your response
                .when()
                .get("/jobs");
         response.prettyPrint();

        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertEquals(ContentType.JSON.toString(), response.contentType());

        int countValue = response.path("count");
        Assertions.assertEquals(19, countValue);

        String secondJobID = response.path("items[1].job_id");
        System.out.println("secondJobID = " + secondJobID);

        int fourthMinSalary = response.path("items[4].min_salary");
        System.out.println("fourthMinSalary = " + fourthMinSalary);

        List<String> allJobTitle = response.path("items.job_title");
        System.out.println("allJobTitle = " + allJobTitle);

    }

    @Test
    public void testJobsWitQueryParam(){

        Response response = given()
                .log().all()
                .queryParam("limit", 5)
                .when().get("/jobs");
        response.prettyPrint();

        Assertions.assertEquals(5, (int)response.path("count"));

        String lastJobID = response.path("items[-1].job_id");
        System.out.println("lastJobID = " + lastJobID);

    }

    @Test
    public void testsingleJobWithPathParam(){

        Response response = given().log().all().pathParam("job_id", "AD_VP").
                when().get("/jobs/{job_id}");

        response.prettyPrint();

        String jobTitle = response.path("job_title");
        Assertions.assertEquals("Administration Vice President",jobTitle );


    }

}
