package io.github.marcinczeczko;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class ServiceStatus {

  private String greeting;
  private String context;

  public String getGreeting() {
    return greeting;
  }

  public void setGreeting(String greeting) {
    this.greeting = greeting;
  }

  public String getContext() {
    return context;
  }

  public void setContext(String context) {
    this.context = context;
  }
}
