package io.tripled.cashlesspay.usecase;

import io.tripled.cashlesspay.model.account.Account;
import io.tripled.cashlesspay.model.account.Accounts;
import io.tripled.cashlesspay.model.EventPublisher;
import io.tripled.cashlesspay.model.account.TopUpWithNegativeAmountNotAllowedException;

import java.math.BigDecimal;

public class TopUpAccountUseCase {

    private final Accounts accounts;
    private final EventPublisher eventPublisher;

    public TopUpAccountUseCase(Accounts accounts, EventPublisher eventPublisher) {
        this.accounts = accounts;
        this.eventPublisher = eventPublisher;
    }

    public void execute(TopUpAccountRequest request, TopUpAccountPresenter presenter) {
        accounts.findById(request.getAccountId()).ifPresentOrElse(
                a -> topUp(a, request.getAmount(), presenter),
                presenter::notFound);
    }

    private void topUp(Account account, BigDecimal amount, TopUpAccountPresenter presenter) {
        try {
            account.topUp(amount);
            accounts.save(account);

            eventPublisher.publishFor(account);
            presenter.success();
        } catch (TopUpWithNegativeAmountNotAllowedException ex) {
            presenter.negativeAmountNotAllowed();
        }

    }
}
