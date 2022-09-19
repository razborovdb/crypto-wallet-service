package com.development.fans.cryptocurrency.wallet.models.requests;

import java.util.Objects;

public class AddCryptocurrencyToWalletRequest {
    private String customerId;
    private String walletName;
    private String cryptoName;
    private String cryptoDescription;
    private double cryptoAmount;

    /**
     * AddCryptocurrencyToWalletRequest.
     */
    public AddCryptocurrencyToWalletRequest() {
    }

    /**
     * AddCryptocurrencyToWalletRequest.
     * @param customerId - customerId
     * @param walletName - walletName
     * @param cryptoName - cryptoName
     * @param cryptoDescription - cryptoDescription
     * @param cryptoAmount - cryptoAmount
     */
    public AddCryptocurrencyToWalletRequest(String customerId, String walletName, String cryptoName,
                                            String cryptoDescription, double cryptoAmount) {
        this.customerId = customerId;
        this.walletName = walletName;
        this.cryptoName = cryptoName;
        this.cryptoDescription = cryptoDescription;
        this.cryptoAmount = cryptoAmount;
    }

    /**
     * AddSongToPlaylistRequest.
     * @param builder - builder
     */
    public AddCryptocurrencyToWalletRequest(Builder builder) {
        this.customerId = builder.customerId;
        this.walletName = builder.walletName;
        this.cryptoName = builder.cryptoName;
        this.cryptoDescription = builder.cryptoDescription;
        this.cryptoAmount = builder.cryptoAmount;
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

    public String getCryptoName() {
        return cryptoName;
    }

    public void setCryptoName(String cryptoName) {
        this.cryptoName = cryptoName;
    }

    public String getCryptoDescription() {
        return cryptoDescription;
    }

    public void setCryptoDescription(String cryptoDescription) {
        this.cryptoDescription = cryptoDescription;
    }

    public double getCryptoAmount() {
        return cryptoAmount;
    }

    public void setCryptoAmount(double cryptoAmount) {
        this.cryptoAmount = cryptoAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AddCryptocurrencyToWalletRequest that = (AddCryptocurrencyToWalletRequest) o;
        return Double.compare(that.cryptoAmount, cryptoAmount) == 0 && Objects.equals(customerId, that.customerId) &&
                Objects.equals(walletName, that.walletName) && Objects.equals(cryptoName, that.cryptoName) &&
                Objects.equals(cryptoDescription, that.cryptoDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, walletName, cryptoName, cryptoDescription, cryptoAmount);
    }

    @Override
    public String toString() {
        return "AddCryptocurrencyToWalletRequest{" +
                "customerId='" + customerId + '\'' +
                ", walletName='" + walletName + '\'' +
                ", cryptoName='" + cryptoName + '\'' +
                ", cryptoDescription='" + cryptoDescription + '\'' +
                ", cryptoAmount=" + cryptoAmount +
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
        private String cryptoName;
        private String cryptoDescription;
        private double cryptoAmount;

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
         * Builder.
         * @param cryptoNameToUse - cryptoNameToUse
         * @return - this
         */
        public Builder withCryptoName(String cryptoNameToUse) {
            this.cryptoName = cryptoNameToUse;
            return this;
        }

        /**
         * Builder.
         * @param cryptoDescriptionToUse - cryptoDescriptionToUse
         * @return - this
         */
        public Builder withCryptoDescription(String cryptoDescriptionToUse) {
            this.cryptoDescription = cryptoDescriptionToUse;
            return this;
        }

        /**
         * Builder.
         * @param cryptoAmountToUse - cryptoAmountToUse
         * @return - this
         */
        public Builder withCryptoAmount(double cryptoAmountToUse) {
            this.cryptoAmount = cryptoAmountToUse;
            return this;
        }

        /**
         * AddCryptocurrencyToWalletRequest.
         * @return - AddCryptocurrencyToWalletRequest
         */
        public AddCryptocurrencyToWalletRequest build() {
            return new AddCryptocurrencyToWalletRequest(this);
        }
    }
}
