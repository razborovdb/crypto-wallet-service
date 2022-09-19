package com.development.fans.cryptocurrency.wallet.models.results;

import com.development.fans.cryptocurrency.wallet.dynamodb.models.Wallet;

public class GetWalletResult {
    private Wallet wallet;

    /**
     * GetWalletResult.
     * @param builder - builder
     */
    public GetWalletResult(Builder builder) {
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
         * @param useWallet - useWallet
         * @return - this
         */
        public Builder withWallet(Wallet useWallet) {
            this.wallet = useWallet;
            return this;
        }


        /**
         * GetWalletResult.
         * @return - GetWalletResult
         */
        public GetWalletResult build() {
            return new GetWalletResult(this);
        }
    }
}
