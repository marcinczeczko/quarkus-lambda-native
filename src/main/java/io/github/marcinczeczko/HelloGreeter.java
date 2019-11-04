package io.github.marcinczeczko;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HelloGreeter {

  public ServiceResult greet(String first, String last) {
    return new ServiceResult().setGreeting(String.format("Hello %s %s.", first, last));
  }
}
