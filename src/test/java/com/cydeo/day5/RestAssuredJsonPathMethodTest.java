package com.cydeo.day5;

import com.cydeo.pojo.SpartanWithID;
import com.cydeo.utility.SpartanAPItestBase;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;
import com.cydeo.utility.SpartanAPItestBase;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class RestAssuredJsonPathMethodTest extends SpartanAPItestBase{

    //Send request to GET /spartans/{id}
    // and estract the data id, name , phone

    @Test
    public void testOneSpartan (){

        //  get first id exist in our system

        int firstId = get("/spartans").path("id[0]");

        System.out.println("firstId = " + firstId);


      Response response =   given()
                .log().uri()  // only lob the request url by choice
                .pathParam("id", firstId).
                when().get("spartans/{id}")
                        .prettyPeek();


      // sane the id from the response
     //   int myId = response.getBody().path("id");


        // Get JsonPath object out of response object

        JsonPath jp = response.jsonPath();
       int myId = jp.getInt("id");
       String myName = jp.getString("name");
       long myPhone = jp.getLong("phone");

        System.out.println("myId = " + myId);
        System.out.println("myName = " + myName);
        System.out.println("myPhone = " + myPhone);


        // store this json result into a Map object
      Map<String, Object> responseBodyAsMap =   jp.getMap("");

        System.out.println("responseBodyAsMap = " + responseBodyAsMap);

    }

    @Test
    public void testSearchExtractData() {
        Response response = given()
                .log().uri()
                .queryParam("nameContains", "Ea")
                .queryParam("gender", "Male").
                when()
                .get("/spartans/search")
                .prettyPeek();

        JsonPath jp = response.jsonPath();

        int total = jp.getInt("totalElement");
        System.out.println("total = " + total);


        Map<String, Object> firstJsonAsMap = jp.getMap("content[0]");

        System.out.println("firstJsonAsMap = " + firstJsonAsMap);


        // List<String> allNames = jp.getList("content.name");

//        in this version of getList method opton to made it obvious
        List<String> allNames = jp.getList("content.name", String.class);
        System.out.println("allNames = " + allNames);

        List<Long> allPhones = jp.getList("content.phone", Long.class);

        //store first json in the result as SpartanWithID POJO
        SpartanWithID sp1 = jp.getObject("content[0]", SpartanWithID.class);
        System.out.println("sp1 = " + sp1);


        //store the entire response json array , into List<SpartannWithID>

        List<SpartanWithID> resultList = jp.getList("content", SpartanWithID.class);
        System.out.println("resultList = " + resultList);

    }




}
/**
 * There are two ways to get the response and extract json data
 *
 * 1>> path("your jsonPath goes here") return type is T(generic)
 * and decided by your variable data type you store
 * int myId=response.path("id")
 *
 * 2>> There is a type(class) in  RestAssured: JsonPath
 * that have lots of methods to extract json body from the response
 * like getInt() , getString(), getDouble(), getObject() and so on....
 *
 * In order to get JosnPath object out of response,
 * we call a method called jsonPath() from the response
 *
 * For example:
 *  JsonPath jp= response.jsonPath("your actual path goes here")
 *
 *  jp.getInt() , jp.getString() and so on
 *
 *  The meaning of the word json path when we use in different places
 *  json path:  -->> when inside " " means the actual path to get the value (like path)
 *
 *  JsonPath : -->> the RestAssured class that have lots of methods
 *  then, jsonPath() : -->> the method of Response object to obtain JsonPath object out of response
 */