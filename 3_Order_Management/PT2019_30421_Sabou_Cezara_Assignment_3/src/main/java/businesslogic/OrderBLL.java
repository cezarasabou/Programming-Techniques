package businesslogic;

import businesslogic.validators.OrderDateValidator;
import businesslogic.validators.Validator;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import dataaccess.OrderRepository;
import model.Product;
import model.ProductOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * class that implements the validators and links the dataaccess layer to the presentation
 */
public class OrderBLL {
    private List<Validator<ProductOrder>> validators;
    private OrderRepository orderRepository;

    public OrderBLL(){
        validators = new ArrayList<Validator<ProductOrder>>();
        validators.add(new OrderDateValidator());

        orderRepository = new OrderRepository();
    }

    public ProductOrder findOrderById(int id){
        ProductOrder order = (ProductOrder) orderRepository.findById(id);
        if(order == null){
            throw new NoSuchElementException("the order by this id does not even exist!");
        }
        return order;
    }
}
