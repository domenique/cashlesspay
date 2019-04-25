package io.tripled.cashlesspay.usecase;

import io.tripled.cashlesspay.model.Account;
import io.tripled.cashlesspay.model.Accounts;

public class TestAccounts implements Accounts {
    public Account lastCreatedAccount;


    @Override
    public void add(Account account) {
        lastCreatedAccount = account;
    }
}
