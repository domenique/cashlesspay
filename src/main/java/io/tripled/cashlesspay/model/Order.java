package io.tripled.cashlesspay.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Order {

    private List<OrderLine> lines;

    public Order(List<OrderLine> lines) {
        this.lines = lines;
    }

    Stream<OrderLine> lines() {
        return lines.stream();
    }

    BigDecimal totalAmount() {
        return lines().map(OrderLine::totalPrice)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(lines, order.lines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lines);
    }

    class Builder {
        private List<OrderLine> lines = new ArrayList<>();

        Builder withLine(String description, int quantity, BigDecimal price) {
            lines.add(new OrderLine(description, quantity, price));

            return this;
        }

        Order build() {
            return new Order(new ArrayList<>(lines));
        }

    }
}
