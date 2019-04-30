package io.tripled.cashlesspay.usecase;

public interface CreateAccountPresenter {
    void nameNotProvided();

    void negativeInitialBalanceProvided();

    void accountCreated(String id);
}
