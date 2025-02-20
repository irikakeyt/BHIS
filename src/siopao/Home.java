package siopao;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Home extends JFrame implements ActionListener {

    Container container = getContentPane();
    JLabel imageLabel = new JLabel();
    ImageIcon imageIcon = new ImageIcon("C:\\Users\\ADMIN\\Pictures\\Saved Pictures\\fin.png");
    JTextField searchTextField = new JTextField();
    JButton searchButton = new JButton("Search");
    JButton logoutButton = new JButton("Logout");
    JButton updateButton = new JButton("Update");
    JButton deleteButton = new JButton("Delete");
    JButton addButton = new JButton("Add");
    JButton refreshButton = new JButton("Refresh");
    JTable dataTable;
    DefaultTableModel tableModel;
    Connection connection;

    Home() {
        getContentPane().setBackground(new Color(255, 255, 255));
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        initializeDatabaseConnection();
        initializeTable(); 
        populateTable();
    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
    	imageLabel.setIcon(imageIcon);
        imageLabel.setBounds(23, 0, 484, 82);
    	searchTextField.setBackground(new Color(255, 255, 255));
    	searchTextField.setBounds(562, 23, 382, 39);
    	searchButton = new JButton("Search");
    	searchButton.setBounds(1003, 32, 67, 21);
    	searchButton.setBackground(new Color(179, 255, 179));
    	logoutButton = new JButton("Logout");
    	logoutButton.setBounds(1400, 718, 75, 33);
    	logoutButton.setBackground(new Color(179, 255, 179));
    	updateButton = new JButton("Update");
    	updateButton.setBounds(213, 718, 82, 33);
    	updateButton.setBackground(new Color(179, 255, 179));
    	deleteButton = new JButton("Delete");
    	deleteButton.setBounds(351, 718, 82, 33);
    	deleteButton.setBackground(new Color(179, 255, 179));
    	addButton = new JButton("Add");
    	addButton.setBounds(73, 718, 75, 33);
    	addButton.setBackground(new Color(179, 255, 179));
    	refreshButton = new JButton("Refresh");
    	refreshButton.setBounds(1099, 32, 75, 21);
    	refreshButton.setBackground(new Color(179, 255, 179));
        dataTable = new JTable();
 
        JScrollPane scrollPane = new JScrollPane(dataTable);
        scrollPane.setBounds(10, 82, 1520, 580);
        scrollPane.getVerticalScrollBar().setBackground(new Color(179, 255, 179));
        container.add(scrollPane);
        
        Border roundedBorder = new RoundedBorder(10);
        searchTextField.setBorder(roundedBorder);
        searchButton.setBorder(roundedBorder);
        logoutButton.setBorder(roundedBorder);
        updateButton.setBorder(roundedBorder);
        deleteButton.setBorder(roundedBorder);
        addButton.setBorder(roundedBorder);
        refreshButton.setBorder(roundedBorder);
    }

    public void addComponentsToContainer() {
        container.add(searchTextField);
        container.add(searchButton);
        container.add(logoutButton);
        container.add(updateButton);
        container.add(deleteButton);
        container.add(addButton);
        container.add(refreshButton); 
        container.add(imageLabel);
    }

    public void addActionEvent() {
        searchButton.addActionListener(this);
        logoutButton.addActionListener(this);
        updateButton.addActionListener(this);
        deleteButton.addActionListener(this);
        addButton.addActionListener(this);

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	tableModel.setRowCount(0);
                populateTable();
                searchTextField.setText("");
            }
        });
    }


    private void initializeDatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/oop", "root", "");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error connecting to the database", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initializeTable() {
        tableModel = new DefaultTableModel();
        dataTable.setBackground(Color.WHITE);
        
        JTableHeader tableHeader = dataTable.getTableHeader();
        tableHeader.setBackground(new Color(144, 238, 144)); // Light Green
        dataTable.setForeground(Color.BLACK); // Text color
        
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) dataTable.getTableHeader().getDefaultRenderer();
        Font headerFont = new Font("Tahoma", Font.BOLD, 18); // Change the font settings as needed
        headerRenderer.setFont(headerFont);
        
        int headerHeight = 30; //header height
        dataTable.getTableHeader().setPreferredSize(new Dimension(0, headerHeight));
        
        dataTable.setModel(tableModel);
        tableModel.addColumn("ID");//0
        tableModel.addColumn("First Name");//1
        tableModel.addColumn("Last Name");//2
        tableModel.addColumn("Senior/PWD");//3
        tableModel.addColumn("Disability");//4
        tableModel.addColumn("Gender");//5
        tableModel.addColumn("Age");//6
        tableModel.addColumn("Date of Birth");//7
        tableModel.addColumn("Contact No.");//8
        tableModel.addColumn("Weight");//9
        tableModel.addColumn("Height");//10
        tableModel.addColumn("Blood Pressure");//11
        tableModel.addColumn("Heart Rate");//12
        tableModel.addColumn("Respiratory Rate");//13
        tableModel.addColumn("Past Medical History");//14
        tableModel.addColumn("Current Medical Conditions");//15
        tableModel.addColumn("Chronic Illness");//16
        tableModel.addColumn("Surgical History");//17
        tableModel.addColumn("Past Medications");//18
        tableModel.addColumn("Current Medications");//19
        
        setColumnWidth(0, 20);
        setColumnWidth(1, 55);
        setColumnWidth(2, 55);
        setColumnWidth(3, 60);
        setColumnWidth(4, 50);
        setColumnWidth(5, 40);
        setColumnWidth(6, 20);
        setColumnWidth(8, 60);
        setColumnWidth(9, 40);
        setColumnWidth(10, 40);
        setColumnWidth(11, 80);
        setColumnWidth(12, 50);  
        setColumnWidth(13, 90);
        setColumnWidth(14, 105);
        setColumnWidth(15, 120);
        setColumnWidth(16, 70);
        setColumnWidth(17, 80);
        setColumnWidth(19, 100);
    }

    private void setColumnWidth(int columnIndex, int width) {
        TableColumn column = dataTable.getColumnModel().getColumn(columnIndex);
        column.setPreferredWidth(width);
    }
        
    private void populateTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM patient";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("patient_id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String sOrPwd = resultSet.getString("SENIOR_OR_PWD");
                String disability = resultSet.getString("disability");
                String gender = resultSet.getString("gender");
                int age = resultSet.getInt("age");
                String dob = resultSet.getString("dateOfBirth");
                int contactNumber = resultSet.getInt("contactNumber");
                String weight = resultSet.getString("weight");
                String height = resultSet.getString("height");
                String bPressure = resultSet.getString("bloodPressure");
                int hRate = resultSet.getInt("heartRate");
                int rRate = resultSet.getInt("respiratoryRate");
                String pmConditions = resultSet.getString("pastMedicalConditions");
                String cmConditions = resultSet.getString("currentMedicalConditions");
                String cIllness = resultSet.getString("chronicIllnesses");
                String sHistory = resultSet.getString("surgicalHistory");
                String pMedications = resultSet.getString("pastMedications");
                String cMedications = resultSet.getString("currentMedications");
                tableModel.addRow(new Object[]{id, firstName, lastName, sOrPwd, disability, gender, age, dob, contactNumber, weight, height, bPressure, hRate, rRate, pmConditions,cmConditions, cIllness, sHistory, pMedications, cMedications,});
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            String searchTerm = searchTextField.getText().trim();
            String sql = "SELECT * FROM patient WHERE `patient_id` = ? OR `firstName` = ? OR `lastName` = ? OR `SENIOR_OR_PWD` = ?" ;

            tableModel.setRowCount(0);

            try {
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, searchTerm);
                statement.setString(2, searchTerm);
                statement.setString(3, searchTerm);
                statement.setString(4, searchTerm);

                ResultSet resultSet = statement.executeQuery();
                
                    while (resultSet.next()) {         
                	int id = resultSet.getInt("patient_id");
                    String firstName = resultSet.getString("firstName");
                    String lastName = resultSet.getString("lastName");
                    String sOrPwd = resultSet.getString("SENIOR_OR_PWD");
                    String disability = resultSet.getString("disability");
                    String gender = resultSet.getString("gender");
                    int age = resultSet.getInt("age");
                    String dob = resultSet.getString("dateOfBirth");
                    int contactNumber = resultSet.getInt("contactNumber");
                    String weight = resultSet.getString("weight");
                    String height = resultSet.getString("height");
                    String bPressure = resultSet.getString("bloodPressure");
                    int hRate = resultSet.getInt("heartRate");
                    int rRate = resultSet.getInt("respiratoryRate");
                    String pmConditions = resultSet.getString("pastMedicalConditions");
                    String  cmConditions = resultSet.getString("currentMedicalConditions");
                    String cIllness = resultSet.getString("chronicIllnesses");
                    String sHistory = resultSet.getString("surgicalHistory");
                    String pMedications = resultSet.getString("pastMedications");
                    String cMedications = resultSet.getString("currentMedications");
                    
                    tableModel.addRow(new Object[]{id, firstName, lastName, sOrPwd, disability, gender, age, dob, contactNumber, weight, height, bPressure, hRate, rRate, pmConditions, cmConditions, cIllness, sHistory, pMedications,  cMedications});
                }
                resultSet.close();
                statement.close();
                }catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == updateButton) {
        	dispose();
            Update frame = new Update();
            frame.setBounds(400, 50, 700, 700);
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	frame.setVisible(true);
        	frame.setResizable(false);
        } else if (e.getSource() == deleteButton) {
            deleteRecord();
        } else if (e.getSource() == addButton) {
        	dispose();
            Add frame = new Add();
            frame.setTitle("BHIS");
            frame.setBounds(400, 50, 700, 700);
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	frame.setVisible(true);
        	frame.setResizable(false);          
            
        } else if (e.getSource() == refreshButton) {
            tableModel.setRowCount(0);
            populateTable(); // Refresh the table
            searchTextField.setText("");
        } else if (e.getSource() == logoutButton) {
        	int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to log out?", "Confirm Logout", JOptionPane.YES_NO_OPTION);  
            if (confirm == JOptionPane.YES_OPTION) {
            dispose();
            LoginFrame frame = new LoginFrame();
            frame.setTitle("BHIS");
            frame.setSize(550, 700);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            }
        }
    }
    
    private void deleteRecord() {
        String idToDelete = JOptionPane.showInputDialog("Enter the ID of the record to delete:");

        if (idToDelete != null && !idToDelete.isEmpty()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this record?", "Delete Record", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                String sql = "DELETE FROM patient WHERE `patient_id`=?";

                try {
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, idToDelete);

                    int rowsDeleted = statement.executeUpdate();

                    if (rowsDeleted > 0) {
                        JOptionPane.showMessageDialog(this, "Record deleted successfully.");
                        // Refresh the table after deleting
                        populateTable();
                    } else {
                        JOptionPane.showMessageDialog(this, "Record not found or delete failed.");
                    }

                    statement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please provide a valid ID to delete.");
        }
        
        tableModel.setRowCount(0);
        populateTable();
    }  

    public static void main(String[] args) {
        Home frame = new Home();
        frame.setTitle("BHIS");
        frame.setSize(1700, 900); // Adjust the window size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
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

        @Override
        public boolean isBorderOpaque() {
            return false;
        }
    }
}


