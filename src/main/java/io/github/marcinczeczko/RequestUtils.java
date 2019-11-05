package io.github.marcinczeczko;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.github.marcinczeczko.model.ErrorResponse;
import io.github.marcinczeczko.model.Todo;
import io.github.marcinczeczko.model.TodoList;
import java.io.IOException;
import org.jboss.logging.Logger;

public class RequestUtils {

  private static final Logger LOG = Logger.getLogger(TodoAdd.class);

  static ObjectWriter writer = new ObjectMapper().writerFor(ErrorResponse.class);
  static ObjectReader todoReader = new ObjectMapper().readerFor(Todo.class);
  static ObjectWriter todoWriter = new ObjectMapper().writerFor(Todo.class);
  static ObjectWriter todoListWriter = new ObjectMapper().writerFor(TodoList.class);

  public static APIGatewayProxyResponseEvent errorResponse(int errorCode, String message) {
    APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
    try {
      return response.withStatusCode(errorCode).withBody(writer.writeValueAsString(new ErrorResponse(message, errorCode)));
    } catch (JsonProcessingException e) {
      LOG.error(e);
      return response.withStatusCode(500).withBody("Internal error");
    }
  }

  public static APIGatewayProxyResponseEvent successResponse(Todo todo) {
    try {
      return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody(todoWriter.writeValueAsString(todo));
    } catch (JsonProcessingException e) {
      return errorResponse(500, "Unable to write TODO to JSON");
    }
  }

  public static APIGatewayProxyResponseEvent successResponse(TodoList todoList) {
    try {
      return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody(todoListWriter.writeValueAsString(todoList));
    } catch (JsonProcessingException e) {
      return errorResponse(500, "Unable to write TODO List to JSON");
    }
  }

  public static Todo fromRequest(APIGatewayProxyRequestEvent event) {
    Todo todo;
    try {
      todo = todoReader.readValue(event.getBody());
    } catch (IOException e) {
      throw new TodoJsonException("Unable to parse TODO in body", e);
    }
    return todo;
  }
}
