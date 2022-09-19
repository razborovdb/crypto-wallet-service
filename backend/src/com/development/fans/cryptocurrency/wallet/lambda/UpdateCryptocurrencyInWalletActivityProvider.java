package com.development.fans.cryptocurrency.wallet.lambda;

import com.development.fans.cryptocurrency.wallet.dependency.DaggerServiceComponent;
import com.development.fans.cryptocurrency.wallet.dependency.ServiceComponent;
import com.development.fans.cryptocurrency.wallet.models.requests.UpdateCryptocurrencyInWalletRequest;
import com.development.fans.cryptocurrency.wallet.models.results.UpdateCryptocurrencyInWalletResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class UpdateCryptocurrencyInWalletActivityProvider
        implements RequestHandler<UpdateCryptocurrencyInWalletRequest, UpdateCryptocurrencyInWalletResult> {
    /**
     * UpdateCryptocurrencyAmountInWalletActivityProvider.
     */
    public UpdateCryptocurrencyInWalletActivityProvider() {
    }

    @Override
    public UpdateCryptocurrencyInWalletResult handleRequest(UpdateCryptocurrencyInWalletRequest input,
                                                            Context context) {
        return getDagger().provideUpdateCryptocurrencyInWalletActivity().handleRequest(input, context);
    }

    private ServiceComponent getDagger() {
        return DaggerServiceComponent.create();
    }


}
