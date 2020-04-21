package presentation.windows;


import dataaccess.ProductRepository;
import model.Product;
import presentation.tables.ProductTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import static reflection.ReflectionProperties.getObjectFields;

public class ProductWindow {
    private JFrame frame = new JFrame("ProductWindow");
    private JFrame productTableFrame = new JFrame("ProductTable");
    private JPanel productTablePanel = new JPanel();
    private JPanel productForm = new JPanel();
    private JLabel productIdLabel = new JLabel();
    private JLabel productNameLabel = new JLabel();
    private JLabel productDescriptionLabel = new JLabel();
    private JLabel productColorLabel = new JLabel();
    private  JLabel productPriceLabel = new JLabel();
    private JLabel productStockNumberLabel = new JLabel();
    JLabel jLabel = new JLabel();

    private JTextField productIdField = new JTextField(15);
    private JTextField productNameField = new JTextField(15);
    private JTextField productDescriptionField = new JTextField(15);
    private JTextField productColorField = new JTextField(15);
    private JTextField productPriceField = new JTextField(15);
    private JTextField productStockNumberField = new JTextField(15);

    private Container panel;

    private JButton createProductButton = new JButton("Create Product");
    private JButton updateProductButton = new JButton("Update Product");
    private JButton deleteProductButton = new JButton("Delete Product");

    private static int newProductId;
    private static String newProductName;
    private static String newProductDescription;
    private static String newProductColor;
    private static float newProductPrice;
    private static int newProductStockNumber;

    private JTable productTable;


    private ProductRepository productRepository = new ProductRepository();
    private List<Product> productList = productRepository.findAll();

    public ProductWindow(){
        prepareGUI();
        addComponents();
        addActionEvents();
    }

    private void prepareGUI(){

        frame.setSize(900, 400);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

    }

    private void addComponents(){

        frame.add(drawTable(),BorderLayout.EAST);
        frame.add(drawProductForm());
        frame.setVisible(true);
    }

    /**
     * this method adds action listeners to every buttons and also whenever a product from the table is selected
     */
    private void addActionEvents(){
        createProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //newProductId = Integer.parseInt(productIdField.getText());
                newProductName = productNameField.getText();
                newProductDescription = productColorField.getText();
                newProductColor = productPriceField.getText();
                newProductPrice = Float.parseFloat(productStockNumberField.getText());
                newProductStockNumber = Integer.parseInt(productStockNumberField.getText());

                Product p = new Product();
                p.setProductName(newProductName);
                p.setProductDescription(newProductDescription);
                p.setProductColor(newProductColor);
                p.setProductPrice(newProductPrice);
                p.setProductStockNumber(newProductStockNumber);
                productRepository.insert(p);
                updateTable();
            }



        });

        updateProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                newProductName = productNameField.getText();
                newProductDescription = productDescriptionField.getText();
                newProductColor = productColorField.getText();
                newProductPrice =Float.parseFloat(productPriceField.getText());
                newProductStockNumber = Integer.parseInt(productStockNumberField.getText());

                int row = productTable.getSelectedRow();
                newProductId = Integer.parseInt((String) productTable.getValueAt(row, 0));
                Product p = new Product();
                p.setProductId(newProductId);
                p.setProductName(newProductName);
                p.setProductDescription(newProductDescription);
                p.setProductColor(newProductColor);
                p.setProductPrice(newProductPrice);
                p.setProductStockNumber(newProductStockNumber);
                productRepository.updateById(p);
                updateTable();
            }

        });

        deleteProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = productTable.getSelectedRow();
                newProductId = Integer.parseInt((String) productTable.getValueAt(row, 0));
                Product p = new Product();
                p.setProductId(newProductId);
                productRepository.delete(p);
                updateTable();

            }



        });

        productTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    int column = target.getSelectedColumn();

                    productNameField.setText(productList.get(row).getProductName());
                    productDescriptionField.setText(productList.get(row).getProductDescription());
                    productColorField.setText(productList.get(row).getProductColor());
                    productPriceField.setText(String.valueOf(productList.get(row).getProductPrice()));
                    productStockNumberField.setText(String.valueOf(productList.get(row).getProductStockNumber()));

                }
            }
        });


    }

    private void updateTable(){
        Product p = new Product();
        productList = productRepository.findAll();
        productTable.setModel(new ProductTable(productList,getObjectFields(p)));
    }



    private JPanel drawProductForm(){

        SpringLayout layout = new SpringLayout();
        productForm.setLayout(layout);
        productForm.setBackground(Color.darkGray);
        productForm.setSize(new Dimension(400,300));


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

        productIdLabel.setBounds(xLabel,yLabel,widthLabel,heightLabel);
        productIdLabel.setForeground(Color.white);
        productIdLabel.setText("Product ID :");
        labels.add(productIdLabel);
        productForm.add(productIdLabel);


        productNameLabel.setBounds(xLabel ,yLabel + 40,widthLabel,heightLabel);
        productNameLabel.setForeground(Color.white);
        productNameLabel.setText("Product Name :");
        labels.add(productNameLabel);
        productForm.add(productNameLabel);

        productDescriptionLabel.setBounds(xLabel ,yLabel + 40,widthLabel,heightLabel);
        productDescriptionLabel.setForeground(Color.white);
        productDescriptionLabel.setText("Product Description :");
        labels.add(productDescriptionLabel);
        productForm.add(productDescriptionLabel);

        productColorLabel.setBounds(xLabel,yLabel + 80,widthLabel,heightLabel);
        productColorLabel.setForeground(Color.white);
        productColorLabel.setText("Product Color :");
        labels.add(productColorLabel);
        productForm.add(productColorLabel);

        productPriceLabel.setBounds(xLabel,yLabel + 120,widthLabel,heightLabel);
        productPriceLabel.setForeground(Color.white);
        productPriceLabel.setText("Product Price :");
        labels.add(productPriceLabel);
        productForm.add(productPriceLabel);

        productStockNumberLabel.setBounds(xLabel,yLabel + 160,widthLabel,heightLabel);
        productStockNumberLabel.setForeground(Color.white);
        productStockNumberLabel.setText("Product StockNumber :");
        labels.add(productStockNumberLabel);
        productForm.add(productStockNumberLabel);

        productIdField.setBounds(xField,yField,widthField,heightField);
        productIdField.setFont(new Font("Arial", Font.BOLD, 15));
        productIdField.setHorizontalAlignment(SwingConstants.LEFT);
        textFields.add(productIdField);
        productForm.add(productIdField);

        productNameField.setBounds(xField, yField + 40, widthField, heightField);
        productNameField.setFont(new Font("Arial", Font.BOLD, 15));
        productNameField.setHorizontalAlignment(SwingConstants.LEFT);
        textFields.add(productNameField);
        productForm.add(productNameField);

        productDescriptionField.setBounds(xField, yField + 40, widthField, heightField);
        productDescriptionField.setFont(new Font("Arial", Font.BOLD, 15));
        productDescriptionField.setHorizontalAlignment(SwingConstants.LEFT);
        textFields.add(productDescriptionField);
        productForm.add(productDescriptionField);

        productColorField.setBounds(xField, yField + 80, widthField, heightField);
        productColorField.setFont(new Font("Arial", Font.BOLD, 15));
        productColorField.setHorizontalAlignment(SwingConstants.LEFT);
        textFields.add(productColorField);
        productForm.add(productColorField);

        productPriceField.setBounds(xField, yField + 120, widthField, heightField);
        productPriceField.setFont(new Font("Arial", Font.BOLD, 15));
        productPriceField.setHorizontalAlignment(SwingConstants.LEFT);
        textFields.add(productPriceField);
        productForm.add(productPriceField);

        productStockNumberField.setBounds(xField, yField + 160, widthField, heightField);
        productStockNumberField.setFont(new Font("Arial", Font.BOLD, 15));
        productStockNumberField.setHorizontalAlignment(SwingConstants.LEFT);
        textFields.add(productStockNumberField);
        productForm.add(productStockNumberField);

        createProductButton.setBounds(500, 50, 250, heightLabel);
        createProductButton.setFont(new Font("Arial", Font.BOLD, 15));
        createProductButton.setBackground(Color.LIGHT_GRAY);
        buttons.add(createProductButton);
        productForm.add(createProductButton);

        updateProductButton.setBounds(500, 90, 250, heightLabel);
        updateProductButton.setFont(new Font("Arial", Font.BOLD, 15));
        updateProductButton.setBackground(Color.LIGHT_GRAY);
        buttons.add(updateProductButton);
        productForm.add(updateProductButton);

        deleteProductButton.setBounds(500, 130, 250, heightLabel);
        deleteProductButton.setFont(new Font("Arial", Font.BOLD, 15));
        deleteProductButton.setBackground(Color.LIGHT_GRAY);
        buttons.add(deleteProductButton);
        productForm.add(deleteProductButton);

        for (int i = 0; i<labels.size(); i++) {

            productForm.add(labels.get(i));
            layout.putConstraint(SpringLayout.WEST,  labels.get(i), 10, SpringLayout.WEST, productForm);
            layout.putConstraint(SpringLayout.NORTH,  labels.get(i), 40*i+10, SpringLayout.NORTH, productForm);
            labels.get(i).setLabelFor(textFields.get(i));
            productForm.add(textFields.get(i));
            layout.putConstraint(SpringLayout.WEST,  textFields.get(i), 200, SpringLayout.WEST, productForm);
            layout.putConstraint(SpringLayout.NORTH,  textFields.get(i), 40*i + 10, SpringLayout.NORTH, productForm);
        }
        for(int i = 0 ; i<buttons.size(); i++){

            productForm.add(buttons.get(i));
            layout.putConstraint(SpringLayout.WEST,  buttons.get(i), 150 * i + 10, SpringLayout.WEST, productForm);
            layout.putConstraint(SpringLayout.NORTH,  buttons.get(i), 330, SpringLayout.NORTH, productForm);
        }

        return productForm;
    }

    private JPanel drawTable(){
        productTablePanel.setBackground(Color.darkGray);
        Product p = new Product();
        productTable = new JTable();
        productTable.setModel(new ProductTable(productList,getObjectFields(p)));
        productTablePanel.add(productTable, BorderLayout.CENTER);
        productTablePanel.revalidate();
        productTablePanel.repaint();

        return productTablePanel;
    }
}
