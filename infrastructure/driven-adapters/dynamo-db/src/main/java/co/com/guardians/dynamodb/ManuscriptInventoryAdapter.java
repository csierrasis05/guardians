package co.com.guardians.dynamodb;

import co.com.guardians.dynamodb.entity.ManuscriptInventoryEntity;
import co.com.guardians.dynamodb.helper.TemplateAdapterOperations;
import co.com.guardians.model.manuscriptinventory.ManuscriptInventory;
import co.com.guardians.model.manuscriptinventory.gateways.ManuscriptInventoryGateway;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ManuscriptInventoryAdapter extends TemplateAdapterOperations<ManuscriptInventory, String, ManuscriptInventoryEntity>  implements ManuscriptInventoryGateway {

    private final DynamoDbTable<ManuscriptInventoryEntity> table;

    public ManuscriptInventoryAdapter(DynamoDbEnhancedClient connectionFactory, ObjectMapper mapper) {
        super(connectionFactory, mapper, d -> mapper.map(d, ManuscriptInventory.class), "manuscriptInventory");
        this.table = connectionFactory.table("manuscriptInventory", TableSchema.fromBean(ManuscriptInventoryEntity.class));
    }

    public List<ManuscriptInventory> getAllManuscripts() {
        return table.scan(ScanEnhancedRequest.builder().build())
                .items()
                .stream()
                .map(entity -> new ManuscriptInventory(entity.getManuscript(), entity.getHiddenClue()))
                .collect(Collectors.toList());
    }

}
