package siopao;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Update extends JFrame implements ActionListener {
	Container container = getContentPane();
	JLabel idLabel = new JLabel("Enter the ID of the record to update");
	JTextField id = new JTextField();
	JLabel firstNameLabel = new JLabel("First Name");
	JTextField firstName = new JTextField();
	JLabel lastNameLabel = new JLabel("Last Name");
	JTextField lastName = new JTextField();
	JLabel sPWDLabel = new JLabel("Senior/PWD");
	JTextField sPWD = new JTextField();
	JLabel disabilityLabel = new JLabel("Disability");
	JTextField disability = new JTextField();
	JLabel genderLabel = new JLabel("Gender");
	JTextField gender = new JTextField();
	JLabel ageLabel = new JLabel("Age");
	JTextField age = new JTextField();
	JLabel dateOfBirthLabel = new JLabel("Date of Birth");
	JTextField dateOfBirth = new JTextField();
	JLabel contactNumberLabel = new JLabel("Contact Number");
	JTextField contactNumber= new JTextField();
	JLabel weightLabel = new JLabel("Weight");
	JTextField weight = new JTextField();
	JLabel heightLabel = new JLabel("Height");
	JTextField  height = new JTextField();
	JLabel bPressureLabel = new JLabel("Blood Pressure");
	JTextField  bPressure = new JTextField();
	JLabel hRateLabel = new JLabel("Heart Rate");
	JTextField  hRate = new JTextField();
	JLabel rRateLabel = new JLabel("Respiratory Rate");
	JTextField  rRate = new JTextField();
	JLabel pastMCLabel = new JLabel("Past Medical History");
	JTextField  pastMC = new JTextField();
	JLabel currentMCLabel = new JLabel("Current Medical COndition");
	JTextField  currentMC = new JTextField();
	JLabel chronicLabel = new JLabel("Chronic Illness");
	JTextField  chronic = new JTextField();
	JLabel sHistoryLabel = new JLabel("Surgical History");
	JTextField  sHistory = new JTextField();
	JLabel pMedicationsLabel = new JLabel("Past Medications");
	JTextField  pMedications = new JTextField();
	JLabel cMedicationsLabel = new JLabel("Current Medications");
	JTextField  cMedications = new JTextField();
	JButton submitButton = new JButton("Update");
	JButton okayButton = new JButton("");
	JLabel lblNewLabel = new JLabel("UPDATE RECORD");
	JButton backButton = new JButton("Back");
	Connection connection;
	int idToUpdate;
	
	 Update() {
		    getContentPane().setBackground(new Color(255, 255, 255));
	        setLayoutManager();
	        setLocationAndSize();
	        addComponentsToContainer();
	        addActionEvent();
	        initializeDatabaseConnection();
	}

			public void setLayoutManager() {
	            container.setLayout(null);
	        }
	        
	        public void setLocationAndSize() {
	        	
	        	idLabel.setBounds(27, 99, 234, 31);
	        	id.setBackground(new Color(255, 255, 255));
	        	id.setBounds(27, 128, 173, 21);
	        	
	        	firstNameLabel.setBounds(25, 159, 92, 31);
	        	firstName.setBackground(new Color(255, 255, 255));
	        	firstName.setBounds(27, 186, 173, 21);
	        	
	        	lastNameLabel.setBounds(27, 225, 101, 31);
	        	lastName.setBackground(new Color(255, 255, 255));
	        	lastName.setBounds(27, 249, 173, 21);
	        	
	        	sPWDLabel.setBounds(25, 280, 145, 31);
	        	sPWD.setBackground(new Color(255, 255, 255));
	        	sPWD.setBounds(27, 307, 173, 21);
	        	
	        	disabilityLabel.setBounds(27, 338, 65, 31);
	        	disability.setBackground(new Color(255, 255, 255));
	        	disability.setBounds(27, 367, 173, 21);
	        	
	        	genderLabel.setBounds(27, 398, 56, 31);
	        	gender.setBackground(new Color(255, 255, 255));
	        	gender.setBounds(27, 420, 173, 21);
	        	
	        	ageLabel.setBounds(27, 451, 44, 31);
	        	age.setBackground(new Color(255, 255, 255));
	        	age.setBounds(27, 477, 173, 21);
	        	
	        	dateOfBirthLabel.setBounds(27, 508, 115, 31);
	        	dateOfBirth.setBackground(new Color(255, 255, 255));
	        	dateOfBirth.setBounds(27, 535, 173, 21);
	        	
	        	contactNumberLabel.setBounds(257, 159, 145, 31);
	        	contactNumber.setBackground(new Color(255, 255, 255));
	        	contactNumber.setBounds(257, 186, 173, 21);
	        	
	        	weightLabel.setBounds(257, 225, 115, 31);
	        	weight.setBackground(new Color(255, 255, 255));
	        	weight.setBounds(257, 249, 175, 21);
	        	
	        	heightLabel.setBounds(261, 280, 93, 31);
	        	height.setBackground(new Color(255, 255, 255));
	        	height.setBounds(257, 307, 175, 21);
	        	
	        	bPressureLabel.setBounds(257, 338, 162, 31);
	        	bPressure.setBackground(new Color(255, 255, 255));
	        	bPressure.setBounds(257, 364, 175, 21);
	        	
	        	hRateLabel.setBounds(257, 398, 129, 31);
	        	hRate.setBackground(new Color(255, 255, 255));
	        	hRate.setBounds(257, 421, 175, 21);
	        	
	        	rRateLabel.setBounds(257, 451, 162, 31);
	        	rRate.setBackground(new Color(255, 255, 255));
	        	rRate.setBounds(257, 477, 175, 21);
	        	
	        	pastMCLabel.setBounds(256, 508, 176, 31);
	        	pastMC.setBackground(new Color(255, 255, 255));
	        	pastMC.setBounds(257, 535, 173, 21);
	        	
	        	currentMCLabel.setBounds(483, 159, 198, 31);
	        	currentMC.setBackground(new Color(255, 255, 255));
	        	currentMC.setBounds(483, 186, 173, 21);
	        	
	        	chronicLabel.setBounds(483, 225, 101, 31);
	        	chronic.setBackground(new Color(255, 255, 255));
	        	chronic.setBounds(483, 249, 173, 21);
	        	
	        	sHistoryLabel.setBounds(483, 280, 198, 31);
	        	sHistory.setBackground(new Color(255, 255, 255));
	        	sHistory.setBounds(483, 307, 169, 21);
	        	
	        	pMedicationsLabel.setBounds(483, 338, 152, 31);
	        	pMedications.setBackground(new Color(255, 255, 255));
	        	pMedications.setBounds(483, 364, 169, 21);
	        	
	        	cMedicationsLabel.setBounds(483, 398, 176, 31);
	        	cMedications.setBackground(new Color(255, 255, 255));
	        	cMedications.setBounds(483, 421, 169, 21);
	        	
	        	submitButton.setBackground(new Color(179, 255, 179));
	        	submitButton.setBounds(576, 625, 80, 21);
	        	okayButton.setBackground(new Color(179, 255, 179));
	        	okayButton.setBounds(210, 135, 12, 9);
	        	
	        	lblNewLabel.setFont(new Font("Imprint MT Shadow", Font.BOLD, 36));
	        	lblNewLabel.setBounds(166, 10, 393, 84);
	        	
	        	backButton.setBackground(new Color(179, 255, 179));
	        	backButton.setBounds(27, 625, 65, 21);
	        	
	        }
	        
	        public void addComponentsToContainer() {
	        	container.add(idLabel);
	        	container.add(id);
	        	container.add(firstNameLabel);
	        	container.add(firstName);
	        	container.add(lastNameLabel);
	        	container.add(lastName);
	        	container.add(sPWDLabel);
	        	container.add(sPWD);
	        	container.add(disabilityLabel);
	        	container.add(disability);
	        	container.add(genderLabel);
	        	container.add(gender);
	        	container.add(ageLabel);
	        	container.add(age);      
	        	container.add(dateOfBirthLabel);
	        	container.add(dateOfBirth);
	        	container.add(contactNumberLabel);
	        	container.add(contactNumber);
	        	container.add(weightLabel);
	        	container.add(weight);
	        	container.add(heightLabel);
	        	container.add(height);
	        	container.add(bPressureLabel);
	        	container.add(bPressure);        	
	        	container.add(hRateLabel);
	        	container.add(hRate);
	        	container.add(rRateLabel);
	        	container.add(rRate);
	        	container.add(pastMCLabel);
	        	container.add(pastMC);
	        	container.add(currentMCLabel);
	        	container.add(currentMC);
	        	container.add(chronicLabel);
	        	container.add(chronic);
	        	container.add(sHistoryLabel);
	        	container.add(sHistory);
	        	container.add(pMedicationsLabel);
	        	container.add(pMedications);	        	
	        	container.add(cMedicationsLabel);
	        	container.add(cMedications);
	        	container.add(submitButton);
	        	container.add(okayButton);
	        	container.add(lblNewLabel);
	        	container.add(backButton);   	        	
	        }
	        
	        public void addActionEvent() {
	            submitButton.addActionListener(this);
	            okayButton.addActionListener(this);
	            backButton.addActionListener(this);

	            dateOfBirth.addFocusListener(new FocusAdapter() {
	                @Override
	                public void focusLost(FocusEvent e) {
	                    String dob = dateOfBirth.getText();
	                    int ageValue = calculateAge(dob);
	                    age.setText(Integer.toString(ageValue)); 
	                    updateAgeInDatabase(idToUpdate, ageValue);
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
	        
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            if (e.getSource() == okayButton) {
	                try {
	                    idToUpdate = Integer.parseInt(id.getText());
	                    String query = "SELECT * FROM patient WHERE patient_id = ?";
	                    PreparedStatement statement = connection.prepareStatement(query);
	                    statement.setInt(1, idToUpdate);

	                    ResultSet resultSet = statement.executeQuery();
	                    
	                    if (resultSet.next()) {
	                        firstName.setText(resultSet.getString("firstName"));
	                        lastName.setText(resultSet.getString("lastName"));
	                        sPWD.setText(resultSet.getString("SENIOR_OR_PWD"));
	                        disability.setText(resultSet.getString("disability"));
	                        gender.setText(resultSet.getString("gender"));
	                        dateOfBirth.setText(resultSet.getString("dateOfBirth"));
	                        contactNumber.setText(Integer.toString(resultSet.getInt("contactNumber")));
	                        weight.setText(resultSet.getString("weight"));
	                        height.setText(resultSet.getString("height"));
	                        bPressure.setText(resultSet.getString("bloodPressure"));
	                        hRate.setText(Integer.toString(resultSet.getInt("heartRate")));
	                        rRate.setText(Integer.toString(resultSet.getInt("respiratoryRate")));
	                        pastMC.setText(resultSet.getString("pastMedicalConditions"));
	                        currentMC.setText(resultSet.getString("currentMedicalConditions"));
	                        chronic.setText(resultSet.getString("chronicIllnesses"));
	                        sHistory.setText(resultSet.getString("surgicalHistory"));
	                        pMedications.setText(resultSet.getString("pastMedications"));
	                        cMedications.setText(resultSet.getString("currentMedications"));

	                        // Calculate and set the age based on the date of birth
	                        String dob = dateOfBirth.getText();
	                        int ageValue = calculateAge(dob);
	                        age.setText(Integer.toString(ageValue));
	                        
	                    } else {
	                        JOptionPane.showMessageDialog(this, "ID not found in the database", "Record Not Found", JOptionPane.ERROR_MESSAGE);
	                    }
	                    resultSet.close();
	                    statement.close();
	                } catch (NumberFormatException ex) {
	                    JOptionPane.showMessageDialog(this, "Invalid ID. Please enter a valid number.", "Invalid ID", JOptionPane.ERROR_MESSAGE);
	                } catch (SQLException ex) {
	                    ex.printStackTrace();
	                    JOptionPane.showMessageDialog(this, "Error loading data from the database", "Database Error", JOptionPane.ERROR_MESSAGE);
	                }
	                } else if (e.getSource() == backButton) {
	                	int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to back?", "Confirm", JOptionPane.YES_NO_OPTION);  
	                    if (confirm == JOptionPane.YES_OPTION) {
	                    dispose();
	                    Home frame = new Home();
	                    frame.setTitle("BHIS");
	                    frame.setSize(1700, 900);
	                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                    frame.setVisible(true);
	                    }
	            } else if (e.getSource() == submitButton) {
	            	 try {
	            	        int idToUpdate = Integer.parseInt(id.getText());
	            	        String updateQuery = "UPDATE patient SET firstName = ?, lastName = ?, SENIOR_OR_PWD = ?, disability = ?, gender = ?, " +
	            	            "age = ?, dateOfBirth = ?, contactNumber = ?, weight = ?, height = ?, bloodPressure = ?, heartRate = ?, " +
	            	            "respiratoryRate = ?, pastMedicalConditions = ?, currentMedicalConditions = ?, chronicIllnesses = ?, " +
	            	            "surgicalHistory = ?, pastMedications = ?, currentMedications = ? WHERE patient_id = ?";
	            	        
	            	        String dob = dateOfBirth.getText();
	            	        int ageValue = calculateAge(dob);

	            	        PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
	            	        updateStatement.setString(1, firstName.getText());
	            	        updateStatement.setString(2, lastName.getText());
	            	        updateStatement.setString(3, sPWD.getText());
	            	        updateStatement.setString(4, disability.getText());
	            	        updateStatement.setString(5, gender.getText());
	            	        updateStatement.setInt(6, Integer.parseInt(age.getText()));
	            	        updateStatement.setString(7, dateOfBirth.getText());
	            	        updateStatement.setInt(8, Integer.parseInt(contactNumber.getText()));
	            	        updateStatement.setString(9, weight.getText());
	            	        updateStatement.setString(10, height.getText());
	            	        updateStatement.setString(11, bPressure.getText());
	            	        updateStatement.setInt(12, Integer.parseInt(hRate.getText()));
	            	        updateStatement.setInt(13, Integer.parseInt(rRate.getText()));
	            	        updateStatement.setString(14, pastMC.getText());
	            	        updateStatement.setString(15, currentMC.getText());
	            	        updateStatement.setString(16, chronic.getText());
	            	        updateStatement.setString(17, sHistory.getText());
	            	        updateStatement.setString(18, pMedications.getText());
	            	        updateStatement.setString(19, cMedications.getText());
	            	        
	            	        updateStatement.setInt(20, idToUpdate); // Set the WHERE clause parameter

	            	        int rowsAffected = updateStatement.executeUpdate();

	            	        if (rowsAffected > 0) {
	            	            JOptionPane.showMessageDialog(this, "Record updated successfully", "Update Success", JOptionPane.INFORMATION_MESSAGE);
	            	            this.dispose();
	            	            Home homeFrame = new Home();
	        	                homeFrame.setTitle("Home");
	        	                homeFrame.setSize(1700, 900);
	        	                homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        	                homeFrame.setVisible(true);
	            	        } else {
	            	            JOptionPane.showMessageDialog(this, "ID not found in the database", "Record Not Found", JOptionPane.ERROR_MESSAGE);
	            	        }
	            	        updateStatement.close();
	            	    } catch (NumberFormatException ex) {
	            	        JOptionPane.showMessageDialog(this, "Invalid ID or input. Please enter valid values.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
	            	    } catch (SQLException ex) {
	            	        ex.printStackTrace();
	            	        JOptionPane.showMessageDialog(this, "Error updating data in the database", "Database Error", JOptionPane.ERROR_MESSAGE);
	            	    }
	            	 if (e.getSource() == dateOfBirth) {
	            		    String dob = dateOfBirth.getText();
	            		    int ageValue = calculateAge(dob);
	            		    age.setText(Integer.toString(ageValue));
	            		}
	            }
	        }
	        
	        private int calculateAge(String birthdate) {
	            String[] parts = birthdate.split("-");
	            int birthYear = Integer.parseInt(parts[0]);
	            int birthMonth = Integer.parseInt(parts[1]);
	            int birthDay = Integer.parseInt(parts[2]);

	            java.util.Date currentDate = new java.util.Date();
	            int currentYear = currentDate.getYear() + 1900;
	            int currentMonth = currentDate.getMonth() + 1;
	            int currentDay = currentDate.getDate();
	            int age = currentYear - birthYear;
	            if (currentMonth < birthMonth || (currentMonth == birthMonth && currentDay < birthDay)) {
	                age--;
	            }
	            return age;
	        }
	        private void updateAgeInDatabase(int patientId, int newAge) {
	            try {
	                String updateAgeQuery = "UPDATE patient SET age = ? WHERE patient_id = ?";
	                PreparedStatement updateAgeStatement = connection.prepareStatement(updateAgeQuery);
	                updateAgeStatement.setInt(1, newAge);
	                updateAgeStatement.setInt(2, patientId);
	                int rowsAffected = updateAgeStatement.executeUpdate();

	                if (rowsAffected > 0) {
	                    // Database update was successful
	                } else {
	                    // Handle the case where the update didn't work (e.g., patient_id not found).
	                }

	                updateAgeStatement.close();
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	        }
	        public static void main(String[] args) {
	        	Update frame = new Update();
	        	frame.setTitle("BHIS");
	        	frame.setBounds(400, 50, 700, 700);
	        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        	frame.setVisible(true);
	        	frame.setResizable(false);
	    }
}


