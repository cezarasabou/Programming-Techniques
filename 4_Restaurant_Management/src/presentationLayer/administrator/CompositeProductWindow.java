package presentationLayer.administrator;

import businessLayer.BaseProduct;
import businessLayer.CompositeProduct;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CompositeProductWindow {

    private JFrame frame = new JFrame("CompositeProductWindow");
    private JPanel baseProductsTablePanel = new JPanel();
    private JTable baseProductsTable = new JTable();
    private JPanel menuItemEditPanel = new JPanel();
    private JPanel menuItemTablePanel = new JPanel();
    private JTable menuItemsTable = new JTable();

    private JButton createCompositeProductButton = new JButton("Create");
    private JButton updateCompositeProductButton = new JButton("Update");
    private JButton addItemToList = new JButton("Add");
    private JButton removeItemFromList = new JButton("Remove");

    private JLabel compositeProductNameLabel = new JLabel();
    private JTextField compositeProductNameField = new JTextField(20);


    private CompositeProduct currentCompositeProduct;
    AdministratorGUI administratorGUI;


    public CompositeProductWindow(AdministratorGUI administratorGUI){
        this.administratorGUI = administratorGUI;
        prepareGUI();
        addComponents();
        addActionEvents();
    }

    public void prepareGUI(){

        frame.setSize(1000, 700);
        frame.setLayout(new BorderLayout());

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

    }

    public void addComponents(){
        frame.add(drawBaseProductsTable(), BorderLayout.SOUTH);
        frame.add(drawMenuItemChange());
        frame.add(drawMenuItemTable(), BorderLayout.WEST);
        frame.setVisible(true);
    }

    public JPanel drawBaseProductsTable(){
        String[] columnNames = {"Product Name", "Product Weight", "Product Price"};

        baseProductsTablePanel.setBackground(Color.darkGray);
        baseProductsTablePanel.setLocation(500,50);
        baseProductsTablePanel.setSize(new Dimension(400,300));
        baseProductsTable = new JTable();
        ArrayList<BaseProduct> currentOrderMenuItems;
        if(currentCompositeProduct != null){
            currentOrderMenuItems = currentCompositeProduct.getBaseProducts();
        }
        else{
            currentOrderMenuItems = new ArrayList<>();
        }
        baseProductsTable.setModel(new BaseProductTable(currentOrderMenuItems));
        baseProductsTablePanel.add(new JScrollPane(baseProductsTable), BorderLayout.CENTER);
        baseProductsTablePanel.revalidate();

        return baseProductsTablePanel;

    }

    public JPanel drawMenuItemChange(){

        menuItemEditPanel.setLayout(null);
        menuItemEditPanel.setBackground(Color.darkGray);
        menuItemEditPanel.setLocation(10,10);
        menuItemEditPanel.setSize(new Dimension(300,300));

        List<JButton> buttons = new ArrayList<JButton>();
        int heightLabel = 20;

        createCompositeProductButton.setBounds(50, 50, 100, heightLabel);
        createCompositeProductButton.setFont(new Font("Arial", Font.BOLD, 15));
        createCompositeProductButton.setBackground(Color.LIGHT_GRAY);
        buttons.add(createCompositeProductButton);
        menuItemEditPanel.add(createCompositeProductButton);

        updateCompositeProductButton.setBounds(50, 90, 100, heightLabel);
        updateCompositeProductButton.setFont(new Font("Arial", Font.BOLD, 15));
        updateCompositeProductButton.setBackground(Color.LIGHT_GRAY);
        buttons.add(updateCompositeProductButton);
        menuItemEditPanel.add(updateCompositeProductButton);

        addItemToList.setBounds(50, 130, 100, heightLabel);
        addItemToList.setFont(new Font("Arial", Font.BOLD, 15));
        addItemToList.setBackground(Color.LIGHT_GRAY);
        buttons.add(addItemToList);
        menuItemEditPanel.add(addItemToList);

        removeItemFromList.setBounds(50, 170, 100, heightLabel);
        removeItemFromList.setFont(new Font("Arial", Font.BOLD, 15));
        removeItemFromList.setBackground(Color.LIGHT_GRAY);
        buttons.add(removeItemFromList);
        menuItemEditPanel.add(removeItemFromList);


        compositeProductNameLabel.setBounds(50,210,100,heightLabel);
        compositeProductNameLabel.setForeground(Color.white);
        compositeProductNameLabel.setText("Product Name:");
        menuItemEditPanel.add(compositeProductNameLabel);


        compositeProductNameField.setBounds(180,210,100,20);
        compositeProductNameField.setFont(new Font("Arial", Font.BOLD, 15));
        compositeProductNameField.setHorizontalAlignment(SwingConstants.LEFT);
        menuItemEditPanel.add(compositeProductNameField);

        return menuItemEditPanel;
    }

    public JPanel drawMenuItemTable(){
        String[] columnNames = {"Product Name", "Product Weight", "Product Price"};
        menuItemTablePanel.setBackground(Color.darkGray);
        menuItemTablePanel.setLocation(100,500);
        menuItemTablePanel.setSize(new Dimension(500,300));
        menuItemsTable = new JTable();
        menuItemsTable.setModel(new MenuItemTable(administratorGUI.getRestaurant().getAvailableMenuItems()));

        menuItemTablePanel.add(new JScrollPane(menuItemsTable), BorderLayout.CENTER);
        menuItemTablePanel.revalidate();

        return menuItemTablePanel;
    }

    public void addActionEvents(){
        createCompositeProductButton.addActionListener((e)->{
            CompositeProduct compositeProduct = new CompositeProduct(new ArrayList<BaseProduct>(), compositeProductNameField.getText());
            administratorGUI.getRestaurant().createMenuItem(compositeProduct);
            administratorGUI.updateTable();

        });

        updateCompositeProductButton.addActionListener((e)->{
            int compositeProductNumber = administratorGUI.getSelectedRow();
            CompositeProduct oldCompositeProduct = (CompositeProduct) administratorGUI.getRestaurant().getAvailableMenuItems().get(compositeProductNumber);
            CompositeProduct newCompositeProduct = new CompositeProduct(oldCompositeProduct.getBaseProducts(),compositeProductNameField.getText());
            administratorGUI.getRestaurant().updateMenuItem(oldCompositeProduct, newCompositeProduct);
            administratorGUI.updateTable();

        });

        addItemToList.addActionListener((e)->{
            int compositeProductNumber = administratorGUI.getSelectedRow();
            CompositeProduct compositeProduct = (CompositeProduct) administratorGUI.getRestaurant().getAvailableMenuItems().get(compositeProductNumber);
            int baseProductNumber = menuItemsTable.getSelectedRow();
            compositeProduct.addBaseProduct((BaseProduct) administratorGUI.getRestaurant().getAvailableMenuItems().get(baseProductNumber));
            updateBaseProductsTable(compositeProduct);

        });

        removeItemFromList.addActionListener((e)->{
            int row = baseProductsTable.getSelectedRow();
            int currentCompositeNumber = administratorGUI.getSelectedRow();
            currentCompositeProduct = (CompositeProduct)administratorGUI.getRestaurant().getAvailableMenuItems().get(currentCompositeNumber);
            BaseProduct baseProduct =(BaseProduct) administratorGUI.getRestaurant().getAvailableMenuItems().get(row);
            currentCompositeProduct.removeBaseProduct(baseProduct);
            updateBaseProductsTable(currentCompositeProduct);
            administratorGUI.updateTable();
        });
    }

    public void updateBaseProductsTable(CompositeProduct compositeProduct){
        baseProductsTable.setModel(new BaseProductTable(compositeProduct.getBaseProducts()));
    }

}
