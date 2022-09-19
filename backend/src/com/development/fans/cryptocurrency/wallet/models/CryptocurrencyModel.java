package com.development.fans.cryptocurrency.wallet.models;

import java.util.Objects;

public class CryptocurrencyModel {
    private String cryptoName;
    private String cryptoDescription;
    private double cryptoAmount;
    private double cryptoCost;

    /**
     * CryptocurrencyModel.
     */
    public CryptocurrencyModel() {

    }

    /**
     * CryptocurrencyModel.
     * @param builder - Builder
     */
    public CryptocurrencyModel(Builder builder) {
        this.cryptoName = builder.cryptoName;
        this.cryptoDescription = builder.cryptoDescription;
        this.cryptoAmount = builder.cryptoAmount;
        this.cryptoCost = builder.cryptoCost;
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

    public double getCryptoCost() {
        return cryptoCost;
    }

    public void setCryptoCost(double cryptoCost) {
        this.cryptoCost = cryptoCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CryptocurrencyModel that = (CryptocurrencyModel) o;
        return Double.compare(that.cryptoAmount, cryptoAmount) == 0 && Double.compare(that.cryptoCost,
                cryptoCost) == 0 && Objects.equals(cryptoName, that.cryptoName) &&
                Objects.equals(cryptoDescription, that.cryptoDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cryptoName, cryptoDescription, cryptoAmount, cryptoCost);
    }

    @Override
    public String toString() {
        return "CryptocurrencyModel{" +
                "cryptoName='" + cryptoName + '\'' +
                ", cryptoDescription='" + cryptoDescription + '\'' +
                ", cryptoAmount=" + cryptoAmount +
                ", cryptoCost=" + cryptoCost +
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
        private String cryptoName;
        private String cryptoDescription;
        private double cryptoAmount;
        private double cryptoCost;

        /**
         * Builder.
         * @param useCryptoName - useCryptoName
         * @return - this
         */
        public Builder withCryptoName(String useCryptoName) {
            this.cryptoName = useCryptoName;
            return this;
        }

        /**
         * Builder.
         * @param useCryptoDescription - useCryptoDescription
         * @return - this
         */
        public Builder withCryptoDescription(String useCryptoDescription) {
            this.cryptoDescription = useCryptoDescription;
            return this;
        }

        /**
         * Builder.
         * @param useCryptoAmount - useCryptoAmount
         * @return - this
         */
        public Builder withCryptoAmount(double useCryptoAmount) {
            this.cryptoAmount = useCryptoAmount;
            return this;
        }

        /**
         * Builder.
         * @param useCryptoCost - useCryptoCost
         * @return - this
         */
        public Builder withCryptoCost(double useCryptoCost) {
            this.cryptoCost = useCryptoCost;
            return this;
        }

        /**
         * SongModel.
         * @return - SongModel
         */
        public CryptocurrencyModel build() {
            return new CryptocurrencyModel(this);
        }
    }
}
