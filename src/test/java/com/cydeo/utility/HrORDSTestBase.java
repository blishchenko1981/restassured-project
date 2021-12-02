package com.cydeo.utility;


import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.reset;
    public class HrORDSTestBase {
    /**
     * This class will serve as Base Class to set up BaseURI and BasePath
     * and clean up after all test for any HR ORDS related test classes.
     */


        // set up and teardown
        @BeforeAll
        public static void setup(){
            RestAssured.baseURI = "http://54.236.150.168:1000" ;
            RestAssured.basePath = "/ords/hr";
            DB_Util.createConnection();

        }

        @AfterAll
        public static void teardown(){
            reset();
            DB_Util.destroy();
        }

    }



