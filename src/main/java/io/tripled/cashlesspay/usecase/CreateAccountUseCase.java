package io.tripled.cashlesspay.usecase;

import io.tripled.cashlesspay.model.Account;
import io.tripled.cashlesspay.model.Accounts;

public class CreateAccountUseCase {

    private final Accounts accounts;

    public CreateAccountUseCase(Accounts accounts) {
        this.accounts = accounts;
    }

    public void execute() {
        accounts.add(new Account());
    }
}
