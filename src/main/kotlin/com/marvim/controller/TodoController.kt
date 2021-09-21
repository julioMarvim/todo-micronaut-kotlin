package com.marvim.controller

import com.marvim.model.Todo
import com.marvim.repository.TodoRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule

@Controller("/todos")
@Secured(SecurityRule.IS_AUTHENTICATED)
class TodoController(private  val todoRepository: TodoRepository) {

    @Get
    @Secured(SecurityRule.IS_AUTHENTICATED)
    fun getTodos(): List<Todo>{
        return todoRepository.findAll()
    }

    @Post
    @Secured(SecurityRule.IS_AUTHENTICATED)
    fun addTodo(todo: Todo): HttpResponse<Todo> {
        return HttpResponse.created(todoRepository.save(todo))
    }
}