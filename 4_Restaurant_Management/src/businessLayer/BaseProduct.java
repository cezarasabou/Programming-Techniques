package businessLayer;

public class BaseProduct extends MenuItem{

    private String baseProductName;
    private int baseProductWeight;
    private double baseProductPrice;

    public BaseProduct(String baseProductName, int baseProductWeight, double baseProductPrice){
        this.baseProductName = baseProductName;
        this.baseProductWeight = baseProductWeight;
        this.baseProductPrice = baseProductPrice;
    }

    public String getBaseProductName() {
        return baseProductName;
    }

    public void setBaseProductName(String baseProductName) {
        this.baseProductName = baseProductName;
    }

    public int getBaseProductWeight() {
        return baseProductWeight;
    }

    public void setBaseProductWeight(int baseProductWeight) {
        this.baseProductWeight = baseProductWeight;
    }

    public double getBaseProductPrice() {
        return baseProductPrice;
    }

    public void setBaseProductPrice(double baseProductPrice) {
        this.baseProductPrice = baseProductPrice;
    }

    @Override
    public double computePrice() {
        return getBaseProductPrice();
    }

    @Override
    public String getMenuItemName() {
        return getBaseProductName();
    }

    @Override
    public int getMenuItemWeight() {
        return getBaseProductWeight();
    }

    @Override
    public String showBaseProductsDetails() {
        return getBaseProductName();
    }
}
