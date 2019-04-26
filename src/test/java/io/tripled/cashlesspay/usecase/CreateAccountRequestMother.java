package io.tripled.cashlesspay.usecase;

final class CreateAccountRequestMother {

    static CreateAccountRequest aValidCreateAccountRequest() {
        return new CreateAccountRequest("Domenique Tilleuil");
    }

    static CreateAccountRequest anInvalidCreateAccountRequest() {
        return new CreateAccountRequest(null);
    }
}
