package com.cydeo.tests.day12;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class day12XMLfileTest {

    @Test
    public void testMovieXML() {

        // if we do not hav emany test to share the baseURI
        // we can directly provide it in given section

        given()
                .baseUri("http://www.omdbapi.com/")
                .queryParam("apiKey", "25d8fdf1")
                .queryParam("s", "The office")
                .queryParam("r", "xml")
                .log().all()

                .when()
                .get()

                .then()
                .log().all()
                .contentType(ContentType.XML)
                .body("root.@totalResults", is("277"))
                //count how many items we had on the page
                .body("root.result.@title", hasSize(10))
        ;
    }

    @Test
    public void testGettingAllMovies() {
// send reuqest figure out how many results do I have
        List<String> allMovies = new ArrayList<>();
        Response response = getMovieResponse("The Office", 1);

        int totalCount = Integer.parseInt(response.path("totalResults"));
        System.out.println("totalCount = " + totalCount);
        List<String> page1Movies = response.path("Search.Title");
        System.out.println("page1Movies = " + page1Movies);

        // add first page movies into allMovies list
        allMovies.addAll(page1Movies);
        System.out.println("totalCount = " + totalCount);
        // count how many pages do we have
        int totalPageCount = (totalCount % 10 == 0) ? totalCount / 10 : totalCount / 10 + 1;

        for (int currentPage = 2; currentPage <= totalPageCount; currentPage++) {
            // sending request to get second page

            response = getMovieResponse("The Office", currentPage);
            List<String> currentPageMovieList = response.path("Search.Title");
            allMovies.addAll(currentPageMovieList);

        }
        Assertions.assertEquals(totalCount, allMovies.size());

        System.out.println("allMovies.size() = " + allMovies.size());
    }

    public static Response getMovieResponse(String movieName, int currentPage) {
        return given()
                .baseUri("http://www.omdbapi.com/")
                .queryParam("apiKey", "25d8fdf1")
                .queryParam("s", "The office")
                .queryParam("page", currentPage)
                .log().all()
                .when().get();
    }
}
