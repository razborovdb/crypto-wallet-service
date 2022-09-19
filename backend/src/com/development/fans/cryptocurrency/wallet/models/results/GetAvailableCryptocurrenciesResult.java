package com.development.fans.cryptocurrency.wallet.models.results;

import com.development.fans.cryptocurrency.wallet.dynamodb.models.Cryptocurrency;

import java.util.List;

public class GetAvailableCryptocurrenciesResult {
    private List<Cryptocurrency> cryptocurrencyList;
    /**
     * GetAvailableCryptocurrenciesResult.
     * @param builder - builder
     */
    public GetAvailableCryptocurrenciesResult(Builder builder) {
        this.cryptocurrencyList = builder.cryptocurrencyList;
    }

    public List<Cryptocurrency> getCryptocurrencyList() {
        return cryptocurrencyList;
    }

    public void setCryptocurrencyList(List<Cryptocurrency> cryptocurrencyList) {
        this.cryptocurrencyList = cryptocurrencyList;
    }

    @Override
    public String toString() {
        return "GetAvailableCryptocurrenciesResult{" +
                "cryptocurrencyList=" + cryptocurrencyList +
                '}';
    }

    /**
     * Builder.
     * @return - Builder
     */
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private List<Cryptocurrency> cryptocurrencyList;
        private Builder() {

        }

        /**
         * Builder.
         * @param cryptocurrencyListToUse - cryptocurrencyListToUse
         * @return - this
         */
        public Builder withCryptocurrencyList(List<Cryptocurrency> cryptocurrencyListToUse) {
            this.cryptocurrencyList = cryptocurrencyListToUse;
            return this;
        }

        /**
         * GetAvailableCryptocurrenciesResult.
         * @return -GetAvailableCryptocurrenciesResult
         */
        public GetAvailableCryptocurrenciesResult build() {
            return new GetAvailableCryptocurrenciesResult(this);
        }
    }
}
