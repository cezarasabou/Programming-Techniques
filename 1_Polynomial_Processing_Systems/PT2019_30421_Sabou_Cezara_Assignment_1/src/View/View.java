package View;

import controller.Controller;
import exception.InvalidInputException;
import exception.PolinomialOperationException;

import javax.swing.*;
import java.awt.*;

public class View {


    private JFrame frame = new JFrame("Polynomial");
    private JLabel label = new JLabel();
    private JLabel p1Label = new JLabel();
    private JLabel p2Label = new JLabel();
    private JLabel resLabel = new JLabel();
    private JTextField firstPolynomial = new JFormattedTextField();
    private JTextField secondPolynomial = new JFormattedTextField();
    private JTextField resultPolynomial = new JFormattedTextField();
    private JButton addButton = new JButton("+");
    private JButton subtractButton = new JButton("-");
    private JButton multiplyButton = new JButton("*");
    private JButton divideButton = new JButton("/");
    private JButton integrateButton = new JButton("\u222B");
    private JButton differentiateButton = new JButton("'");
    private JButton clearButton = new JButton("clear");


    private final Controller controller;


    public View(Controller controller) {
        prepareGUI();
        addComponents();
        addActionEvents();
        this.controller = controller;
    }

    public void prepareGUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 490);
        frame.getContentPane().setLayout(null);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void addComponents() {

        label.setBounds(50, 0, 450, 50);
        label.setForeground(Color.white);
        label.setText("Please enter the polynomials in the first two text boxes(e.g: 5x^2+4x^1+7x^0)");
        frame.add(label);

        p1Label.setBounds(10,40,100,50);
        p1Label.setForeground(Color.white);
        p1Label.setText("first one:");
        frame.add(p1Label);

        firstPolynomial.setBounds(100, 40, 270, 40);
        firstPolynomial.setFont(new Font("Arial", Font.BOLD, 16));
        firstPolynomial.setHorizontalAlignment(SwingConstants.RIGHT);
        frame.add(firstPolynomial);

        p2Label.setBounds(10,80,100,50);
        p2Label.setForeground(Color.white);
        p2Label.setText("second one:");
        frame.add(p2Label);

        secondPolynomial.setBounds(100, 80, 270, 40);
        secondPolynomial.setFont(new Font("Arial", Font.BOLD, 16));
        secondPolynomial.setHorizontalAlignment(SwingConstants.RIGHT);
        frame.add(secondPolynomial);

        resLabel.setBounds(10,120,100,50);
        resLabel.setForeground(Color.white);
        resLabel.setText("result:");
        frame.add(resLabel);

        resultPolynomial.setBounds(100, 120, 270, 40);
        resultPolynomial.setFont(new Font("Arial", Font.BOLD, 16));
        resultPolynomial.setHorizontalAlignment(SwingConstants.RIGHT);
        frame.add(resultPolynomial);

        addButton.setBounds(100, 170, 100, 40);
        addButton.setFont(new Font("Arial", Font.BOLD, 20));
        addButton.setBackground(new Color(239, 188, 2));
        frame.add(addButton);

        subtractButton.setBounds(270, 170, 100, 40);
        subtractButton.setFont(new Font("Arial", Font.BOLD, 20));
        subtractButton.setBackground(new Color(239, 188, 2));
        frame.add(subtractButton);

        multiplyButton.setBounds(100, 230, 100, 40);
        multiplyButton.setFont(new Font("Arial", Font.BOLD, 20));
        multiplyButton.setBackground(new Color(239, 188, 2));
        frame.add(multiplyButton);

        divideButton.setBounds(270, 230, 100, 40);
        divideButton.setFont(new Font("Arial", Font.BOLD, 20));
        divideButton.setBackground(new Color(239, 188, 2));
        frame.add(divideButton);

        integrateButton.setBounds(100, 290, 100, 40);
        integrateButton.setFont(new Font("Arial", Font.BOLD, 20));
        integrateButton.setBackground(new Color(239, 188, 2));
        frame.add(integrateButton);

        differentiateButton.setBounds(270, 290, 100, 40);
        differentiateButton.setFont(new Font("Arial", Font.BOLD, 20));
        differentiateButton.setBackground(new Color(239, 188, 2));
        frame.add(differentiateButton);

        clearButton.setBounds(175, 390, 130, 40);
        clearButton.setFont(new Font("Arial", Font.BOLD, 20));
        clearButton.setBackground(Color.RED);
        frame.add(clearButton);
    }

    void addActionEvents() {
        addButton.addActionListener(e -> {
            System.out.println("The add button was pressed");
            // call a function from controller that knows how to perform the operation
           try {
               String result = controller.add(firstPolynomial.getText(), secondPolynomial.getText());
               resultPolynomial.setText(result);
           }
           catch (InvalidInputException error) {
                JOptionPane.showMessageDialog(frame, error.getMessage());
           }
        });
        subtractButton.addActionListener(e -> {
            System.out.println("The subtract button was pressed");
            // call a function from controller that knows how to perform the operation
            try {
                String result = controller.subtract(firstPolynomial.getText(), secondPolynomial.getText());
                resultPolynomial.setText(result);
            }
            catch (InvalidInputException error) {
                JOptionPane.showMessageDialog(frame, error.getMessage());
            }
        });

        multiplyButton.addActionListener(e -> {
            System.out.println("The multiply button was pressed");
            // call a function from controller that knows how to perform the operation
            try {
                String result = controller.multiply(firstPolynomial.getText(), secondPolynomial.getText());
                resultPolynomial.setText(result);
            }
            catch (InvalidInputException error) {
                JOptionPane.showMessageDialog(frame, error.getMessage());
            }
        });

        divideButton.addActionListener(e -> {
            System.out.println("The divide button was pressed");
            // call a function from controller that knows how to perform the operation
            try {
                String result3 = controller.divide(firstPolynomial.getText(), secondPolynomial.getText());
                resultPolynomial.setText(result3);
            }
            catch (PolinomialOperationException operationException) {
                JOptionPane.showMessageDialog(frame,operationException.getMessage());
            }
            catch (InvalidInputException inputError) {
                JOptionPane.showMessageDialog(frame,inputError.getMessage());

            }

        });

        integrateButton.addActionListener(e -> {
            System.out.println("The integrate button was pressed");
            // call a function from controller that knows how to perform the operation
            try {
                String result4 = controller.integrate(firstPolynomial.getText());
                resultPolynomial.setText(result4);
            }
            catch (InvalidInputException error){
                JOptionPane.showMessageDialog(frame,error.getMessage());
            }
        });

        differentiateButton.addActionListener(e -> {
            System.out.println("The differentiate button was pressed");
            // call a function from controller that knows how to perform the operation
            try {
                String result4 = controller.differentiate(firstPolynomial.getText());
                resultPolynomial.setText(result4);
            }
            catch (InvalidInputException error){
                JOptionPane.showMessageDialog(frame,error.getMessage());
            }
        });

        clearButton.addActionListener(e -> {
            System.out.println("The clear button was pressed");
            // call a function from controller that knows how to perform the operation
            firstPolynomial.setText("");
            secondPolynomial.setText("");
            resultPolynomial.setText("");
        });




    }
}
