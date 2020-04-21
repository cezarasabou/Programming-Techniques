package presentationLayer.waiter;

import businessLayer.BaseProduct;
import businessLayer.MenuItem;
import businessLayer.Order;
import businessLayer.Restaurant;
import presentationLayer.administrator.MenuItemTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class WaiterGUI {
    private JFrame frame = new JFrame("AdministratorWindow");
    private JPanel waiterPanel = new JPanel();

    private JButton createNewOrderButton = new JButton("Create Order");
    private JButton addItemToOrderButton = new JButton("Add item");
    private JButton removeItemFromOrderButton = new JButton("Remove item");
    private JButton placeOrderButton = new JButton("Place order");
    private JButton computeBillButton = new JButton("Compute Bill");

    private JPanel menuItemTablePanel = new JPanel();
    private JTable menuItemsTable;
    private JPanel orderItemTablePanel = new JPanel();
    private JTable orderItemsTable;
    private JPanel allOrdersTablePanel = new JPanel();
    private JTable allOrdersTable;
    private Order currentOrder;
    private ArrayList<Order> allOrders = new ArrayList<>();
    private Restaurant restaurant;


    public WaiterGUI(Restaurant restaurant){
        this.restaurant = restaurant;
        prepareGUI();
        addComponents();
        addActionListeners();
    }

    public void prepareGUI(){
        frame.setSize(1000,800);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.darkGray);
    }

    public void addComponents(){
        frame.add(drawMenuItemTable());
        frame.add(drawAllOrdersTable());
        frame.add(drawOrderItemTable());
        frame.add(drawOrderEditPanel());
        frame.setVisible(true);
    }

    public JPanel drawMenuItemTable(){
        String[] columnNames = {"Product Name", "Product Weight", "Product Price"};
        menuItemTablePanel.setBackground(Color.darkGray);
        menuItemTablePanel.setLocation(400,10);
        menuItemTablePanel.setSize(new Dimension(500,300));
        menuItemsTable = new JTable();
        menuItemsTable.setModel(new MenuItemTable(restaurant.getAvailableMenuItems()));

        menuItemTablePanel.add(new JScrollPane(menuItemsTable), BorderLayout.CENTER);
        menuItemTablePanel.revalidate();

        return menuItemTablePanel;
    }

    public JPanel drawOrderItemTable(){
        String[] columnNames = {"Product Name", "Product Weight", "Product Price"};
        orderItemTablePanel.setBackground(Color.darkGray);
        orderItemTablePanel.setLocation(500,400);
        orderItemTablePanel.setSize(new Dimension(500,300));
        orderItemsTable = new JTable();
        ArrayList<MenuItem> currentOrderMenuItems;
        if(currentOrder != null){
           currentOrderMenuItems = restaurant.getMenuItemsForOrder(currentOrder);
        }
        else{
            currentOrderMenuItems = new ArrayList<>();
        }
        orderItemsTable.setModel(new MenuItemTable(currentOrderMenuItems));
        orderItemTablePanel.add(new JScrollPane(orderItemsTable), BorderLayout.CENTER);
        orderItemTablePanel.revalidate();

        return orderItemTablePanel;
    }

    public JPanel drawAllOrdersTable(){
        allOrdersTablePanel.setBackground(Color.darkGray);
        allOrdersTablePanel.setLocation(10, 400);
        allOrdersTablePanel.setSize(new Dimension(500,300));
        allOrdersTable = new JTable();
        allOrdersTable.setModel(new OrdersTable(restaurant.getOrders()));
        allOrdersTablePanel.add(new JScrollPane(allOrdersTable), BorderLayout.CENTER);
        allOrdersTablePanel.revalidate();

        return allOrdersTablePanel;
    }


    public JPanel drawOrderEditPanel(){
        SpringLayout layout = new SpringLayout();
        waiterPanel.setLayout(layout);
        waiterPanel.setBackground(Color.darkGray);
        waiterPanel.setSize(new Dimension(300,300));

        List<JButton> buttons = new ArrayList<JButton>();
        int heightLabel = 20;

        createNewOrderButton.setBounds(500, 50, 250, heightLabel);
        createNewOrderButton.setFont(new Font("Arial", Font.BOLD, 15));
        createNewOrderButton.setBackground(Color.LIGHT_GRAY);
        buttons.add(createNewOrderButton);
        waiterPanel.add(createNewOrderButton);

        addItemToOrderButton.setBounds(500, 170, 250, heightLabel);
        addItemToOrderButton.setFont(new Font("Arial", Font.BOLD, 15));
        addItemToOrderButton.setBackground(Color.LIGHT_GRAY);
        buttons.add(addItemToOrderButton);
        waiterPanel.add(addItemToOrderButton);

        removeItemFromOrderButton.setBounds(500, 90, 250, heightLabel);
        removeItemFromOrderButton.setFont(new Font("Arial", Font.BOLD, 15));
        removeItemFromOrderButton.setBackground(Color.LIGHT_GRAY);
        buttons.add(removeItemFromOrderButton);
        waiterPanel.add(removeItemFromOrderButton);

        placeOrderButton.setBounds(500, 90, 250, heightLabel);
        placeOrderButton.setFont(new Font("Arial", Font.BOLD, 15));
        placeOrderButton.setBackground(Color.LIGHT_GRAY);
        buttons.add(placeOrderButton);
        waiterPanel.add(placeOrderButton);

        computeBillButton.setBounds(500, 210, 250, heightLabel);
        computeBillButton.setFont(new Font("Arial", Font.BOLD, 15));
        computeBillButton.setBackground(Color.LIGHT_GRAY);
        buttons.add(computeBillButton);
        waiterPanel.add(computeBillButton);

        for(int i = 0 ; i<buttons.size(); i++){

            waiterPanel.add(buttons.get(i));
            layout.putConstraint(SpringLayout.WEST,  buttons.get(i),120 , SpringLayout.WEST, waiterPanel);
            layout.putConstraint(SpringLayout.NORTH,  buttons.get(i), 80 * i + 10, SpringLayout.NORTH, waiterPanel);
        }

        return waiterPanel;

    }

    public void addActionListeners(){
        createNewOrderButton.addActionListener((e)->{
            Order order = restaurant.createNewOrder();
            updateOrderTable();
        });

        addItemToOrderButton.addActionListener((e)->{
            int orderNumber = allOrdersTable.getSelectedRow();
            int itemNumber = menuItemsTable.getSelectedRow();
            Order order = restaurant.getOrders().get(orderNumber);
            MenuItem menuItem = restaurant.getAvailableMenuItems().get(itemNumber);
            restaurant.addMenuItemToOrder(order,menuItem);
            updateMenuItemsForOrder(order);
        });

        placeOrderButton.addActionListener((e)->{
            int row = allOrdersTable.getSelectedRow();
            Order order = restaurant.getOrders().get(row);
            restaurant.placeOrder(order);
        });

        computeBillButton.addActionListener((e)->{
            int row = allOrdersTable.getSelectedRow();
            Order order = restaurant.getOrders().get(row);
            restaurant.generateBill(order);
        });

        allOrdersTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    Order order = restaurant.getOrders().get(row);
                   updateMenuItemsForOrder(order);
                }
            }
        });


    }

    public void updateOrderTable(){
        allOrdersTable.setModel(new OrdersTable(restaurant.getOrders()));
    }

    public void updateMenuItemsForOrder(Order order){
        orderItemsTable.setModel(new MenuItemTable(restaurant.getMenuItemsForOrder(order)));
    }
}
