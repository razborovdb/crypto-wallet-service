package com.development.fans.cryptocurrency.wallet.lambda;

import com.development.fans.cryptocurrency.wallet.dependency.DaggerServiceComponent;
import com.development.fans.cryptocurrency.wallet.dependency.ServiceComponent;
import com.development.fans.cryptocurrency.wallet.models.requests.GetWalletRequest;
import com.development.fans.cryptocurrency.wallet.models.results.GetWalletResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetWalletActivityProvider implements RequestHandler<GetWalletRequest, GetWalletResult> {
    /**
     * GetWalletActivityProvider.
     */

    public GetWalletActivityProvider() {

    }

    @Override
    public GetWalletResult handleRequest(final GetWalletRequest getWalletRequest, Context context) {
        return getDagger().provideGetWalletActivity().handleRequest(getWalletRequest, context);
    }

    private ServiceComponent getDagger() {
        return DaggerServiceComponent.create();
    }
}
