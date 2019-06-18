package io.tripled.cashlesspay.usecase;

import java.math.BigDecimal;

public final class TopUpAccountRequestMother {

    public static TopUpAccountRequest.Builder aTopUpOf5() {
        return TopUpAccountRequest.aTopUpAccountRequest()
                .withAmount(BigDecimal.valueOf(5L));
    }
}
