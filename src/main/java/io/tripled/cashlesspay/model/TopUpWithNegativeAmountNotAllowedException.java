package io.tripled.cashlesspay.model;

public class TopUpWithNegativeAmountNotAllowedException extends RuntimeException {
    TopUpWithNegativeAmountNotAllowedException(String message) {
        super(message);
    }
}
