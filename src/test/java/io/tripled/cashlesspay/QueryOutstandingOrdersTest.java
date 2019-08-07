package io.tripled.cashlesspay;

import io.tripled.cashlesspay.model.Order;
import io.tripled.cashlesspay.model.OrderStatus;
import io.tripled.cashlesspay.model.Orders;
import io.tripled.cashlesspay.query.OutstandingOrderResponse;
import io.tripled.cashlesspay.query.OutstandingOrdersQuery;
import io.tripled.cashlesspay.query.TestOutstandingOrdersPresenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class QueryOutstandingOrdersTest {

  private OutstandingOrdersQuery query;
  private Orders orders;

  @BeforeEach
  void setUp() {
    orders = new Orders();
    query = new OutstandingOrdersQuery(orders);
  }

  @Test
  void returnsEmtpyArrayIfNoOutstandingOrders() {
    TestOutstandingOrdersPresenter presenter = new TestOutstandingOrdersPresenter();

    query.execute(presenter);

    assertThat(presenter.outstandingOrders).isEmpty();
  }

  @Test
  void returnsAnOutstandingOrderIfFound() {
    orders.add(Order.anOrder()
        .withLine("Beer", 1, BigDecimal.ZERO)
        .build());
    TestOutstandingOrdersPresenter presenter = new TestOutstandingOrdersPresenter();

    query.execute(presenter);

    OutstandingOrderResponse expectedResponse = new OutstandingOrderResponse();
    expectedResponse.addItem("Beer", 1);
    assertThat(presenter.outstandingOrders).containsOnly(expectedResponse);
  }
  
  @Test
  void returnsNoOutstandingOrdersForDeliveredOrders() {
    orders.add(Order.anOrder()
        .withLine("Beer", 1, BigDecimal.ONE)
        .withStatus(OrderStatus.DELIVERED)
        .build());

    TestOutstandingOrdersPresenter presenter = new TestOutstandingOrdersPresenter();

    query.execute(presenter);
    assertThat(presenter.outstandingOrders).isEmpty();
  }
}
