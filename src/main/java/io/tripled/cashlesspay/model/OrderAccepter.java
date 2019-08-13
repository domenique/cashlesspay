package io.tripled.cashlesspay.model;

import io.tripled.cashlesspay.model.account.Account;
import io.tripled.cashlesspay.model.account.Accounts;

import java.util.Optional;

public class OrderAccepter {

  private String accountId;

  private Accounts accounts;

  public void acceptOrder(String accountId) {
    Optional<Account> account = accounts.findById(accountId);

    account.ifPresent(account1 -> {
      if account
    });



  }
}
