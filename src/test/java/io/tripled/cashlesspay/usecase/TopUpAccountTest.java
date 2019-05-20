package io.tripled.cashlesspay.usecase;

import io.tripled.cashlesspay.model.Account;
import io.tripled.cashlesspay.model.TestAccounts;
import io.tripled.cashlesspay.model.TestEventPublisher;
import io.tripled.cashlesspay.model.event.AccountToppedUpEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class TopUpAccountTest {

    private TestAccounts accounts;
    private TestEventPublisher eventPublisher;
    private TopUpAccountUseCase useCase;
    private TestTopUpAccountPresenter presenter;


    @BeforeEach
    void setUp() {
        accounts = new TestAccounts();
        eventPublisher = new TestEventPublisher();
        useCase = new TopUpAccountUseCase(accounts, eventPublisher);
        presenter = new TestTopUpAccountPresenter();
    }

    @Test
    @DisplayName("Should top up the account if found.")
    void topsUpTheAccountIfFound() {
        var account = Account.anAccount()
                .withName("Domenique Tilleuil")
                .withInitialBalance(BigDecimal.TEN)
                .build();
        accounts.add(account);
        var request = TopUpAccountRequest.aTopUpAccountRequest()
                .withAccountId(account.id())
                .withAmount(BigDecimal.valueOf(5L))
                .build();

        useCase.execute(request, presenter);

        assertThat(presenter.successCalled).isTrue();
        assertThat(accounts.findById(account.id()).map(Account::balance))
                .hasValue(BigDecimal.TEN.add(BigDecimal.valueOf(5L)));
        assertThat(eventPublisher.findFirst(AccountToppedUpEvent.class))
                .isPresent();
    }

    @Test
    @DisplayName("Should not top up the account if not found.")
    void failsWhenAccountNotFound() {
        var request = TopUpAccountRequest.aTopUpAccountRequest()
                .withAccountId("fake-id")
                .withAmount(BigDecimal.valueOf(5L))
                .build();

        useCase.execute(request, presenter);

        assertThat(presenter.notFoundCalled).isTrue();
        assertThat(accounts.accountsById).isEmpty();
        assertThat(eventPublisher.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Should fial if the amount is negative")
    void failsWhenAmountIsNegative() {
        var account = Account.anAccount()
                .withName("Domenique Tilleuil")
                .withInitialBalance(BigDecimal.TEN)
                .build();
        accounts.add(account);
        var request = TopUpAccountRequest.aTopUpAccountRequest()
                .withAccountId(account.id())
                .withAmount(BigDecimal.valueOf(-5L))
                .build();

        useCase.execute(request, presenter);

        assertThat(presenter.negativeAmountNotAllowedCalled).isTrue();
        assertThat(accounts.findById(account.id()).map(Account::balance)).hasValue(BigDecimal.TEN);
        assertThat(eventPublisher.isEmpty()).isTrue();
    }
}
