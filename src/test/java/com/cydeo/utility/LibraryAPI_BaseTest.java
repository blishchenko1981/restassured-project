package com.cydeo.utility;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.*;

public class LibraryAPI_BaseTest {

    @BeforeAll
    public static void setup(){
        baseURI= "https://library2.cybertekschool.com/";
        basePath= "/rest/v1";


    }
    @AfterAll
    public static void tearDown() {
        reset();
    }


}
