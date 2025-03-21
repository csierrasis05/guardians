package co.com.guardians.dynamodb.config;

import co.com.guardians.dynamodb.entity.ManuscriptInventoryEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.auth.credentials.WebIdentityTokenFileCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;

@Configuration
public class DynamoDBConfig {

    @Bean
    @Profile("local")
    public DynamoDbClient amazonDynamoDBLocal(@Value("${aws.region}") String region,
                                         @Value("${aws.dynamodb.endpoint}") String endpoint) {
        return DynamoDbClient.builder()
                .credentialsProvider(ProfileCredentialsProvider.create("default"))
                .region(Region.of(region))
                .endpointOverride(URI.create(endpoint))
                .build();
    }

    @Bean
    @Profile("!local")
    public DynamoDbClient amazonDynamoDB(@Value("${aws.region}") String region) {
        return DynamoDbClient.builder()
//                .credentialsProvider(ProfileCredentialsProvider.create("default"))
                .credentialsProvider(WebIdentityTokenFileCredentialsProvider.create())
                .region(Region.of(region))
                .build();
    }

    @Bean
    public DynamoDbEnhancedClient getDynamoDbEnhancedClient(DynamoDbClient client) {
        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(client)
                .build();
    }

}
