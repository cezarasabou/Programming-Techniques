package presentationLayer.GUI;

import businessLayer.Restaurant;
import presentationLayer.administrator.AdministratorGUI;
import presentationLayer.waiter.WaiterGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Simple GUI that groups all of the windows, which can be accessed by pressing buttons
 */

public class GUI extends JFrame {
    private JFrame frame = new JFrame("Order Management");

    private JButton adminButton = new JButton("Administrator");
    private JButton waiterButton = new JButton("Waiter");
    private JLabel adminLabel = new JLabel("see, create, delete or update menuItem information here :");
    private JLabel waiterLabel = new JLabel("see, create, delete or update order information here :");

    private Restaurant restaurant;

    private int xLabel = 10;
    private int yLabel = 50;
    private int widthLabel = 300;
    private int heightLabel = 20;

    public GUI(Restaurant restaurant){
        this.restaurant = restaurant;
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

        adminLabel.setBounds(xLabel,yLabel,widthLabel,heightLabel);
        adminLabel.setForeground(Color.white);
        frame.add(adminLabel);

        adminButton.setBounds(400, 50, 170, 40);
        adminButton.setFont(new Font("Arial", Font.BOLD, 15));
        adminButton.setBackground(Color.LIGHT_GRAY);
        frame.add(adminButton);

        waiterLabel.setBounds(xLabel,yLabel + 100,widthLabel,heightLabel);
        waiterLabel.setForeground(Color.white);
        frame.add(waiterLabel);

        waiterButton.setBounds(400, 150, 170, 40);
        waiterButton.setFont(new Font("Arial", Font.BOLD, 15));
        waiterButton.setBackground(Color.LIGHT_GRAY);
        frame.add(waiterButton);


        frame.setVisible(true);


    }

    public void addActionEvents(){
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdministratorGUI administratorGUI = new AdministratorGUI(restaurant);
            }

        });

        waiterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WaiterGUI waiterGUI = new WaiterGUI(restaurant);
            }

        });

    }

}


