package com.development.fans.cryptocurrency.wallet.lambda;

import com.development.fans.cryptocurrency.wallet.dependency.DaggerServiceComponent;
import com.development.fans.cryptocurrency.wallet.dependency.ServiceComponent;
import com.development.fans.cryptocurrency.wallet.models.requests.CreateWalletRequest;
import com.development.fans.cryptocurrency.wallet.models.results.CreateWalletResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class CreateWalletActivityProvider implements RequestHandler<CreateWalletRequest, CreateWalletResult> {
    /**
     * CreateWalletActivityProvider.
     */
    public CreateWalletActivityProvider() {
    }

    /**
     * handleRequest.
     * @param createWalletRequest The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return - CreateWalletResult
     */
    @Override
    public CreateWalletResult handleRequest(final CreateWalletRequest createWalletRequest, Context context) {
        return getDagger().provideCreateWalletActivity().handleRequest(createWalletRequest, context);
    }

    private ServiceComponent getDagger() {
        return DaggerServiceComponent.create();
    }
}
