package io.tripled.cashlesspay.usecase;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class MakeOrderRequest {

    private String accountId;
    private List<OrderItem> items;

    public static Builder aMakeOrderRequest() {
        return new Builder();
    }

    private MakeOrderRequest(String accountId, List<OrderItem> items) {
        this.accountId = accountId;
        this.items = items;
    }

    public String getAccountId() {
        return accountId;
    }

    public BigDecimal getAmount() {
        return items.stream()
                .map(OrderItem::totalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    Stream<OrderItem> items() {
        return items.stream();
    }

    static class OrderItem {
        private String description;
        private int quantity;
        private BigDecimal price;

        private OrderItem(String description, int quantity, BigDecimal price) {
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

        public BigDecimal totalPrice() {
            return price.multiply(BigDecimal.valueOf(quantity));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            OrderItem orderItem = (OrderItem) o;
            return quantity == orderItem.quantity &&
                    Objects.equals(description, orderItem.description) &&
                    Objects.equals(price, orderItem.price);
        }

        @Override
        public int hashCode() {
            return Objects.hash(description, quantity, price);
        }
    }

    public static class Builder {
        private String accountId;
        private List<OrderItem> items = new ArrayList<>();

        public Builder withAccountId(String accountId) {
            this.accountId = accountId;
            return this;
        }


        public Builder withOrderItem(String description, int quantity, BigDecimal price) {
            items.add(new OrderItem(description, quantity, price));
            return this;
        }

        public MakeOrderRequest build() {
            return new MakeOrderRequest(accountId, items);
        }
    }
}
