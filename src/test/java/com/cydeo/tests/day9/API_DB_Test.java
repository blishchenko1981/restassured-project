package com.cydeo.tests.day9;

import com.cydeo.tests.day6.HRORDSTest;
import com.cydeo.utility.DB_Util;
import com.cydeo.utility.HrORDSTestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class API_DB_Test extends HrORDSTestBase {

    @Test
    public void testRegion() {


        DB_Util.runQuery("select * from regions");
        System.out.println("DB_Util.getRowCount() = " + DB_Util.getRowCount());

        int expectedCount = DB_Util.getRowCount();
        DB_Util.displayAllData();


    }


    @DisplayName("Test Region count from API with DB")
    @Tag("db")
    @Test
    public void testRegionsCount() {

        // get expected result from DB
        DB_Util.runQuery("select * from regions");
        int expectedCount = DB_Util.getRowCount();


        // check same data from API side
        given().log().all().
                when().get("/regions").
                then().log().all()
                .statusCode(200)
                .body("count", is(expectedCount))
                .body("items", hasSize(expectedCount))
        ;


    }

    //
    @Test
    public void testSingleRegion() {
        DB_Util.runQuery("SELECT * FROM REGIONS WHERE REGION_ID = 1 ");
        Map<String, String> dbResultMap = DB_Util.getRowMap(1);
        System.out.println("dbResultMap = " + dbResultMap);

        int expectedRegionID = Integer.parseInt(dbResultMap.get("REGION_ID"));
        String expectedName = dbResultMap.get("REGION_NAME");

        given()
                .pathParam("region_id", 1)
                .log().all()
                .when()
                .get("/regions/{region_id}")
                .then().log().body()
                .statusCode(200)
                .body("region_id", equalTo(expectedRegionID))
                .body("region_name", is(expectedName))
        ;

    }

}
