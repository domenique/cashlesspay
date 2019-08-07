package io.tripled.cashlesspay.query;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OutstandingOrderResponse {

  private List<OutstandingOrderLine> lines;

  public OutstandingOrderResponse() {
    lines = new ArrayList<>();
  }

  public void addItem(String description, int quantity) {
    lines.add(new OutstandingOrderLine(description, quantity));
  }

  class OutstandingOrderLine {
    private String description;
    private int quantity;

    public OutstandingOrderLine(String description, int quantity) {
      this.description = description;
      this.quantity = quantity;
    }

    public String getDescription() {
      return description;
    }

    public int getQuantity() {
      return quantity;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      OutstandingOrderLine that = (OutstandingOrderLine) o;
      return quantity == that.quantity &&
          Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
      return Objects.hash(description, quantity);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    OutstandingOrderResponse response = (OutstandingOrderResponse) o;
    return Objects.equals(lines, response.lines);
  }

  @Override
  public int hashCode() {
    return Objects.hash(lines);
  }
}
