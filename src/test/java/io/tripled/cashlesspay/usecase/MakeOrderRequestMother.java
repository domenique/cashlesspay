package io.tripled.cashlesspay.usecase;

import java.math.BigDecimal;

final class MakeOrderRequestMother {

    static MakeOrderRequest.Builder aValidMakeOrder() {
        return MakeOrderRequest.aMakeOrderRequest()
                .withOrderItem("Beer", 1, BigDecimal.ONE);
    }

}
