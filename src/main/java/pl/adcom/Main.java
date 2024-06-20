package pl.adcom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Pressure Measurement App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel userPanel = new JPanel();
        userPanel.setLayout(new GridLayout(6, 2));

        JLabel userIdLabel = new JLabel("User ID:");
        JTextField userIdField = new JTextField();
        JLabel firstNameLabel = new JLabel("First Name:");
        JTextField firstNameField = new JTextField();
        JLabel lastNameLabel = new JLabel("Last Name:");
        JTextField lastNameField = new JTextField();
        JLabel genderLabel = new JLabel("Gender:");
        JComboBox<Gender> genderComboBox = new JComboBox<>(Gender.values());
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();

        JButton addUserButton = new JButton("Add User");

        userPanel.add(userIdLabel);
        userPanel.add(userIdField);
        userPanel.add(firstNameLabel);
        userPanel.add(firstNameField);
        userPanel.add(lastNameLabel);
        userPanel.add(lastNameField);
        userPanel.add(genderLabel);
        userPanel.add(genderComboBox);
        userPanel.add(emailLabel);
        userPanel.add(emailField);
        userPanel.add(new JLabel());
        userPanel.add(addUserButton);

        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserService userService = new UserServiceImpl();
                User user = new User(
                        Long.parseLong(userIdField.getText()),
                        firstNameField.getText(),
                        lastNameField.getText(),
                        (Gender) genderComboBox.getSelectedItem(),
                        emailField.getText()
                );
                userService.addUser(user);
                JOptionPane.showMessageDialog(frame, "User added successfully!");
            }
        });

        tabbedPane.add("Users", userPanel);

        JPanel pressurePanel = new JPanel();
        pressurePanel.setLayout(new GridLayout(7, 2));

        JLabel pressureIdLabel = new JLabel("Pressure ID:");
        JTextField pressureIdField = new JTextField();
        JLabel upperPressureLabel = new JLabel("Upper Pressure:");
        JTextField upperPressureField = new JTextField();
        JLabel lowerPressureLabel = new JLabel("Lower Pressure:");
        JTextField lowerPressureField = new JTextField();
        JLabel pulseLabel = new JLabel("Pulse:");
        JTextField pulseField = new JTextField();
        JLabel startedMeassureLabel = new JLabel("Started Measure Time:");
        JTextField startedMeassureField = new JTextField();
        JLabel userIdPressureLabel = new JLabel("User ID:");
        JTextField userIdPressureField = new JTextField();

        JButton addPressureButton = new JButton("Add Pressure");

        pressurePanel.add(pressureIdLabel);
        pressurePanel.add(pressureIdField);
        pressurePanel.add(upperPressureLabel);
        pressurePanel.add(upperPressureField);
        pressurePanel.add(lowerPressureLabel);
        pressurePanel.add(lowerPressureField);
        pressurePanel.add(pulseLabel);
        pressurePanel.add(pulseField);
        pressurePanel.add(startedMeassureLabel);
        pressurePanel.add(startedMeassureField);
        pressurePanel.add(userIdPressureLabel);
        pressurePanel.add(userIdPressureField);
        pressurePanel.add(new JLabel());
        pressurePanel.add(addPressureButton);

        addPressureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PressureService pressureService = new PressureServiceImpl();
                Pressure pressure = new Pressure(
                        Long.parseLong(pressureIdField.getText()),
                        Integer.parseInt(upperPressureField.getText()),
                        Integer.parseInt(lowerPressureField.getText()),
                        Integer.parseInt(pulseField.getText()),
                        LocalTime.parse(startedMeassureField.getText()),
                        Long.parseLong(userIdPressureField.getText())
                );
                pressureService.addPressure(pressure);
                JOptionPane.showMessageDialog(frame, "Pressure added successfully!");
            }
        });

        tabbedPane.add("Pressures", pressurePanel);

        frame.add(tabbedPane);
        frame.setVisible(true);
    }
}