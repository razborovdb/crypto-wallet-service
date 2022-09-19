package com.development.fans.cryptocurrency.wallet.models;

import com.development.fans.cryptocurrency.wallet.dynamodb.models.Cryptocurrency;

import java.util.List;
import java.util.Objects;

public class WalletModel {
    private String customerId;
    private String walletName;
    private String walletDescription;
    private double cryptosCount;
    private double cryptosCost;
    private List<Cryptocurrency> cryptocurrenciesList;

    /**
     * WalletModel.
     */
    public WalletModel() {

    }

    /**
     * @param builder - Builder
     */
    public WalletModel(Builder builder) {
        this.customerId = builder.customerId;
        this.walletDescription = builder.walletDescription;
        this.walletName = builder.walletName;
        this.cryptosCount = builder.cryptosCount;
        this.cryptosCost = builder.cryptosCost;
        this.cryptocurrenciesList = builder.cryptocurrenciesList;
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

    public String getWalletDescription() {
        return walletDescription;
    }

    public void setWalletDescription(String walletDescription) {
        this.walletDescription = walletDescription;
    }

    public double getCryptosCount() {
        return cryptosCount;
    }

    public void setCryptosCount(double cryptosCount) {
        this.cryptosCount = cryptosCount;
    }

    public double getCryptosCost() {
        return cryptosCost;
    }

    public void setCryptosCost(double cryptosCost) {
        this.cryptosCost = cryptosCost;
    }

    public List<Cryptocurrency> getCryptocurrenciesList() {
        return cryptocurrenciesList;
    }

    public void setCryptocurrenciesList(List<Cryptocurrency> cryptocurrenciesList) {
        this.cryptocurrenciesList = cryptocurrenciesList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WalletModel that = (WalletModel) o;
        return Double.compare(that.cryptosCount, cryptosCount) == 0 &&
                Double.compare(that.cryptosCost, cryptosCost) == 0 &&
                Objects.equals(customerId, that.customerId) &&
                Objects.equals(walletName, that.walletName) &&
                Objects.equals(walletDescription, that.walletDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, walletName, walletDescription, cryptosCount, cryptosCost);
    }

    @Override
    public String toString() {
        return "WalletModel{" +
                "customerId='" + customerId + '\'' +
                ", walletName='" + walletName + '\'' +
                ", walletDescription='" + walletDescription + '\'' +
                ", cryptosCount=" + cryptosCount +
                ", cryptosCost=" + cryptosCost +
                ", cryptocurrenciesList=" + cryptocurrenciesList +
                '}';
    }

    /**
     * Builder.
     *
     * @return - Builder
     */
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String customerId;
        private String walletName;
        private String walletDescription;
        private double cryptosCount;
        private double cryptosCost;
        private List<Cryptocurrency> cryptocurrenciesList;

        /**
         * Builder.
         *
         * @param useCustomerId - useCustomerId
         * @return - this
         */
        public Builder withCustomerId(String useCustomerId) {
            this.customerId = useCustomerId;
            return this;
        }

        /**
         * Builder.
         *
         * @param useWalletName - useWalletName
         * @return - this
         */
        public Builder withWalletName(String useWalletName) {
            this.walletName = useWalletName;
            return this;
        }

        /**
         * Builder.
         *
         * @param useWalletDescription - useWalletDescription
         * @return - this
         */
        public Builder withWalletDescription(String useWalletDescription) {
            this.walletDescription = useWalletDescription;
            return this;
        }

        /**
         * Builder.
         *
         * @param useCryptosCount - useCryptosCount
         * @return - this
         */
        public Builder withCryptosCount(double useCryptosCount) {
            this.cryptosCount = useCryptosCount;
            return this;
        }

        /**
         * Builder.
         *
         * @param useCryptosCost - useCryptosCost
         * @return - this
         */
        public Builder withCryptosCost(double useCryptosCost) {
            this.cryptosCost = useCryptosCost;
            return this;
        }

        /**
         * Builder.
         *
         * @param useCryptocurrenciesList - useCryptocurrenciesList
         * @return - this
         */
        public Builder withCryptocurrenciesList(List<Cryptocurrency> useCryptocurrenciesList) {
            this.cryptocurrenciesList = useCryptocurrenciesList;
            return this;
        }

        /**
         * PlaylistModel.
         *
         * @return - PlaylistModel
         */
        public WalletModel build() {
            return new WalletModel(this);
        }
    }
}
