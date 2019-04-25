package io.tripled.cashlesspay.usecase;

class CreateAccountRequest {
    private final String name;

    CreateAccountRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
