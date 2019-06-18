package io.tripled.cashlesspay.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Orders {

    private List<Order> orders;

    public Orders() {
        this.orders = new ArrayList<>();
    }

    public Orders(List<Order> orders) {
        this.orders = orders;
    }

    public void add(Order order) {
        this.orders.add(order);
    }

    public Stream<Order> stream() {
        return orders.stream();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders1 = (Orders) o;
        return Objects.equals(orders, orders1.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orders);
    }
}
