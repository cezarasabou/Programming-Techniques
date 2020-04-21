package businessLayer;


import java.util.ArrayList;
import java.util.List;

public interface RestaurantProcessing{

    public void createMenuItem(MenuItem menuItem);
    public void deleteMenuItem(MenuItem menuItem);
    public void updateMenuItem(MenuItem oldMenuItem, MenuItem newMenuItem);

    public Order createNewOrder();
    public double computePrice(Order order);
    public void generateBill(Order order);
    public List<String> getMenuItemFieldNames();
    public ArrayList<MenuItem> getAvailableMenuItems();



}
