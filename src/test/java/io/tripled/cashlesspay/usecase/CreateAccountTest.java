package io.tripled.cashlesspay.usecase;

import io.tripled.cashlesspay.model.Account;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateAccountTest {

    @Test
    void createNewAccount() {
        TestAccounts accounts = new TestAccounts();
        CreateAccountUseCase useCase = new CreateAccountUseCase(accounts);

        useCase.execute();

        Account createdAccount = accounts.lastCreatedAccount;
        assertThat(createdAccount).isNotNull();
    }
}
