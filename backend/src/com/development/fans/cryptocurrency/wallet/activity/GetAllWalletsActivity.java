package com.development.fans.cryptocurrency.wallet.activity;

import com.development.fans.cryptocurrency.wallet.dynamodb.WalletDao;
import com.development.fans.cryptocurrency.wallet.dynamodb.models.Wallet;
import com.development.fans.cryptocurrency.wallet.models.requests.GetAllWalletsRequest;
import com.development.fans.cryptocurrency.wallet.models.results.GetAllWalletsResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.List;
import javax.inject.Inject;

public class GetAllWalletsActivity implements RequestHandler<GetAllWalletsRequest, GetAllWalletsResult> {
    private final WalletDao walletDao;

    /**
     * Instantiates a new GetWalletActivity object.
     *
     * @param walletDao WalletDao to access the wallet table.
     */
    @Inject
    public GetAllWalletsActivity(WalletDao walletDao) {
        this.walletDao = walletDao;
    }
    /**
     * This method handles the incoming request by retrieving the all wallets with customerId from the database.
     * <p>
     * It then returns the wallets list.
     * <p>
     * If the wallets does not exist, this should return empty wallets list.
     *
     * @param getAllWalletsRequest request object containing the playlist ID
     * @return GetAllWalletsResult wallets list
     */
    @Override
    public GetAllWalletsResult handleRequest(final GetAllWalletsRequest getAllWalletsRequest, Context context) {
        String requestedCustomerId = getAllWalletsRequest.getCustomerId();
        List<Wallet> walletList = walletDao.getAllWalletsForCustomerId(requestedCustomerId);

        return GetAllWalletsResult.builder()
                .withWalletList(walletList)
                .build();
    }
}
