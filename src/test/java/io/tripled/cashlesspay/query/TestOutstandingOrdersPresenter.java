package io.tripled.cashlesspay.query;

import java.util.ArrayList;
import java.util.List;

public class TestOutstandingOrdersPresenter implements OutstandingOrdersPresenter {

  public List<OutstandingOrderResponse> outstandingOrders;

  @Override
  public void succes(List<OutstandingOrderResponse> outstandingOrders) {
    this.outstandingOrders = new ArrayList<>(outstandingOrders);
  }
}
