package com.cydeo.tests.Practice;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class Day6 {


    @BeforeAll
    public static void  setUp(){
        RestAssured.baseURI = "http://54.236.150.168:1000";
        RestAssured.basePath = "/ords/hr/";

    }







}
