package io.tripled.cashlesspay.model;

import java.math.BigDecimal;
import java.util.Objects;

public class OrderLine {
    private String description;
    private int quantity;
    private BigDecimal price;

    public OrderLine(String description, int quantity, BigDecimal price) {
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    public String description() {
        return description;
    }

    public int quantity() {
        return quantity;
    }

    public BigDecimal price() {
        return price;
    }

    BigDecimal totalPrice() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderLine orderItem = (OrderLine) o;
        return quantity == orderItem.quantity &&
                Objects.equals(description, orderItem.description) &&
                Objects.equals(price, orderItem.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, quantity, price);
    }

}
