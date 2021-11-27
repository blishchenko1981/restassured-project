package com.cydeo.day6;

import com.cydeo.pojo.Driver;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class FormulaOneAPI_Test {

    // SEND GET http://ergast.com/api/f1/drivers.json
    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://ergast.com" ;
        RestAssured.basePath = "/api/f1" ;

    }
    @AfterAll
    public static void cleanup(){
        RestAssured.reset();
    }

    @Test
    public void testDriverDeserialization(){

        JsonPath jp = get("/drivers.json")
                .prettyPeek()
                .jsonPath();

        // first driver json path
        Driver d1 = jp.getObject("MRData.DriverTable.Drivers[0]",Driver.class);
        System.out.println("d1 = " + d1);


        List<Driver> allDrivers = jp.getList("MRData.DriverTable.Drivers",Driver.class);
        System.out.println("allDrivers = " + allDrivers);

        // find out name of all italians from list

        for (Driver allDriver : allDrivers) {

            if(allDriver.getNationality().equals("Italian")){
                System.out.println("allDriver.getGivenName() = " + allDriver.getGivenName());
            }
        }
    }
}
