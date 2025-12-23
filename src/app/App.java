package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import structure.UniSystem;
import model.Student;
import services.*;

public class App extends JFrame {

    // GUI components
    private JButton addStudentButton;
    private JPanel panel;
    private JTextField addStuField;

    // Reference to your system
    private UniSystem system;

    public App() {
        // Initialize system singleton
        system = UniSystem.getInstance();

        // Window setup
        setTitle("Test Student GUI");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel
        panel = new JPanel();
        panel.setLayout(new FlowLayout());

        // Text field
        addStuField = new JTextField(15);

        // Button
        addStudentButton = new JButton("Add Student");

        // Add components to panel
        panel.add(new JLabel("Student Name:"));
        panel.add(addStuField);
        panel.add(addStudentButton);

        // Add panel to frame
        add(panel);

        // Button logic
        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = addStuField.getText().trim();
                if (!name.isEmpty()) {
                    Student s = new Student(name, 18); // Age is just for testing
                    system.getStudentManager().addStudent(s);
                    JOptionPane.showMessageDialog(App.this, "Added: " + name);
                    addStuField.setText(""); // clear field
                } else {
                    JOptionPane.showMessageDialog(App.this, "Please enter a name!");
                }
            }
        });
    }

    public static void main(String[] args) {
        // Launch GUI
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                App app = new App();
                app.setVisible(true);
            }
        });
    }
}
