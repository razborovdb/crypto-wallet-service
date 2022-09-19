package com.development.fans.cryptocurrency.wallet.models.requests;

import java.util.Objects;

public class GetAvailableCryptocurrenciesRequest {
    double greaterThan;
    /**
     * GetAvailableCryptocurrenciesRequest.
     */
    public GetAvailableCryptocurrenciesRequest() {

    }
    /**
     * GetAvailableCryptocurrenciesRequest.
     * @param builder -builder
     */
    public GetAvailableCryptocurrenciesRequest(Builder builder) {
        this.greaterThan = builder.greaterThan;
    }

    public double getGreaterThan() {
        return greaterThan;
    }

    public void setGreaterThan(double greaterThan) {
        this.greaterThan = greaterThan;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GetAvailableCryptocurrenciesRequest that = (GetAvailableCryptocurrenciesRequest) o;
        return Double.compare(that.greaterThan, greaterThan) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(greaterThan);
    }

    @Override
    public String toString() {
        return "GetAvailableCryptocurrenciesRequest{" +
                "greaterThan=" + greaterThan +
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
        double greaterThan;
        private Builder() {

        }

        /**
         * Builder.
         * @param greaterThanToUse - greaterThanToUse
         * @return - this
         */
        public Builder withGreaterThan(double greaterThanToUse) {
            this.greaterThan = greaterThanToUse;
            return this;
        }


        /**
         * GetAvailableCryptocurrenciesRequest.
         * @return -GetAvailableCryptocurrenciesRequest
         */
        public GetAvailableCryptocurrenciesRequest build() {
            return new GetAvailableCryptocurrenciesRequest(this);
        }
    }

}
