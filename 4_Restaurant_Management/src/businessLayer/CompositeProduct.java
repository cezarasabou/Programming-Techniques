package businessLayer;

import java.util.ArrayList;


public class CompositeProduct extends MenuItem{


    private String compositeProductName;
    private int compositeProductWeight;
    private double compositeProductPrice;
    private ArrayList<BaseProduct> baseProducts;

    public CompositeProduct(ArrayList<BaseProduct > productList, String compositeProductName) {
        this.baseProducts = productList;
        this.compositeProductName = compositeProductName;
    }

    public String showCompositProductContents() {
        StringBuilder sb = new StringBuilder();
        for(BaseProduct baseProduct : baseProducts){
            sb.append(baseProduct.getBaseProductName()+" ");
        }
        return sb.toString();
    }

    public String getCompositeProductName(){
        return compositeProductName;
    }


    public int getCompositeProductWeight() {
        return computeWeight();
    }


    public double getCompositeProductPrice() {
        return computePrice();
    }

    public void setCompositeProductPrice(double compositeProductPrice) {
        this.compositeProductPrice = compositeProductPrice;
    }

    public void addBaseProduct(BaseProduct newBaseProduct){
        baseProducts.add(newBaseProduct);
    }

    public void removeBaseProduct(BaseProduct baseProduct){
        baseProducts.remove(baseProduct);
    }

    public ArrayList<BaseProduct> getBaseProducts(){
        return baseProducts;
    }

    /**
     * calculates weight
     */
    public int computeWeight(){
        int weight = 0;
        for(BaseProduct baseProduct : baseProducts){
            weight += baseProduct.getBaseProductWeight();
        }
        assert (weight<1500);
        return weight;
    }

    /**
     * calculates price
     * assuming that some base products exist
     * @return final price
     */
    @Override
    public double computePrice() {
        double finalPrice = 0;
        for(BaseProduct baseProduct : baseProducts){
            finalPrice += baseProduct.getBaseProductPrice();
        }
        assert finalPrice>=0;
        return finalPrice;

    }



    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String getMenuItemName() {
        return getCompositeProductName();
    }

    @Override
    public int getMenuItemWeight() {
        return getCompositeProductWeight();
    }

    @Override
    public String showBaseProductsDetails() {
        return showCompositProductContents();
    }
}