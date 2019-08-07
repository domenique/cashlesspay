package io.tripled.cashlesspay.model.account;

import java.util.Optional;

public interface Accounts {

    void add(Account account);

    Optional<Account> findById(String id);

    void save(Account account);
}
