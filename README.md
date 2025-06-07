# 💱 Real-Time Currency Converter (Java)

A console-based currency converter built in Java. It fetches real-time exchange rates using the [ExchangeRate API](https://www.exchangerate-api.com/).

---

## 📦 Features

- Convert one currency to another in real time
- Console interface
- Uses `org.json` for JSON parsing
- Supports custom input currencies and amounts

---

## 🧪 Sample Usage

Enter base currency (e.g., USD): USD
Enter target currency (e.g., INR): INR
Enter amount: 100
100.00 USD = 8321.75 INR

---

## 🚀 How to Run

### Prerequisites:
- Java 11 or above
- JSON library: `json-20240303.jar`

### Compile:

```bash
javac -cp json-20240303.jar src/RealTimeCurrencyConverter.java