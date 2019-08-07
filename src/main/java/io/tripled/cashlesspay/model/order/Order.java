package io.tripled.cashlesspay.model.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Order {

  private List<OrderLine> lines;
  private OrderStatus status;

  public static Builder anOrder() {
    return new Builder();
  }

  public Order(List<OrderLine> lines) {
    this.lines = lines;
    status = OrderStatus.ORDERED;
  }

  public Order(List<OrderLine> lines, OrderStatus status) {
    this.lines = lines;
    this.status = status;
  }

  public Stream<OrderLine> lines() {
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

  public boolean isNotDeliveredYet() {
    return status != OrderStatus.DELIVERED;
  }

  public static class Builder {
    private List<OrderLine> lines = new ArrayList<>();
    private OrderStatus orderStatus;

    public Builder withLine(String description, int quantity, BigDecimal price) {
      lines.add(new OrderLine(description, quantity, price));

      return this;
    }

    public Builder withStatus(OrderStatus status) {
      orderStatus = status;

      return this;
    }

    public Order build() {
      Order order = new Order(new ArrayList<>(lines), orderStatus);
      return order;
    }

  }
}
