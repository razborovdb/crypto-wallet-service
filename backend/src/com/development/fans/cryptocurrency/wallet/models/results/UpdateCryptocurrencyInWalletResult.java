package com.development.fans.cryptocurrency.wallet.models.results;

import com.development.fans.cryptocurrency.wallet.dynamodb.models.Wallet;

public class UpdateCryptocurrencyInWalletResult {
    private Wallet wallet;

    /**
     * UpdateCryptocurrencyInWalletResult.
     * @param builder - builder
     */
    public UpdateCryptocurrencyInWalletResult(Builder builder) {
        this.wallet = builder.wallet;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    /**
     * Builder.
     * @return - Builder
     */
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Wallet wallet;

        /**
         * Builder.
         * @param walletToUse - walletToUse
         * @return - this
         */
        public Builder withWallet(Wallet walletToUse) {
            this.wallet = walletToUse;
            return this;
        }

        /**
         * UpdateCryptocurrencyInWalletResult.
         * @return - UpdateCryptocurrencyInWalletResult
         */
        public UpdateCryptocurrencyInWalletResult build() {
            return new UpdateCryptocurrencyInWalletResult(this);
        }
    }
}
