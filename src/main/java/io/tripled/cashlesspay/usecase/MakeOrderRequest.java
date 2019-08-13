package io.tripled.cashlesspay.usecase;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MakeOrderRequest {

  private String accountId;
  private List<Item> items;

  public static Builder aMakeOrderRequest() {
    return new Builder();
  }

  private MakeOrderRequest(String accountId, List<Item> items) {
    this.accountId = accountId;
    this.items = items;
  }

  String getAccountId() {
    return accountId;
  }


  public BigDecimal getAmount() {
    return items.stream()
        .map(Item::totalPrice)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  static class Item {
    private String description;
    private int quantity;
    private BigDecimal price;

    private Item(String description, int quantity, BigDecimal price) {
      this.description = description;
      this.quantity = quantity;
      this.price = price;
    }

    String description() {
      return description;
    }

    int quantity() {
      return quantity;
    }

    BigDecimal price() {
      return price;
    }

    BigDecimal totalPrice() {
      return price.multiply(BigDecimal.valueOf(quantity));
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Item item = (Item) o;
      return quantity == item.quantity &&
          Objects.equals(description, item.description) &&
          Objects.equals(price, item.price);
    }

    @Override
    public int hashCode() {
      return Objects.hash(description, quantity, price);
    }
  }

  public static class Builder {
    private String accountId;
    private List<Item> items = new ArrayList<>();

    public Builder withAccountId(String accountId) {
      this.accountId = accountId;
      return this;
    }


    public Builder withOrderItem(String description, int quantity, BigDecimal price) {
      items.add(new Item(description, quantity, price));
      return this;
    }

    public MakeOrderRequest build() {
      return new MakeOrderRequest(accountId, items);
    }
  }
}
