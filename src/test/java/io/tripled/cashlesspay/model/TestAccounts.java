package io.tripled.cashlesspay.model;

import io.tripled.cashlesspay.model.account.Account;
import io.tripled.cashlesspay.model.account.Accounts;

import java.util.HashMap;
import java.util.Optional;

public class TestAccounts implements Accounts {

    public HashMap<String, Account> accountsById;
    public Account lastCreatedAccount;

    public TestAccounts() {
        accountsById = new HashMap<>();
    }

    @Override
    public void add(Account account) {
        lastCreatedAccount = account;
        accountsById.put(account.id(), account);
    }

    @Override
    public Optional<Account> findById(String id) {
        return Optional.ofNullable(accountsById.get(id));
    }

    @Override
    public void save(Account account) {
        add(account);
    }
}
