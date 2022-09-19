package com.development.fans.cryptocurrency.wallet.lambda;

import com.development.fans.cryptocurrency.wallet.dependency.DaggerServiceComponent;
import com.development.fans.cryptocurrency.wallet.dependency.ServiceComponent;
import com.development.fans.cryptocurrency.wallet.models.requests.AddCryptocurrencyToWalletRequest;
import com.development.fans.cryptocurrency.wallet.models.results.AddCryptocurrencyToWalletResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class AddCryptocurrencyToWalletActivityProvider
        implements RequestHandler<AddCryptocurrencyToWalletRequest, AddCryptocurrencyToWalletResult> {
    /**
     * AddCryptocurrencyToWalletActivityProvider.
     */
    public AddCryptocurrencyToWalletActivityProvider() {
    }

    @Override
    public AddCryptocurrencyToWalletResult handleRequest(AddCryptocurrencyToWalletRequest input, Context context) {
        return getDagger().provideAddCryptocurrencyToWalletActivity().handleRequest(input, context);
    }

    private ServiceComponent getDagger() {
        return DaggerServiceComponent.create();
    }

}
