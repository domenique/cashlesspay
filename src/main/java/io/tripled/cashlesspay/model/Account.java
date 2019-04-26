package io.tripled.cashlesspay.model;

import java.util.Objects;
import java.util.UUID;

public class Account {
    private final String id;
    private final String name;

    public static AccountBuilder anAccount() {
        return new AccountBuilder();
    }

    private Account(String name) {
        id = Long.toHexString(UUID.randomUUID().getMostSignificantBits());
        this.name = name;
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
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

        public AccountBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public Account build() {
            return new Account(name);
        }
    }
}
