package io.tripled.cashlesspay.model;

public class TopUpWithNegativeAmountNotAllowedException extends Exception {
    TopUpWithNegativeAmountNotAllowedException(String message) {
        super(message);
    }
}
