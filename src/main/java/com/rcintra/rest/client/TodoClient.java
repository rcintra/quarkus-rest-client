package com.rcintra.rest.client;

import com.rcintra.dto.Todo;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

// https://jsonplaceholder.typicode.com/todos
@Path("/todos")
@RegisterRestClient
public interface TodoClient {

    @GET
    List<Todo> todos();

    @GET
    @Path("/{id}")
    Todo findById(@PathParam("id") Long id);
}
