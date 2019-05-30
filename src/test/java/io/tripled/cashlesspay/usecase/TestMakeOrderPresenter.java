package io.tripled.cashlesspay.usecase;

public class TestMakeOrderPresenter implements MakeOrderPresenter {

    public boolean accountNotFoundCalled;
    public boolean insuffucientBalanceCalled;
    boolean successCalled;


    @Override
    public void success() {
        successCalled = true;
    }

    @Override
    public void accountNotFound() {
        accountNotFoundCalled = true;
    }

    @Override
    public void insuffucientBalance() {
        insuffucientBalanceCalled = true;
    }
}
