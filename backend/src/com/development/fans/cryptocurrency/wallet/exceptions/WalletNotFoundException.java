package com.development.fans.cryptocurrency.wallet.exceptions;

/**
 * Exception to throw when a given Wallet with customerId and walletName
 * is not found in the database.
 */
public class WalletNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * Exception with no message or cause.
     */
    public WalletNotFoundException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public WalletNotFoundException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public WalletNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public WalletNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
