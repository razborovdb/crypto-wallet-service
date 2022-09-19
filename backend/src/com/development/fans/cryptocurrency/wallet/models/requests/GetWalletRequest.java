package com.development.fans.cryptocurrency.wallet.models.requests;

import java.util.Objects;

public class GetWalletRequest {
    private String customerId;
    private String walletName;
    /**
     * GetWalletRequest.
     */
    public GetWalletRequest() {

    }

    /**
     * GetWalletRequest.
     * @param builder - builder
     */
    public GetWalletRequest(Builder builder) {
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
        GetWalletRequest that = (GetWalletRequest) o;
        return Objects.equals(customerId, that.customerId) && Objects.equals(walletName, that.walletName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, walletName);
    }

    @Override
    public String toString() {
        return "GetWalletRequest{" +
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
        private Builder() {

        }

        /**
         * Builder.
         * @param useCustomerId - useCustomerId
         * @return - this
         */
        public Builder withCustomerId(String useCustomerId) {
            this.customerId = useCustomerId;
            return this;
        }

        /**
         * Builder.
         * @param useWalletName - useWalletName
         * @return - this
         */
        public Builder withWalletName(String useWalletName) {
            this.walletName = useWalletName;
            return this;
        }

        /**
         * GetWalletRequest.
         * @return -GetWalletRequest
         */
        public GetWalletRequest build() {
            return new GetWalletRequest(this);
        }
    }
}
