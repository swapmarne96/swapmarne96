package com.swapnil.api.base;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class BaseTest {

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

        String configuredUrl = System.getProperty(
                "baseUrl",
                properties.getProperty("baseUrl")
        );

        RestAssured.baseURI = configuredUrl;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
