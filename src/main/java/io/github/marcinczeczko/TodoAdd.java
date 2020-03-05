package io.github.marcinczeczko;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import io.github.marcinczeczko.model.Todo;
import javax.inject.Inject;
import javax.inject.Named;
import org.jboss.logging.Logger;

@Named("add")
public class TodoAdd implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

  private static final Logger LOG = Logger.getLogger(TodoAdd.class);

  @Inject
  ToDoService service;

  @Override
  public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, final Context context) {
    String contentType = request.getHeaders().get("Content-Type");

    if (contentType == null || !contentType.matches("application/json")) {
      return RequestUtils.errorResponse(415, "[Lambda] application/json is allowed only");
    }

    try {
      Todo todo = RequestUtils.fromRequest(request);
      service.addItem(todo);
      return RequestUtils.successResponse(todo);

    } catch (TodoJsonException e) {
      LOG.error("Error processing request", e);
      return RequestUtils.errorResponse(400, "[Lambda] unable to read Todo JSON from the body");
    } catch (Exception e) {
      LOG.error("Error processing request", e);
      return RequestUtils.errorResponse(500, "[Lambda] Unable to Add item to DynamoDB");
    }
  }
}
