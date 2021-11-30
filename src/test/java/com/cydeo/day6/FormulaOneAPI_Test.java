package com.cydeo.day6;

import com.cydeo.pojo.Driver;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.*;

import static io.restassured.RestAssured.*;

public class FormulaOneAPI_Test {

    // SEND GET http://ergast.com/api/f1/drivers.json
    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://ergast.com";
        RestAssured.basePath = "/api/f1";

    }

    @AfterAll
    public static void cleanup() {
        RestAssured.reset();
    }

    @Test
    public void testDriverDeserialization() {

        JsonPath jp = get("/drivers.json")
                .prettyPeek()
                .jsonPath();

        // first driver json path
        Driver d1 = jp.getObject("MRData.DriverTable.Drivers[0]", Driver.class);
        System.out.println("d1 = " + d1);


        List<Driver> allDrivers = jp.getList("MRData.DriverTable.Drivers", Driver.class);
        System.out.println("allDrivers = " + allDrivers);

        // find out name of all italians from list

        for (Driver allDriver : allDrivers) {
            if (allDriver.getNationality().equals("Italian")) {
                System.out.println("allDriver.getGivenName() = " + allDriver.getGivenName());
            }
        }

        for (Driver each : allDrivers) {

            System.out.println("each.getDriverId() = " + each.getDriverId());

        }
        ArrayList<String> countries = new ArrayList<>();

        Map<String, Integer> frequencyOfCountries = new TreeMap<>();

        for (Driver allDriver : allDrivers) {
            System.out.println("allDriver.getNationality() = " + allDriver.getNationality());
            countries.add(allDriver.getNationality());
        }


        Set<String> setOfCountries = new TreeSet<>(countries);

//        for (String country : countries) {
//
//            for (String s : countries) {
//                if(country.equals(s)){}
//
//
//            }
//        }

        for (String each : setOfCountries) {

            int i = 0;
            for (Driver every : allDrivers) {

                if (every.getNationality().equals(each)) {
                    i++;
                }

            }

            frequencyOfCountries.put(each, i);

        }

        System.out.println("frequencyOfCountries = " + frequencyOfCountries);
        int n = 0;
        for (Map.Entry<String, Integer> entry : frequencyOfCountries.entrySet()) {
            if (entry.getValue() > n) {
                n = entry.getValue();
            }
        }

        for (Map.Entry<String, Integer> entry : frequencyOfCountries.entrySet()) {

            if(entry.getValue()== n){
                System.out.println("Most frequent country is - " + entry.getKey());
            }
        }




        }


}

