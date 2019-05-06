package io.tripled.cashlesspay.usecase;

import io.tripled.cashlesspay.model.Account;
import io.tripled.cashlesspay.model.Accounts;
import io.tripled.cashlesspay.model.TopUpWithNegativeAmountNotAllowedException;

import java.math.BigDecimal;

public class TopUpAccountUseCase {

    private final Accounts accounts;

    public TopUpAccountUseCase(Accounts accounts) {
        this.accounts = accounts;
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
            presenter.success();
        } catch (TopUpWithNegativeAmountNotAllowedException ex) {
            presenter.negativeAmountNotAllowed();
        }

    }
}
