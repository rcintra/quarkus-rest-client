package com.rcintra.resource;

import com.rcintra.dto.Todo;
import com.rcintra.exception.TodoNotFoundException;
import com.rcintra.service.TodoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;


@Path("/api")
public class TodoResource {

    @Inject
    TodoService todoService;

    @GET
    @Path("/todos/{id}")
    public Response todoById(@PathParam("id") Long id) {
        Todo todo = todoService.findById(id);
        if (todo == null) {
            throw new TodoNotFoundException("Todo not found");
        }
       return Response.ok().entity(todo).build();
    }

    @GET
    @Path("/todos")
    public Response todos() {
        try {
            return Response.ok().entity(todoService.todos()).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

}
