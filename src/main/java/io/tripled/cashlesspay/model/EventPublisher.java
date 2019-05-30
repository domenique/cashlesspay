package io.tripled.cashlesspay.model;

public interface EventPublisher {

    void publishFor(EventProvider eventProvider);
}