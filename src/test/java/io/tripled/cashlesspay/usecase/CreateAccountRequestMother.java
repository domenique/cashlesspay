package io.tripled.cashlesspay.usecase;

import java.math.BigDecimal;

final class CreateAccountRequestMother {

    static CreateAccountRequest.CreateAccountRequestBuilder aValidCreateAccountRequest() {
        return CreateAccountRequest.aCreateAccountRequest()
                .withName("Domenique Tilleuil")
                .withInitialBalance(BigDecimal.TEN);
    }

    static CreateAccountRequest.CreateAccountRequestBuilder anInvalidCreateAccountRequest() {
        return CreateAccountRequest
                .aCreateAccountRequest();
    }
}
