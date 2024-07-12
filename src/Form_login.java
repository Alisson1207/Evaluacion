import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Form_login {
    public JPanel panel1;
    private JTextField tf_usuario;
    private JPasswordField pf_contrasenia;
    private JLabel lb_usuario;
    private JLabel lb_contrasenia;
    private JButton bt_ingresar;

    public Form_login() {
        bt_ingresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/sistema_hospitalario";
                String user = "root";
                String password = "123456";

                try (Connection connection = DriverManager.getConnection(url, user, password)) {
                    System.out.println("Conectado a la base de datos");
                    String sql = "SELECT * FROM usuario WHERE username = '" + tf_usuario.getText() + "' AND password = '" + pf_contrasenia.getText() + "'";
                    ;

                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(sql);


                    JFrame frame1 = null;
                    frame1 = new JFrame("Registrar Pacientes");
                    frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame1.setContentPane(new Form_registrarpacientes().panel2);
                    frame1.pack();
                    frame1.setSize(600, 500);
                    frame1.setLocationRelativeTo(null);
                    frame1.setVisible(true);


                } catch (SQLException e1) {
                    System.out.println(e1);
                }
            }

            });
        }
    }

