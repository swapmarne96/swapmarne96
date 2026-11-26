# Java API Automation Framework

A maintainable API automation starter built with **Java 17, REST Assured, TestNG and Maven**. It demonstrates API validation, reusable request configuration, schema checks and CI execution.

## Tech stack
- Java 17
- REST Assured
- TestNG
- Maven
- JSON Schema Validator
- GitHub Actions

## Project structure
```
java-api-automation-framework/
├── .github/workflows/maven.yml
├── pom.xml
├── testng.xml
├── src
│   └── test
│       ├── java/com/swapnil/api
│       │   ├── base/BaseTest.java
│       │   └── tests/PostsApiTest.java
│       └── resources
│           ├── config.properties
│           └── schemas/post-schema.json
└── README.md
```

## Scenarios covered
- GET a post and validate status, headers and response fields
- Validate a response against a JSON schema
- POST a new post and validate the created response
- PUT an existing post and validate the updated response
- DELETE a post and validate the response
- Validate a non-existent resource returns 404

The sample uses JSONPlaceholder, a public demo REST API. Its write operations are simulated and do not persist data.

## Run locally
Prerequisites: JDK 17+ and Maven 3.8+.

```bash
cd java-api-automation-framework
mvn clean test
```

Override the API base URL if needed:
```bash
mvn clean test -DbaseUrl=https://jsonplaceholder.typicode.com
```

## Engineering notes
- Keep environment-specific values in configuration, not test methods.
- Keep assertions explicit and focused on observable API behaviour.
- Never commit real credentials or access tokens.
- Extend this foundation with authentication providers, data-driven tests, parallel execution and richer reporting as the project grows.

## Author
**Swapnil Marne** — Lead QA Engineer | SDET | Automation & Quality Engineering
