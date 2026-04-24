# Ecommerce API

A RESTful API for managing products built with Spring Boot.

## Features
- Get all products
- Get product by ID
- Create new product
- Update product
- Partial update (Patch)
- Delete product
- Filter by category, name, or price
- Input validation
- Global error handling

## Technologies
- Java 21
- Spring Boot
- Lombok
- Validation

## API Endpoints

| Method | URL | Description |
| :--- | :--- | :--- |
| GET | /api/v1/products | Get all products |
| GET | /api/v1/products/{id} | Get product by ID |
| POST | /api/v1/products | Create new product |
| PUT | /api/v1/products/{id} | Update product |
| PATCH | /api/v1/products/{id} | Partial update |
| DELETE | /api/v1/products/{id} | Delete product |
| GET | /api/v1/products/filter | Filter products |

## Author
Gumatay, Estrada , Gallano