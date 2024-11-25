import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverter {
    public static void main(String[] args) {
        // Membuat frame utama
        JFrame frame = new JFrame("Aplikasi Konversi Suhu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(5, 2, 10, 10));

        // Komponen GUI
        JLabel inputLabel = new JLabel("Masukkan Suhu:");
        JTextField inputField = new JTextField();
        JLabel unitLabel = new JLabel("Pilih Satuan Asal:");
        String[] units = {"Celcius", "Fahrenheit", "Kelvin"};
        JComboBox<String> unitComboBox = new JComboBox<>(units);
        JLabel targetUnitLabel = new JLabel("Konversi ke Satuan:");
        JComboBox<String> targetUnitComboBox = new JComboBox<>(units);
        JButton convertButton = new JButton("Konversi");
        JLabel resultLabel = new JLabel("Hasil: -");

        // Menambahkan komponen ke frame
        frame.add(inputLabel);
        frame.add(inputField);
        frame.add(unitLabel);
        frame.add(unitComboBox);
        frame.add(targetUnitLabel);
        frame.add(targetUnitComboBox);
        frame.add(new JLabel()); // Spacer
        frame.add(convertButton);
        frame.add(new JLabel()); // Spacer
        frame.add(resultLabel);

        // Listener tombol konversi
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double inputTemperature = Double.parseDouble(inputField.getText());
                    String fromUnit = (String) unitComboBox.getSelectedItem();
                    String toUnit = (String) targetUnitComboBox.getSelectedItem();

                    double result = convertTemperature(inputTemperature, fromUnit, toUnit);
                    resultLabel.setText(String.format("Hasil: %.2f %s", result, toUnit));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Masukkan angka yang valid!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.setVisible(true);
    }

    // Fungsi konversi suhu
    public static double convertTemperature(double temperature, String fromUnit, String toUnit) {
        if (fromUnit.equals(toUnit)) {
            return temperature; // Tidak perlu konversi jika satuan sama
        }

        // Konversi ke Celcius terlebih dahulu
        double tempInCelsius;
        switch (fromUnit) {
            case "Fahrenheit":
                tempInCelsius = (temperature - 32) * 5 / 9;
                break;
            case "Kelvin":
                tempInCelsius = temperature - 273.15;
                break;
            default: // "Celcius"
                tempInCelsius = temperature;
        }

        // Konversi dari Celcius ke satuan target
        switch (toUnit) {
            case "Fahrenheit":
                return tempInCelsius * 9 / 5 + 32;
            case "Kelvin":
                return tempInCelsius + 273.15;
            default: // "Celcius"
                return tempInCelsius;
        }
    }
}
