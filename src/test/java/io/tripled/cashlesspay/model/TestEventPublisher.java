package io.tripled.cashlesspay.model;

import java.util.List;
import java.util.Optional;

public class TestEventPublisher implements EventPublisher {

    private List<Object> uncommittedEvents;

    @Override
    public void publishFor(EventProvider eventProvider) {
        uncommittedEvents = eventProvider.uncommittedEvents();
    }

    public <T> Optional<T> findFirst(Class<T> type) {
        return uncommittedEvents.stream()
                .filter(i -> i.getClass().isAssignableFrom(type))
                .findFirst().map(o -> (T) o);
    }

    public boolean isEmpty() {
        return uncommittedEvents == null || uncommittedEvents.isEmpty();

    }
}
