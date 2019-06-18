package io.tripled.cashlesspay.usecase;

import io.tripled.cashlesspay.model.Accounts;
import io.tripled.cashlesspay.model.EventPublisher;
import io.tripled.cashlesspay.model.InsufficientBalanceAvailableException;
import io.tripled.cashlesspay.model.Order;
import io.tripled.cashlesspay.model.OrderLine;

import java.util.stream.Collectors;
import java.util.stream.Stream;

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
                account.order(toOrders(request.items()));
                eventPublisher.publishFor(account);
                presenter.success();
            } catch (InsufficientBalanceAvailableException ex) {
                presenter.insuffucientBalance();
            }
        }, presenter::accountNotFound);
    }

    private Order toOrders(Stream<MakeOrderRequest.OrderItem> items) {
        return new Order(items.map(i -> new OrderLine(i.description(), i.quantity(), i.price()))
                .collect(Collectors.toList()));
    }

}
