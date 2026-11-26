package com.swapnil.api.tests;

import com.swapnil.api.base.BaseTest;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostsApiTest extends BaseTest {

    @Test(description = "GET an existing post and validate its key fields")
    public void shouldGetPostById() {
        given()
                .accept(ContentType.JSON)
        .when()
                .get("/posts/1")
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
                .accept(ContentType.JSON)
        .when()
                .get("/posts/1")
        .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/post-schema.json"));
    }

    @Test(description = "POST a new post")
    public void shouldCreatePost() {
        String requestBody = """
                {
                  "title": "API automation test",
                  "body": "Created using REST Assured",
                  "userId": 1
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
        .when()
                .post("/posts")
        .then()
                .statusCode(201)
                .body("title", equalTo("API automation test"))
                .body("userId", equalTo(1))
                .body("id", notNullValue());
    }

    @Test(description = "PUT an existing post")
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
                .contentType(ContentType.JSON)
                .body(requestBody)
        .when()
                .put("/posts/1")
        .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("title", equalTo("Updated API automation test"));
    }

    @Test(description = "DELETE an existing post")
    public void shouldDeletePost() {
        given()
        .when()
                .delete("/posts/1")
        .then()
                .statusCode(200);
    }

    @Test(description = "Return 404 for a post that does not exist")
    public void shouldReturnNotFoundForUnknownPost() {
        given()
        .when()
                .get("/posts/999999")
        .then()
                .statusCode(404);
    }
}
