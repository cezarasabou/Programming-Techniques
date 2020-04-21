package model;

/**
 * product class that holds the attributes
 */
public class Product {
    private int productId;
    private String productName;
    private String productDescription;
    private String productColor;
    private float productPrice;
    private int productStockNumber;

    public int getProductStockNumber() {
        return productStockNumber;
    }

    public void setProductStockNumber(int productStockNumber) {
        this.productStockNumber = productStockNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductColor() {
        return productColor;
    }

    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
