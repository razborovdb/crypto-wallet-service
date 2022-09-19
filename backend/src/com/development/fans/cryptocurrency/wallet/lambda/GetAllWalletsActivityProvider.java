package com.development.fans.cryptocurrency.wallet.lambda;

import com.development.fans.cryptocurrency.wallet.dependency.DaggerServiceComponent;
import com.development.fans.cryptocurrency.wallet.dependency.ServiceComponent;
import com.development.fans.cryptocurrency.wallet.models.requests.GetAllWalletsRequest;
import com.development.fans.cryptocurrency.wallet.models.results.GetAllWalletsResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetAllWalletsActivityProvider implements RequestHandler<GetAllWalletsRequest, GetAllWalletsResult> {
    /**
     * GetAllWalletsActivityProvider.
     */
    public GetAllWalletsActivityProvider() {
    }

    /**
     * handleRequest.
     * @param getAllWalletsRequest The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return - wallet list
     */
    @Override
    public GetAllWalletsResult handleRequest(final GetAllWalletsRequest getAllWalletsRequest, Context context) {
        return getDagger().provideGetAllWalletsActivity().handleRequest(getAllWalletsRequest, context);
    }

    private ServiceComponent getDagger() {
        return DaggerServiceComponent.create();
    }
}
