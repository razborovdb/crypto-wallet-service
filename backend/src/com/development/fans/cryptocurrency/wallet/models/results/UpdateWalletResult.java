package com.development.fans.cryptocurrency.wallet.models.results;

import com.development.fans.cryptocurrency.wallet.dynamodb.models.Wallet;

public class UpdateWalletResult {
    private Wallet wallet;

    /**
     * UpdateWalletResult.
     * @param builder - Builder
     */
    public UpdateWalletResult(Builder builder) {
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
         * Build.
         * @return - UpdateWalletResult
         */
        public UpdateWalletResult build() {
            return new UpdateWalletResult(this);
        }
    }
}
