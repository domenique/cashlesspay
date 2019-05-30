package io.tripled.cashlesspay.usecase;

import java.math.BigDecimal;

final class TopUpAccountRequestMother {

    static TopUpAccountRequest.Builder aTopUpOf5() {
        return TopUpAccountRequest.aTopUpAccountRequest()
                .withAmount(BigDecimal.valueOf(5L));
    }
}
