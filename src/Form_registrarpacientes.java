import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Form_registrarpacientes {
    public JPanel panel2;
    private JLabel lb_titulo;
    private JTextField tf_cedula;
    private JTextField tf_historial;
    private JTextField tf_nombre;
    private JTextField tf_apellido;
    private JTextField tf_telefono;
    private JTextField tf_edad;
    private JTextField tf_descripcion;
    private JButton bt_registrar;
    private JLabel lb_cedula;
    private JLabel lb_historial;
    private JLabel lb_nombre;
    private JLabel lb_apellido;
    private JLabel lb_telefono;
    private JLabel lb_edad;
    private JLabel lb_descripcion;
    private JButton bt_buscar;

    public Form_registrarpacientes() {
        bt_registrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/sistema_hospitalario";
                String user = "root";
                String password = "123456";

                String cedula = tf_cedula.getText();
                String historial = tf_historial.getText();
                String nombre = tf_nombre.getText();
                String apellido = tf_apellido.getText();
                String telefono = tf_telefono.getText();
                String edad = tf_edad.getText();
                String descripcion = tf_descripcion.getText();

                String sql = "INSERT INTO paciente (cedula, n_historial_clinico, nombre, apellido, telefono, edad, descripcion_enfermedad) VALUES (?, ?, ?, ?,?,?,?)";


                try (Connection connection = DriverManager.getConnection(url, user, password);
                     PreparedStatement statement = connection.prepareStatement(sql)) {

                    statement.setString(1, cedula);
                    statement.setString(2, historial);
                    statement.setString(3, nombre);
                    statement.setString(4, apellido);
                    statement.setString(5, telefono);
                    statement.setString(6, edad);
                    statement.setString(7, descripcion);

                    int rowsInserted = statement.executeUpdate();

                    if (rowsInserted > 0) {
                        System.out.println("Datos insertados correctamente");
                    } else {
                        System.out.println("No se han insertado datos");
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    System.out.println("Error al insertar datos: " + ex.getMessage());
                }
            }
        });
        bt_buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame1 = null;
                frame1 = new JFrame("Buscar Paciente");
                frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame1.setContentPane(new Form_buscar().panel3);
                frame1.pack();
                frame1.setSize(600, 500);
                frame1.setLocationRelativeTo(null);
                frame1.setVisible(true);

            }
        });
    }
}

