package io.github.marcinczeczko;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class ServiceResult {

  private String greeting;
  private String context;

  public String getGreeting() {
    return greeting;
  }

  public ServiceResult setGreeting(String greeting) {
    this.greeting = greeting;
    return this;
  }

  public String getContext() {
    return context;
  }

  public ServiceResult setContext(String context) {
    this.context = context;
    return this;
  }
}
