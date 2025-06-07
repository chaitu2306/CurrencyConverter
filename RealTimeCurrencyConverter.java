import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;

public class RealTimeCurrencyConverter {

    private static final String API_KEY = "aaf26c306e91d0d52a1b8897"; // Replace with your API key

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Real-Time Currency Converter ===");
        System.out.print("Enter base currency (e.g., USD): ");
        String baseCurrency = scanner.nextLine().toUpperCase();

        System.out.print("Enter target currency (e.g., INR): ");
        String targetCurrency = scanner.nextLine().toUpperCase();

        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();

        try {
            double rate = fetchExchangeRate(baseCurrency, targetCurrency);
            double convertedAmount = amount * rate;

            System.out.printf("%.2f %s = %.2f %s%n",
                    amount, baseCurrency, convertedAmount, targetCurrency);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }

    public static double fetchExchangeRate(String base, String target) throws Exception {
        String urlStr = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/" + base;

        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed to fetch data: HTTP code " + conn.getResponseCode());
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder json = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            json.append(line);
        }
        reader.close();

        JSONObject data = new JSONObject(json.toString());
        if (!data.getString("result").equals("success")) {
            throw new RuntimeException("API Error: " + data.getString("error-type"));
        }

        return data.getJSONObject("conversion_rates").getDouble(target);
    }
}
