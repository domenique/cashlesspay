package io.tripled.cashlesspay.usecase;

import java.math.BigDecimal;
import java.util.Objects;

public class TopUpAccountRequest {

    private String accountId;
    private BigDecimal amount;

    public static Builder aTopUpAccountRequest() {
        return new Builder();
    }

    private TopUpAccountRequest(String accountId, BigDecimal amount) {
        this.accountId = accountId;
        this.amount = amount;
    }

    public String getAccountId() {
        return accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TopUpAccountRequest that = (TopUpAccountRequest) o;
        return Objects.equals(accountId, that.accountId) &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, amount);
    }

    public static class Builder {
        private String accountId;
        private BigDecimal amount;

        public Builder withAccountId(String accountId) {
            this.accountId = accountId;
            return this;
        }

        public Builder withAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public TopUpAccountRequest build() {
            return new TopUpAccountRequest(accountId, amount);
        }
    }
}
