package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;

public class TestOneSpartan2 {

    //GET http://54.90.169.134:8000/api/spartans/21

    @BeforeAll
    public static void setup(){
        RestAssured.baseURI = "http://54.90.169.134:8000";
        RestAssured.basePath = "/api";
    }

    @AfterAll
    public static void teardown(){
        // method to clean our basePath and baseURI after all tests in current class

      //  RestAssured.reset();
        reset();

    }

    // add a new test to check GET api/hello Endpoin
    // verify status code is 200, content type is text?plain, body is hello from sparta< not JSON>


    @Test
    public void testHelloAgain (){
        Response response = get("/hello");
        Assertions.assertEquals(200, response.statusCode());
      //  Assertions.assertEquals(ContentType.TEXT.toString(), response.contentType());
        Assertions.assertEquals("Hello from Sparta", response.asString());

    }



    @Test
    public void testOneSpartan() {

        Response response = get("/spartans/21");

        int statusCode = response.getStatusCode();
        System.out.println("statusCode = " + statusCode);

        response.prettyPrint();
        System.out.println("response.asString() = " + response.asString());

        // getting header from the response
        //using header ("header name")
        System.out.println("response.header(\"Content-Type\") = "
                + response.header("Content-Type"));
        System.out.println("response.header(\"Date\") = "
                + response.header("Date"));

        System.out.println("response.getHeader(\"Date\") = "
                + response.getHeader("Date"));

        System.out.println("response.header(\"Keep-Alive\") = " + response.header("Keep-Alive"));
        response.getHeader("Connection");


    }

    @Test
    public void testContentType() {

        // rest assured has special support for common headers like Content-type
        Response response = get("/spartans/21");

        // two lines below DO exactly the same
        System.out.println("response.contentType() = " + response.contentType());
        System.out.println("response.getContentType() = " + response.getContentType());

        // assertion to verify content-type = application/json

        Assertions.assertEquals("application/json", response.contentType());

        // REST asured has different type of content type is represented in
        // ContentType. - to get all of types
        System.out.println("ContentType.JSON = " + ContentType.JSON);
        System.out.println("ContentType.XML = " + ContentType.XML);
        System.out.println("ContentType.TEXT = " + ContentType.TEXT);
        System.out.println("ContentType.URLENC = " + ContentType.URLENC);

        Assertions.assertEquals(ContentType.JSON.toString(), response.contentType());
    }

    @Test
    public void testJSONBody() {

        Response response = get("/spartans/21");
        // print out the payload
        response.prettyPrint();
        // just like navigating through html xpath to get to certain element
        // you can navigate through json to get the value of  certainn key using jsonpath
        // easiest way to get the value using jsonpath is using path method from response object

        System.out.println("response.path(\"id\") = " + response.path("id"));
        System.out.println("response.path(\"name\") = " + response.path("name"));
        System.out.println("response.path(\"gender\") = " + response.path("gender"));
        System.out.println("response.path(\"phone\") = " + response.path("phone"));

        // save the json value you got from the key into variables

        int myId         = response.path("id");
        String myName    = response.path("name");
        String myGender  = response.path("gender");
        int phoneNumber = response.path("phone");


        System.out.println("myId = " + myId);
        System.out.println("myName = " + myName);
        System.out.println("myGender = " + myGender);
        System.out.println("phoneNumber = " + phoneNumber);

        // write assertions to verify the json body
        Assertions.assertEquals(21,myId);
        Assertions.assertEquals("Mark",myName);
        Assertions.assertEquals("Male",myGender);
        Assertions.assertEquals(1852873386,phoneNumber);

    }

}
