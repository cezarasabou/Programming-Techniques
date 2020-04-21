package presentation;

import model.Client;
import model.Product;

import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class PrintOrder {

    private Client c;
    private Product p;
    private int numberOfItems;
    private static int billNumber = 0;
    private BufferedWriter writer;

    public PrintOrder(Client c, Product p, int numberOfItems){
        this.c = c;
        this.p = p;
        this.numberOfItems = numberOfItems;
        initWriter();
    }

    public Client getC() {
        return c;
    }

    public void setC(Client c) {
        this.c = c;
    }

    public Product getP() {
        return p;
    }

    public void setP(Product p) {
        this.p = p;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    /**
     * this method takes the product and the client and prints a bill into a text file, specifying the time, the total,
     * the name of the buyer and then name of the product
     */
    public void submitOrder(){
        float totalPrice = p.getProductPrice() * numberOfItems;
        Date d = new Date();
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        cal.setTime(d);

        try {
            System.out.println("bill nr is " + billNumber);
            writer.write("Bill " + billNumber + " created on " + cal.get(Calendar.DAY_OF_MONTH)+"."+ cal.get(Calendar.MONTH) +"."+ cal.get(Calendar.YEAR) + " at " + d.getHours() +":"+ d.getMinutes() + "\n");
            writer.write("Client " + c.getClientName() + "\n");
            writer.write("Product " + p.getProductName() + "\n");
            writer.write("With price " + totalPrice + "\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        billNumber += 1;
    }

    private void initWriter() {
        try {
            String RESOURCES_PATH = "src/main/resources/";
            String BILL_FILE = "bill";
            writer = new BufferedWriter(new PrintWriter(new File(
                    String.format("%s%s_%s.txt", RESOURCES_PATH, BILL_FILE, billNumber))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
