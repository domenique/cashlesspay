package io.tripled.cashlesspay.usecase;

public interface MakeOrderPresenter {

    void success();

    void accountNotFound();

    void insuffucientBalance();
}
