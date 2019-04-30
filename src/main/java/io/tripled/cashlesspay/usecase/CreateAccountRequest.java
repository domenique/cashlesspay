package io.tripled.cashlesspay.usecase;

import java.math.BigDecimal;

class CreateAccountRequest {
    private final String name;
    private final BigDecimal initialBalance;

    public static CreateAccountRequestBuilder aCreateAccountRequest() {
        return new CreateAccountRequestBuilder();
    }

    CreateAccountRequest(String name, BigDecimal initialBalance) {
        this.name = name;
        this.initialBalance = initialBalance;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public static class CreateAccountRequestBuilder {
        private String name;
        private BigDecimal initialBalance;

        public CreateAccountRequestBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public CreateAccountRequestBuilder withInitialBalance(BigDecimal initialBalance) {
            this.initialBalance = initialBalance;
            return this;
        }

        public CreateAccountRequest build() {
            return new CreateAccountRequest(name, initialBalance);
        }

    }
}
