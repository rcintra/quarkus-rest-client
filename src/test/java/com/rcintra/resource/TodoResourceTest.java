package com.rcintra.resource;

import com.rcintra.exception.TodoNotFoundException;
import com.rcintra.service.TodoService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class TodoResourceTest {

    @Inject
    TodoService todoService;

    @Test
    void testTodoService() {
        Assertions.assertNotNull(todoService);
    }

    @Test
    void findAll() {
        given()
          .when().get("/api/todos")
          .then()
             .statusCode(200)
             .body("$.size()", is(200));
    }

    @Test
    void testTodoByIdEndpoint() {
        given()
          .when().get("/api/todos/1")
          .then()
             .statusCode(200)
             .body("id", is(1))
                .body("userId", is(1))
             .body("title", is("delectus aut autem"))
                .body("completed", is(false));
    }

    @Test
    void testTodoByIdEndpointNotFound() {
        TodoNotFoundException todoNotFoundException = Assertions.assertThrows(TodoNotFoundException.class, () -> todoService.findById(201L));
        Assertions.assertEquals("Todo not found", todoNotFoundException.getMessage());
    }

    @Test
    void testTodoByIdEndpointNotFoundResponse() {
        given()
          .when().get("/api/todos/201")
          .then()
             .statusCode(404)
             .body("message", is("Todo not found"));
    }

}