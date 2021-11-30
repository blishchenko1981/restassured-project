package com.cydeo.day7;


import com.cydeo.pojo.Place;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class ZipCodeAPI_Test {


    @BeforeAll
    public static void setup() {
        baseURI = "https://api.zippopotam.us";
        basePath = "/us";
    }

    @AfterAll
    public static void teardown() {
        reset();
    }


    @Test
    public void testZipToCity() {

        given()
                .log().all()
                .pathParam("zip", 28079)
                .accept(ContentType.JSON).
                when()
                .get("/{zip}")
                .then()
                .log().all()
                .statusCode(is(200))
                .contentType(ContentType.JSON)
                .body("country", is("United States"))
                .body("places[0].state", is("North Carolina"))
                .body("'post code'", equalTo("28079"))
                .body("places[0].'place name'", is("Fairfax"))
        ;
    }
    @ParameterizedTest(name = "getting zipCode {0}")
    //@CsvFileSource(resources = "/zipcodes.csv")
    @MethodSource("com.cydeo.day8.ZipcodeMethodSourceTest#getAllZipCodes")

    public void testZipToCityDDT(int zipParam){

        System.out.println(zipParam);
        given()
                .log().uri()
                .pathParam("zip", zipParam).
        when()
                .get("/{zip}").
        then()
                .statusCode(200)

        ;

    }







/**
 *   Homework :
 *   in one test
 *   send request to GET https://api.zippopotam.us/us/va/fairfax
 *   log request all parts
 *   use va and fairfax as path variables with name state / city
 *   send get request verify
 *   status code 200 , json format
 */
@Test
public void homework() {

         given().log().uri()
                  .pathParam("state", "va")
                .pathParam("city", "fairfax")
                 .when().get("/{state}/{city}")
                 .then().log().all()
                 .statusCode(is(200))
              .contentType(ContentType.JSON)
         ;
      }

      @Test
    public void homework2 (){
         JsonPath jp =  given().log().uri()
                  .pathParam("state", "va")
                  .pathParam("city", "fairfax")
                  .when().get("/{state}/{city}").prettyPeek()
                  .jsonPath()
          ;

         String lastCity = jp.getString("places[-1].'place name'");
          System.out.println("lastCity = " + lastCity);

          List<String> listOfZip = jp.getList("places.'post code'");
          System.out.println("listOfZip = " + listOfZip);

          Place firstPlace = jp.getObject("places[0]", Place.class);
          System.out.println("firstPlace = " + firstPlace);
          System.out.println("firstPlace.getLatitude() = " + firstPlace.getLatitude());
          if(firstPlace.getLatitude()> 33){
              System.out.println("latitude is actually a number");
          }

          List<Place> listAllPLaces = jp.getList("places");
          System.out.println("listAllPLaces = " + listAllPLaces);
      }
      /**
 *   in another test
 *   send same request and store the response object
 *   get JsonPath object from the response
 *   print last place name
 *   print all zip codes after storing it into the list
 *   create a pojo called Place to represent place json object
 *      with these specific fields :
 *      - name as String
 *      - postCode as int
 *      - latitude as float
 *      - longitude as float
 *      {
 *             "place name": "Fairfax",
 *             "longitude": "-77.3242",
 *             "post code": "22030",
 *             "latitude": "38.8458"
 *         }
 *  de-serialize the first response into Place pojo and print it out
 *  save all the place json array into List<Place> and print it out.
 *
 */




      @ParameterizedTest
      @CsvFileSource(resources = "/state_city.csv", numLinesToSkip = 1)
    public void homeworkDDTZip(String state, String city){

          System.out.println("state = " + state);
          System.out.println("city = " + city);
          //send request to GET https://api.zippopotam.us/us/{state}/{city}
          given()
                  .log().uri()
                  .pathParam("state", state)
                  .pathParam("city", city).
          when()
                  .get("/{state}/{city}").
                  then()
                  .log().all()
                  .statusCode(is(200))
                  .contentType(ContentType.JSON)

          ;


      }




}



