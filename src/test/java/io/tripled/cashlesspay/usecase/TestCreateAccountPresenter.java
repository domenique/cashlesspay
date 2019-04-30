package io.tripled.cashlesspay.usecase;

public class TestCreateAccountPresenter implements CreateAccountPresenter {

    public boolean nameNotProvidedCalled;
    public boolean negativeInitialBalanceCalled;
    public String id;

    @Override
    public void nameNotProvided() {
        nameNotProvidedCalled = true;
    }

    @Override
    public void negativeInitialBalanceProvided() {
        negativeInitialBalanceCalled = true;
    }

    @Override
    public void accountCreated(String id) {
        this.id = id;
    }
}
