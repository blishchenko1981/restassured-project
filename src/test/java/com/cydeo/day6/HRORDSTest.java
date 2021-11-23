package com.cydeo.day6;

import com.cydeo.pojo.Job;
import com.cydeo.utility.HrORDSTestBase;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class HRORDSTest extends HrORDSTestBase {

    //GET Jobs
    @Test
    public void testJobs(){
        JsonPath jp = given().log().uri()
                .when().get("/jobs")
                .prettyPeek().jsonPath();
        // we want to de-serialize first json object from json array
        // we want to be able to follow java naming convention for pojo fields
        // we want to ignore the json field we do not care about : link field

        Job j1 = jp.getObject("items[0]", Job.class) ;
        System.out.println("j1 = " + j1);

        // save all result into List<Job>



    }

}
