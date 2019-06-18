package io.tripled.cashlesspay.usecase;

import io.tripled.cashlesspay.model.Accounts;
import io.tripled.cashlesspay.model.EventPublisher;
import io.tripled.cashlesspay.model.InsufficientBalanceAvailableException;

public class MakeOrderUseCase {

    private final Accounts accounts;
    private final EventPublisher eventPublisher;

    public MakeOrderUseCase(Accounts accounts, EventPublisher eventPublisher) {
        this.accounts = accounts;
        this.eventPublisher = eventPublisher;
    }

    public void execute(MakeOrderRequest request, MakeOrderPresenter presenter) {
        accounts.findById(request.getAccountId()).ifPresentOrElse(account -> {
            try {
                account.order(request.toOrder());
                eventPublisher.publishFor(account);
                presenter.success();
            } catch (InsufficientBalanceAvailableException ex) {
                presenter.insuffucientBalance();
            }
        }, presenter::accountNotFound);
    }


}
