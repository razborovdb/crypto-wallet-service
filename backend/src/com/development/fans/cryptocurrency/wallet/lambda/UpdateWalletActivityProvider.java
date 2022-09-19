package com.development.fans.cryptocurrency.wallet.lambda;

import com.development.fans.cryptocurrency.wallet.dependency.DaggerServiceComponent;
import com.development.fans.cryptocurrency.wallet.dependency.ServiceComponent;
import com.development.fans.cryptocurrency.wallet.models.requests.UpdateWalletRequest;
import com.development.fans.cryptocurrency.wallet.models.results.UpdateWalletResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class UpdateWalletActivityProvider implements RequestHandler<UpdateWalletRequest, UpdateWalletResult>  {
    /**
     * UpdateWalletActivityProvider.
     */
    public UpdateWalletActivityProvider() {
    }

    @Override
    public UpdateWalletResult handleRequest(final UpdateWalletRequest getUpdateWalletRequest, Context context) {
        return getDagger().provideUpdateWalletActivity().handleRequest(getUpdateWalletRequest, context);
    }

    private ServiceComponent getDagger() {
        return DaggerServiceComponent.create();
    }
}
