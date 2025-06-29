# OnlineShopping.com Microservices Backend

This repository contains a production-quality, microservices-based backend for OnlineShopping.com, built with Java Spring Boot. The architecture follows best practices for microservices, including service discovery, API gateway, secure endpoints, and robust testing.

## Overview

This backend is composed of the following microservices:

- **product-service**: Handles product CRUD operations, filtering, paging, and role-based access control (RBAC) for admin and customer roles.
- **price-service**: Manages product pricing information.
- **inventory-service**: Tracks product inventory levels.
- **api-gateway**: Provides a single entry point for all services, with dynamic routing and service discovery.
- **service-discovery**: Eureka server for registering and discovering services.

### Key Features
- **Service-to-service communication** using REST APIs
- **API Gateway** (Spring Cloud Gateway) for unified routing
- **Eureka Service Discovery** for dynamic service registration
- **Swagger/OpenAPI** documentation for all REST endpoints
- **Logging and distributed tracing**
- **Unit tests** for all core logic
- **No Lombok**: All Java code is explicit (constructors, getters, setters, etc.)
- **H2 in-memory databases** for local development and testing
- **Role-based access control** (admin/customer) in product-service
- **Filtering and paging** for product listings

## Ports

- product-service: `8082`
- price-service: `8083`
- inventory-service: `8084`
- api-gateway: `8085`
- service-discovery (Eureka): `8761`

## Getting Started

### Prerequisites
- Java 11 or higher (Java 21/24+ supported)
- Maven 3.6+

### Running the System
1. **Start the Eureka server**
   - `cd service-discovery`
   - `mvn spring-boot:run`
   - Access dashboard: [http://localhost:8761](http://localhost:8761)
2. **Start each microservice** (in separate terminals):
   - `cd <service-folder>`
   - `mvn spring-boot:run`
3. **Start the API Gateway**
   - `cd api-gateway`
   - `mvn spring-boot:run`
   - Access gateway: [http://localhost:8085](http://localhost:8085)
4. **Access Swagger UI**
   - For each service: `http://localhost:<port>/swagger-ui.html` or `/swagger-ui/index.html`
5. **Access H2 Console**
   - For each service: `http://localhost:<port>/h2-console` (JDBC URL: `jdbc:h2:mem:<dbname>`)

### Security
- **Basic Auth** is enabled for product-service:
  - Admin: `admin` / `adminpass`
  - Customer: `customer` / `custpass`

### Development Notes
- Use `mvn clean install` to build all services
- All code is explicit Java (no Lombok)
- Paging, filtering, and RBAC are implemented in product-service

### Troubleshooting
- If a port is in use, either stop the process or change the port in the service's `application.yml`.
- If you see memory errors, close other programs or increase your system's virtual memory.
- All services must be running for full integration.
- If you encounter git push errors due to large files, use BFG Repo-Cleaner or git filter-repo to remove large files from git history, and ensure `.gitignore` excludes `target/` and `*.jar` files.

## Project Structure

- `product-service/` - Product microservice (CRUD, filtering, paging, RBAC)
- `price-service/` - Price microservice
- `inventory-service/` - Inventory microservice
- `api-gateway/` - API Gateway
- `service-discovery/` - Eureka server
- `.gitignore` - Excludes build artifacts and large files
- `README.md` - Project documentation

## Author
- Backend by Muthamizhl

---
For more details, see each service's folder and Swagger UI documentation.
