package businessLayer;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Order implements Serializable {
    private int orderId;
    private int tableId;
    private Date orderDate;


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId &&
                tableId == order.tableId &&
                Objects.equals(orderDate, order.orderDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, tableId, orderDate);
    }


    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", tableId=" + tableId +
                ", orderDate=" + orderDate +
                '}';
    }
}
