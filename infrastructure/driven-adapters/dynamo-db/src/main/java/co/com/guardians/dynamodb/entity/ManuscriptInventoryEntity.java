package co.com.guardians.dynamodb.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@Setter
@ToString
@DynamoDbBean
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ManuscriptInventoryEntity {
    private String manuscript;
    private String hiddenClue;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("manuscript")
    public String getManuscript() { return manuscript; }

    @DynamoDbAttribute("hiddenClue")
    public String getHiddenCluel() {
        return hiddenClue;
    }
}
