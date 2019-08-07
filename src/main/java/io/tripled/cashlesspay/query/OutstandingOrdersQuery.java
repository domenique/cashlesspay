package io.tripled.cashlesspay.query;

import io.tripled.cashlesspay.model.Order;
import io.tripled.cashlesspay.model.Orders;

import java.util.List;
import java.util.stream.Collectors;

public class OutstandingOrdersQuery {

  private Orders orders;

  public OutstandingOrdersQuery(Orders orders) {
    this.orders = orders;
  }

  public void execute(OutstandingOrdersPresenter presenter) {
    List<OutstandingOrderResponse> responses = orders.stream()
        .filter(Order::isNotDeliveredYet)
        .map(this::toResponse)
        .collect(Collectors.toList());

    presenter.succes(responses);
  }

  private OutstandingOrderResponse toResponse(Order order) {
    OutstandingOrderResponse response = new OutstandingOrderResponse();
    order.lines()
        .forEach(line -> response.addItem(line.description(), line.quantity()));
    return response;
  }
}
