package io.github.marcinczeczko;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import io.github.marcinczeczko.model.TodoList;
import javax.inject.Inject;
import javax.inject.Named;
import org.jboss.logging.Logger;

@Named("fetchAll")
public class TodoFetchAll implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

  private static final Logger LOG = Logger.getLogger(TodoFetchAll.class);

  @Inject
  ToDoService service;

  @Override
  public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, final Context context) {
    try {
      return RequestUtils.successResponse(new TodoList(service.fetchAll()));
    } catch (Exception e) {
      LOG.error("Error processing request", e);
      return RequestUtils.errorResponse(500, "[Lambda] Unable to fetch TODO from DynamoDB");
    }
  }
}
