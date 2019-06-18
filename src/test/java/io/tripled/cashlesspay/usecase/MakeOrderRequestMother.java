package io.tripled.cashlesspay.usecase;

import java.math.BigDecimal;

public final class MakeOrderRequestMother {

    public static MakeOrderRequest.Builder aValidMakeOrder() {
        return MakeOrderRequest.aMakeOrderRequest()
                .withOrderItem("Beer", 1, BigDecimal.ONE);
    }

}
