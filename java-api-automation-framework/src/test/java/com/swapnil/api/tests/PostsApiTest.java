package com.swapnil.api.tests;

import com.swapnil.api.base.BaseTest;
import com.swapnil.api.constants.ApiEndpoints;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class PostsApiTest extends BaseTest {

    @Test(description = "GET an existing post and validate its key fields")
    public void shouldGetPostById() {
        given()
                .spec(requestSpec)
        .when()
                .get(ApiEndpoints.POST_BY_ID)
        .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(1))
                .body("userId", equalTo(1))
                .body("title", not(emptyOrNullString()))
                .body("body", not(emptyOrNullString()));
    }

    @Test(description = "Validate the post response against its JSON schema")
    public void shouldMatchPostJsonSchema() {
        given()
                .spec(requestSpec)
        .when()
                .get(ApiEndpoints.POST_BY_ID)
        .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/post-schema.json"));
    }

    @Test(description = "POST a new post and validate the created representation")
    public void shouldCreatePost() {
        String requestBody = """
                {
                  "title": "API automation test",
                  "body": "Created using REST Assured",
                  "userId": 1
                }
                """;

        given()
                .spec(requestSpec)
                .body(requestBody)
        .when()
                .post(ApiEndpoints.POSTS)
        .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("title", equalTo("API automation test"))
                .body("userId", equalTo(1))
                .body("id", notNullValue());
    }

    @Test(description = "PUT an existing post and validate the updated representation")
    public void shouldUpdatePost() {
        String requestBody = """
                {
                  "id": 1,
                  "title": "Updated API automation test",
                  "body": "Updated using REST Assured",
                  "userId": 1
                }
                """;

        given()
                .spec(requestSpec)
                .body(requestBody)
        .when()
                .put(ApiEndpoints.POST_BY_ID)
        .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("title", equalTo("Updated API automation test"));
    }

    @Test(description = "DELETE an existing post")
    public void shouldDeletePost() {
        given()
                .spec(requestSpec)
        .when()
                .delete(ApiEndpoints.POST_BY_ID)
        .then()
                .statusCode(200);
    }

    @Test(description = "Return 404 for a post that does not exist")
    public void shouldReturnNotFoundForUnknownPost() {
        given()
                .spec(requestSpec)
        .when()
                .get(ApiEndpoints.UNKNOWN_POST)
        .then()
                .statusCode(404);
    }
}
