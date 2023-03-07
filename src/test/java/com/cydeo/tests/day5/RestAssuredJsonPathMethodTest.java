package com.cydeo.tests.day5;

import com.cydeo.pojo.SpartanWithID;
import com.cydeo.utility.SpartanAPItestBase;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

@Tag("day5test")
public class RestAssuredJsonPathMethodTest extends SpartanAPItestBase {

    //Send request to GET /spartans/{id}
    // and estract the data id, name , phone

    @Test
    public void testOneSpartan() {

        //  get first id exist in our system

        int firstId = get("/spartans").path("id[0]");

        System.out.println("firstId = " + firstId);


        Response response = given()
                .log().uri()  // only lob the request url by choice
                .pathParam("id", firstId).
                when().get("spartans/{id}")
                .prettyPeek();


        // save the id from the response
        //   int myId = response.getBody().path("id");

        System.out.println("response.body().as(Map.class) = " + response.body().as(Map.class));

        Map<String, Object> practiceMap = response.getBody().as(Map.class);
        System.out.println("practiceMap = " + practiceMap);
        // Get JsonPath object out of response object

        JsonPath jp = response.jsonPath();
        int myId = jp.getInt("id");
        String myName = jp.getString("name");
        String myGender = jp.getString("gender");
        long myPhone = jp.getLong("phone");

        System.out.println("myId = " + myId);
        System.out.println("myName = " + myName);
        System.out.println("myGender = " + myGender);
        System.out.println("myPhone = " + myPhone);


        // store this json result into a Map object
        Map<String, Object> responseBodyAsMap = jp.getMap("");
        System.out.println("responseBodyAsMap = " + responseBodyAsMap);

        // how to access phone number field out of this map
        Long phone = (Long) responseBodyAsMap.get("phone");
        System.out.println("phone = " + phone);

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


        List<String> listOfNames = jp.getList("content.name");
        System.out.println("listOfNames = " + listOfNames);


        //store first json in the result as SpartanWithID POJO
        SpartanWithID sp1 = jp.getObject("content[0]", SpartanWithID.class);
        System.out.println("sp1 = " + sp1);


        //store the entire response json array , into List<SpartannWithID>

        List<SpartanWithID> resultList = jp.getList("content", SpartanWithID.class);
        System.out.println("resultList = " + resultList);


    }


    @Test
    public void homeWork() {


             Response response =    given()
                        .log().all()
                        .pathParam("request", "search")
                        .queryParam("nameContains", "Ea")
                        .queryParam("gender", "Male").
                        when()
                        .get("/spartans/{request}").prettyPeek();

             JsonPath jsonPath = response.jsonPath();

        List<Map<String, Object>> result = jsonPath.getList("content");
        System.out.println("result = " + result);

        Long thirdPhone = jsonPath.getLong("content[2].phone");
        System.out.println("thirdPhone = " + thirdPhone);

        String thirdName = jsonPath.getString("content[-1].name");
        System.out.println("thirdName = " + thirdName);

        Map<String, Object> firstElement = jsonPath.getMap("content[0]");
        System.out.println("firstElement = " + firstElement);

        // get second body as map:

        Map<String, Object> mapFirst = response.path("content[0]");
        Map<String, Object> mapSecond = response.body().path("content[1]");
        Map<String, Object> mapThird = response.path("content[2]");
        System.out.println("mapSecond = " + mapSecond);
        System.out.println("mapThird = " + mapThird);

        // get all names from jsonPath

        JsonPath jpHomeWork = response.jsonPath();
        String firstName = jpHomeWork.getString("content[0].name");
        System.out.println("firstName = " + firstName);
    }


}
/**
 * There are two ways to get the response and extract json data
 * <p>
 * 1>> path("your jsonPath goes here") return type is T(generic)
 * and decided by your variable data type you store
 * int myId=response.path("id")
 * <p>
 * 2>> There is a type(class) in  RestAssured: JsonPath
 * that have lots of methods to extract json body from the response
 * like getInt() , getString(), getDouble(), getObject() and so on....
 * <p>
 * In order to get JosnPath object out of response,
 * we call a method called jsonPath() from the response
 * <p>
 * For example:
 * JsonPath jp= response.jsonPath("your actual path goes here")
 * <p>
 * jp.getInt() , jp.getString() and so on
 * <p>
 * The meaning of the word json path when we use in different places
 * json path:  -->> when inside " " means the actual path to get the value (like path)
 * <p>
 * JsonPath : -->> the RestAssured class that have lots of methods
 * then, jsonPath() : -->> the method of Response object to obtain JsonPath object out of response
 */