package io.tripled.cashlesspay.usecase;

import io.tripled.cashlesspay.model.Account;
import io.tripled.cashlesspay.model.Accounts;

public class CreateAccountUseCase {

    private final Accounts accounts;

    public CreateAccountUseCase(Accounts accounts) {
        this.accounts = accounts;
    }

    public void execute(CreateAccountRequest request) {
        accounts.add(Account.anAccount()
                .withName(request.getName())
                .build());
    }
}
