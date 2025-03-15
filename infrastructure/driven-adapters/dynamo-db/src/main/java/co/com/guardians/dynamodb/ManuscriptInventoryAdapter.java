package co.com.guardians.dynamodb;

import co.com.guardians.dynamodb.entity.ManuscriptInventoryEntity;
import co.com.guardians.dynamodb.helper.TemplateAdapterOperations;
import co.com.guardians.model.manuscriptinventory.ManuscriptInventory;
import co.com.guardians.model.manuscriptinventory.gateways.ManuscriptInventoryGateway;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;
import software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.model.Select;

import java.util.List;

@Repository
public class ManuscriptInventoryAdapter extends TemplateAdapterOperations<ManuscriptInventory, String, ManuscriptInventoryEntity>  implements ManuscriptInventoryGateway {

    public ManuscriptInventoryAdapter(DynamoDbEnhancedClient connectionFactory,  ObjectMapper mapper) {
        super(connectionFactory, mapper, d -> mapper.map(d,  ManuscriptInventory.class), "manuscriptInventory");
    }
    public long countItemsInTable(DynamoDbTable<ManuscriptInventoryEntity> table) {
        ScanEnhancedRequest scanRequest = ScanEnhancedRequest.builder()
                .consistentRead(false)
                .select(Select.COUNT)
                .build();

        PageIterable<ManuscriptInventoryEntity> response = table.scan(scanRequest);
        return response.stream()
                .mapToLong(page -> page.items().size())
                .sum();
    }

    public List<ManuscriptInventory> getEntityByPartitionKey(String partitionKey) {
        QueryEnhancedRequest queryExpression = generateQueryExpression(partitionKey);
        return query(queryExpression);
    }

    private QueryEnhancedRequest generateQueryExpression(String partitionKey) {
        return QueryEnhancedRequest.builder()
                .queryConditional(QueryConditional.keyEqualTo(Key.builder().partitionValue(partitionKey).build()))
                .build();
    }

    public List< ManuscriptInventory> getEntityBySomeKeys(String partitionKey, String sortKey) {
        QueryEnhancedRequest queryExpression = generateQueryExpression(partitionKey, sortKey);
        return query(queryExpression);
    }
//
//    public List< ManuscriptInventory> getEntityBySomeKeysByIndex(String partitionKey, String sortKey) {
//        QueryEnhancedRequest queryExpression = generateQueryExpression(partitionKey, sortKey);
//        return queryByIndex(queryExpression, "secondary_index" /*index is optional if you define in constructor*/);
//    }

    private QueryEnhancedRequest generateQueryExpression(String partitionKey, String sortKey) {
        return QueryEnhancedRequest.builder()
                .queryConditional(QueryConditional.keyEqualTo(Key.builder().partitionValue(partitionKey).build()))
                .queryConditional(QueryConditional.sortGreaterThanOrEqualTo(Key.builder().sortValue(sortKey).build()))
                .build();
    }
}
