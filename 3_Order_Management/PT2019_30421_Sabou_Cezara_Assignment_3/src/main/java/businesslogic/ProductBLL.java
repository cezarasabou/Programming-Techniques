package businesslogic;

import dataaccess.ProductRepository;
import model.Product;

/**
 * class that implements the validators and links the dataaccess layer to the presentation
 */
import java.util.NoSuchElementException;

public class ProductBLL {
    private ProductRepository productRepository;

    public ProductBLL(){
        productRepository = new ProductRepository();
    }

    public Product findProductById(int id){
        Product p =(Product) productRepository.findById(id);
        if(p == null){
            throw new NoSuchElementException("product with this id");
        }
        return p;
    }
}
