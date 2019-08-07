package io.tripled.cashlesspay.model.account;

import io.tripled.cashlesspay.model.EventProvider;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Account implements EventProvider {
  private final String id;
  private final String name;
  private final List<Transaction> transactions;
  private final List<Object> uncommittedEvents;

  public static AccountBuilder anAccount() {
    return new AccountBuilder();
  }

  private Account(String name, List<Transaction> transactions) {
    id = Long.toHexString(UUID.randomUUID().getMostSignificantBits());
    this.transactions = transactions;
    uncommittedEvents = new ArrayList<>();
    this.name = name;
    uncommittedEvents.add(new AccountCreatedEvent());
  }

  public String id() {
    return id;
  }

  public String name() {
    return name;
  }

  public BigDecimal balance() {
    return transactions.stream()
        .reduce(Transaction.ZERO, Transaction::add)
        .toBigDecimal();
  }

  public void topUp(BigDecimal amount) throws TopUpWithNegativeAmountNotAllowedException {
    if (amount.compareTo(BigDecimal.ZERO) <= 0) {
      throw new TopUpWithNegativeAmountNotAllowedException("Cannot topUp an account with a negative amount");
    }
    transactions.add(new Transaction(amount));
    uncommittedEvents.add(new AccountToppedUpEvent());
  }

  public void registerTransaction(BigDecimal amount) throws InsufficientBalanceAvailableException {
    if (balance().subtract(amount).compareTo(BigDecimal.ZERO) >= 0) {
      transactions.add(new Transaction(amount.negate()));
      uncommittedEvents.add(new TransactionRegisteredEvent());
    } else {
      throw new InsufficientBalanceAvailableException();
    }
  }

  @Override
  public List<Object> uncommittedEvents() {
    return Collections.unmodifiableList(uncommittedEvents);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Account account = (Account) o;
    return Objects.equals(name, account.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  public static class AccountBuilder {

    private String name;
    private List<Transaction> transactions = new ArrayList<>();

    public AccountBuilder withName(String name) {
      this.name = name;
      return this;
    }

    public AccountBuilder withInitialBalance(BigDecimal initialBalance) {
      transactions.add(new Transaction(initialBalance));
      return this;
    }

    public Account build() {
      return new Account(name, transactions);
    }
  }
}
