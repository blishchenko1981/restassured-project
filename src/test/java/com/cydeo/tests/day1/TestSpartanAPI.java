package com.cydeo.tests.day1;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.* ;

@Tag("smokeAll")
public class TestSpartanAPI {

    @Test
    public void testHello(){
        System.out.println("Hello world");
        // send request  to below url and save the response
        //  http://54.90.169.134:8000/api/hello

        RestAssured.get("http://54.90.169.134:8000/api/hello");

        Response response = get("http://54.90.169.134:8000/api/hello");

        System.out.println("response = " + response.statusCode());

        System.out.println("response.prettyPrint() = " + response.prettyPrint());

        Integer status = response.getStatusCode();
        Assertions.assertEquals(200, status);

    }

}
