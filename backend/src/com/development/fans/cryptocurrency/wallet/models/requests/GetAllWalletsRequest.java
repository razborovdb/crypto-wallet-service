package com.development.fans.cryptocurrency.wallet.models.requests;

import java.util.Objects;

public class GetAllWalletsRequest {
    private String customerId;

    /**
     *
     */
    public GetAllWalletsRequest() {

    }
    /**
     * GetAllWalletsRequest.
     * @param customerId - customerId
     */
    public GetAllWalletsRequest(String customerId) {
        this.customerId = customerId;
    }

    /**
     * GetAllWalletsRequest.
     * @param builder - builder
     */
    public GetAllWalletsRequest(Builder builder) {
        this.customerId = builder.customerId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GetAllWalletsRequest that = (GetAllWalletsRequest) o;
        return Objects.equals(customerId, that.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId);
    }

    @Override
    public String toString() {
        return "GetAllWalletsRequest{" +
                "customerId='" + customerId + '\'' +
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
         * GetAllWalletsRequest.
         * @return -GetAllWalletsRequest
         */
        public GetAllWalletsRequest build() {
            return new GetAllWalletsRequest(this);
        }
    }
}
