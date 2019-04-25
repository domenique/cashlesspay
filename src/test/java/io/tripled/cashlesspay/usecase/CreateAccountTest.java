package io.tripled.cashlesspay.usecase;

import io.tripled.cashlesspay.model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CreateAccountTest {

    private TestAccounts accounts;
    private CreateAccountUseCase useCase;

    @BeforeEach
    void setUp() {
        accounts = new TestAccounts();
        useCase = new CreateAccountUseCase(accounts);
    }

    @Test
    void createNewAccount() {
        useCase.execute();

        Account createdAccount = accounts.lastCreatedAccount;
        assertThat(createdAccount).isNotNull();
    }
}
