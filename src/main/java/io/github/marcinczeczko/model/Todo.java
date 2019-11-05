package io.github.marcinczeczko.model;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Todo {

  private String id;
  private String name;

  public Todo(String id, String name) {
    this.id = id;
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}
