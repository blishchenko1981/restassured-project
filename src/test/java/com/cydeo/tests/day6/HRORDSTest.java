package com.cydeo.tests.day6;

import com.cydeo.pojo.Car;
import com.cydeo.pojo.Job;
import com.cydeo.utility.HrORDSTestBase;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class HRORDSTest extends HrORDSTestBase {

    //GET Jobs
    @Test
    public void testJobs(){
        JsonPath jp = given().log().uri()
                .when()
                .get("/jobs")
               // .prettyPeek()
                .jsonPath();
        // we want to de-serialize first json object from json array
        // we want to be able to follow java naming convention for pojo fields
        // we want to ignore the json field we do not care about : link field

        Job j1 = jp.getObject("items[0]", Job.class) ;
        System.out.println("j1 = " + j1);

        // save all result into List<Job>
        List<Job> allJobs = jp.getList("items", Job.class);
        System.out.println("allJobs = " + allJobs);

        for (Job eachJob : allJobs) {

            if(eachJob.getMinSalary()>5000){

                System.out.println("job title with salary more than 5000 - " + eachJob.getJobTitle());

            }

        }


        // same steps with LOMBOK POJO
        Job j2 = jp.getObject("items[0]", Job.class) ;
        System.out.println("j2 = " + j2);

        List<Job> allJobs2 = jp.getList("items", Job.class);
        System.out.println("allJobs2 = " + allJobs2);
    }


    @Test
    public void testCarPOJO_Class(){

        Car c1 = new Car("Accord", "Honda", 2012, false);
        System.out.println("c1 = " + c1);

        c1.setModel("Civic");
        System.out.println("c1.getModel() = " + c1.getModel());

    }
}
