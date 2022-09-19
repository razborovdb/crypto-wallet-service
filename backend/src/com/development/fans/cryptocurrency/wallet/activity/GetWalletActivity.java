package com.development.fans.cryptocurrency.wallet.activity;

import com.development.fans.cryptocurrency.wallet.dynamodb.WalletDao;
import com.development.fans.cryptocurrency.wallet.dynamodb.models.Wallet;
import com.development.fans.cryptocurrency.wallet.models.WalletModel;
import com.development.fans.cryptocurrency.wallet.models.requests.GetWalletRequest;
import com.development.fans.cryptocurrency.wallet.models.results.GetWalletResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import javax.inject.Inject;

public class GetWalletActivity implements RequestHandler<GetWalletRequest, GetWalletResult> {

    private final WalletDao walletDao;

    /**
     * Instantiates a new GetWalletActivity object.
     *
     * @param walletDao WalletDao to access the wallet table.
     */
    @Inject
    public GetWalletActivity(WalletDao walletDao) {
        this.walletDao = walletDao;
    }

    /**
     * This method handles the incoming request by retrieving the wallet from the database.
     * <p>
     * It then returns the wallet.
     * <p>
     * If the wallet does not exist, this should throw a WalletNotFoundException.
     *
     * @param getWalletRequest request object containing the playlist ID
     * @return GetWalletResult result object containing the API defined {@link WalletModel}
     */
    @Override
    public GetWalletResult handleRequest(final GetWalletRequest getWalletRequest, Context context) {
        String requestedCustomerId = getWalletRequest.getCustomerId();
        String requestedWalletName = getWalletRequest.getWalletName();
        Wallet wallet = walletDao.getWallet(requestedCustomerId, requestedWalletName);

        return GetWalletResult.builder()
                .withWallet(wallet)
                .build();
    }
}
