import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Form_buscar {
    public JPanel panel3;
    private JLabel lb_titulo;
    private JPasswordField pf_cedula;
    private JButton bt_buscar;
    private JLabel lb_buscarcedula;
    private JLabel lb_resultado;

    public Form_buscar() {
        bt_buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/sistema_hospitalario";
                String user = "root";
                String password = "123456";

                String cedula =pf_cedula.getText();

                String sql = "SELECT * FROM paciente WHERE cedula = ?";

                try (Connection connection = DriverManager.getConnection(url, user, password);
                     PreparedStatement statement = connection.prepareStatement(sql)) {

                    statement.setString(1, cedula);

                    ResultSet resultSet = statement.executeQuery();

                    if (resultSet.next()) {
                        String nombre = resultSet.getString("nombre");
                        int historial = Integer.parseInt(resultSet.getString("n_historial"));
                        String apellido = resultSet.getString("apellido");
                        String telefono = resultSet.getString("telefono");
                        String edad = resultSet.getString("edad");


                        lb_resultado.setText("<html>Cédula: " + cedula + "<br>Nombre: " + nombre +  "</html>");
                    } else {
                        lb_resultado.setText("No se encontraron datos para la cédula: " + cedula);
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    lb_resultado.setText("Error al buscar datos: " + ex.getMessage());
                }
            }
        });
    }
}
