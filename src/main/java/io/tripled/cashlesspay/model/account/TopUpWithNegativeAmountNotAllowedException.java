package io.tripled.cashlesspay.model.account;

public class TopUpWithNegativeAmountNotAllowedException extends Exception {
    TopUpWithNegativeAmountNotAllowedException(String message) {
        super(message);
    }
}
