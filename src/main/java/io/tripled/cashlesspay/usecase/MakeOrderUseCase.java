package io.tripled.cashlesspay.usecase;

import io.tripled.cashlesspay.model.Accounts;
import io.tripled.cashlesspay.model.EventPublisher;

public class MakeOrderUseCase {

    private final Accounts accounts;
    private final EventPublisher eventPublisher;

    public MakeOrderUseCase(Accounts accounts, EventPublisher eventPublisher) {
        this.accounts = accounts;
        this.eventPublisher = eventPublisher;
    }

    public void execute(MakeOrderRequest request, MakeOrderPresenter presenter) {
        accounts.findById(request.getAccountId()).ifPresentOrElse(account -> {
            if (account.balance().compareTo(request.getAmount()) >= 0) {
                account.registerTransaction(request.getAmount());
                presenter.success();
            } else {
                presenter.insuffucientBalance();
            }
            eventPublisher.publishFor(account);
        }, presenter::accountNotFound);

    }

}
