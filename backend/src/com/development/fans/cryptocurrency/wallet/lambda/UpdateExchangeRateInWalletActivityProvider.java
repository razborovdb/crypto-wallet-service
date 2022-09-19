package com.development.fans.cryptocurrency.wallet.lambda;

import com.development.fans.cryptocurrency.wallet.dependency.DaggerServiceComponent;
import com.development.fans.cryptocurrency.wallet.dependency.ServiceComponent;
import com.development.fans.cryptocurrency.wallet.models.requests.UpdateExchangeRateInWalletRequest;
import com.development.fans.cryptocurrency.wallet.models.results.UpdateExchangeRateInWalletResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class UpdateExchangeRateInWalletActivityProvider implements RequestHandler<UpdateExchangeRateInWalletRequest,
        UpdateExchangeRateInWalletResult> {
    /**
     * UpdateAvailableCryptocurrenciesActivityProvider.
     */
    public UpdateExchangeRateInWalletActivityProvider() {
    }

    @Override
    public UpdateExchangeRateInWalletResult handleRequest(UpdateExchangeRateInWalletRequest input, Context context) {
        return getDagger().provideUpdateExchangeRateInWalletActivity().handleRequest(input, context);
    }

    private ServiceComponent getDagger() {
        return DaggerServiceComponent.create();
    }


}
