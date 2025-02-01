package calculator;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculator extends JFrame {

    private final JTextField display;
    private double firstNumber = 0;
    private String operation = "";
    private boolean isNewNumber = true;

    public Calculator() {
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        // Display
        display = new JTextField("0");
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setBackground(new Color(230, 230, 230));
        display.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout(5, 5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(new Color(240, 240, 240));

        // Display to main panel
        mainPanel.add(display, BorderLayout.NORTH);

        // Buttons panel
        JPanel buttonsPanel = new JPanel(new GridLayout(5, 4, 5, 5));
        buttonsPanel.setBackground(new Color(240, 240, 240));

        // Create and add buttons
        String[] buttonLabels = {
            "C", "±", "%", "÷",
            "7", "8", "9", "x",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", ".", "="
        };

        for (String label : buttonLabels) {
            JButton button = createButton(label);
            if (label.equals("0")) {
                button.setPreferredSize(new Dimension(button.getPreferredSize().width * 2, 
                    button.getPreferredSize().height));
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridwidth = 2;
                buttonsPanel.add(button, gbc);
            } else {
                buttonsPanel.add(button);
            }
        }

        mainPanel.add(buttonsPanel, BorderLayout.CENTER);
        add(mainPanel);

        pack();
        setLocationRelativeTo(null);
    }

    public Calculator(JTextField display) throws HeadlessException {
        this.display = display;
    }

    public Calculator(JTextField display, String title) throws HeadlessException {
        super(title);
        this.display = display;
    }

    private JButton createButton(String label) {
        JButton button = new JButton(label);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setFocusPainted(false);
        
        // Style on button type
        if ("0123456789.".contains(label)) {
            button.setBackground(new Color(252, 252, 252));
        } else if ("=".equals(label)) {
            button.setBackground(new Color(98, 102, 244));
            button.setForeground(Color.WHITE);
        } else {
            button.setBackground(new Color(230, 230, 230));
        }

        button.addActionListener(e -> handleButtonClick(label));
        
        // Hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(button.getBackground().darker());
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if ("0123456789.".contains(label)) {
                    button.setBackground(new Color(252, 252, 252));
                } else if ("=".equals(label)) {
                    button.setBackground(new Color(98, 102, 244));
                } else {
                    button.setBackground(new Color(230, 230, 230));
                }
            }
        });

        return button;
    }

    private void handleButtonClick(String label) {
        switch (label) {
            case "C" -> {
                display.setText("0");
                firstNumber = 0;
                operation = "";
                isNewNumber = true;
            }
                
            case "±" -> {
                double value = Double.parseDouble(display.getText());
                display.setText(String.valueOf(-value));
            }
                
            case "%" -> {
                double value = Double.parseDouble(display.getText()) / 100;
                display.setText(String.valueOf(value));
            }
                
            case "=" -> {
                calculateResult();
                isNewNumber = true; // Reset state after calculation
            }
                
            case "+", "-", "x", "÷" -> {
                handleOperator(label);
                isNewNumber = true; // Reset state after operator is pressed
            }
            case "." -> {
                if (!display.getText().contains(".")) {
                    display.setText(display.getText() + ".");
                }
            }
            default -> {
                if (isNewNumber) {
                    display.setText(label);
                    isNewNumber = false;
                } else {
                    display.setText(display.getText() + label);
                }
            }
        }
    }
    
    private void handleOperator(String label) {
        firstNumber = Double.parseDouble(display.getText());
        operation = label;
        isNewNumber = true;
    }

    private void calculateResult() {
        if (operation.isEmpty()) return;
        
        double secondNumber = Double.parseDouble(display.getText());
        double result = 0;

        if (operation.equals("÷") && secondNumber == 0) {
            display.setText("Error");
            return;
        }

        result = switch (operation) {
            case "÷" -> firstNumber / secondNumber;
            case "x" -> firstNumber * secondNumber;
            case "-" -> firstNumber - secondNumber;
            case "+" -> firstNumber + secondNumber;
            default -> throw new IllegalStateException("Unexpected value: " + operation);
        };

        // Format result to avoid unnecessary decimal places
        String resultStr = String.valueOf(result);
        if (resultStr.endsWith(".0")) {
            resultStr = resultStr.substring(0, resultStr.length() - 2);
        }
        
        display.setText(resultStr);
        firstNumber = result; // Store the result for further operations
        operation = "";
        isNewNumber = true;
    }

    public static void main(String[] args) {
        // Set system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.err.println("Error setting look and feel: " + e.getMessage());
        }

        SwingUtilities.invokeLater(() -> {
            Calculator calculator = new Calculator();
            calculator.setVisible(true);
        });
    }

    public JTextField getDisplay() {
        return display;
    }
}