import javax.swing.*; // Biblioteca que esta encargada para interfaces graficas
import java.awt.*; // Biblioteca que gestiona los componentes graficos
import java.awt.event.ActionEvent;
import java.net.URI;
import java.net.http.HttpClient; // Biblioteca en la cual podemos solicitar en HTTP
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject; // Biblioteca que maneja al JSON

public class ConveMond {
    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    // Es el metodo con el cual se crea y se muestra la interfaz grafica
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Conversor de Monedas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel amountLabel = new JLabel("Cantidad:");
        JTextField amountField = new JTextField();
        JLabel fromLabel = new JLabel("De:");
        JTextField fromField = new JTextField("USD");
        JLabel toLabel = new JLabel("A:");
        JTextField toField = new JTextField("EUR");
        JButton convertButton = new JButton("Convertir");
        JLabel resultLabel = new JLabel("Resultado:");
        
        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(fromLabel);
        panel.add(fromField);
        panel.add(toLabel);
        panel.add(toField);
        panel.add(convertButton);
        panel.add(resultLabel);

        frame.add(panel);
        frame.setVisible(true);

        // Cuando se presiona el boton se realiza la accion que solicitamos en este caso la conversion de la moneda que tenemos a la que queremos saber su valor
        convertButton.addActionListener((ActionEvent e) -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                String fromCurrency = fromField.getText().toUpperCase();
                String toCurrency = toField.getText().toUpperCase();
                double rate = getExchangeRate(fromCurrency, toCurrency);
                double convertedAmount = amount * rate;
                resultLabel.setText("Resultado: " + convertedAmount);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
            }
        });
    }

    // con este apartado podemos obtener la tasa de la API
    private static double getExchangeRate(String from, String to) throws Exception {
        HttpClient client = HttpClient.newHttpClient(); // El cliente HTTP hace su solicitud
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + from))
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject json = new JSONObject(response.body()); // Donde se procesa la respuesta de JSON
        return json.getJSONObject("rates").getDouble(to);
    }
}
