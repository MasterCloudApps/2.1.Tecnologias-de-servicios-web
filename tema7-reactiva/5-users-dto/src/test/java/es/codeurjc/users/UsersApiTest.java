package es.codeurjc.users;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsersApiTest {

    @LocalServerPort
    int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void createNewUserTest() {

        given().
                request()
                .body("{ \"firstName\" : \"Antonio\", \"lastName\": \"González\" }")
                .contentType(ContentType.JSON).
        when().
                post("/users/").
        then().
                statusCode(201).
                body("id", notNullValue());

    }

    @Test
    public void getById() {

        given().
                request().
        when().
                get("/users/1").
        then().
                statusCode(200).
                body("id", is(1));
    }

    @Test
    public void getByIdNofFound() {

        given().
                request().
                when().
                get("/users/1000").
                then().
                statusCode(404);
    }

    @Test
    public void getByFirstName() {

        given().
                request().param("firstName", "Juan").
        when().
                get("/users/").
        then().
                statusCode(200).
                body("size()", is(2));
    }

    @Test
    public void deleteUser() {

        given().
            request().
        when().
            delete("/users/2").
        then().
            statusCode(200).
            body("id", is(2));

        given().
            request().
        when().
            get("/users/2").
        then().
            statusCode(404);

    }

}