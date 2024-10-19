import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorUI extends JFrame {

    private JTextField displayField;
    private double firstNumber = 0;
    private double secondNumber = 0;
    private String operator = "";
    
    public CalculatorUI() {
        // Frame settings
        setTitle("Calculator");
        setSize(400, 600);  // Increased size for better button placement
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));  // Add padding
        setResizable(false);  // Prevent resizing
        
        // Create display field
        displayField = new JTextField();
        displayField.setEditable(false);
        displayField.setFont(new Font("Arial", Font.BOLD, 32));  // Larger font for better readability
        displayField.setHorizontalAlignment(SwingConstants.RIGHT);  // Align text to the right
        displayField.setPreferredSize(new Dimension(400, 100));  // Make display larger
        displayField.setBackground(Color.WHITE);  // Set a background color for clarity
        add(displayField, BorderLayout.NORTH);

        // Create buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 10, 10));  // More rows for additional features, add spacing between buttons
        
        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C"  // Add a Clear button for user convenience
        };

        // Customize buttons
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.BOLD, 24));
            button.setFocusPainted(false);  // Remove focus border for a cleaner look
            button.setBackground(new Color(220, 220, 220));  // Set a light color for buttons
            button.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));  // Add a border to buttons
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }

        // Add padding around button panel
        JPanel paddedPanel = new JPanel(new BorderLayout());
        paddedPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));  // Padding around buttons
        paddedPanel.add(buttonPanel, BorderLayout.CENTER);
        
        add(paddedPanel, BorderLayout.CENTER);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            
            if (command.charAt(0) >= '0' && command.charAt(0) <= '9' || command.equals(".")) {
                displayField.setText(displayField.getText() + command);
            } else if (command.equals("/")) {
                firstNumber = Double.parseDouble(displayField.getText());
                operator = "/";
                displayField.setText("");
            } else if (command.equals("*")) {
                firstNumber = Double.parseDouble(displayField.getText());
                operator = "*";
                displayField.setText("");
            } else if (command.equals("-")) {
                firstNumber = Double.parseDouble(displayField.getText());
                operator = "-";
                displayField.setText("");
            } else if (command.equals("+")) {
                firstNumber = Double.parseDouble(displayField.getText());
                operator = "+";
                displayField.setText("");
            } else if (command.equals("=")) {
                secondNumber = Double.parseDouble(displayField.getText());
                
                switch (operator) {
                    case "/":
                        displayField.setText(String.valueOf(firstNumber / secondNumber));
                        break;
                    case "*":
                        displayField.setText(String.valueOf(firstNumber * secondNumber));
                        break;
                    case "-":
                        displayField.setText(String.valueOf(firstNumber - secondNumber));
                        break;
                    case "+":
                        displayField.setText(String.valueOf(firstNumber + secondNumber));
                        break;
                }
            } else if (command.equals("C")) {
                // Clear button functionality
                displayField.setText("");
                firstNumber = 0;
                secondNumber = 0;
                operator = "";
            }
        }
    }
    
    public static void main(String[] args) {
        // Run the Calculator UI
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CalculatorUI().setVisible(true);
            }
        });
    }
}
