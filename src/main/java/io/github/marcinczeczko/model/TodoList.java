package io.github.marcinczeczko.model;

import io.quarkus.runtime.annotations.RegisterForReflection;
import java.util.List;

@RegisterForReflection
public class TodoList {

  private List<Todo> todos;

  public TodoList() {
    //Default constructor
  }

  public TodoList(List<Todo> todos) {
    this.todos = todos;
  }

  public List<Todo> getTodos() {
    return todos;
  }
}
