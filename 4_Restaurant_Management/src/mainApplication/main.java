package mainApplication;

import businessLayer.*;
import businessLayer.MenuItem;
import dataAccessLayer.FileWriter;
import dataAccessLayer.RestaurantSerializator;
import presentationLayer.GUI.GUI;
import presentationLayer.administrator.AdministratorGUI;
import presentationLayer.chef.ChefGUI;
import presentationLayer.waiter.WaiterGUI;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

public class main {

    public static void main(String[] args){
        System.out.println("am inceput tema 4");
        BaseProduct bp1 = new BaseProduct("Carne pui", 400, 12.50);
        BaseProduct bp2 = new BaseProduct("Cartofi prajiti", 250, 6);
        ArrayList<BaseProduct> baseProducts = new ArrayList<BaseProduct>();
        baseProducts.add(bp1);
        baseProducts.add(bp2);


       assert(bp1.getBaseProductWeight()>0);
       assert (bp1.getBaseProductWeight()<1000);

        CompositeProduct cp1 = new CompositeProduct(baseProducts, "meniu 1");

        ArrayList<MenuItem> menu = new ArrayList<>();
        menu.add(bp1);
        menu.add(bp2);
        menu.add(cp1);

        BaseProduct bp3 = new BaseProduct("mici", 23, 1);
        menu.add(bp3);

        RestaurantSerializator serializator = new RestaurantSerializator();
        ///serializator.writeInFile(menu);
        ArrayList<MenuItem> newMenu = serializator.readProducts();

        for(MenuItem menuItem : newMenu){
            System.out.println(menuItem.getMenuItemName());
            System.out.println(menuItem.showBaseProductsDetails());
            System.out.println(menuItem.getMenuItemWeight());
            System.out.println(menuItem.computePrice());
        }


        Restaurant restaurant = new Restaurant(serializator);
        Order order = restaurant.createNewOrder();
        restaurant.addMenuItemToOrder(order, bp1);
        ChefGUI chefGUI = new ChefGUI();
        restaurant.addObserver(chefGUI);
        GUI gui = new GUI(restaurant);


    }
}
