package io.tripled.cashlesspay.model;

import java.util.List;

interface EventProvider {
    List<Object> uncommittedEvents();   
}