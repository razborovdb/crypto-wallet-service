package com.development.fans.cryptocurrency.wallet.exceptions;

public class CryptocurrencyNotAddedException extends RuntimeException {


    /**
     * Exception with no message or cause.
     */
    public CryptocurrencyNotAddedException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public CryptocurrencyNotAddedException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public CryptocurrencyNotAddedException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public CryptocurrencyNotAddedException(String message, Throwable cause) {
        super(message, cause);
    }
}
