# Weather API

This project builds a Weather API that fetches and returns weather data from a third-party API. It demonstrates working with third-party APIs, caching mechanisms using Redis, and rate-limiting with Bucket4j in a Spring Boot application.

## Features

- Fetches real-time weather data from a third-party API.
- Implements in-memory caching using Redis for faster responses and reduced API calls.
- Uses environment variables to store sensitive information like API keys and Redis connection strings.
- Handles errors gracefully, ensuring proper responses for invalid city codes or third-party API downtime.
- Implements rate-limiting with Bucket4j to prevent abuse.

## Requirements

- [Redis](https://redis.io/) (In-memory data store)
- A third-party weather API (e.g., [Visual Crossing Weather API](https://www.visualcrossing.com/weather-api))
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Bucket4j](https://bucket4j.com/) (Rate limiting library)

## Setup

1. **Clone the Repository**
   ```bash
   git clone https://github.com/ErickBrayan/Weather-API.git
   cd weather-api
   ```

2. **Set Up Environment Variables**
   Create an `application.properties` or `application.yml` file in the `src/main/resources` directory and add the following variables:
   ```properties
   api.key=<Your_Weather_API_Key>
   ```

3. **Run Redis**
   Make sure Redis is running locally or is accessible remotely.

4. **Build and Run the Application**
   Use Maven or Gradle to build and run the application:
   ```bash
   ./mvnw spring-boot:run
   ```
   or
   ```bash
   ./gradlew bootRun
   ```

## How It Works

1. **Fetch Weather Data**: When a user requests weather data for a city, the application checks the Redis cache.
    - If the data is cached, it is returned directly.
    - If not, the application fetches data from the third-party API and stores it in Redis with a 12-hour expiration.

2. **Environment Variables**: Sensitive information like API keys and Redis connection details are managed using environment variables for security.

3. **Error Handling**: The application gracefully handles errors such as invalid city codes or third-party API failures, providing meaningful responses to the user.

4. **Rate Limiting**: Bucket4j is used to implement rate-limiting. The number of allowed requests per time period can be configured to prevent abuse.

## API Endpoints

- **GET /{city}**
  Fetches weather data for the specified city.

  **Parameters:**
    - `city`: Name or code of the city.

  **Responses:**
    - `200 OK`: Returns weather data.
    - `400 Bad Request`: Invalid city name or code.
    - `429 Too Many Requests`: Exceeded rate limit.
    - `500 Internal Server Error`: Third-party API or other system failures.

## Example Request and Response

**Request:**
```bash
GET /London
```

**Response:**
```json
{
  "resolvedAddress": "London, England, United Kingdom",
  "description": "Similar temperatures continuing with a chance of rain multiple days & a chance of snow Sunday.",
  "temp": 12.166666666666666,
  "feelslike": 12.166666666666666
}
```

https://roadmap.sh/projects/weather-api-wrapper-service

