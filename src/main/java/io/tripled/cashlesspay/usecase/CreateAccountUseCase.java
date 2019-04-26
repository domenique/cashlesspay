package io.tripled.cashlesspay.usecase;

import io.tripled.cashlesspay.model.Account;
import io.tripled.cashlesspay.model.Accounts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateAccountUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateAccountUseCase.class);
    private final Accounts accounts;

    public CreateAccountUseCase(Accounts accounts) {
        this.accounts = accounts;
    }

    public void execute(CreateAccountRequest request, CreateAccountPresenter presenter) {
        if ( isValid(request)) {
            Account account = Account.anAccount()
                    .withName(request.getName())
                    .build();
            accounts.add(account);

            LOGGER.info("New Account created with id {} for {}", account.id(), account.name());
            presenter.accountCreated(account.id());
        } else {
            presenter.nameNotProvided();
        }
    }

    private boolean isValid(CreateAccountRequest request) {
        return request.getName() != null && !request.getName().isEmpty();
    }
}
