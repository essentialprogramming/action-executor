package com.integration;

import com.api.model.StoryInput;
import com.api.output.ExecutionHistoryJSON;
import com.util.TestEntityGenerator;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class StoryControllerTest {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @AfterAll
    public static void afterAll() {
        RestAssured.reset();
    }

    @Test
    void should_return_status_ok() {

        final StoryInput storyInput = TestEntityGenerator.getStoryInput();

        //get story key
        RestAssured.basePath = "/v1/action";

        String storyKey =
                given()
                        .accept(ContentType.JSON)
                        .contentType(ContentType.JSON)
                        .body(storyInput)
                        .when()
                        .post("/execute")
                        .then()
                        .assertThat()
                        .statusCode(200)
                        .extract().path("storyKey");

        RestAssured.basePath = "/v1/story";

        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .queryParam("storyKey", storyKey)
                .queryParam("reviewStatus", "ACCEPTED")
        .when()
                .post("/review")
        .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    void should_return_status_bad_request() {

        final StoryInput storyInput = TestEntityGenerator.getStoryInput();

        //get story key
        RestAssured.basePath = "/v1/action";

        String storyKey =
                given()
                        .accept(ContentType.JSON)
                        .contentType(ContentType.JSON)
                        .body(storyInput)
                .when()
                        .post("/execute")
                .then()
                        .assertThat()
                        .statusCode(200)
                        .extract().path("storyKey");

        RestAssured.basePath = "/v1/story";

        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .queryParam("storyKey", storyKey)
                .queryParam("reviewStatus", "BAMBOOZLED")
        .when()
                .post("/review")
        .then()
                .assertThat()
                .statusCode(400)
                .and()
                .body("message", equalTo("Invalid review status! Must be of value: ACCEPTED, REJECTED, CHANGES_REQUIRED"));
    }

    @Test
    void should_return_status_status_not_found() {

        RestAssured.basePath = "/v1/story";

        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .queryParam("storyKey", "idontexist")
                .queryParam("reviewStatus", "ACCEPTED")
        .when()
                .post("/review")
        .then()
                .assertThat()
                .statusCode(404)
                .and()
                .body("message", equalTo("Story not found!"));
    }

    @Test
    void should_return_status_status_unprocessable_entity() {

        final StoryInput storyInput = TestEntityGenerator.getStoryInput();

        //get story key
        RestAssured.basePath = "/v1/action";

        String storyKey =
                given()
                        .accept(ContentType.JSON)
                        .contentType(ContentType.JSON)
                        .body(storyInput)
                .when()
                        .post("/execute")
                .then()
                        .assertThat()
                        .statusCode(200)
                        .extract().path("storyKey");

        RestAssured.basePath = "/v1/story";

        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .queryParam("storyKey", storyKey)
                .queryParam("reviewStatus", "ACCEPTED")
        .when()
                .post("/review")
        .then()
                .assertThat()
                .statusCode(200);

        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .queryParam("storyKey", storyKey)
                .queryParam("reviewStatus", "REJECTED")
        .when()
                .post("/review")
        .then()
                .assertThat()
                .statusCode(422)
                .and()
                .body("message", equalTo("Story not in pull request!"));
    }
}
