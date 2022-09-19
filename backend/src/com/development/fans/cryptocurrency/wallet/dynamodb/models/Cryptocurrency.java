package com.development.fans.cryptocurrency.wallet.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

/**
 * Represents a record in the cryptocurrency table.
 */
@DynamoDBTable(tableName = "cryptocurrency")
public class Cryptocurrency {
    public static final String CRYPTOCURRENCY_INDEX = "cryptocurrencyIndex";
    private String cryptoName;
    private String cryptoDescription;
    private Double cryptoAmount;
    private Double cryptoCost;

    @DynamoDBHashKey(attributeName = "cryptoName")
    @DynamoDBIndexRangeKey(attributeName = "cryptoName", globalSecondaryIndexName = CRYPTOCURRENCY_INDEX)
    public String getCryptoName() {
        return cryptoName;
    }

    public void setCryptoName(String cryptoName) {
        this.cryptoName = cryptoName;
    }

    @DynamoDBAttribute(attributeName = "cryptoDescription")
    public String getCryptoDescription() {
        return cryptoDescription;
    }

    public void setCryptoDescription(String cryptoDescription) {
        this.cryptoDescription = cryptoDescription;
    }

    public Double getCryptoAmount() {
        return cryptoAmount;
    }

    @DynamoDBAttribute(attributeName = "cryptoAmount")
    public void setCryptoAmount(Double cryptoAmount) {
        this.cryptoAmount = cryptoAmount;
    }

    @DynamoDBIndexHashKey(attributeName = "cryptoCost", globalSecondaryIndexName = CRYPTOCURRENCY_INDEX)
    public Double getCryptoCost() {
        return cryptoCost;
    }

    public void setCryptoCost(Double cryptoCost) {
        this.cryptoCost = cryptoCost;
    }

}
