# Java API Automation Framework

A maintainable API automation starter built with **Java 17, REST Assured, TestNG and Maven**. This project demonstrates reusable request configuration, HTTP method coverage, response assertions, JSON schema validation and CI execution.

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
├── pom.xml
├── testng.xml
├── src/test/
│   ├── java/com/swapnil/api/
│   │   ├── base/BaseTest.java
│   │   ├── constants/ApiEndpoints.java
│   │   └── tests/PostsApiTest.java
│   └── resources/
│       ├── config.properties
│       └── schemas/post-schema.json
└── README.md
.github/workflows/
└── java-api-automation.yml
```

## Framework design
- **BaseTest:** loads environment configuration and builds a reusable REST Assured request specification.
- **ApiEndpoints:** centralizes endpoint paths so tests do not scatter URL strings.
- **PostsApiTest:** contains readable TestNG scenarios that focus on API behaviour and response assertions.
- **config.properties:** provides the default base URL; a Maven system property can override it.
- **JSON schema:** validates the structure and data types of a post response.

## Scenarios covered
- GET a post and validate status, content type and response fields
- Validate a response against a JSON schema
- POST a new post and validate the created response
- PUT an existing post and validate the updated response
- DELETE a post and validate the response
- Validate a non-existent resource returns 404

The sample uses JSONPlaceholder, a public demo REST API. Its write operations are simulated and do not persist data. Do not use this demo API to validate real payment or production business flows.

## Run locally
Prerequisites: JDK 17+ and Maven 3.8+.

```bash
cd java-api-automation-framework
mvn clean test
```

Override the API base URL:
```bash
mvn clean test -DbaseUrl=https://jsonplaceholder.typicode.com
```

## CI
The GitHub Actions workflow at `.github/workflows/java-api-automation.yml` runs the Maven test suite on pushes, pull requests and manual dispatches that target this project.

## Engineering notes
- Keep environment-specific values in configuration, not test methods.
- Reuse request setup while keeping test-specific data and assertions explicit.
- Validate business outcomes, not only HTTP status codes.
- Never commit real credentials, access tokens or production data.
- Future extensions can include authentication providers, data-driven tests, parallel execution and richer reporting.

## Author
**Swapnil Marne** — Lead QA Engineer | SDET | Automation & Quality Engineering
