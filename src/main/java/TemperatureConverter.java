import java.util.Scanner;

public class TemperatureConverter {

    public double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5.0 / 9.0;
    }

    public double celsiusToFahrenheit(double celsius) {
        return (celsius * 9.0 / 5.0) + 32;
    }

    public boolean isExtremeTemperature(double celsius) {
        return celsius < -40 || celsius > 50;
    }
    public double kelvinToCelsius(double kelvin) {
        return (kelvin - 273.15);
    }

    public static void main(String[] args) {
        TemperatureConverter converter = new TemperatureConverter();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nTemperature Converter Menu:");
            System.out.println("1. Fahrenheit to Celsius");
            System.out.println("2. Celsius to Fahrenheit");
            System.out.println("3. Kelvin to Celsius");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            double temperature;
            switch (choice) {
                case 1:
                    System.out.print("Enter temperature in Fahrenheit: ");
                    temperature = scanner.nextDouble();
                    System.out.println("Result: " + converter.fahrenheitToCelsius(temperature) + " Celsius");
                    break;
                case 2:
                    System.out.print("Enter temperature in Celsius: ");
                    temperature = scanner.nextDouble();
                    System.out.println("Result: " + converter.celsiusToFahrenheit(temperature) + " Fahrenheit");
                    break;
                case 3:
                    System.out.print("Enter temperature in Kelvin: ");
                    temperature = scanner.nextDouble();
                    System.out.println("Result: " + converter.kelvinToCelsius(temperature) + " Celsius");
                    break;
                case 4:
                    System.out.println("Exited.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }
}
