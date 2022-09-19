package com.development.fans.cryptocurrency.wallet.dynamodb;

import com.development.fans.cryptocurrency.wallet.dynamodb.models.Wallet;
import com.development.fans.cryptocurrency.wallet.exceptions.WalletNotFoundException;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

public class WalletDao {
    private final DynamoDBMapper dynamoDbMapper;

    /**
     * WalletDao.
     * @param dynamoDbMapper - DynamoDBMapper
     */
    @Inject
    public WalletDao(DynamoDBMapper dynamoDbMapper) {
        this.dynamoDbMapper = dynamoDbMapper;
    }

    /**
     * Wallets.
     * @param customerId - customerId
     * @param walletName - walletName
     * @return - wallet
     */
    public Wallet getWallet(String customerId, String walletName) {
        Wallet wallet = dynamoDbMapper.load(Wallet.class, customerId, walletName);
        if (wallet == null) {
            throw new WalletNotFoundException("Cannot find wallet with customerId = " + customerId +
                    " and walletName = " + walletName);
        }

        return wallet;
    }

    /**
     * getAllWalletsForCustomerId.
     * @param customerId - customerId
     * @return - walletList
     */
    public List<Wallet> getAllWalletsForCustomerId(String customerId) {
        Map<String, AttributeValue> walletAV = new HashMap<String, AttributeValue>();
        walletAV.put(":val1", new AttributeValue().withS(customerId));
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("customerId = :val1")
                .withExpressionAttributeValues(walletAV)
                .withConsistentRead(false);

        List<Wallet> walletList = new ArrayList<>(dynamoDbMapper.scan(Wallet.class, scanExpression));
        if (walletList == null) {
            walletList = new ArrayList<>();
        }

        return walletList;
    }

    /**
     * savedWallet.
     * @param wallet - wallet
     * @return - Wallet
     */
    public Wallet saveWallet(Wallet wallet) {

        this.dynamoDbMapper.save(wallet);
        Wallet savedWallet = this.dynamoDbMapper.load(Wallet.class, wallet.getCustomerId(), wallet.getWalletName());

        if (savedWallet == null) {
            throw new WalletNotFoundException("Cannot create wallet with customerId = " + wallet.getCustomerId() +
                    " and walletName = " + wallet.getWalletName());
        }

        return savedWallet;
    }
}
