package com.development.fans.cryptocurrency.wallet.models.results;

import com.development.fans.cryptocurrency.wallet.dynamodb.models.Wallet;

public class CreateWalletResult {
    private Wallet wallet;

    /**
     * CreateWalletResult.
     * @param builder - builder
     */
    public CreateWalletResult(Builder builder) {
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
         * CreateWalletResult.
         * @return - CreateWalletResult
         */
        public CreateWalletResult build() {
            return new CreateWalletResult(this);
        }
    }
}
