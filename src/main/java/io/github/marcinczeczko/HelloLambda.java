package io.github.marcinczeczko;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import java.util.Map;
import javax.inject.Inject;

public class HelloLambda implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

  @Inject
  HelloGreeter greeter;

  @Override
  public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, final Context context) {
    Map<String, String> query = request.getQueryStringParameters();

    return new APIGatewayProxyResponseEvent().withBody(greeter.greet(query.get("firstname"), query.get("lastname"))).withStatusCode(200);
  }
}
