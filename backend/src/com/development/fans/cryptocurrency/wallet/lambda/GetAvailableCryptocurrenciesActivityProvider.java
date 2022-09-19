package com.development.fans.cryptocurrency.wallet.lambda;

import com.development.fans.cryptocurrency.wallet.dependency.DaggerServiceComponent;
import com.development.fans.cryptocurrency.wallet.dependency.ServiceComponent;
import com.development.fans.cryptocurrency.wallet.models.requests.GetAvailableCryptocurrenciesRequest;
import com.development.fans.cryptocurrency.wallet.models.results.GetAvailableCryptocurrenciesResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetAvailableCryptocurrenciesActivityProvider implements RequestHandler<GetAvailableCryptocurrenciesRequest,
        GetAvailableCryptocurrenciesResult> {
    /**
     * GetAvailableCryptocurrenciesActivityProvider.
     */
    public GetAvailableCryptocurrenciesActivityProvider() {
    }
    @Override
    public GetAvailableCryptocurrenciesResult handleRequest(
            final GetAvailableCryptocurrenciesRequest getAvailableCryptocurrenciesRequest, Context context) {
        return getDagger().provideGetAvailableCryptocurrenciesActivity()
                .handleRequest(getAvailableCryptocurrenciesRequest, context);
    }

    private ServiceComponent getDagger() {
        return DaggerServiceComponent.create();
    }
}
