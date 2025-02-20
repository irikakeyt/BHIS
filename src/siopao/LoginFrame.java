package siopao;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class LoginFrame extends JFrame implements ActionListener {

    Container container = getContentPane();
    JLabel imageLabel = new JLabel();
    JLabel userLabel = new JLabel("Username");
    JLabel passwordLabel = new JLabel("Password");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    JButton resetButton = new JButton("RESET");
    JCheckBox showPassword = new JCheckBox("Show Password");
    ImageIcon imageIcon = new ImageIcon("C:\\Users\\ADMIN\\Pictures\\Saved Pictures\\banner.png");

    LoginFrame() {
        getContentPane().setBackground(new Color(255, 255, 255));
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
    	imageLabel.setIcon(imageIcon);
        imageLabel.setBounds(29, 39, 487, 173);
        userLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        userLabel.setBounds(87, 241, 100, 30);
        userTextField.setBounds(170, 236, 258, 44);
        passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        passwordLabel.setBounds(87, 319, 100, 30);
        passwordField.setBounds(170, 314, 258, 44);
        showPassword.setBackground(new Color(255, 255, 255));
        showPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
        showPassword.setBounds(164, 374, 150, 15);
        loginButton.setBackground(new Color(255, 255, 255));
        loginButton.setFont(new Font("Tahoma", Font.BOLD, 13));
        loginButton.setBounds(138, 531, 100, 30);
        resetButton.setBackground(new Color(255, 255, 255));
        resetButton.setFont(new Font("Tahoma", Font.BOLD, 13));
        resetButton.setBounds(317, 531, 100, 30);
       
        Border roundedBorder = new RoundedBorder(15);
        loginButton.setBorder(roundedBorder);
        resetButton.setBorder(roundedBorder);
        showPassword.setBorder(roundedBorder);
        userTextField.setBorder(roundedBorder);
        passwordField.setBorder(roundedBorder);
    }

    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(resetButton);
        container.add(imageLabel);
    }

    public void addActionEvent() {
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
    }

    private boolean checkDatabaseCredentials(String username, String password) {
        String url = "jdbc:mysql://localhost:3306/oop";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, "root", "");

            String query = "SELECT * FROM user_table WHERE username = ? AND password = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            return resultSet.next();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String userText = userTextField.getText();
            String pwdText = new String(passwordField.getPassword());

            if (checkDatabaseCredentials(userText, pwdText)) {
                Home homeFrame = new Home();
                homeFrame.setTitle("Home");
                homeFrame.setSize(1700, 900);
                homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                homeFrame.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }
        } else if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
        } else if (e.getSource() == resetButton) {
            userTextField.setText(""); 
            passwordField.setText(""); 
        }
    }

    public static void main(String[] args) {
        LoginFrame frame = new LoginFrame();
        frame.setTitle("BHIS");
        frame.setVisible(true);
        frame.setBounds(500, 50, 550, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }
    
    static class RoundedBorder implements Border {
        private int radius;

        public RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(Color.BLACK);
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(radius, radius, radius, radius);
        }

        public boolean isBorderOpaque() {
            return false;
        }
    }
}
