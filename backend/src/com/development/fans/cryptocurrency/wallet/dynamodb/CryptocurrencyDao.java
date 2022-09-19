package com.development.fans.cryptocurrency.wallet.dynamodb;

import com.development.fans.cryptocurrency.wallet.dynamodb.models.Cryptocurrency;
import com.development.fans.cryptocurrency.wallet.exceptions.CryptocurrencyNotFoundException;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

public class CryptocurrencyDao {
    private final DynamoDBMapper dynamoDbMapper;

    /**
     * CryptocurrencyDao.
     * @param dynamoDbMapper - DynamoDBMapper
     */
    @Inject
    public CryptocurrencyDao(DynamoDBMapper dynamoDbMapper) {
        this.dynamoDbMapper = dynamoDbMapper;
    }

    /**
     * Cryptocurrency.
     * @param cryptoName - cryptoName
     * @return - cryptocurrency
     */
    public Cryptocurrency getCryptocurrency(String cryptoName) {
        Cryptocurrency cryptocurrency = dynamoDbMapper.load(Cryptocurrency.class, cryptoName);
        if (cryptocurrency == null) {
            throw new CryptocurrencyNotFoundException("Cannot find cryptocurrency with cryptoName = " + cryptoName);
        }

        return cryptocurrency;
    }

    /**
     * getAllCryptocurrency.
     * @param greaterThan - greaterThan
     * @return cryptocurrencyList
     */
    public List<Cryptocurrency> getAllCryptocurrency(Double greaterThan) {
        Map<String, AttributeValue> cryptocurrencyAV = new HashMap<String, AttributeValue>();
        cryptocurrencyAV.put(":val1", new AttributeValue().withN(Double.toString(greaterThan)));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("cryptoCost > :val1")
                .withExpressionAttributeValues(cryptocurrencyAV)
                .withConsistentRead(false)
                .withIndexName(Cryptocurrency.CRYPTOCURRENCY_INDEX);

        List<Cryptocurrency> cryptocurrencyList = new ArrayList<>(dynamoDbMapper.scan(Cryptocurrency.class,
                scanExpression));
        if (cryptocurrencyList == null) {
            cryptocurrencyList = new ArrayList<>();
        }

        return cryptocurrencyList;
    }
}
