package presentation.windows;

import dataaccess.ClientRepository;
import dataaccess.ProductRepository;
import model.Client;
import model.Product;
import presentation.PrintOrder;
import presentation.tables.ClientTable;
import presentation.tables.ProductTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static reflection.ReflectionProperties.getObjectFields;

public class OrderWindow {
    private JFrame frame = new JFrame("OrderWindow");

    private JPanel clientTablePanel = new JPanel();
    private JPanel productTablePanel = new JPanel();

    private JLabel clientTableLabel = new JLabel();
    private JLabel productTableLabel = new JLabel();
    private JLabel insertQuantityLabel = new JLabel();

    private JTextField insertQuantityField = new JTextField();

    private JButton submitOrderButton = new JButton("Submit Order");

    private JTable clientTable;
    private JTable productTable;

    private ClientRepository clientRepository = new ClientRepository();
    private ProductRepository productRepository = new ProductRepository();

    private List clientList = clientRepository.findAll();
    private List productList = productRepository.findAll();

    private int xLabel = 20;
    private int yLabel = 20;
    private int widthLabel = 100;
    private int heightLabel = 20;

    private int numberOfItems;


    public OrderWindow(){
        prepareGUI();
        addComponents();
        addActionEvents();
    }

    public void prepareGUI(){
        frame.setSize(1000, 600);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.darkGray);
    }

    public void addComponents(){

        SpringLayout layout = new SpringLayout();

        clientTableLabel.setBounds(xLabel,yLabel,widthLabel,heightLabel);
        clientTableLabel.setForeground(Color.white);
        clientTableLabel.setText("Clients :");
        frame.add(clientTableLabel);

        productTableLabel.setBounds(xLabel + 500,yLabel,widthLabel,heightLabel);
        productTableLabel.setForeground(Color.white);
        productTableLabel.setText("Products :");
        frame.add(productTableLabel);

        JPanel p1 = drawClientTable();
        p1.setBounds(20, 50, 400, 400);
        frame.add(p1);
        JPanel p2 = drawProductTable();
        p2.setBounds(450, 50, 500, 400);
        frame.add(p2);


        submitOrderButton.setBounds(300, 500, 250, heightLabel);
        submitOrderButton.setFont(new Font("Arial", Font.BOLD, 15));
        submitOrderButton.setBackground(Color.LIGHT_GRAY);
        frame.add(submitOrderButton);

        insertQuantityLabel.setBounds(300 ,450,100,heightLabel);
        insertQuantityLabel.setForeground(Color.white);
        insertQuantityLabel.setText("Insert Quantity :");
        frame.add(insertQuantityLabel);

        insertQuantityField.setBounds(400 ,450,100,heightLabel);
        insertQuantityField.setFont(new Font("Arial", Font.BOLD, 15));
        insertQuantityField.setHorizontalAlignment(SwingConstants.LEFT);
        frame.add(insertQuantityField);


        frame.setVisible(true);
    }

    private void updateClientTable(){
        Client c = new Client();
        clientList = clientRepository.findAll();
        clientTable.setModel(new ClientTable(clientList,getObjectFields(c)));
    }

    private void updateProductTable(){
        Product p = new Product();
        productList = productRepository.findAll();
        productTable.setModel(new ProductTable(productList,getObjectFields(p)));
    }

    private JPanel drawClientTable(){


        clientTablePanel.setBackground(Color.darkGray);
        clientTablePanel.setMinimumSize(new Dimension(450,200));
        Client c = new Client();
        clientTable = new JTable();
        clientTable.setModel(new ClientTable(clientList,getObjectFields(c)));
        // JScrollPane scrollPane = new JScrollPane(clientTable);
        clientTablePanel.add(clientTable);
        //clientTablePanel.add(scrollPane);
        //scrollPane.setPreferredSize(new Dimension(20,300));


        clientTablePanel.revalidate();
        clientTablePanel.repaint();



        return clientTablePanel;
    }

    private JPanel drawProductTable(){
        productTablePanel.setBackground(Color.darkGray);
        productTablePanel.setMinimumSize(new Dimension(450,200));
        Product p = new Product();
        productTable = new JTable();
        productTable.setModel(new ProductTable(productList,getObjectFields(p)));
        productTablePanel.add(productTable);

        productTablePanel.revalidate();
        productTablePanel.repaint();

        return productTablePanel;
    }

    private void addActionEvents(){
        submitOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Client c = new Client();
                int clientRow = clientTable.getSelectedRow();
                if(clientRow < 0){
                    JOptionPane.showMessageDialog(frame, "You must select a client","ERROR", JOptionPane.ERROR_MESSAGE);
                }else{
                    c.setClientId(Integer.parseInt((String) clientTable.getValueAt(clientRow, 0)));
                    c.setClientName((String) clientTable.getValueAt(clientRow,1));
                    c.setClientAddress((String)clientTable.getValueAt(clientRow,2));
                    c.setClientPhoneNumber((String)clientTable.getValueAt(clientRow,3));
                    c.setClientBirthDate((String)clientTable.getValueAt(clientRow,4));
                }



                int productRow = productTable.getSelectedRow();
                if(productRow< 0){
                    JOptionPane.showMessageDialog(frame, "You must select a product","ERROR", JOptionPane.ERROR_MESSAGE);
                }else{
                    Product p = new Product();
                    p.setProductId(Integer.parseInt((String) productTable.getValueAt(productRow, 0)));
                    p.setProductName((String) productTable.getValueAt(productRow,1));
                    p.setProductDescription((String) productTable.getValueAt(productRow,2));
                    p.setProductColor((String) productTable.getValueAt(productRow,3));
                    p.setProductPrice(Float.parseFloat((String) productTable.getValueAt(productRow, 4)));
                    p.setProductStockNumber(Integer.parseInt((String) productTable.getValueAt(productRow, 5)));

                    numberOfItems = Integer.parseInt(insertQuantityField.getText());
                    if(numberOfItems > p.getProductStockNumber()){
                        JOptionPane.showMessageDialog(frame, "Number of items you selected is bigger than what we have in stock at the moment","ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        PrintOrder newPrintOrder = new PrintOrder(c, p, numberOfItems);
                        int oldNumber = p.getProductStockNumber();
                        p.setProductStockNumber(oldNumber - numberOfItems);
                        productRepository.updateById(p);
                        updateProductTable();
                        newPrintOrder.submitOrder();
                    }
                }



            }

        });
    }
}
