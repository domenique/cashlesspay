package io.tripled.cashlesspay.query;

import java.util.List;

public interface OutstandingOrdersPresenter {

  void succes(List<OutstandingOrderResponse> outstandingOrderResponses);
}
