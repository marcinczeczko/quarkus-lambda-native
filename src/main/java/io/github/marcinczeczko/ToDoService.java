package io.github.marcinczeczko;

import io.github.marcinczeczko.model.Todo;
import io.quarkus.runtime.annotations.ConfigItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.jboss.logging.Logger;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanResponse;

@ApplicationScoped
public class ToDoService {

  private static final String TODO_ID = "todoId";
  private static final String TODO_VAL = "todoValue";

  private static final Logger LOG = Logger.getLogger(ToDoService.class);

  @ConfigItem(name = "dynamo.table.name")
  String tableName;

  @Inject
  DynamoDbClient dynamo;

  public List<Todo> fetchAll() {
    List<Todo> result = new ArrayList<>();
    ScanRequest scanRequest = ScanRequest.builder().tableName(tableName).attributesToGet(TODO_ID, TODO_VAL).build();
    ScanResponse scanResponse = dynamo.scan(scanRequest);
    for (Map<String, AttributeValue> item : scanResponse.items()) {
      result.add(new Todo(item.get(TODO_ID).s(), item.get(TODO_VAL).s()));
    }
    return result;
  }

  public void addItem(Todo todo) {
    Map<String, AttributeValue> item = new HashMap<>();
    item.put(TODO_ID, AttributeValue.builder().s(todo.getId()).build());
    item.put(TODO_VAL, AttributeValue.builder().s(todo.getName()).build());

    dynamo.putItem(PutItemRequest.builder().tableName(tableName).item(item).build());
  }

}
