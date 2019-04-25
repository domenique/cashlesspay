package io.tripled.cashlesspay.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.tripled.cashlesspay.model.Account.anAccount;
import static io.tripled.cashlesspay.usecase.CreateAccountRequestMother.aValidCreateAccountRequest;
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
    @DisplayName("Should store the new account")
    void storeTheNewAccount() {
        useCase.execute(aValidCreateAccountRequest());

        assertThat(accounts.lastCreatedAccount)
                .isNotNull();
    }

    @Test
    @DisplayName("Should store the a name in the account when given.")
    void storeNameOnTheAccount() {
        CreateAccountRequest request = aValidCreateAccountRequest();

        useCase.execute(request);

        assertThat(accounts.lastCreatedAccount)
                .isEqualTo(anAccount()
                        .withName("Domenique Tilleuil")
                        .build());
    }
}
