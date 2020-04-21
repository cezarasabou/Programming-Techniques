package dataAccessLayer;

import businessLayer.MenuItem;
import businessLayer.Order;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class RestaurantSerializator {
    public ArrayList<MenuItem> readProducts(){
        try (FileInputStream dataIn = new FileInputStream("restaurantProducts.ser");
            ObjectInputStream in = new ObjectInputStream(dataIn);){
            ArrayList<MenuItem> menu = (ArrayList<MenuItem>) in.readObject();
            return menu;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void writeInFile(ArrayList<MenuItem> menu){
        try(FileOutputStream dataOut = new FileOutputStream("restaurantProducts.ser");){
            ObjectOutputStream out = new ObjectOutputStream(dataOut);
            out.writeObject(menu);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public HashMap<Order, ArrayList<MenuItem>> readOrders(){
        HashMap<Order, ArrayList<MenuItem>> orders = new HashMap<>();
        try (FileInputStream dataIn = new FileInputStream("restaurantOrders.ser");
             ObjectInputStream in = new ObjectInputStream(dataIn);){
             orders = (HashMap<Order, ArrayList<MenuItem>>) in.readObject();
            return orders;
        } catch (Exception e){
            e.printStackTrace();
        }
        return orders;
    }

    public void writeOrdersInFile(HashMap<Order, ArrayList<MenuItem>> orders){
        try(FileOutputStream dataOut = new FileOutputStream("restaurantOrders.ser");){
            ObjectOutputStream out = new ObjectOutputStream(dataOut);
            out.writeObject(orders);
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
