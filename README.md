# OnlineShopping.com Microservices Backend

This project is a production-quality, microservices-based backend for OnlineShopping.com, built with Java Spring Boot. It follows best practices for microservices architecture, including service discovery, API gateway, and secure, testable endpoints.

## Microservices
- **product-service**: Manages products with CRUD, filtering, paging, and role-based access control (admin/customer).
- **price-service**: Manages product prices.
- **inventory-service**: Manages product inventory.
- **api-gateway**: Central entry point for all services, with routing and service discovery.
- **service-discovery**: Eureka server for service registration and discovery.

## Features
- Service-to-service communication
- API Gateway (Spring Cloud Gateway)
- Eureka Service Discovery
- Swagger/OpenAPI documentation
- Logging and tracing
- Unit tests
- No Lombok (explicit Java code)
- H2 in-memory databases for local development

## Ports
- product-service: 8082
- price-service: 8083
- inventory-service: 8084
- api-gateway: 8085
- service-discovery (Eureka): 8761

## How to Run
1. **Start Eureka server**
   - `cd service-discovery`
   - `mvn spring-boot:run`
   - Access dashboard: [http://localhost:8761](http://localhost:8761)
2. **Start each microservice** (in separate terminals):
   - `cd <service-folder>`
   - `mvn spring-boot:run`
3. **Start API Gateway**
   - `cd api-gateway`
   - `mvn spring-boot:run`
   - Access gateway: [http://localhost:8085](http://localhost:8085)
4. **Access Swagger UI**
   - For each service: `http://localhost:<port>/swagger-ui.html` or `/swagger-ui/index.html`
5. **Access H2 Console**
   - For each service: `http://localhost:<port>/h2-console` (JDBC URL: `jdbc:h2:mem:<dbname>`)

## Security
- Basic Auth enabled for product-service:
  - Admin: `admin` / `adminpass`
  - Customer: `customer` / `custpass`

## Development Notes
- Java 11+ required (Java 21/24+ supported)
- Use `mvn clean install` to build all services
- All code is explicit Java (no Lombok)
- Paging, filtering, and RBAC implemented in product-service

## Troubleshooting
- If a port is in use, either stop the process or change the port in the service's `application.yml`.
- If you see memory errors, close other programs or increase your system's virtual memory.
- All services must be running for full integration.

## Author
- Backend by Muthamizhl

---
For more details, see each service's folder and Swagger UI documentation.
