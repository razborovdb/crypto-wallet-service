package com.development.fans.cryptocurrency.wallet.activity;

import com.development.fans.cryptocurrency.wallet.dynamodb.CryptocurrencyDao;
import com.development.fans.cryptocurrency.wallet.dynamodb.WalletDao;
import com.development.fans.cryptocurrency.wallet.dynamodb.models.Cryptocurrency;
import com.development.fans.cryptocurrency.wallet.dynamodb.models.Wallet;
import com.development.fans.cryptocurrency.wallet.exceptions.CryptocurrencyNotAddedException;
import com.development.fans.cryptocurrency.wallet.exceptions.InvalidAttributeValueException;
import com.development.fans.cryptocurrency.wallet.exceptions.WalletNotFoundException;
import com.development.fans.cryptocurrency.wallet.models.requests.AddCryptocurrencyToWalletRequest;
import com.development.fans.cryptocurrency.wallet.models.results.AddCryptocurrencyToWalletResult;
import com.development.fans.cryptocurrency.wallet.util.CryptocurrencyWalletServiceUtils;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class AddCryptocurrencyToWalletActivity implements RequestHandler<AddCryptocurrencyToWalletRequest,
        AddCryptocurrencyToWalletResult> {
    private final WalletDao walletDao;
    private final CryptocurrencyDao cryptocurrencyDao;

    /**
     * AddCryptocurrencyToWalletActivity.
     * @param walletDao - walletDao
     * @param cryptocurrencyDao - cryptocurrencyDao
     */
    @Inject
    public AddCryptocurrencyToWalletActivity(WalletDao walletDao, CryptocurrencyDao cryptocurrencyDao) {
        this.walletDao = walletDao;
        this.cryptocurrencyDao = cryptocurrencyDao;
    }

    /**
     * This method handles the incoming request by persisting a new cryptocurrency to the wallet
     * with the provided customerId, walletName,  cryptoName, cryptoDescription, and cryptoAmount from the request.
     * <p>
     * It then returns the wallet with the added cryptocurrency.
     * <p>
     * If the provided customerId, walletName, or cryptoName has invalid characters, throws an
     * InvalidAttributeValueException
     * <p>
     * If the provided cryptoName doesn't exist in the available cryptocurrencies table, throws an
     * InvalidAttributeValueException
     * <p>
     * If the provided cryptoName already exists in the wallet, throws an InvalidAttributeValueException
     *
     * @param input AddCryptocurrencyToWalletRequest
     * @return AddCryptocurrencyToWalletResult
     */
    @Override
    public AddCryptocurrencyToWalletResult handleRequest(AddCryptocurrencyToWalletRequest input, Context context) {
        String customerId = input.getCustomerId();
        String walletName = input.getWalletName();
        String cryptoName = input.getCryptoName();
        String cryptoDescription = input.getCryptoDescription();
        double cryptoAmount = input.getCryptoAmount();
        if (!CryptocurrencyWalletServiceUtils.isValidString(customerId)) {
            throw new InvalidAttributeValueException("customerId: " + customerId + " is not valid");
        }
        if (!CryptocurrencyWalletServiceUtils.isValidString(walletName)) {
            throw new InvalidAttributeValueException("wallet name: " + walletName + " is not valid");
        }
        if (!CryptocurrencyWalletServiceUtils.isValidString(cryptoName)) {
            throw new InvalidAttributeValueException("cryptocurrency name: " + cryptoName + " is not valid");
        }
        if (cryptoAmount < 0) {
            throw new InvalidAttributeValueException("cryptocurrency amount should be greater or equal 0 ");
        }
        //
        List<Cryptocurrency> cryptocurrencyList = cryptocurrencyDao.getAllCryptocurrency(0.0);

        if (cryptocurrencyList == null) {
            throw new InvalidAttributeValueException(cryptoName + " is not in the available cryptocurrencies list");
        }
        if (cryptocurrencyList.isEmpty()) {
            throw new InvalidAttributeValueException(cryptoName + " is not in the available cryptocurrencies list");
        }
        boolean isInAvailable = false;
        Cryptocurrency availableCrypto = new Cryptocurrency();
        for (int i = 0; i < cryptocurrencyList.size(); i++) {
            availableCrypto = cryptocurrencyList.get(i);
            if (availableCrypto.getCryptoName().equals(cryptoName)) {
                isInAvailable = true;
                break;
            }
        }
        if (!isInAvailable) {
            throw new InvalidAttributeValueException(cryptoName + " is not in the available cryptocurrencies list");
        }
        Wallet wallet = walletDao.getWallet(customerId, walletName);
        if (wallet == null) {
            throw new WalletNotFoundException("Wallet with customerId = " + customerId +
                    "and wallet name = " + walletName + " is not found");
        }
        List<Cryptocurrency> cryptocurrencyInWallet = wallet.getCryptocurrenciesList();
        if (cryptocurrencyInWallet == null) {
            cryptocurrencyInWallet = new ArrayList<>();
        }
        boolean isInWallet = false;
        for (int i = 0; i < cryptocurrencyInWallet.size(); i++) {
            if (cryptocurrencyInWallet.get(i).getCryptoName().equals(cryptoName)) {
                isInWallet = true;
                break;
            }
        }
        if (isInWallet) {
            throw new InvalidAttributeValueException(cryptoName + " is already in wallet " + walletName);
        }
        Cryptocurrency newCrypto = new Cryptocurrency();
        newCrypto.setCryptoName(cryptoName);
        newCrypto.setCryptoDescription(cryptoDescription);
        newCrypto.setCryptoAmount(cryptoAmount);
        newCrypto.setCryptoCost(cryptoAmount * availableCrypto.getCryptoCost());
        cryptocurrencyInWallet.add(newCrypto);
        wallet.setCryptosCount(wallet.getCryptosCount() + 1);
        wallet.setCryptosCost(wallet.getCryptosCost() + newCrypto.getCryptoCost());
        wallet.setCryptocurrenciesList(cryptocurrencyInWallet);

        Wallet walletAfterAdd = walletDao.saveWallet(wallet);
        if (walletAfterAdd == null) {
            throw new CryptocurrencyNotAddedException("Cryptocurrency " + " isn't added");
        }

        return AddCryptocurrencyToWalletResult.builder()
                .withWallet(walletAfterAdd)
                .build();
    }
}
