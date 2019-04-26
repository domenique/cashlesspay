package io.tripled.cashlesspay.usecase;

public interface CreateAccountPresenter {
    void nameNotProvided();

    void accountCreated(String id);
}
