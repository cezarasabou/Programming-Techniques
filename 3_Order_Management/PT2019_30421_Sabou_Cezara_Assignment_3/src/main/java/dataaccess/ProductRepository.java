package dataaccess;


import model.Product;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository extends GeneralRepository{
    private Connection connection;
    private List<Product> productList = new ArrayList<Product>();

    public ProductRepository() {
        super(Product.class);
    }

}
