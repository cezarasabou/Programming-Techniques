package businessLayer;
import java.io.Serializable;

public abstract class MenuItem implements Serializable {

    private String menuItemName;
    private int menuItemWeight;
    private int menuItemPrice;



    public abstract String getMenuItemName();
    public abstract int getMenuItemWeight();
    public abstract double computePrice();
    public abstract String showBaseProductsDetails();

}
