package com.development.fans.cryptocurrency.wallet.activity;

import com.development.fans.cryptocurrency.wallet.dynamodb.CryptocurrencyDao;
import com.development.fans.cryptocurrency.wallet.dynamodb.WalletDao;
import com.development.fans.cryptocurrency.wallet.dynamodb.models.Cryptocurrency;
import com.development.fans.cryptocurrency.wallet.dynamodb.models.Wallet;
import com.development.fans.cryptocurrency.wallet.exceptions.CryptocurrencyNotUpdatedException;
import com.development.fans.cryptocurrency.wallet.exceptions.InvalidAttributeValueException;
import com.development.fans.cryptocurrency.wallet.exceptions.WalletNotFoundException;
import com.development.fans.cryptocurrency.wallet.models.requests.UpdateExchangeRateInWalletRequest;
import com.development.fans.cryptocurrency.wallet.models.results.UpdateExchangeRateInWalletResult;
import com.development.fans.cryptocurrency.wallet.util.CryptocurrencyWalletServiceUtils;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class UpdateExchangeRateInWalletActivity implements RequestHandler<UpdateExchangeRateInWalletRequest,
        UpdateExchangeRateInWalletResult> {
    private final WalletDao walletDao;
    private final CryptocurrencyDao cryptocurrencyDao;

    /**
     * UpdateExchangeRateInWalletActivity.
     * @param walletDao - walletDao
     * @param cryptocurrencyDao - cryptocurrencyDao
     */
    @Inject
    public UpdateExchangeRateInWalletActivity(WalletDao walletDao, CryptocurrencyDao cryptocurrencyDao) {
        this.walletDao = walletDao;
        this.cryptocurrencyDao = cryptocurrencyDao;
    }
    /**
     * This method handles the incoming request by updating cryptocurrencies cost in the wallet
     * according to current exchange rate
     * with the provided customerId, walletName from the request.
     * <p>
     * It then returns the wallet with the updated cryptocurrencies cost.
     * <p>
     * If the provided customerId, walletName has invalid characters, throws an
     * InvalidAttributeValueException
     * <p>
     *
     * @param input UpdateCryptocurrencyInWalletRequest
     * @return UpdateCryptocurrencyInWalletResult
     */
    @Override
    public UpdateExchangeRateInWalletResult handleRequest(UpdateExchangeRateInWalletRequest input, Context context) {
        String customerId = input.getCustomerId();
        String walletName = input.getWalletName();

        if (!CryptocurrencyWalletServiceUtils.isValidString(customerId)) {
            throw new InvalidAttributeValueException("customerId: " + customerId + " is not valid");
        }
        if (!CryptocurrencyWalletServiceUtils.isValidString(walletName)) {
            throw new InvalidAttributeValueException("wallet name: " + walletName + " is not valid");
        }

        //
        List<Cryptocurrency> cryptocurrencyList = cryptocurrencyDao.getAllCryptocurrency(0.0);
        boolean isCryptoListValid = true;
        if (cryptocurrencyList == null) {
            cryptocurrencyList = new ArrayList<>();
            isCryptoListValid = false;
        }
        if (cryptocurrencyList.isEmpty()) {
            isCryptoListValid = false;
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

        for (int i = 0; i < cryptocurrencyInWallet.size(); i++) {
            String searchCryptoName = cryptocurrencyInWallet.get(i).getCryptoName();
            boolean isInAvailable = false;
            int index = -1;
            for (int j = 0; j < cryptocurrencyList.size(); j++) {
                if (cryptocurrencyList.get(j).getCryptoName().equals(searchCryptoName)) {
                    isInAvailable = true;
                    index = j;
                    break;
                }
            }
            double curExchangeRate = 0;
            if (isInAvailable) {
                curExchangeRate = cryptocurrencyList.get(index).getCryptoCost();
            } else {
                if (cryptocurrencyInWallet.get(i).getCryptoAmount() > 0) {
                    curExchangeRate = cryptocurrencyInWallet.get(i).getCryptoCost() /
                            cryptocurrencyInWallet.get(i).getCryptoAmount();
                }
            }
            cryptocurrencyInWallet.get(i).setCryptoCost(cryptocurrencyInWallet.get(i).getCryptoAmount() *
                    curExchangeRate);
        }

        double cryptosCost = 0.0;
        for (int i = 0; i < cryptocurrencyInWallet.size(); i++) {
            cryptosCost += cryptocurrencyInWallet.get(i).getCryptoCost();
        }

        wallet.setCryptosCost(cryptosCost);

        Wallet walletAfterUpdate = walletDao.saveWallet(wallet);
        if (walletAfterUpdate == null) {
            throw new CryptocurrencyNotUpdatedException("Cryptocurrencies exchange rate aren't updated in wallet " +
                    walletName);
        }

        return UpdateExchangeRateInWalletResult.builder()
                .withWallet(walletAfterUpdate)
                .build();
    }
}
