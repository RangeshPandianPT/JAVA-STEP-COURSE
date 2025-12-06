# Currency Converter API 💱

A RESTful API for real-time currency conversion with user authentication, conversion history tracking, and external exchange rate integration.

## 🚀 Features

- **User Authentication**: Register users with API key generation
- **Currency Conversion**: Convert amounts between currencies using real-time exchange rates
- **Conversion History**: Save and retrieve conversion transaction history
- **Exchange Rates**: List supported currencies and current rates
- **Dual Authentication**: Support for both Basic Auth and API Key authentication
- **H2 Database**: In-memory database for development and testing

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot 3.2.0**
  - Spring Web
  - Spring Data JPA
  - Spring Security
  - Spring WebFlux (WebClient)
- **H2 Database** (In-memory)
- **Lombok** (Reduces boilerplate code)
- **Jackson** (JSON processing)
- **Maven** (Build tool)

## 📁 Project Structure

```
Currency_converter/
├── src/
│   ├── main/
│   │   ├── java/com/converter/currencyconverter/
│   │   │   ├── config/
│   │   │   │   └── WebClientConfig.java
│   │   │   ├── controller/
│   │   │   │   ├── AuthController.java
│   │   │   │   ├── CurrencyConversionController.java
│   │   │   │   └── ExchangeRateController.java
│   │   │   ├── dto/
│   │   │   │   ├── ConversionHistoryDTO.java
│   │   │   │   ├── ConversionRequest.java
│   │   │   │   ├── ConversionResponse.java
│   │   │   │   ├── ExchangeRateResponse.java
│   │   │   │   ├── UserRegistrationRequest.java
│   │   │   │   └── UserRegistrationResponse.java
│   │   │   ├── entity/
│   │   │   │   ├── ConversionHistory.java
│   │   │   │   └── User.java
│   │   │   ├── exception/
│   │   │   │   ├── ErrorResponse.java
│   │   │   │   ├── ExchangeRateException.java
│   │   │   │   ├── GlobalExceptionHandler.java
│   │   │   │   ├── UserAlreadyExistsException.java
│   │   │   │   └── UserNotFoundException.java
│   │   │   ├── repository/
│   │   │   │   ├── ConversionHistoryRepository.java
│   │   │   │   └── UserRepository.java
│   │   │   ├── security/
│   │   │   │   ├── ApiKeyAuthenticationFilter.java
│   │   │   │   ├── CustomUserDetailsService.java
│   │   │   │   └── SecurityConfig.java
│   │   │   ├── service/
│   │   │   │   ├── CurrencyConversionService.java
│   │   │   │   ├── ExchangeRateService.java
│   │   │   │   └── UserService.java
│   │   │   └── CurrencyConverterApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
└── pom.xml
```

## 🔧 Setup & Installation

### Prerequisites
- Java 17 or higher
- Maven 3.6+

### Steps

1. **Clone the repository**
   ```bash
   cd Currency_converter
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the application**
   - Base URL: `http://localhost:8080/api`
   - H2 Console: `http://localhost:8080/api/h2-console`
     - JDBC URL: `jdbc:h2:mem:currencydb`
     - Username: `sa`
     - Password: (leave empty)

## 📡 API Endpoints

### Authentication Endpoints

#### 1. Health Check (Public)
```http
GET /api/auth/health
```

**Response:**
```json
{
  "status": "UP",
  "message": "Currency Converter API is running"
}
```

#### 2. Register User (Public)
```http
POST /api/auth/register
Content-Type: application/json

{
  "username": "john_doe",
  "password": "password123",
  "email": "john@example.com"
}
```

**Response:**
```json
{
  "username": "john_doe",
  "email": "john@example.com",
  "apiKey": "CK-A1B2C3D4E5F6G7H8I9J0K1L2M3N4O5P6",
  "message": "User registered successfully. Please save your API key."
}
```

#### 3. Regenerate API Key (Authenticated)
```http
POST /api/auth/regenerate-api-key
X-API-Key: YOUR_API_KEY
```

**Response:**
```json
{
  "apiKey": "CK-NEW123456789ABCDEF",
  "message": "API key regenerated successfully"
}
```

### Currency Conversion Endpoints

#### 4. Convert Currency (Authenticated)
```http
POST /api/convert
X-API-Key: YOUR_API_KEY
Content-Type: application/json

{
  "fromCurrency": "USD",
  "toCurrency": "INR",
  "amount": 100
}
```

**Response:**
```json
{
  "fromCurrency": "USD",
  "toCurrency": "INR",
  "amount": 100,
  "convertedAmount": 8325.50,
  "exchangeRate": 83.255,
  "timestamp": "2025-12-06T10:30:00"
}
```

#### 5. Get Conversion History (Authenticated)
```http
GET /api/convert/history
X-API-Key: YOUR_API_KEY
```

**Response:**
```json
[
  {
    "id": 1,
    "fromCurrency": "USD",
    "toCurrency": "INR",
    "amount": 100,
    "convertedAmount": 8325.50,
    "exchangeRate": 83.255,
    "conversionDate": "2025-12-06T10:30:00"
  }
]
```

#### 6. Get History by Currency Pair (Authenticated)
```http
GET /api/convert/history/USD/INR
X-API-Key: YOUR_API_KEY
```

### Exchange Rate Endpoints

#### 7. Get All Rates for Base Currency (Authenticated)
```http
GET /api/rates/USD
X-API-Key: YOUR_API_KEY
```

**Response:**
```json
{
  "EUR": 0.92,
  "GBP": 0.79,
  "INR": 83.26,
  "JPY": 149.50,
  "AUD": 1.52,
  "CAD": 1.36
}
```

#### 8. Get Specific Exchange Rate (Authenticated)
```http
GET /api/rates/USD/EUR
X-API-Key: YOUR_API_KEY
```

**Response:**
```json
{
  "from": "USD",
  "to": "EUR",
  "rate": 0.92
}
```

## 🔐 Authentication Methods

The API supports two authentication methods:

### 1. API Key (Recommended)
Add the API key to request headers:
```
X-API-Key: YOUR_API_KEY
```

### 2. Basic Authentication
Use username and password:
```
Authorization: Basic base64(username:password)
```

## 🧪 Testing with cURL

### Register a new user
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d "{\"username\":\"testuser\",\"password\":\"test123\",\"email\":\"test@example.com\"}"
```

### Convert currency
```bash
curl -X POST http://localhost:8080/api/convert \
  -H "Content-Type: application/json" \
  -H "X-API-Key: YOUR_API_KEY" \
  -d "{\"fromCurrency\":\"USD\",\"toCurrency\":\"INR\",\"amount\":100}"
```

### Get conversion history
```bash
curl http://localhost:8080/api/convert/history \
  -H "X-API-Key: YOUR_API_KEY"
```

### Get exchange rates
```bash
curl http://localhost:8080/api/rates/USD \
  -H "X-API-Key: YOUR_API_KEY"
```

## 📊 Database Schema

### Users Table
- `id` (Primary Key)
- `username` (Unique)
- `password` (Encrypted)
- `email` (Unique)
- `api_key` (Unique)
- `enabled` (Boolean)
- `created_at` (Timestamp)

### Conversion History Table
- `id` (Primary Key)
- `user_id` (Foreign Key)
- `from_currency` (String)
- `to_currency` (String)
- `amount` (Decimal)
- `converted_amount` (Decimal)
- `exchange_rate` (Decimal)
- `conversion_date` (Timestamp)

## 🌐 External API

This application uses [ExchangeRate-API](https://www.exchangerate-api.com/) for real-time exchange rates.

**API Endpoint:** `https://api.exchangerate-api.com/v4/latest/{BASE_CURRENCY}`

**Note:** The free tier has rate limits. For production use, consider using an API key from ExchangeRate-API or other providers.

## 🎓 Learning Outcomes

Through this project, you'll learn:

1. **RESTful API Design**: Creating well-structured REST endpoints
2. **Spring Boot**: Application configuration and component integration
3. **External API Integration**: Using WebClient for HTTP requests
4. **JSON Processing**: Working with Jackson for serialization/deserialization
5. **Spring Security**: Implementing authentication and authorization
6. **JPA/Hibernate**: Database operations with Spring Data JPA
7. **Exception Handling**: Global error handling and custom exceptions
8. **Validation**: Request validation using Bean Validation
9. **Layered Architecture**: Separation of concerns (Controller, Service, Repository)

## 🔒 Security Features

- Password encryption using BCrypt
- API key generation and validation
- Stateless authentication (JWT-ready architecture)
- CSRF protection disabled for stateless API
- Basic Auth + API Key dual authentication support

## 🚧 Future Enhancements

- [ ] JWT token authentication
- [ ] Redis caching for exchange rates
- [ ] Rate limiting per user
- [ ] Pagination for history endpoints
- [ ] Scheduled jobs to update exchange rates
- [ ] Support for cryptocurrency conversion
- [ ] Swagger/OpenAPI documentation
- [ ] Docker containerization
- [ ] Unit and integration tests
- [ ] Multiple currency conversion in single request

## 📝 Configuration

Key configurations in `application.properties`:

```properties
# Server
server.port=8080
server.servlet.context-path=/api

# Database
spring.datasource.url=jdbc:h2:mem:currencydb

# External API
exchange.api.url=https://api.exchangerate-api.com/v4/latest
exchange.api.timeout=5000
```

## 🤝 Contributing

Feel free to fork this project and submit pull requests for improvements!

## 📄 License

This project is open-source and available for educational purposes.

## 👨‍💻 Author

Created as a beginner-friendly Spring Boot learning project.

---

**Happy Coding! 🚀**
