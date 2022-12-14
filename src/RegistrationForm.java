import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class RegistrationForm extends JDialog{
    private JTextField tfName;
    private JTextField tfEmail;
    private JTextField tfPhone;
    private JTextField tfAdress;
    private JPasswordField pfPassword;
    private JPasswordField pfConfirmPassword;
    private JButton btnRegister;
    private JButton btnCancel;
    private JPanel registerPanel;
    private JButton btnLoginHandling;

    public RegistrationForm(JFrame parent){
        super(parent);
        setTitle("Create a new account");
        setContentPane(registerPanel);
        setMinimumSize(new Dimension(620, 660));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                registerUser();
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });

        btnLoginHandling.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
                LoginForm myLoginForm = new LoginForm(null);
            }

        });
        setVisible(true);
    }

    private void registerUser() {
        String name = tfName.getText();
        String email = tfEmail.getText();
        String phone = tfPhone.getText();
        String adress = tfAdress.getText();
        String password = String.valueOf(pfPassword.getPassword());
        String confirmPassword = String.valueOf(pfConfirmPassword.getPassword());

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || adress.isEmpty() || password.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Please enter all fields",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!password.equals(confirmPassword)){
            JOptionPane.showMessageDialog(this,
                    "Confirm Password does not match",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        user = addUserToDatabase(name, email, phone, adress, password);
        if (user != null){
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Failed to register new user",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    public User user;
    private User addUserToDatabase(String name, String email, String phone, String adress, String password){
        User user = null;
        final String DB_URL = "jdbc:mysql://localhost/newdatabase";
        final String USERNAME = "root";
        final String PASSWORD = "root";

        try{
            Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            //Connected

            Statement stmt = connection.createStatement();
            String sql = "INSERT INTO users (name, email, phone, adress) " +
                    "VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, adress);
            //preparedStatement.setString(5, password);

            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0){
                user = new User();
                user.name = name;
                user.email = email;
                user.phone = phone;
                user.adress = adress;
                user.password = password;
            }

            stmt.close();
            connection.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        return user;
    }

    public static void main(String[] args) {
        //LoginForm myLoginForm = new LoginForm(null);
        RegistrationForm myForm = new RegistrationForm(null);
        /*
        User user = myForm.user;
            if (user != null){
                System.out.println("Succesful registration of: " + user.name);
            } else {
                System.out.println("Registration canceled");
            }

         */
    }

}
