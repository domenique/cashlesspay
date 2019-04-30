package io.tripled.cashlesspay.usecase;

import io.tripled.cashlesspay.model.Account;
import io.tripled.cashlesspay.model.Accounts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

import static java.util.Objects.isNull;

public class CreateAccountUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateAccountUseCase.class);
    private final Accounts accounts;

    public CreateAccountUseCase(Accounts accounts) {
        this.accounts = accounts;
    }

    public void execute(CreateAccountRequest request, CreateAccountPresenter presenter) {
        if (!isNameProvided(request)) {
            presenter.nameNotProvided();
            return;
        }

        if (!isInitialBalancePositive(request)) {
            presenter.negativeInitialBalanceProvided();
            return;
        }

        Account account = Account.anAccount()
                .withName(request.getName())
                .withInitialBalance(request.getInitialBalance())
                .build();
        accounts.add(account);

        LOGGER.info("New Account created with id {} for {} with initial balance of {}", account.id(), account.name(), account.balance());
        presenter.accountCreated(account.id());
    }

    private boolean isInitialBalancePositive(CreateAccountRequest request) {
        return request.getInitialBalance() != null && request.getInitialBalance().compareTo(BigDecimal.ZERO) >= 0;
    }

    private boolean isNameProvided(CreateAccountRequest request) {
        return !isNull(request.getName()) && !request.getName().isEmpty();
    }
}
