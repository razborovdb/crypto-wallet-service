package com.development.fans.cryptocurrency.wallet.dependency;

import com.amazon.ata.aws.dynamodb.DynamoDbClientProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * DynamoDBMapper.
 * @return - DynamoDBMapper
 */
@Module
public class DaoModule {
    /**
     * DynamoDBMapper.
     * @return - DynamoDBMapper
     */
    @Singleton
    @Provides
    public DynamoDBMapper provideDynamoDBMapper() {
        return new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient(getRegion()));
    }

    private Regions getRegion() {

        return Regions.US_WEST_2;
    }
}
