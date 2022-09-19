package com.development.fans.cryptocurrency.wallet.activity;

import com.development.fans.cryptocurrency.wallet.dynamodb.CryptocurrencyDao;
import com.development.fans.cryptocurrency.wallet.dynamodb.WalletDao;
import com.development.fans.cryptocurrency.wallet.dynamodb.models.Cryptocurrency;
import com.development.fans.cryptocurrency.wallet.dynamodb.models.Wallet;
import com.development.fans.cryptocurrency.wallet.exceptions.CryptocurrencyNotFoundException;
import com.development.fans.cryptocurrency.wallet.exceptions.CryptocurrencyNotUpdatedException;
import com.development.fans.cryptocurrency.wallet.exceptions.InvalidAttributeValueException;
import com.development.fans.cryptocurrency.wallet.exceptions.WalletNotFoundException;
import com.development.fans.cryptocurrency.wallet.models.requests.UpdateCryptocurrencyInWalletRequest;
import com.development.fans.cryptocurrency.wallet.models.results.UpdateCryptocurrencyInWalletResult;
import com.development.fans.cryptocurrency.wallet.util.CryptocurrencyWalletServiceUtils;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class UpdateCryptocurrencyInWalletActivity implements RequestHandler<UpdateCryptocurrencyInWalletRequest,
        UpdateCryptocurrencyInWalletResult> {
    private final WalletDao walletDao;
    private final CryptocurrencyDao cryptocurrencyDao;

    /**
     * UpdateCryptocurrencyInWalletActivity.
     * @param walletDao - walletDao
     * @param cryptocurrencyDao - cryptocurrencyDao
     */
    @Inject
    public UpdateCryptocurrencyInWalletActivity(WalletDao walletDao, CryptocurrencyDao cryptocurrencyDao) {
        this.walletDao = walletDao;
        this.cryptocurrencyDao = cryptocurrencyDao;
    }
    /**
     * This method handles the incoming request by updating an existing cryptocurrency in the wallet
     * with the provided customerId, walletName,  cryptoName, cryptoDescription, and cryptoAmount from the request.
     * <p>
     * It then returns the wallet with the updated cryptocurrency.
     * <p>
     * If the provided customerId, walletName, or cryptoName has invalid characters, throws an
     * InvalidAttributeValueException
     * <p>
     * If the provided cryptoName doesn't exist in the wallet, throws an InvalidAttributeValueException
     *
     * @param input UpdateCryptocurrencyInWalletRequest
     * @return UpdateCryptocurrencyInWalletResult
     */
    @Override
    public UpdateCryptocurrencyInWalletResult handleRequest(UpdateCryptocurrencyInWalletRequest input,
                                                            Context context) {
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
        boolean isCryptoListValid = true;
        if (cryptocurrencyList == null) {
            isCryptoListValid = false;
        }
        if (cryptocurrencyList.isEmpty()) {
            isCryptoListValid = false;
        }
        boolean isInAvailable = false;
        Cryptocurrency availableCrypto = new Cryptocurrency();
        if (isCryptoListValid) {
            for (int i = 0; i < cryptocurrencyList.size(); i++) {
                availableCrypto = cryptocurrencyList.get(i);
                if (availableCrypto.getCryptoName().equals(cryptoName)) {
                    isInAvailable = true;
                    break;
                }
            }
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
        int index = -1;
        for (int i = 0; i < cryptocurrencyInWallet.size(); i++) {
            if (cryptocurrencyInWallet.get(i).getCryptoName().equals(cryptoName)) {
                isInWallet = true;
                index = i;
                break;
            }
        }
        if (!isInWallet) {
            throw new CryptocurrencyNotFoundException(cryptoName + " isn't found in wallet " + walletName);
        }
        double curExchangeRate = 0;
        if (isInAvailable) {
            curExchangeRate = availableCrypto.getCryptoCost();
        } else {
            if (cryptocurrencyInWallet.get(index).getCryptoAmount() > 0) {
                curExchangeRate = cryptocurrencyInWallet.get(index).getCryptoCost() /
                        cryptocurrencyInWallet.get(index).getCryptoAmount();
            }
        }

        cryptocurrencyInWallet.get(index).setCryptoDescription(cryptoDescription);
        cryptocurrencyInWallet.get(index).setCryptoAmount(cryptoAmount);
        cryptocurrencyInWallet.get(index).setCryptoCost(cryptoAmount * curExchangeRate);
        double cryptosCost = 0.0;
        for (int i = 0; i < cryptocurrencyInWallet.size(); i++) {
            cryptosCost += cryptocurrencyInWallet.get(i).getCryptoCost();
        }

        wallet.setCryptosCost(cryptosCost);

        Wallet walletAfterUpdate = walletDao.saveWallet(wallet);
        if (walletAfterUpdate == null) {
            throw new CryptocurrencyNotUpdatedException("Cryptocurrency " + cryptoName + " isn't updated in wallet " +
                    walletName);
        }

        return UpdateCryptocurrencyInWalletResult.builder()
                .withWallet(walletAfterUpdate)
                .build();
    }
}
