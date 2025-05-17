import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class VentanaPrincipal extends JFrame {
    private JFrame ventanaSecundaria;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public VentanaPrincipal() {
        setTitle("Ventana Principal");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configurar la ventana secundaria
        ventanaSecundaria = new JFrame("Ventana Secundaria");
        ventanaSecundaria.setSize(30, 600);
        ventanaSecundaria.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        ventanaSecundaria.setLocation(getX() - ventanaSecundaria.getWidth(), getY());

        // Crear un panel para los botones en la ventana secundaria
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(5, 1)); // 5 filas y 1 columna

        // Crear los botones
        JButton botonVentana1 = new JButton("Abrir Ventana 1");
        JButton botonVentana2 = new JButton("Abrir Ventana 2");
        JButton botonVentana3 = new JButton("Abrir Ventana 3");
        JButton botonVentana4 = new JButton("Abrir Ventana 4");
        JButton botonDB = new JButton("Conectar y Consultar DB");

        // Asignar acciones a los botones
        botonVentana1.addActionListener(e -> abrirVentana1());
        botonVentana2.addActionListener(e -> abrirVentana2());
        botonVentana3.addActionListener(e -> abrirVentana3());
        botonVentana4.addActionListener(e -> abrirVentana4());
        botonDB.addActionListener(e -> conectarYConsultarDB());

        // Agregar los botones al panel
        panelBotones.add(botonVentana1);
        panelBotones.add(botonVentana2);
        panelBotones.add(botonVentana3);
        panelBotones.add(botonVentana4);
        panelBotones.add(botonDB);

        // Agregar el panel de botones a la ventana secundaria
        ventanaSecundaria.add(panelBotones, BorderLayout.CENTER);

        // Hacer visible la ventana principal y la secundaria
        setVisible(true);
        ventanaSecundaria.setVisible(true);

        // Listener para sincronizar el estado de las ventanas
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowIconified(WindowEvent e) {
                ventanaSecundaria.setExtendedState(JFrame.ICONIFIED);
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                ventanaSecundaria.setExtendedState(JFrame.NORMAL);
            }

            @Override
            public void windowClosing(WindowEvent e) {
                ventanaSecundaria.dispose();
                dispose();
            }
        });

        // Listener para sincronizar la posición de las ventanas
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
                ventanaSecundaria.setLocation(getX() - ventanaSecundaria.getWidth(), getY());
            }

            @Override
            public void componentResized(ComponentEvent e) {
                ventanaSecundaria.setLocation(getX() - ventanaSecundaria.getWidth(), getY());
            }
        });
    }

    // Métodos para abrir ventanas
    private void abrirVentana1() {
        JFrame ventana1 = new JFrame("Ventana 1");
        ventana1.setSize(400, 300);
        ventana1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana1.setLocationRelativeTo(null);

        JLabel etiqueta = new JLabel("Esta es la Ventana 1", SwingConstants.CENTER);
        ventana1.add(etiqueta);

        ventana1.setVisible(true);
    }

    private void abrirVentana2() {
        JFrame ventana2 = new JFrame("Ventana 2");
        ventana2.setSize(400, 300);
        ventana2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana2.setLocationRelativeTo(null);

        JLabel etiqueta = new JLabel("Esta es la Ventana 2", SwingConstants.CENTER);
        ventana2.add(etiqueta);

        ventana2.setVisible(true);
    }

    private void abrirVentana3() {
        JFrame ventana3 = new JFrame("Ventana 3");
        ventana3.setSize(400, 300);
        ventana3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana3.setLocationRelativeTo(null);

        JLabel etiqueta = new JLabel("Esta es la Ventana 3", SwingConstants.CENTER);
        ventana3.add(etiqueta);

        ventana3.setVisible(true);
    }

    private void abrirVentana4() {
        JFrame ventana4 = new JFrame("Ventana 4");
        ventana4.setSize(400, 300);
        ventana4.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana4.setLocationRelativeTo(null);

        JLabel etiqueta = new JLabel("Esta es la Ventana 4", SwingConstants.CENTER);
        ventana4.add(etiqueta);

        ventana4.setVisible(true);
    }

    private void conectarYConsultarDB() {
        String ip = JOptionPane.showInputDialog("Introduce la IP:");
        String pto = JOptionPane.showInputDialog("Introduce el puerto:");
        String usr = JOptionPane.showInputDialog("Introduce el usuario:");
        String pwd = JOptionPane.showInputDialog("Introduce la contraseña:");
        String database = JOptionPane.showInputDialog("Introduce el nombre de la base de datos:");

        setConnection(ip, pto, usr, pwd, database);

        String query = JOptionPane.showInputDialog("Introduce la consulta SQL:");
        setQuery(query);

        ResultSet rs = getResultSet();
        displayResult(rs, 0);
    }

    private void setConnection(String ip, String pto, String usr, String pwd, String database) {
        String url = "jdbc:mysql://" + ip + ":" + pto + "/" + database;
        try {
            connection = DriverManager.getConnection(url, usr, pwd);
            statement = connection.createStatement();
            JOptionPane.showMessageDialog(null, "Conexión establecida con éxito.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    private void setQuery(String query) {
        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al ejecutar la consulta: " + e.getMessage());
        }
    }

    private ResultSet getResultSet() {
        return resultSet;
    }

    private void displayResult(ResultSet rs, int pos) {
        try {
            if (rs != null && rs.absolute(pos + 1)) {
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();
                StringBuilder result = new StringBuilder();
                for (int i = 1; i <= columnCount; i++) {
                    result.append(metaData.getColumnName(i)).append(": ").append(rs.getString(i)).append("\n");
                }
                JOptionPane.showMessageDialog(null, result.toString());
            } else {
                JOptionPane.showMessageDialog(null, "No hay más resultados.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar el resultado: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal());
    }
}