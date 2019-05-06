package io.tripled.cashlesspay.usecase;

public class TestTopUpAccountPresenter implements TopUpAccountPresenter {
    public boolean successCalled;
    public boolean notFoundCalled;
    public boolean negativeAmountNotAllowedCalled;

    @Override
    public void success() {
        successCalled = true;
    }

    @Override
    public void notFound() {
        notFoundCalled = true;
    }

    @Override
    public void negativeAmountNotAllowed() {
        negativeAmountNotAllowedCalled = true;
    }
}
