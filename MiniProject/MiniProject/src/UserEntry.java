import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.io.IOException;

public class UserEntry {

    private JFrame frame;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UserEntry window = new UserEntry();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * @wbp.parser.entryPoint
     */
    public UserEntry() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 1028, 652);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //JPanel for background
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                super.paintComponent(g);
                //  background image
                ImageIcon icon = new ImageIcon("C:\\Users\\Anisha\\Desktop\\Eclipsu\\MiniProject\\src\\bookimage.jpeg");
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        
        frame.getContentPane().add(backgroundPanel, BorderLayout.CENTER);
        
        JButton button = new JButton("LET'S GO");
        button.setBackground(new Color(255, 255, 255));
        button.setForeground(new Color(0, 0, 0));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openBooksPage();
            }
        });
        button.setFont(new Font("Tahoma", Font.PLAIN, 25));
        backgroundPanel.add(button);
        
        JLabel lblWelcomeToBookShop = new JLabel("BOOK-VAULT MANAGEMENT SYSTEM");
        lblWelcomeToBookShop.setForeground(new Color(0, 0, 0));
        lblWelcomeToBookShop.setFont(new Font("Tahoma", Font.BOLD, 36));
        lblWelcomeToBookShop.setHorizontalAlignment(JLabel.CENTER);
        frame.getContentPane().add(lblWelcomeToBookShop, BorderLayout.NORTH);
    }
    
    private void openBooksPage() {
        // "books" class and main method
        UserMain.main(new String[0]);
        // Close current frame
        frame.dispose();
    }
}
