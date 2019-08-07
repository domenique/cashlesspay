package io.tripled.cashlesspay.model;

import java.util.List;

public interface EventProvider {
  List<Object> uncommittedEvents();
}