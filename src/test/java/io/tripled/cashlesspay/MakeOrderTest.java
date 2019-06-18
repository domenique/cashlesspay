package io.tripled.cashlesspay;

import io.tripled.cashlesspay.model.Account;
import io.tripled.cashlesspay.model.TestAccounts;
import io.tripled.cashlesspay.model.TestEventPublisher;
import io.tripled.cashlesspay.usecase.MakeOrderRequestMother;
import io.tripled.cashlesspay.usecase.MakeOrderUseCase;
import io.tripled.cashlesspay.usecase.TestMakeOrderPresenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static io.tripled.cashlesspay.usecase.MakeOrderRequestMother.aValidMakeOrder;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Make order usecase")
class MakeOrderTest {

    private MakeOrderUseCase useCase;
    private TestAccounts accounts;
    private TestEventPublisher eventPublisher;
    private TestMakeOrderPresenter presenter;

    @BeforeEach
    void setUp() {
        accounts = new TestAccounts();
        eventPublisher = new TestEventPublisher();
        useCase = new MakeOrderUseCase(accounts, eventPublisher);
        presenter = new TestMakeOrderPresenter();
    }

    @Test
    @DisplayName("should fail if the account is not found.")
    void failsIfAccountNotFound() {
        var request = MakeOrderRequestMother.aValidMakeOrder()
                .build();
        var presenter = new TestMakeOrderPresenter();

        useCase.execute(request, presenter);

        assertThat(presenter.accountNotFoundCalled).isTrue();
    }

    @Test
    @DisplayName("should fail if the account has insufficient money.")
    void failsIfTheAmountIsInsufficient() {
        Account emptyAccount = Account.anAccount()
                .withName("Domenique Tilleuil")
                .withInitialBalance(BigDecimal.ZERO)
                .build();
        accounts.add(emptyAccount);
        var request = aValidMakeOrder()
                .withAccountId(emptyAccount.id())
                .build();

        useCase.execute(request, presenter);

        assertThat(presenter.insuffucientBalanceCalled).isTrue();
    }

    @Test
    @DisplayName("should register the order if the balance is sufficient.")
    void registersOrder() {
        Account account = Account.anAccount()
                .withName("Domenique Tilleuil")
                .withInitialBalance(BigDecimal.TEN)
                .build();
        accounts.add(account);
        var request = aValidMakeOrder()
                .withAccountId(account.id())
                .build();

        useCase.execute(request, presenter);

        assertThat(presenter.successCalled).isTrue();
    }

    @Test
    @DisplayName("Deducts the money from the account.")
    void deductsFromTheBalance() {
        Account account = Account.anAccount()
                .withName("Domenique Tilleuil")
                .withInitialBalance(BigDecimal.TEN)
                .build();
        accounts.add(account);
        var request = aValidMakeOrder()
                .withAccountId(account.id())
                .build();

        useCase.execute(request, presenter);

        assertThat(presenter.successCalled).isTrue();
        assertThat(accounts.findById(account.id()).map(Account::balance)).hasValue(BigDecimal.valueOf(9L));
    }
}
