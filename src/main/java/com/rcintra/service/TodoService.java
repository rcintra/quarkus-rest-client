package com.rcintra.service;


import com.rcintra.dto.Todo;
import com.rcintra.rest.client.TodoClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@ApplicationScoped
public class TodoService {

    @RestClient
    TodoClient todoClient;

    public List<Todo> todos() {
        return todoClient.todos();
    }

    public Todo findById(Long id) {
        return todoClient.findById(id);
    }
}
