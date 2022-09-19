package com.development.fans.cryptocurrency.wallet.activity;

import com.development.fans.cryptocurrency.wallet.dynamodb.CryptocurrencyDao;
import com.development.fans.cryptocurrency.wallet.dynamodb.models.Cryptocurrency;
import com.development.fans.cryptocurrency.wallet.models.requests.GetAvailableCryptocurrenciesRequest;
import com.development.fans.cryptocurrency.wallet.models.results.GetAvailableCryptocurrenciesResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.List;
import javax.inject.Inject;

public class GetAvailableCryptocurrenciesActivity implements RequestHandler<GetAvailableCryptocurrenciesRequest,
        GetAvailableCryptocurrenciesResult>  {
    private final CryptocurrencyDao cryptocurrencyDao;

    /**
     * GetAvailableCryptocurrenciesActivity.
     * @param cryptocurrencyDao - cryptocurrencyDao
     */
    @Inject
    public GetAvailableCryptocurrenciesActivity(CryptocurrencyDao cryptocurrencyDao) {
        this.cryptocurrencyDao = cryptocurrencyDao;
    }
    /**
     * This method handles the incoming request by retrieving the available cryptocurrencies from the database.
     * <p>
     * It then returns the cryptocurrencies list.
     * <p>
     * If the table is empty, this should return empty cryptocurrencies list.
     *
     * @param getAvailableCryptocurrenciesRequest request available cryptocurrencies list
     * @return GetAvailableCryptocurrenciesResult result object containing the API defined
     * {@link com.development.fans.cryptocurrency.wallet.models.CryptocurrencyModel}
     */
    @Override
    public GetAvailableCryptocurrenciesResult handleRequest(
            final GetAvailableCryptocurrenciesRequest getAvailableCryptocurrenciesRequest, Context context) {
        double requestedGreaterThan = getAvailableCryptocurrenciesRequest.getGreaterThan();
        List<Cryptocurrency> cryptocurrencyList = cryptocurrencyDao.getAllCryptocurrency(requestedGreaterThan);

        return GetAvailableCryptocurrenciesResult.builder()
                .withCryptocurrencyList(cryptocurrencyList)
                .build();
    }
}
