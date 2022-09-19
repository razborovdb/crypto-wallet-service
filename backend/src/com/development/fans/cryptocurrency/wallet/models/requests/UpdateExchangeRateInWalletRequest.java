package com.development.fans.cryptocurrency.wallet.models.requests;

import java.util.Objects;

public class UpdateExchangeRateInWalletRequest {
    private String customerId;
    private String walletName;

    /**
     * UpdateCryptocurrencyInWalletRequest.
     */
    public UpdateExchangeRateInWalletRequest() {
    }

    /**
     * UpdateExchangeRateInWalletRequest.
     * @param customerId - customerId
     * @param walletName - walletName
     */
    public UpdateExchangeRateInWalletRequest(String customerId, String walletName) {
        this.customerId = customerId;
        this.walletName = walletName;
    }

    /**
     * AddSongToPlaylistRequest.
     * @param builder - builder
     */
    public UpdateExchangeRateInWalletRequest(Builder builder) {
        this.customerId = builder.customerId;
        this.walletName = builder.walletName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getWalletName() {
        return walletName;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UpdateExchangeRateInWalletRequest that = (UpdateExchangeRateInWalletRequest) o;
        return Objects.equals(customerId, that.customerId) && Objects.equals(walletName, that.walletName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, walletName);
    }

    @Override
    public String toString() {
        return "UpdateExchangeRateInWalletRequest{" +
                "customerId='" + customerId + '\'' +
                ", walletName='" + walletName + '\'' +
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
        private String customerId;
        private String walletName;

        /**
         * Builder.
         */
        private Builder() {

        }

        /**
         * Builder.
         * @param customerIdToUse - customerIdToUse
         * @return - this
         */
        public Builder withCustomerId(String customerIdToUse) {
            this.customerId = customerIdToUse;
            return this;
        }

        /**
         * Builder.
         * @param walletNameToUse - walletNameToUse
         * @return - this
         */
        public Builder withWalletName(String walletNameToUse) {
            this.walletName = walletNameToUse;
            return this;
        }

        /**
         * UpdateExchangeRateInWalletRequest.
         * @return - UpdateExchangeRateInWalletRequest
         */
        public UpdateExchangeRateInWalletRequest build() {
            return new UpdateExchangeRateInWalletRequest(this);
        }
    }
}
