package businessLayer;

import dataAccessLayer.FileWriter;
import dataAccessLayer.RestaurantSerializator;
import java.util.*;

public class Restaurant extends Observable implements RestaurantProcessing  {

    RestaurantSerializator serializator;
    private ArrayList<MenuItem> availableMenuItems;
    private HashMap<Order, ArrayList<MenuItem>> orders;
    private static int orderId = 0;


    public Restaurant(RestaurantSerializator restaurantSerializator){
        this.serializator = restaurantSerializator;
        orders = serializator.readOrders();
        availableMenuItems = serializator.readProducts();
    }


    //administrator
    @Override
    public void createMenuItem(MenuItem menuItem) {
        availableMenuItems.add(menuItem);
        serializator.writeInFile(availableMenuItems);
    }

    @Override
    public void deleteMenuItem(MenuItem menuItem) {
        availableMenuItems.remove(menuItem);
        serializator.writeInFile(availableMenuItems);
    }

    @Override
    public void updateMenuItem(MenuItem oldMenuItem, MenuItem newMenuIem) {
        availableMenuItems.remove(oldMenuItem);
        availableMenuItems.add(newMenuIem);
        serializator.writeInFile(availableMenuItems);
    }


    /**
     *
     * @return newly created order
     */
    @Override
    public Order createNewOrder() {
        Date date = new Date();
        Order order = new Order();
        Random random = new Random();
        order.setTableId(random.nextInt(100));
        order.setOrderDate(date);
        order.setOrderId(orderId);
        orderId ++;
        orders.put(order, new ArrayList<>());
        serializator.writeOrdersInFile(orders);
        return order;

    }

    public void addMenuItemToOrder(Order order, MenuItem menuItem){
        assert menuItem != null;
        if(orders.get(order) != null){
            orders.get(order).add(menuItem);
            serializator.writeOrdersInFile(orders);
        }
        else{
            ArrayList<MenuItem> menuItems = new ArrayList<>();
            menuItems.add(menuItem);
            orders.put(order,menuItems);
            serializator.writeOrdersInFile(orders);
        }
    }

    public void placeOrder(Order order){
        this.setChanged();
        notifyObservers(order);

    }


    @Override
    public double computePrice(Order order) {
        double orderPrice = 0;
        assert orderPrice == 0;
        for(MenuItem menuItem : orders.get(order)){
            orderPrice += menuItem.computePrice();
        }
        assert orderPrice>0;
        return orderPrice;
    }


    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }

    @Override
    public List<String> getMenuItemFieldNames() {
        List<String> menuItemFieldNames = new ArrayList<>();
        menuItemFieldNames.add("menuItemName");
        menuItemFieldNames.add("menuItemWeight");
        menuItemFieldNames.add("menuItemPrice");
        return menuItemFieldNames;
    }

    @Override
    public ArrayList<MenuItem> getAvailableMenuItems() {
        return availableMenuItems;
    }

    @Override
    public void generateBill(Order order) {
        FileWriter fileWriter = new FileWriter();
        fileWriter.generateBill(order,computePrice(order),orders.get(order));
    }
    public ArrayList<Order> getOrders(){
        return new ArrayList<>(orders.keySet());
    }

    public ArrayList<MenuItem> getMenuItemsForOrder(Order order){
        return orders.get(order);
    }

}
