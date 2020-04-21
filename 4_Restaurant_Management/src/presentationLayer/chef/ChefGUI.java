package presentationLayer.chef;

import businessLayer.Order;
import businessLayer.Restaurant;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public  class ChefGUI implements Observer {

    private Restaurant restaurant;



    public ChefGUI(){
    }

    @Override
    public void update(Observable o, Object arg) {
        Order order = (Order)arg;
        System.out.println("chef got notified by restaurant" + order.toString());
        JOptionPane.showMessageDialog(null, "chef got notified by restaurant" + order.toString());
    }
}
