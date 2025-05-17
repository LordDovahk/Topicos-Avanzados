import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ConversorUnidades {

    private JFrame frame = new JFrame("Conversor de Unidades");
    private JComboBox<String> comboTipo = new JComboBox<>(new String[]{"Longitud (m a km)", "Peso (kg a lb)", "Temperatura (°C a °F)"});
    private JTextField txtValor = new JTextField(10);
    private JLabel lblResultado = new JLabel("Resultado:");

    public ConversorUnidades() {
        frame.setLayout(new FlowLayout());
        frame.setSize(600, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(comboTipo);
        frame.add(txtValor);
        frame.add(new JButton("Convertir") {{
            addActionListener(e -> convertirUnidad());
        }});
        frame.add(lblResultado);
        frame.add(new JButton("Limpiar") {{
            addActionListener(e -> limpiarCampos());
        }});
        frame.setVisible(true);
    }

    private void convertirUnidad() {
        try {
            double valor = Double.parseDouble(txtValor.getText());
            String tipo = (String) comboTipo.getSelectedItem();
            double resultado = tipo.equals("Longitud (m a km)") ? valor / 1000 :
                    tipo.equals("Peso (kg a lb)") ? valor * 2.20462 :
                            (valor * 9 / 5) + 32;
            lblResultado.setText("Resultado: " + String.format("%.2f", resultado) +
                    (tipo.contains("Longitud") ? " km" : tipo.contains("Peso") ? " lb" : " °F"));
        } catch (NumberFormatException ex) {
            lblResultado.setText("Ingrese un número válido");
        }
    }

    private void limpiarCampos() {
        txtValor.setText("");
        lblResultado.setText("Resultado:");
        comboTipo.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        new ConversorUnidades();
    }
}