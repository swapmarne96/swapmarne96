package com.swapnil.api.base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static io.restassured.http.ContentType.JSON;

public abstract class BaseTest {

    protected RequestSpecification requestSpec;

    @BeforeClass(alwaysRun = true)
    public void configureApi() throws IOException {
        Properties properties = new Properties();

        try (InputStream input = getClass().getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new IllegalStateException("config.properties was not found");
            }
            properties.load(input);
        }

        String baseUrl = System.getProperty(
                "baseUrl",
                properties.getProperty("baseUrl")
        );

        if (baseUrl == null || baseUrl.isBlank()) {
            throw new IllegalStateException(
                    "API base URL is missing. Set baseUrl in config.properties or pass -DbaseUrl."
            );
        }

        requestSpec = new RequestSpecBuilder()
                .setBaseUri(baseUrl.trim())
                .setAccept(JSON)
                .setContentType(JSON)
                .build();

        // Log request and response details only when a validation fails.
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
