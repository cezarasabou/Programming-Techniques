package dataAccessLayer;

import businessLayer.MenuItem;
import businessLayer.Order;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class FileWriter {

    private BufferedWriter writer;
    private static int billNumber = 0;

    public FileWriter() {
        initWriter();
    }

    private void initWriter() {
        try {
            String RESOURCES_PATH = "src/resources/";
            String BILL_FILE = "bill";
            writer = new BufferedWriter(new PrintWriter(new File(
                    String.format("%s%s_%s.txt", RESOURCES_PATH, BILL_FILE, billNumber))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void generateBill(Order order, double price, ArrayList<MenuItem> menuItems){
        try {

            writer.write(order.toString());
            writer.newLine();
            for(MenuItem menuItem : menuItems){
                writer.write(menuItem.showBaseProductsDetails());
                writer.newLine();
            }
            writer.write("price "+price);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        billNumber ++;
    }
}
