package com.cydeo.tests.day6;

import com.cydeo.pojo.Article;
import com.cydeo.pojo.Article2;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;


public class NewsAPI_Deserialization_Test {
//https://newsapi.org/v2/everything?q=Apple&from=2021-11-15&sortBy=popularity
    // c533252d80484e0eadaa1a28a5d2ffdd


    @BeforeAll
    public static void setUp() {
        baseURI = "https://newsapi.org";
        basePath = "/v2";
    }

    @AfterAll
    public static void teardown() {
        reset();
    }

    @Test
    public void testNews() {

        JsonPath jp = given()
                .log().all()
                .queryParam("country", "us")
                .header("Authorization", "Bearer c533252d80484e0eadaa1a28a5d2ffdd").
                when()
                .get("/top-headlines").prettyPeek()
                .jsonPath();

        //Create a POJO class to represent Article
        //required fields : source , author , title
        //get a List<Article> from json array
        // print out the name of author and title of article if source id is null

        //try to get first Article into POJO
//        Article a1 = jp.getObject("article[0]", Article.class);
//        System.out.println("a1 = " + a1);
//        System.out.println("a1.getSource().get(\"id\") = " + a1.getSource().get("id"));

        // get a list
        List<Article> allArticles = jp.getList("articles", Article.class);
        for (Article each : allArticles) {

            if (each.getSource().get("id") != null) {
                System.out.println("each.getAuthor() = " + each.getAuthor());
            }

        }
        Article2 a2= jp.getObject("articles[0]", Article2.class);
        System.out.println("a2 = " + a2);

        System.out.println("a2.getSource().getId() = " + a2.getSource().getId());



        List<Article2> allArt2 = jp.getList("articles", Article2.class);
        for (Article2 each : allArt2) {
            if(each.getSource().getId() == null){
                System.out.println("each.getAuthor() = " + each.getAuthor());
            }
        }

    }



}

/**
 * GET https://newsapi.org/v2/top-headlines?country=us
 * Header :  Authorization = Bearer c533252d80484e0eadaa1a28a5d2ffdd
 */
