package io.github.marcinczeczko;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Named;
import org.jboss.logging.Logger;

@Named("hello")
public class HelloLambda implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

  private static final Logger log = Logger.getLogger(HelloLambda.class);

  @Inject
  HelloGreeter greeter;

  ObjectWriter writer = new ObjectMapper().writerFor(ServiceResult.class);

  @Override
  public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, final Context context) {
    Map<String, String> query = request.getQueryStringParameters();
    log.info(String.format("[%s] Processed data", context.getAwsRequestId()));

    ServiceResult result = greeter.greet(query.get("firstname"), query.get("lastname")).setContext(context.toString());

    try {
      return new APIGatewayProxyResponseEvent().withBody(writer.writeValueAsString(result)).withStatusCode(200);
    } catch (JsonProcessingException e) {
      log.error("Error processing", e);
      return new APIGatewayProxyResponseEvent().withBody(e.getMessage()).withStatusCode(500);
    }
  }
}
