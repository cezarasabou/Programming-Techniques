package dataaccess;

import model.ProductOrder;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository extends GeneralRepository{
    private List<ProductOrder> orderList = new ArrayList<ProductOrder>();

    public OrderRepository() {
        super(ProductOrder.class);
    }
}
