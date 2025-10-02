import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private TextField inputField = new TextField();
    private Label resultLabel = new Label();
    private ComboBox<String> fromUnitComboBox = new ComboBox<>();
    private ComboBox<String> toUnitComboBox = new ComboBox<>();
    private double convertedValue;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        // Set up UI components
        inputField.setPromptText("Enter temperature");

        // Initialize combo boxes
        fromUnitComboBox.getItems().addAll("Celsius", "Fahrenheit", "Kelvin");
        fromUnitComboBox.setValue("Celsius");

        toUnitComboBox.getItems().addAll("Celsius", "Fahrenheit", "Kelvin");
        toUnitComboBox.setValue("Fahrenheit");

        Button convertButton = new Button("Convert");
        convertButton.setOnAction(e -> convertTemperature());

        Button saveButton = new Button("Save to DB");
        saveButton.setOnAction(e -> Database.saveTemperature(
                Double.parseDouble(inputField.getText()),
                fromUnitComboBox.getValue(),
                convertedValue,
                toUnitComboBox.getValue(),
                resultLabel));

        // Create layout
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        gridPane.add(new Label("From:"), 0, 0);
        gridPane.add(fromUnitComboBox, 1, 0);
        gridPane.add(new Label("To:"), 0, 1);
        gridPane.add(toUnitComboBox, 1, 1);
        gridPane.add(new Label("Temperature:"), 0, 2);
        gridPane.add(inputField, 1, 2);

        VBox root = new VBox(10, gridPane, convertButton, resultLabel, saveButton);
        root.setPadding(new Insets(15));

        Scene scene = new Scene(root, 350, 250);

        stage.setTitle("Temperature Converter");
        stage.setScene(scene);
        stage.show();
    }

    private void convertTemperature() {
        try {
            double inputValue = Double.parseDouble(inputField.getText());
            String fromUnit = fromUnitComboBox.getValue();
            String toUnit = toUnitComboBox.getValue();

            convertedValue = convertTemperatureValue(inputValue, fromUnit, toUnit);

            resultLabel.setText(String.format("%.2f° %s = %.2f° %s",
                    inputValue, fromUnit.charAt(0), convertedValue, toUnit.charAt(0)));

        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input! Please enter a valid number.");
        } catch (Exception ex) {
            resultLabel.setText("Conversion error: " + ex.getMessage());
        }
    }

    private double convertTemperatureValue(double value, String fromUnit, String toUnit) {
        // Convert to Celsius first as base unit
        double celsius;

        switch (fromUnit) {
            case "Celsius":
                celsius = value;
                break;
            case "Fahrenheit":
                celsius = fahrenheitToCelsius(value);
                break;
            case "Kelvin":
                celsius = kelvinToCelsius(value);
                break;
            default:
                throw new IllegalArgumentException("Unknown from unit: " + fromUnit);
        }

        // Convert from Celsius to target unit
        switch (toUnit) {
            case "Celsius":
                return celsius;
            case "Fahrenheit":
                return celsiusToFahrenheit(celsius);
            case "Kelvin":
                return celsiusToKelvin(celsius);
            default:
                throw new IllegalArgumentException("Unknown to unit: " + toUnit);
        }
    }

    // Additional Functions to Implement
    private double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5/9;
    }

    private double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    private double celsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }

    // Existing function
    private double celsiusToFahrenheit(double celsius) {
        return (celsius * 9/5) + 32;
    }
}