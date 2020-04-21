package presentation;

import presentation.windows.ClientWindow;
import presentation.windows.OrderWindow;
import presentation.windows.PasswordWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Simple GUI that groups all of the windows, which can be accessed by pressing buttons
 */

public class GUI extends JFrame {
    private JFrame frame = new JFrame("Order Management");

    private JButton clientsButton = new JButton("See Clients");
    private JButton productsButton = new JButton("See Products");
    private JButton orderButton = new JButton("Place Order");
    private JLabel clientLabel = new JLabel("see, create, delete or update client information here :");
    private JLabel productLabel = new JLabel("see, create, delete or update product information here :");
    private JLabel orderLabel = new JLabel("place your order here");

    private int xLabel = 10;
    private int yLabel = 50;
    private int widthLabel = 300;
    private int heightLabel = 20;

    public GUI(){
        prepareGUI();
        addComponents();
        addActionEvents();
    }

    public void prepareGUI(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.darkGray);
        frame.setLocationRelativeTo(null);
    }

    public void addComponents(){

        clientLabel.setBounds(xLabel,yLabel,widthLabel,heightLabel);
        clientLabel.setForeground(Color.white);
        frame.add(clientLabel);

        clientsButton.setBounds(400, 50, 170, 40);
        clientsButton.setFont(new Font("Arial", Font.BOLD, 15));
        clientsButton.setBackground(Color.LIGHT_GRAY);
        frame.add(clientsButton);

        productLabel.setBounds(xLabel,yLabel + 100,widthLabel,heightLabel);
        productLabel.setForeground(Color.white);
        frame.add(productLabel);

        productsButton.setBounds(400, 150, 170, 40);
        productsButton.setFont(new Font("Arial", Font.BOLD, 15));
        productsButton.setBackground(Color.LIGHT_GRAY);
        frame.add(productsButton);

        orderLabel.setBounds(xLabel,yLabel + 200,widthLabel,heightLabel);
        orderLabel.setForeground(Color.white);
        frame.add(orderLabel);

        orderButton.setBounds(400, 250, 170, 40);
        orderButton.setFont(new Font("Arial", Font.BOLD, 15));
        orderButton.setBackground(Color.LIGHT_GRAY);
        frame.add(orderButton);

        frame.setVisible(true);


    }

    public void addActionEvents(){
        clientsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientWindow clientWindow = new ClientWindow();
            }

        });

        productsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PasswordWindow passwordWindow = new PasswordWindow();
            }

        });

        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrderWindow orderWindow = new OrderWindow();
            }

        });


    }

}
