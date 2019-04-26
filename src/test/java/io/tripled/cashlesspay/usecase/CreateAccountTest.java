package io.tripled.cashlesspay.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static io.tripled.cashlesspay.model.Account.anAccount;
import static io.tripled.cashlesspay.usecase.CreateAccountRequestMother.aValidCreateAccountRequest;
import static io.tripled.cashlesspay.usecase.CreateAccountRequestMother.anInvalidCreateAccountRequest;
import static org.assertj.core.api.Assertions.assertThat;

class CreateAccountTest {

    private TestAccounts accounts;
    private CreateAccountUseCase useCase;
    private TestCreateAccountPresenter presenter;

    @BeforeEach
    void setUp() {
        accounts = new TestAccounts();
        useCase = new CreateAccountUseCase(accounts);
        presenter = new TestCreateAccountPresenter();
    }

    @Test
    @DisplayName("Should store the new account")
    void storeTheNewAccount() {
        useCase.execute(aValidCreateAccountRequest().build(), presenter);

        assertThat(accounts.lastCreatedAccount)
                .isNotNull();
        assertThat(presenter.id)
                .isNotEmpty();
    }

    @Test
    @DisplayName("Should store the a name in the account when given.")
    void storeNameOnTheAccount() {
        CreateAccountRequest request = aValidCreateAccountRequest().build();

        useCase.execute(request, presenter);

        assertThat(accounts.lastCreatedAccount)
                .isEqualTo(anAccount()
                        .withName("Domenique Tilleuil")
                        .build());
        assertThat(presenter.id)
                .isNotEmpty();
    }

    @Test
    @DisplayName("Should not store the acount when the name is not provided.")
    void notStoreWhenNameNotGiven() {
        CreateAccountRequest request = anInvalidCreateAccountRequest().build();

        useCase.execute(request, presenter);

        assertThat(accounts.lastCreatedAccount)
                .isNull();
        assertThat(presenter.nameNotProvidedCalled)
                .isTrue();
    }

    @Test
    @DisplayName("Should store the given initial balance on the account.")
    void storeInitialBalance() {
        CreateAccountRequest request = aValidCreateAccountRequest().build();

        useCase.execute(request, presenter);

        assertThat(presenter.id)
                .isNotEmpty();
        assertThat(accounts.lastCreatedAccount.balance())
                .isEqualTo(BigDecimal.TEN);
    }

    @Test
    @DisplayName("Should be able to create an account with a balance of ZERO.")
    void storeInitialBalanceWithZero() {
        CreateAccountRequest request = aValidCreateAccountRequest()
                .withInitialBalance(BigDecimal.ZERO)
                .build();

        useCase.execute(request, presenter);

        assertThat(presenter.id)
                .isNotEmpty();
        assertThat(accounts.lastCreatedAccount.balance())
                .isEqualTo(BigDecimal.ZERO);
    }
}
