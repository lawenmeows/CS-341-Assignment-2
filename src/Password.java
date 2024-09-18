import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A simple Password Strength Checker that checks for the largest block
 * of identical adjacent characters in a password.
 */
public class Password {

    private JFrame frame;
    private JPasswordField passwordField;
    private JTextArea outputArea;
    private JLabel errorLabel;

    /**
     * Main method to launch the app.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Password app = new Password();
            app.frame.setVisible(true);
        });
    }

    /**
     * Constructor to set up the GUI.
     */
    public Password() {
        initialize(); // Call initialize instead of setupGUI
    }

    /**
     * Initializes the contents of the frame.
     * Use WindowBuilder to modify GUI components after this method is generated.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Password Strength Checker");
        frame.setBounds(100, 100, 400, 250); // Set window size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout()); // Set layout manually

        // Input components
        JLabel inputLabel = new JLabel("Enter Password:");
        frame.getContentPane().add(inputLabel);

        passwordField = new JPasswordField(12);
        frame.getContentPane().add(passwordField);

        // Submit button
        JButton submitButton = new JButton("Submit");
        frame.getContentPane().add(submitButton);

        // Error label
        errorLabel = new JLabel("");
        errorLabel.setForeground(Color.RED);
        frame.getContentPane().add(errorLabel);

        // Output area
        outputArea = new JTextArea(5, 30);
        outputArea.setEditable(false);
        frame.getContentPane().add(new JScrollPane(outputArea));

        // Reset button
        JButton resetButton = new JButton("Reset");
        frame.getContentPane().add(resetButton);

        // Button actions
        submitButton.addActionListener(e -> processPassword());
        resetButton.addActionListener(e -> resetFields());
    }

    /**
     * Processes the password, validates it, and finds the largest block of adjacent characters.
     */
    private void processPassword() {
        String password = new String(passwordField.getPassword());
        errorLabel.setText("");  // Clear previous errors
        outputArea.setText("");  // Clear previous output

        // Validate password length
        if (password.length() < 8 || password.length() > 12) {
            errorLabel.setText("Error: Password must be between 8 and 12 characters.");
            return;
        }

        // Check for spaces
        if (password.contains(" ")) {
            errorLabel.setText("Error: Password cannot contain spaces.");
            return;
        }

        // Find the largest block
        int largestBlock = findLargestBlock(password);
        outputArea.append("The largest block is " + largestBlock + ".\n");

        // Give feedback based on block size
        if (largestBlock > 2) {
            outputArea.append("Consider reducing the block by " + (largestBlock - 2) + " characters.");
        } else {
            outputArea.append("This is a decent password.");
        }
    }

    /**
     * Finds the length of the largest block of adjacent identical characters.
     */
    private int findLargestBlock(String password) {
        int maxBlock = 1;
        int currentBlock = 1;

        for (int i = 1; i < password.length(); i++) {
            if (password.charAt(i) == password.charAt(i - 1)) {
                currentBlock++;
            } else {
                currentBlock = 1;
            }
            if (currentBlock > maxBlock) {
                maxBlock = currentBlock;
            }
        }
        return maxBlock;
    }

    /**
     * Resets the input and output fields.
     */
    private void resetFields() {
        passwordField.setText("");
        outputArea.setText("");
        errorLabel.setText("");
    }
}

