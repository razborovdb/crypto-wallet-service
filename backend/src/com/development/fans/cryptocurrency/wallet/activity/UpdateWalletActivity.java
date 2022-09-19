package com.development.fans.cryptocurrency.wallet.activity;

import com.development.fans.cryptocurrency.wallet.dynamodb.WalletDao;
import com.development.fans.cryptocurrency.wallet.dynamodb.models.Wallet;
import com.development.fans.cryptocurrency.wallet.exceptions.InvalidAttributeValueException;
import com.development.fans.cryptocurrency.wallet.models.requests.UpdateWalletRequest;
import com.development.fans.cryptocurrency.wallet.models.results.UpdateWalletResult;
import com.development.fans.cryptocurrency.wallet.util.CryptocurrencyWalletServiceUtils;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import javax.inject.Inject;

public class UpdateWalletActivity implements RequestHandler<UpdateWalletRequest, UpdateWalletResult>  {
    private final WalletDao walletDao;

    /**
     * UpdateWalletActivity.
     *
     * @param walletDao - walletDao
     */
    @Inject
    public UpdateWalletActivity(WalletDao walletDao) {
        this.walletDao = walletDao;
    }

    /**
     * This method handles the incoming request by retrieving the wallet, updating it,
     * and persisting the wallet.
     * <p>
     * It then returns the updated wallet.
     * <p>
     * If the wallet does not exist, this should throw a WalletNotFoundException.
     * <p>
     * If the provided walletName or customerID has invalid characters, throws an
     * InvalidAttributeValueException
     *
     * @param updateWalletRequest request object containing the customerId, walletName
     * @return UpdateWalletResult result object containing the API defined
     * {@link com.development.fans.cryptocurrency.wallet.models.WalletModel}
     */

    @Override
    public UpdateWalletResult handleRequest(final UpdateWalletRequest updateWalletRequest, Context context) {

        String customerId = updateWalletRequest.getCustomerId();
        String walletName = updateWalletRequest.getWalletName();
        String walletDescription = updateWalletRequest.getWalletDescription();

        Wallet wallet = walletDao.getWallet(customerId, walletName);

        if (!CryptocurrencyWalletServiceUtils.isValidString(customerId)) {
            throw new InvalidAttributeValueException("customerId: " + customerId + " is not valid");
        }
        if (!CryptocurrencyWalletServiceUtils.isValidString(walletName)) {
            throw new InvalidAttributeValueException("wallet name: " + walletName + " is not valid");
        }

        wallet.setWalletDescription(walletDescription);

        Wallet createdWallet = walletDao.saveWallet(wallet);

        return UpdateWalletResult.builder()
                .withWallet(createdWallet)
                .build();
    }
}
