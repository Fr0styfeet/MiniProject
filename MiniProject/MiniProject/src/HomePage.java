import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class HomePage {

    JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    HomePage window = new HomePage();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public HomePage() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 766, 401);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblUsername.setBounds(203, 90, 100, 30);
        frame.getContentPane().add(lblUsername);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblPassword.setBounds(203, 151, 100, 30);
        frame.getContentPane().add(lblPassword);

        usernameField = new JTextField();
        usernameField.setBounds(330, 90, 150, 30);
        frame.getContentPane().add(usernameField);

        passwordField = new JPasswordField();
        passwordField.setBounds(330, 151, 150, 30);
        frame.getContentPane().add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Tahoma", Font.PLAIN, 23));
        loginButton.setBounds(274, 221, 121, 46);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (isValidLogin(username, password)) {
                    openBooksPage();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid username or password.", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        frame.getContentPane().add(loginButton);

        JLabel lblBookVaultManagement = new JLabel("BOOK VAULT MANAGEMENT SYSTEM");
        lblBookVaultManagement.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblBookVaultManagement.setBounds(128, 26, 533, 37);
        frame.getContentPane().add(lblBookVaultManagement);
    }

    private boolean isValidLogin(String username, String password) {

        return "anisha".equals(username) && "123".equals(password);
    }

    private void openBooksPage() {
        frame.dispose(); 
        books.main(new String[0]); 
    }
}
