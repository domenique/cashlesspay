package io.tripled.cashlesspay.usecase;

import java.math.BigDecimal;

public final class CreateAccountRequestMother {

    public static CreateAccountRequest.CreateAccountRequestBuilder aValidCreateAccountRequest() {
        return CreateAccountRequest.aCreateAccountRequest()
                .withName("Domenique Tilleuil")
                .withInitialBalance(BigDecimal.TEN);
    }

    public static CreateAccountRequest.CreateAccountRequestBuilder anInvalidCreateAccountRequest() {
        return CreateAccountRequest
                .aCreateAccountRequest();
    }
}
