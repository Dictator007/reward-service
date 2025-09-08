# Rewards API (Java 17, Spring Boot 3.3)

A clean, scalable, and resilient REST API for dynamically calculating customer reward points across hotels, restaurants, and casinos, with proper layering, error handling, and ready-to-use implementation.

## Why Dynamic Calculation?
- No need to maintain a ledger or separate database for reward points.
- Calculates rewards in real-time based on transactional data from existing services.
- Provides flexibility, scalability, and resilience by leveraging external APIs and modern fault tolerance patterns.

### Reward Rules
- **Hotel stays:** 10 points per night stayed.
- **Restaurant reservations:** 5 points per reservation.
- **Casino spend:** 1 point per $10 spent.

### API Endpoints

Base path: `/api/v1/customers/{customerId}/rewards`

- `GET /customers/{customerId}/rewards?categories=hotels,casinos` — calculate reward points
  ```http
  GET /api/v1/customers/cust001/rewards?categories=hotels,casinos


### Swagger URLs

    GET /swagger-ui/index.html


### HealthCheck 

    GET /actuator/health 

    GET /actuator/info 

    GET /actuator/metrics 

    GET /actuator/env 


## Additional Points

- Developed the application following SOLID principles to ensure maintainable and scalable code.

- Designed a loosely coupled architecture to promote separation of concerns and easier testing.

- Applied the Strategy Design Pattern:

- Integrated Swagger/OpenAPI for API documentation, providing an interactive interface for exploring and testing endpoints.

- Added Spring Boot Actuator to monitor application health, metrics, and operational insights in real time.


