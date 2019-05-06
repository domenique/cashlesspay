package io.tripled.cashlesspay.usecase;

public interface TopUpAccountPresenter {
    void success();

    void notFound();

    void negativeAmountNotAllowed();
}
