package com.development.fans.cryptocurrency.wallet.exceptions;

public class WalletNotCreatedException extends RuntimeException {

    /**
     * Exception with no message or cause.
     */
    public WalletNotCreatedException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public WalletNotCreatedException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public WalletNotCreatedException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public WalletNotCreatedException(String message, Throwable cause) {
        super(message, cause);
    }
}
