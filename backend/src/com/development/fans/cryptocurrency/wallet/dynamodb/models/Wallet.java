package com.development.fans.cryptocurrency.wallet.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.List;

/**
 * Represents a record in the wallets table.
 */
@DynamoDBTable(tableName = "wallets")
public class Wallet {

    private String customerId;
    private String walletName;
    private String walletDescription;
    private Double cryptosCount;
    private Double cryptosCost;
    private List<Cryptocurrency>  cryptocurrenciesList;

    @DynamoDBHashKey(attributeName = "customerId")
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @DynamoDBRangeKey(attributeName = "walletName")
    public String getWalletName() {
        return walletName;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

    @DynamoDBAttribute(attributeName = "walletDescription")
    public String getWalletDescription() {
        return walletDescription;
    }

    public void setWalletDescription(String walletDescription) {
        this.walletDescription = walletDescription;
    }

    @DynamoDBAttribute(attributeName = "cryptosCount")
    public Double getCryptosCount() {
        return cryptosCount;
    }

    public void setCryptosCount(Double cryptosCount) {
        this.cryptosCount = cryptosCount;
    }

    @DynamoDBAttribute(attributeName = "cryptosCost")
    public Double getCryptosCost() {
        return cryptosCost;
    }

    public void setCryptosCost(Double cryptosCost) {
        this.cryptosCost = cryptosCost;
    }

    @DynamoDBAttribute(attributeName = "cryptocurrenciesList")
    public List<Cryptocurrency> getCryptocurrenciesList() {
        return cryptocurrenciesList;
    }

    public void setCryptocurrenciesList(List<Cryptocurrency> cryptocurrenciesList) {
        this.cryptocurrenciesList = cryptocurrenciesList;
    }

}
