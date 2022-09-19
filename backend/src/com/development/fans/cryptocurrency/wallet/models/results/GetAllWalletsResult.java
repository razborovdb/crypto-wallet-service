package com.development.fans.cryptocurrency.wallet.models.results;

import com.development.fans.cryptocurrency.wallet.dynamodb.models.Wallet;

import java.util.List;

public class GetAllWalletsResult {
    private List<Wallet> walletList;

    /**
     * GetAllWalletsResult.
     * @param builder - builder
     */
    public GetAllWalletsResult(Builder builder) {
        this.walletList = builder.walletList;
    }

    public List<Wallet> getWalletList() {
        return walletList;
    }

    public void setWalletList(List<Wallet> walletList) {
        this.walletList = walletList;
    }

    /**
     * Builder.
     * @return - Builder
     */
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private List<Wallet> walletList;

        /**
         * Builder.
         * @param walletListToUse - walletListToUse
         * @return - this
         */
        public Builder withWalletList(List<Wallet> walletListToUse) {
            this.walletList = walletListToUse;
            return this;
        }


        /**
         * GetWalletResult.
         * @return - GetWalletResult
         */
        public GetAllWalletsResult build() {
            return new GetAllWalletsResult(this);
        }
    }
}
