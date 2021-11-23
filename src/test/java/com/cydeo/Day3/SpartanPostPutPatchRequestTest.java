package com.cydeo.Day3;

import com.cydeo.utility.SpartanAPItestBase;
import org.junit.jupiter.api.Test;
import com.cydeo.utility.SpartanAPItestBase;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SpartanPostPutPatchRequestTest extends SpartanAPItestBase {

    @Test
    public void testAddOneDataStringBody(){

        /**
         POST /spartans
         content type is json
         body is
         {
         "name":"API POST",
         "gender":"Male",
         "phone":1231231231
         }
         *
         * expect status 201
         * body json format
         * response body
         * {
         *     "success": "A Spartan is Born!",
         *     "data": {
         *         "id": 544,
         *         "name": "API POST",
         *         "gender": "Male",
         *         "phone": 1231231231
         *     }
         * }
         */

        String strBody = "{\n" +
                "         \"name\":\"API POST\",\n" +
                "         \"gender\":\"Male\",\n" +
                "         \"phone\":1231231231\n" +
                "         }";

        given()
                .log().all()
                .contentType(ContentType.JSON)  // contnt type header
                .body(strBody) // this how we provide body for POST
                .when()
                .post("/spartans")
                .then()
                .log().all()
                .statusCode(201)
                .body("success", is("A Spartan is Born!"))
                .body("data.name", is("API POST"))
                .body("data.phone", is (1231231231))

                ;


         }

         @Test
    public void testPatch(){

        String UpdateName = "{\"name\": \"Artem\"}";

           //  System.out.println("given().\n                log().all()\n                .contentType(ContentType.JSON)\n                .body(UpdateName)\n                .pathParam(\"id\", 2)\n                .when()\n                .patch(\"spartans/{id}\")\n                .getStatusCode() = " + given().
                    given()

                            .log().all()
                     .contentType(ContentType.JSON)
                     .body(UpdateName)
                     .pathParam("id", 2)

                     .when()
                     .patch("spartans/{id}")

                     .then()
                     .log().all()
                     .statusCode(204)

                    ;
         }

         @Test
    public void testPUT(){
             /**
              PUT /spartans
              content type is json
              body is
              {
              "name":"New Body",
              "gender":"Male",
              "phone":5555555555
              }
              *
              * expect status 204
              */

        String updatePerson = "   {\n" +
                "             \"name\":\"New Body\",\n" +
                "             \"gender\":\"Male\",\n" +
                "             \"phone\":5555555555\n" +
                "         }";

        given().log().all()
                .header("Content-Type", ContentType.JSON)
                .body(updatePerson)
                .pathParam("id", 3)

                .when()
                .put("/spartans/{id}")

                .then()
                .log().all()
                .statusCode(  equalTo(204)   );



         }

         @Test
    public void deleteTest(){

        Response response = get("/spartans");
        int lastId =  response.path("id[-1]");
given().log().all().pathParam("id", lastId).when().delete("spartans/{id}").then().statusCode(204);
             // we can addintionlay send another request to this id and expect 404
            // to make sure it actually worked




         }


}
