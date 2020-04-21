package presentation.windows;

import model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * this class is used for authentification, given that a normal user cannot restock the products
 */
public class PasswordWindow {
    JFrame frame = new JFrame("Passwird Window");

    JLabel passwordLabel = new JLabel("Password :");
    JTextField passwordTextfield = new JTextField();
    JButton submitPasswordButton = new JButton("Submit password");

    public PasswordWindow(){
        prepareGUI();
        addComponents();
        addActionEvents();
    }

    private void prepareGUI(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 200);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.darkGray);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addComponents(){
        passwordLabel.setBounds(20,70,80,30);
        passwordLabel.setForeground(Color.white);
        frame.add(passwordLabel);

        passwordTextfield.setBounds(150,70,100,30);
        passwordTextfield.setFont(new Font("Arial", Font.BOLD, 15));
        passwordTextfield.setHorizontalAlignment(SwingConstants.LEFT);
        frame.add(passwordTextfield);

        submitPasswordButton.setBounds(300, 70, 100, 30);
        submitPasswordButton.setFont(new Font("Arial", Font.BOLD, 15));
        submitPasswordButton.setBackground(Color.LIGHT_GRAY);
        frame.add(submitPasswordButton);

        frame.setVisible(true);

    }

    private void addActionEvents(){
        submitPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              String password = passwordTextfield.getText();
              if(password != null){
                  if(password.equals("cezara") == true){
                      ProductWindow productWindow = new ProductWindow();
                  }
                  else {
                      JOptionPane.showMessageDialog(frame, "Wrong password, try again", "Error", JOptionPane.ERROR_MESSAGE);
                  }
              }
            }



        });
    }

}
