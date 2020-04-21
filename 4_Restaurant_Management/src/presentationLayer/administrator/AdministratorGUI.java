package presentationLayer.administrator;

import businessLayer.BaseProduct;
import businessLayer.CompositeProduct;
import businessLayer.MenuItem;
import businessLayer.Restaurant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class AdministratorGUI {
    private JFrame frame = new JFrame("AdministratorWindow");
    private JPanel adminPanel = new JPanel();

    private JButton createBaseProductButton = new JButton("Create Base Product");
    private JButton createCompositeProductButton = new JButton("Create Composite Product");
    private JButton deleteMenuItemButton = new JButton("Delete Menu Item");
    private JButton updateBaseProductButton = new JButton("Update Base Product");
    private JButton updateCompositeProductButton = new JButton("Update Composite Product");


    private JPanel menuItemTablePanel = new JPanel();
    private JTable menuItemsTable;
    private Restaurant restaurant;

    BaseProductWindow baseProductWindow;
    CompositeProductWindow compositeProductWindow;

    private String newProductName;
    private int newProductWeight;
    private int newProductPrice;

    public AdministratorGUI(Restaurant restaurant){
        this.restaurant = restaurant;
       prepareGUI();
       addComponents();
       addActionEvents();
    }

    public void prepareGUI(){
        frame.setSize(900,400);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.darkGray);
    }

    public void addComponents(){
        frame.add(drawMenuItemTable(), BorderLayout.WEST);
        frame.add(drawMenuItemChange());
        frame.setVisible(true);
    }

    public JPanel drawMenuItemTable(){
        String[] columnNames = {"Product Name", "Product Weight", "Product Price"};
        menuItemTablePanel.setBackground(Color.darkGray);
        menuItemsTable = new JTable();
        menuItemsTable.setModel(new MenuItemTable(restaurant.getAvailableMenuItems()));

        menuItemTablePanel.add(new JScrollPane(menuItemsTable), BorderLayout.CENTER);
        menuItemTablePanel.revalidate();

        return menuItemTablePanel;

    }

    public JPanel drawMenuItemChange(){
        SpringLayout layout = new SpringLayout();
        adminPanel.setLayout(layout);
        adminPanel.setBackground(Color.darkGray);
        adminPanel.setSize(new Dimension(300,300));

        List<JButton> buttons = new ArrayList<JButton>();
        int heightLabel = 20;

        createBaseProductButton.setBounds(500, 50, 250, heightLabel);
        createBaseProductButton.setFont(new Font("Arial", Font.BOLD, 15));
        createBaseProductButton.setBackground(Color.LIGHT_GRAY);
        buttons.add(createBaseProductButton);
        adminPanel.add(createBaseProductButton);

        updateBaseProductButton.setBounds(500, 170, 250, heightLabel);
        updateBaseProductButton.setFont(new Font("Arial", Font.BOLD, 15));
        updateBaseProductButton.setBackground(Color.LIGHT_GRAY);
        buttons.add(updateBaseProductButton);
        adminPanel.add(updateBaseProductButton);

        createCompositeProductButton.setBounds(500, 90, 250, heightLabel);
        createCompositeProductButton.setFont(new Font("Arial", Font.BOLD, 15));
        createCompositeProductButton.setBackground(Color.LIGHT_GRAY);
        buttons.add(createCompositeProductButton);
        adminPanel.add(createCompositeProductButton);

        updateCompositeProductButton.setBounds(500, 210, 250, heightLabel);
        updateCompositeProductButton.setFont(new Font("Arial", Font.BOLD, 15));
        updateCompositeProductButton.setBackground(Color.LIGHT_GRAY);
        buttons.add(updateCompositeProductButton);
        adminPanel.add(updateCompositeProductButton);

        deleteMenuItemButton.setBounds(500, 130, 250, heightLabel);
        deleteMenuItemButton.setFont(new Font("Arial", Font.BOLD, 15));
        deleteMenuItemButton.setBackground(Color.LIGHT_GRAY);
        buttons.add(deleteMenuItemButton);
        adminPanel.add(deleteMenuItemButton);


        for(int i = 0 ; i<buttons.size(); i++){

            adminPanel.add(buttons.get(i));
            layout.putConstraint(SpringLayout.WEST,  buttons.get(i),120 , SpringLayout.WEST, adminPanel);
            layout.putConstraint(SpringLayout.NORTH,  buttons.get(i), 80 * i + 10, SpringLayout.NORTH, adminPanel);
        }

        return adminPanel;

    }

    public void addActionEvents(){
        createBaseProductButton.addActionListener((e)->{
            baseProductWindow = new BaseProductWindow(this);
            updateTable();

        });
        updateBaseProductButton.addActionListener((e)->{
            int row = menuItemsTable.getSelectedRow();
            baseProductWindow = new BaseProductWindow(this);
            BaseProduct newBaseProduct = (BaseProduct) restaurant.getAvailableMenuItems().get(row);
            baseProductWindow.setFields(newBaseProduct);


        });
        createCompositeProductButton.addActionListener((e)-> {
            compositeProductWindow = new CompositeProductWindow(this);
            updateTable();
        });
        updateCompositeProductButton.addActionListener((e)->{
            int row = menuItemsTable.getSelectedRow();
            compositeProductWindow = new CompositeProductWindow(this);

        });

        deleteMenuItemButton.addActionListener((e)->{
            int row = menuItemsTable.getSelectedRow();
            restaurant.deleteMenuItem(restaurant.getAvailableMenuItems().get(row));
            updateTable();
        });

    }

    public void updateTable(){
        menuItemsTable.setModel(new MenuItemTable(restaurant.getAvailableMenuItems()));
    }

    public Restaurant getRestaurant(){
        return restaurant;
    }

    public int getSelectedRow(){
        return menuItemsTable.getSelectedRow();
    }

    public void updateRestaurantMenuItem(MenuItem oldMenuItem, MenuItem newMenuItem){
        restaurant.updateMenuItem(oldMenuItem, newMenuItem);
    }
}
