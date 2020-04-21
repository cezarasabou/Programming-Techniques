package presentationLayer.administrator;

import businessLayer.BaseProduct;
import businessLayer.MenuItem;
import sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class BaseProductWindow {
    private JFrame frame = new JFrame("BaseProductWindow");
    private JPanel baseProductPanel = new JPanel();
    private JLabel baseProductNameLabel = new JLabel();
    private JLabel baseProductWeightLabel = new JLabel();
    private JLabel baseProductPriceLabel = new JLabel();

    private JTextField baseProductNameField = new JTextField(20);
    private JTextField baseProductWeightField = new JTextField(20);
    private JTextField baseProductPriceField = new JTextField(20);

    private JButton createBaseProductButton = new JButton("Create base product");
    private JButton updateBaseProductButton = new JButton("Update base product");
    private AdministratorGUI administratorGUI;

    private static String productName;
    private static int productWeight;
    private static double productPrice;

    private BaseProduct newBaseProduct;

    public BaseProductWindow(AdministratorGUI administratorGUI){
        this.administratorGUI = administratorGUI;
        initializeHandlers();
    }

    private void initializeHandlers() {
        prepareGUI();
        addCommponents();
        addActionEvents();
    }

    public void prepareGUI(){
        frame.setSize(500, 300);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }

    public void addCommponents(){
        SpringLayout layout = new SpringLayout();
        baseProductPanel.setLayout(layout);
        baseProductPanel.setBackground(Color.darkGray);
        java.util.List<JLabel> labels = new ArrayList<JLabel>();
        java.util.List<JTextField> textFields = new ArrayList<JTextField>();
        List<JButton> buttons = new ArrayList<JButton>();

        int xLabel = 20;
        int yLabel = 20;
        int widthLabel = 100;
        int heightLabel = 20;

        int xField = 200;
        int yField = 20;
        int widthField = 200;
        int heightField = 10;

        baseProductNameLabel.setBounds(xLabel,yLabel,widthLabel,heightLabel);
        baseProductNameLabel.setForeground(Color.white);
        baseProductNameLabel.setText("Base Product Name:");
        labels.add(baseProductNameLabel);
        baseProductPanel.add(baseProductNameLabel);


        baseProductWeightLabel.setBounds(xLabel ,yLabel + 40,widthLabel,heightLabel);
        baseProductWeightLabel.setForeground(Color.white);
        baseProductWeightLabel.setText("Base Product Weight :");
        labels.add(baseProductWeightLabel);
        baseProductPanel.add(baseProductWeightLabel);

        baseProductPriceLabel.setBounds(xLabel,yLabel + 80,widthLabel,heightLabel);
        baseProductPriceLabel.setForeground(Color.white);
        baseProductPriceLabel.setText("Base Product  price:");
        labels.add(baseProductPriceLabel);
        baseProductPanel.add(baseProductPriceLabel);


        baseProductNameField.setBounds(xField,yField,widthField,heightField);
        baseProductNameField.setFont(new Font("Arial", Font.BOLD, 15));
        baseProductNameField.setHorizontalAlignment(SwingConstants.LEFT);
        textFields.add(baseProductNameField);
        baseProductPanel.add(baseProductNameField);

        baseProductWeightField.setBounds(xField, yField + 40, widthField, heightField);
        baseProductWeightField.setFont(new Font("Arial", Font.BOLD, 15));
        baseProductWeightField.setHorizontalAlignment(SwingConstants.LEFT);
        textFields.add(baseProductWeightField);
        baseProductPanel.add(baseProductWeightField);

        baseProductPriceField.setBounds(xField, yField + 80, widthField, heightField);
        baseProductPriceField.setFont(new Font("Arial", Font.BOLD, 15));
        baseProductPriceField.setHorizontalAlignment(SwingConstants.LEFT);
        textFields.add(baseProductPriceField);
        baseProductPanel.add(baseProductPriceField);


        createBaseProductButton.setBounds(500, 50, 250, heightLabel);
        createBaseProductButton.setFont(new Font("Arial", Font.BOLD, 15));
        createBaseProductButton.setBackground(Color.LIGHT_GRAY);
        buttons.add(createBaseProductButton);
        baseProductPanel.add(createBaseProductButton);

        updateBaseProductButton.setBounds(500, 50, 250, heightLabel);
        updateBaseProductButton.setFont(new Font("Arial", Font.BOLD, 15));
        updateBaseProductButton.setBackground(Color.LIGHT_GRAY);
        buttons.add(updateBaseProductButton);
        baseProductPanel.add(updateBaseProductButton);


        for (int i = 0; i<labels.size(); i++) {

            baseProductPanel.add(labels.get(i));
            layout.putConstraint(SpringLayout.WEST,  labels.get(i), 10, SpringLayout.WEST, baseProductPanel);
            layout.putConstraint(SpringLayout.NORTH,  labels.get(i), 40*i+10, SpringLayout.NORTH, baseProductPanel);
            labels.get(i).setLabelFor(textFields.get(i));
            baseProductPanel.add(textFields.get(i));
            layout.putConstraint(SpringLayout.WEST,  textFields.get(i), 200, SpringLayout.WEST, baseProductPanel);
            layout.putConstraint(SpringLayout.NORTH,  textFields.get(i), 40*i + 10, SpringLayout.NORTH, baseProductPanel);
        }

            baseProductPanel.add(createBaseProductButton);
            layout.putConstraint(SpringLayout.WEST, createBaseProductButton, 150 , SpringLayout.WEST, baseProductPanel);
            layout.putConstraint(SpringLayout.NORTH,  createBaseProductButton, 230, SpringLayout.NORTH, baseProductPanel);

            baseProductPanel.add(updateBaseProductButton);
            layout.putConstraint(SpringLayout.WEST, updateBaseProductButton, 170 , SpringLayout.WEST, baseProductPanel);
            layout.putConstraint(SpringLayout.NORTH,  updateBaseProductButton, 180, SpringLayout.NORTH, baseProductPanel);


        frame.add(baseProductPanel);
        frame.setVisible(true);
    }

    public void addActionEvents(){
        createBaseProductButton.addActionListener((e)-> {

                    productName = baseProductNameField.getText();
                    productWeight = Integer.parseInt(baseProductWeightField.getText());
                    productPrice = Double.parseDouble(baseProductPriceField.getText());

                    newBaseProduct = new BaseProduct(productName, productWeight, productPrice);
                    administratorGUI.getRestaurant().createMenuItem(newBaseProduct);
                    administratorGUI.updateTable();
                }
        );
        updateBaseProductButton.addActionListener((e)->{
            productName = baseProductNameField.getText();
            productWeight = Integer.parseInt(baseProductWeightField.getText());
            productPrice = Double.parseDouble(baseProductPriceField.getText());
            newBaseProduct = new BaseProduct(productName, productWeight, productPrice);
            int row = administratorGUI.getSelectedRow();
            MenuItem oldMenuItem = administratorGUI.getRestaurant().getAvailableMenuItems().get(row);
            administratorGUI.updateRestaurantMenuItem(oldMenuItem, newBaseProduct);
            administratorGUI.updateTable();

        });
    }

    public void setFields(BaseProduct baseProduct){
        baseProductNameField.setText(baseProduct.getBaseProductName());
        baseProductWeightField.setText(Integer.toString(baseProduct.getBaseProductWeight()));
        baseProductPriceField.setText(Double.toString(baseProduct.getBaseProductPrice()));

    }

    public BaseProduct getFields(){
        BaseProduct fieldsBaseProduct = new BaseProduct(baseProductNameField.getText(), Integer.parseInt(baseProductWeightField.getText()), Double.parseDouble(baseProductPriceField.getText()));
        return fieldsBaseProduct;

    }

    public void setNewBaseProduct(BaseProduct baseProduct){
        newBaseProduct = baseProduct;
    }
    public BaseProduct getNewBaseProduct(){
        return newBaseProduct;
    }

}
