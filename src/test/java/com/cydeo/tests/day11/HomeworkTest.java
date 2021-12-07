package com.cydeo.tests.day11;

import com.github.javafaker.Faker;
import com.sun.javaws.security.AppContextUtil;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import org.junit.jupiter.api.*;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.*;

public class HomeworkTest {


    // Homework , Create a method to get Random valid driverId
    // so you can pass to the test /drivers/{driverId}  instead of guessing valid driver id

    // Explore the rest of the Collection
    // for example :
    // http://ergast.com/api/f1/2021/drivers
    // practice getting xml element text value , xml element attributes
    // same goes for the rest of the collection.


    public String getRandomDriverID() {

        XmlPath xp = get("http://ergast.com/api/f1/drivers").xmlPath();

        List<String> allDriverIds = xp.getList("MRData.DriverTable.Driver.@driverId");
        System.out.println("allDriverIds = " + allDriverIds);

        Faker faker = new Faker();
        String randomId = allDriverIds.get(faker.number().numberBetween(0, allDriverIds.size() - 1));

        System.out.println("randomId = " + randomId);

        return randomId;
    }


    // Homework 2 :
    // Send this request in Movie API get xml response by using query param r=xml
    // GET http://www.omdbapi.com/?t=The Mandalorian&r=xml?apiKey=YourKeyGoesHere

    // print out all the movie attributes
    // verify root element attribute  totalResults="11"
    // store all the titles on List<String> and print.
    // verify the size of list match the attribute totalResults="11"

    // http://www.omdbapi.com/?s=The Mandalorian&r=xml?apiKey=a3fabd87

    @Test
    public void MovieAPITest () {

        XmlPath xp = given().log().all()
                .accept(ContentType.XML)
                .queryParam("apikey", "a3fabd87")
                .when().get("http://www.omdbapi.com/?s=The Mandalorian&r=xml").xmlPath().prettyPeek();

        System.out.println("xp.getList(\"root.result.@year\") = " + xp.getList("root.result.@year"));
        System.out.println("xp.getList(\"root.result.@imbdID\") = " + xp.getList("root.result.@imdbID"));
        System.out.println("xp.getList(\"root.result.@type\") = " + xp.getList("root.result.@type"));
        System.out.println("xp.getList(\" root.result.@title\") = " + xp.getList(" root.result.@title"));
        System.out.println("xp.getList(\"root.result.@poster\") = " + xp.getList("root.result.@poster"));


        int resultsTotal = xp.getInt("root.@totalResults");
        Assertions.assertEquals(11, resultsTotal);
        System.out.println("resultsTotal = " + resultsTotal);


       List<String> allTitles =  xp.getList(" root.result.@title");
        System.out.println("allTitles.size() = " + allTitles.size());
        Assertions.assertEquals(resultsTotal, allTitles.size());
    }



    // Homework 3 :
    // Send request to  GET /spartans provide accept header to get XML response
    // practice getting the value of xml elements for single elements
    // or list of elements

    @Test
    public void getXMLelements() {

        //http://54.90.169.134:8000/api/spartans
        XmlPath xp = given().log().uri()
                .accept(ContentType.XML)
                .when().get("http://54.90.169.134:8000/api/spartans").xmlPath();

        String thirdName = xp.getString("List.item[3].name");
        System.out.println("thirdName = " + thirdName);

        List<String> allNames = xp.getList("List.item.name");
        System.out.println("allNames = " + allNames);
    }


    // Homework 4 :
          /*
            Send GET Request to https://vpic.nhtsa.dot.gov/api/vehicles/GetMakeForManufacturer/988?format=xml
            and verify the
            count element text is 2
            message Results returned successfully
            first Make_ID is  474 , Make_Name Honda
            */
    @DisplayName("testing DOT API for get request")
    @Test
    public void DOTrequest() {

        Response response =
                given()
                        .log().all()
                        .queryParam("format", "xml")

                        .when()
                        .get("https://vpic.nhtsa.dot.gov/api/vehicles/GetMakeForManufacturer/988?format=xml");

        response.body().prettyPeek();

        XmlPath xp = response.xmlPath();
        int countNumber = xp.getInt("Response.Count");
        System.out.println("countNumber = " + countNumber);
        Assertions.assertEquals(2, countNumber);

        String makeId = xp.getString("Response.Results.MakesForMfg[0].Make_ID");
        System.out.println("MakeId = " + makeId);
        Assertions.assertEquals("474", makeId );

        String makeName = xp.getString("Response.Results.MakesForMfg[0].Make_Name");
        Assertions.assertEquals("HONDA", makeName);
        System.out.println("makeName = " + makeName);


    }


}
