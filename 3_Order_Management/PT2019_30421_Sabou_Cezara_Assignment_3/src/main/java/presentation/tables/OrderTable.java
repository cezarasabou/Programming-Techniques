package presentation.tables;

import model.ProductOrder;
import java.util.List;

public class OrderTable extends AbstractTable {

    public OrderTable(List<ProductOrder> orderList , List<String>  tableFieldNames) {
        super(orderList,tableFieldNames, ProductOrder.class);
    }


}
