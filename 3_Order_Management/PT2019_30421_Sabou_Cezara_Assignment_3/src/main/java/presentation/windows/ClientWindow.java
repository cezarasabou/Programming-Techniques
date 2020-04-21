package presentation.windows;

import dataaccess.ClientRepository;
import model.Client;
import presentation.tables.ClientTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import static reflection.ReflectionProperties.getObjectFields;

public class ClientWindow extends JFrame{
    private JFrame frame = new JFrame("ClientWindow");
    private JFrame clientTableFrame = new JFrame("ClientTable");
    private JPanel clientTablePanel = new JPanel();
    private JPanel clientForm = new JPanel();
    private JLabel clientIdLabel = new JLabel();
    private JLabel clientNameLabel = new JLabel();
    private JLabel clientAddressLabel = new JLabel();
    private  JLabel clientPhoneNumberLabel = new JLabel();
    private JLabel clientBirthDateLabel = new JLabel();
    JLabel jLabel = new JLabel();

    private JTextField clientIdField = new JTextField(15);
    private JTextField clientNameField = new JTextField(15);
    private JTextField clientAddressField = new JTextField(15);
    private JTextField clientPhoneNumberField = new JTextField(15);
    private JTextField clientBirthDateField = new JTextField(15);

    private JButton createClientButton = new JButton("Create Client");
    private JButton updateClientButton = new JButton("Update Client");
    private JButton deleteClientButton = new JButton("Delete Client");

    private static int newClientId;
    private static String newClientName;
    private static String newClientAddress;
    private static String newClientPhoneNumber;
    private static String newClientBirthDate;

    private JTable clientTable;


    private ClientRepository clientRepository = new ClientRepository();
    private List<Client> clientList = clientRepository.findAll();

    public ClientWindow(){
        prepareGUI();
        addComponents();
        addActionEvents();
    }

    public void prepareGUI(){

        frame.setSize(900, 400);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

    }

    public void addComponents(){

       frame.add(drawTable(),BorderLayout.EAST);
       frame.add(drawClientForm());
       frame.setVisible(true);

    }

    public void addActionEvents(){
        createClientButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 //newClientId = Integer.parseInt(clientIdField.getText());
                 newClientName = clientNameField.getText();
                 newClientAddress = clientAddressField.getText();
                 newClientPhoneNumber = clientPhoneNumberField.getText();
                 newClientBirthDate = clientBirthDateField.getText();

                 Client c = new Client();
                 c.setClientName(newClientName);
                 c.setClientAddress(newClientAddress);
                 c.setClientPhoneNumber(newClientPhoneNumber);
                 c.setClientBirthDate(newClientBirthDate);
                 clientRepository.insert(c);
                 updateTable();
             }



        });

        updateClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                newClientName = clientNameField.getText();
                newClientAddress = clientAddressField.getText();
                newClientPhoneNumber = clientPhoneNumberField.getText();
                newClientBirthDate = clientBirthDateField.getText();

                Client c = new Client();
                int row = clientTable.getSelectedRow();
                newClientId = Integer.parseInt((String) clientTable.getValueAt(row, 0));
                c.setClientId(newClientId);
                c.setClientName(newClientName);
                c.setClientAddress(newClientAddress);
                c.setClientPhoneNumber(newClientPhoneNumber);
                c.setClientBirthDate(newClientBirthDate);
                clientRepository.updateById(c);
                updateTable();
            }

        });

        deleteClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = clientTable.getSelectedRow();
                newClientId = Integer.parseInt((String) clientTable.getValueAt(row, 0));
                Client c = new Client();
                c.setClientId(newClientId);
                clientRepository.delete(c);
                updateTable();

            }



        });

        clientTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    int column = target.getSelectedColumn();

                    clientNameField.setText(clientList.get(row).getClientName());
                    clientAddressField.setText(clientList.get(row).getClientAddress());
                    clientPhoneNumberField.setText(clientList.get(row).getClientPhoneNumber());
                    clientBirthDateField.setText(clientList.get(row).getClientBirthDate());

                }
            }
        });
    }

    private void updateTable(){
        Client c = new Client();
        clientList = clientRepository.findAll();
        clientTable.setModel(new ClientTable(clientList,getObjectFields(c)));
    }



    private JPanel drawClientForm(){

        SpringLayout layout = new SpringLayout();
        clientForm.setLayout(layout);
        clientForm.setBackground(Color.darkGray);
        clientForm.setSize(new Dimension(400,300));


        List<JLabel> labels = new ArrayList<JLabel>();
        List<JTextField> textFields = new ArrayList<JTextField>();
        List<JButton> buttons = new ArrayList<JButton>();

        int xLabel = 20;
        int yLabel = 20;
        int widthLabel = 100;
        int heightLabel = 20;

        int xField = 200;
        int yField = 20;
        int widthField = 200;
        int heightField = 10;

        clientIdLabel.setBounds(xLabel,yLabel,widthLabel,heightLabel);
        clientIdLabel.setForeground(Color.white);
        clientIdLabel.setText("Client ID :");
        labels.add(clientIdLabel);
        clientForm.add(clientIdLabel);


        clientNameLabel.setBounds(xLabel ,yLabel + 40,widthLabel,heightLabel);
        clientNameLabel.setForeground(Color.white);
        clientNameLabel.setText("Client Name :");
        labels.add(clientNameLabel);
        clientForm.add(clientNameLabel);

        clientAddressLabel.setBounds(xLabel,yLabel + 80,widthLabel,heightLabel);
        clientAddressLabel.setForeground(Color.white);
        clientAddressLabel.setText("Client Address :");
        labels.add(clientAddressLabel);
        clientForm.add(clientAddressLabel);

        clientPhoneNumberLabel.setBounds(xLabel,yLabel + 120,widthLabel,heightLabel);
        clientPhoneNumberLabel.setForeground(Color.white);
        clientPhoneNumberLabel.setText("Client PhoneNumber :");
        labels.add(clientPhoneNumberLabel);
        clientForm.add(clientPhoneNumberLabel);

        clientBirthDateLabel.setBounds(xLabel,yLabel + 160,widthLabel,heightLabel);
        clientBirthDateLabel.setForeground(Color.white);
        clientBirthDateLabel.setText("Client Birthdate :");
        labels.add(clientBirthDateLabel);
        clientForm.add(clientBirthDateLabel);

        clientIdField.setBounds(xField,yField,widthField,heightField);
        clientIdField.setFont(new Font("Arial", Font.BOLD, 15));
        clientIdField.setHorizontalAlignment(SwingConstants.LEFT);
        textFields.add(clientIdField);
        clientForm.add(clientIdField);

        clientNameField.setBounds(xField, yField + 40, widthField, heightField);
        clientNameField.setFont(new Font("Arial", Font.BOLD, 15));
        clientNameField.setHorizontalAlignment(SwingConstants.LEFT);
        textFields.add(clientNameField);
        clientForm.add(clientNameField);

        clientAddressField.setBounds(xField, yField + 80, widthField, heightField);
        clientAddressField.setFont(new Font("Arial", Font.BOLD, 15));
        clientAddressField.setHorizontalAlignment(SwingConstants.LEFT);
        textFields.add(clientAddressField);
        clientForm.add(clientAddressField);

        clientPhoneNumberField.setBounds(xField, yField + 120, widthField, heightField);
        clientPhoneNumberField.setFont(new Font("Arial", Font.BOLD, 15));
        clientPhoneNumberField.setHorizontalAlignment(SwingConstants.LEFT);
        textFields.add(clientPhoneNumberField);
        clientForm.add(clientPhoneNumberField);

        clientBirthDateField.setBounds(xField, yField + 160, widthField, heightField);
        clientBirthDateField.setFont(new Font("Arial", Font.BOLD, 15));
        clientBirthDateField.setHorizontalAlignment(SwingConstants.LEFT);
        textFields.add(clientBirthDateField);
        clientForm.add(clientBirthDateField);

        createClientButton.setBounds(500, 50, 250, heightLabel);
        createClientButton.setFont(new Font("Arial", Font.BOLD, 15));
        createClientButton.setBackground(Color.LIGHT_GRAY);
        buttons.add(createClientButton);
        clientForm.add(createClientButton);

        updateClientButton.setBounds(500, 90, 250, heightLabel);
        updateClientButton.setFont(new Font("Arial", Font.BOLD, 15));
        updateClientButton.setBackground(Color.LIGHT_GRAY);
        buttons.add(updateClientButton);
        clientForm.add(updateClientButton);

        deleteClientButton.setBounds(500, 130, 250, heightLabel);
        deleteClientButton.setFont(new Font("Arial", Font.BOLD, 15));
        deleteClientButton.setBackground(Color.LIGHT_GRAY);
        buttons.add(deleteClientButton);
        clientForm.add(deleteClientButton);

        for (int i = 0; i<labels.size(); i++) {

            clientForm.add(labels.get(i));
            layout.putConstraint(SpringLayout.WEST,  labels.get(i), 10, SpringLayout.WEST, clientForm);
            layout.putConstraint(SpringLayout.NORTH,  labels.get(i), 40*i+10, SpringLayout.NORTH, clientForm);
            labels.get(i).setLabelFor(textFields.get(i));
            clientForm.add(textFields.get(i));
            layout.putConstraint(SpringLayout.WEST,  textFields.get(i), 200, SpringLayout.WEST, clientForm);
            layout.putConstraint(SpringLayout.NORTH,  textFields.get(i), 40*i + 10, SpringLayout.NORTH, clientForm);
        }
        for(int i = 0 ; i<buttons.size(); i++){

            clientForm.add(buttons.get(i));
            layout.putConstraint(SpringLayout.WEST,  buttons.get(i), 150 * i + 10, SpringLayout.WEST, clientForm);
            layout.putConstraint(SpringLayout.NORTH,  buttons.get(i), 230, SpringLayout.NORTH, clientForm);
        }

        return clientForm;
    }

    private JPanel drawTable(){
        clientTablePanel.setBackground(Color.darkGray);
        Client c = new Client();
        clientTable = new JTable();
        clientTable.setModel(new ClientTable(clientList,getObjectFields(c)));
        clientTablePanel.add(clientTable, BorderLayout.CENTER);
        clientTablePanel.revalidate();
        clientTablePanel.repaint();

        return clientTablePanel;
    }
}
