import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JDialog{
    private JTextField tfLogin;
    private JPasswordField pfPassword;
    private JPanel loginPanel;
    private JButton btnLogin;
    private JButton btnCancel;
    private JButton btnRegisterHandling;

    public LoginForm(JFrame parent1) {
        super(parent1);
        setTitle("Login to application");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(620, 660));
        setModal(true);
        setLocationRelativeTo(parent1);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);


        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });

        btnRegisterHandling.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
                RegistrationForm myForm1 = new RegistrationForm(null);
            }
        });
        setVisible(true);
    }
}
