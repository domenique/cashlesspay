package io.tripled.cashlesspay.model.account;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Transaction {

    static final Transaction ZERO = new Transaction(BigDecimal.ZERO);
    private String id;
    private BigDecimal amount;
    private LocalDateTime transactionTime;

    Transaction(BigDecimal amount) {
        id = Long.toHexString(UUID.randomUUID().getMostSignificantBits());
        this.amount = amount;
        transactionTime = LocalDateTime.now();
    }

    Transaction add(Transaction transaction) {
        return new Transaction(amount.add(transaction.amount));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    BigDecimal toBigDecimal() {
        return amount;
    }
}
