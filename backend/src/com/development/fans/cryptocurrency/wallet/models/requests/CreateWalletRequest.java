package com.development.fans.cryptocurrency.wallet.models.requests;

import java.util.Objects;

public class CreateWalletRequest {
    private String customerId;
    private String walletName;
    private String walletDescription;

    /**
     * CreateWalletRequest.
     */
    public CreateWalletRequest() {

    }
    /**
     * CreateWalletRequest.
     * @param customerId - customerId
     * @param walletName - walletName
     * @param walletDescription - walletDescription
     */
    public CreateWalletRequest(String customerId, String walletName, String walletDescription) {
        this.customerId = customerId;
        this.walletName = walletName;
        this.walletDescription = walletDescription;
    }

    /**
     * CreateWalletRequest.
     * @param builder - Builder
     */
    public CreateWalletRequest(Builder builder) {
        this.customerId = builder.customerId;
        this.walletName = builder.walletName;
        this.walletDescription = builder.walletDescription;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreateWalletRequest that = (CreateWalletRequest) o;
        return Objects.equals(customerId, that.customerId) && Objects.equals(walletName, that.walletName) &&
                Objects.equals(walletDescription, that.walletDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, walletName, walletDescription);
    }

    @Override
    public String toString() {
        return "CreateWalletRequest{" +
                "customerId='" + customerId + '\'' +
                ", walletName='" + walletName + '\'' +
                ", walletDescription='" + walletDescription + '\'' +
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
        private String walletDescription;

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
         * @param walletDescriptionToUse - walletDescriptionToUse
         * @return - this
         */
        public Builder withWalletDescription(String walletDescriptionToUse) {
            this.walletDescription = walletDescriptionToUse;
            return this;
        }

        /**
         * CreateWalletRequest.
         * @return - CreateWalletRequest
         */
        public CreateWalletRequest build() {
            return new CreateWalletRequest(this);
        }
    }
}
