package com.development.fans.cryptocurrency.wallet.activity;

import com.development.fans.cryptocurrency.wallet.dynamodb.WalletDao;
import com.development.fans.cryptocurrency.wallet.dynamodb.models.Cryptocurrency;
import com.development.fans.cryptocurrency.wallet.dynamodb.models.Wallet;
import com.development.fans.cryptocurrency.wallet.exceptions.InvalidAttributeValueException;
import com.development.fans.cryptocurrency.wallet.exceptions.WalletNotCreatedException;
import com.development.fans.cryptocurrency.wallet.models.requests.CreateWalletRequest;
import com.development.fans.cryptocurrency.wallet.models.results.CreateWalletResult;
import com.development.fans.cryptocurrency.wallet.util.CryptocurrencyWalletServiceUtils;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class CreateWalletActivity implements RequestHandler<CreateWalletRequest, CreateWalletResult> {
    private final WalletDao walletDao;

    /**
     * CreateWalletActivity.
     * @param walletDao - walletDao
     */
    @Inject
    public CreateWalletActivity(WalletDao walletDao) {
        this.walletDao = walletDao;
    }

    /**
     * This method handles the incoming request by persisting a new wallet
     * with the provided customerId, walletName and walletDescription from the request.
     * <p>
     * It then returns the newly created wallet.
     * <p>
     * If the provided customerId or walletName has invalid characters, throws an
     * InvalidAttributeValueException
     * <p>
     * If the wallet isn't created, throws an WalletNotCreatedException
     *
     * @param createWalletRequest createWalletRequest
     * @return CreateWalletResult
     */
    @Override
    public CreateWalletResult handleRequest(final CreateWalletRequest createWalletRequest, Context context) {
        String customerId = createWalletRequest.getCustomerId();
        String walletName = createWalletRequest.getWalletName();
        String walletDescription = createWalletRequest.getWalletDescription();
        if (!CryptocurrencyWalletServiceUtils.isValidString(customerId)) {
            throw new InvalidAttributeValueException("customerId: " + customerId + " is not valid");
        }
        if (!CryptocurrencyWalletServiceUtils.isValidString(walletName)) {
            throw new InvalidAttributeValueException("wallet name: " + walletName + " is not valid");
        }
        Double cryptosCount = 0.0;
        Double cryptosCost = 0.0;

        List<Cryptocurrency> cryptocurrencyList = new ArrayList<>();
        Wallet wallet = new Wallet();
        wallet.setCustomerId(customerId);
        wallet.setWalletName(walletName);
        wallet.setWalletDescription(walletDescription);
        wallet.setCryptosCount(cryptosCount);
        wallet.setCryptosCost(cryptosCost);
        wallet.setCryptocurrenciesList(cryptocurrencyList);
        Wallet createdWallet = walletDao.saveWallet(wallet);
        if (createdWallet == null) {
            throw new WalletNotCreatedException("Wallet isn't created");
        }

        return CreateWalletResult.builder()
                .withWallet(createdWallet)
                .build();
    }

}
