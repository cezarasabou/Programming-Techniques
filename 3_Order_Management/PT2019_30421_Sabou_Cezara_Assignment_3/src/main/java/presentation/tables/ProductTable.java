package presentation.tables;

import model.Product;

import java.util.List;

public class ProductTable extends AbstractTable {

    public ProductTable(List<Product> productList, List<String>  tableFieldNames) {
       super(productList,tableFieldNames, Product.class);
    }


}
