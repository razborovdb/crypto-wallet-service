package com.development.fans.cryptocurrency.wallet.exceptions;

public class CryptocurrencyNotUpdatedException extends RuntimeException {
    /**
     * Exception with no message or cause.
     */
    public CryptocurrencyNotUpdatedException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public CryptocurrencyNotUpdatedException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public CryptocurrencyNotUpdatedException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public CryptocurrencyNotUpdatedException(String message, Throwable cause) {
        super(message, cause);
    }
}
