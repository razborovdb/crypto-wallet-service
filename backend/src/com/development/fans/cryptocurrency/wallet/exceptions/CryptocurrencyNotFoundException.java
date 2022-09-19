package com.development.fans.cryptocurrency.wallet.exceptions;

/**
 * Exception to throw when a given Cryptocurrency with cryptoName is not found
 * in the database.
 */
public class CryptocurrencyNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 2L;

    /**
     * Exception with no message or cause.
     */
    public CryptocurrencyNotFoundException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public CryptocurrencyNotFoundException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public CryptocurrencyNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public CryptocurrencyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
