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
- Database persistence with MySQL and Spring Data JPA

## Technologies
- Java 21
- Spring Boot
- Spring Data JPA
- MySQL
- Lombok
- Validation

## Database Schema

### Tables and Relationships

**categories**
- `id` (PK, auto-increment)
- `name` (unique, not null)
- One Category → Many Products

**products**
- `id` (PK, auto-increment)
- `name` (not null)
- `description`
- `price` (not null)
- `image_url`
- `stock_quantity` (not null)
- `category_id` (FK → categories.id)
- Many Products → One Category
- One Product → Many OrderItems

**orders**
- `id` (PK, auto-increment)
- `customer_name` (not null)
- `order_date`
- One Order → Many OrderItems

**order_items**
- `id` (PK, auto-increment)
- `quantity` (not null)
- `unit_price` (not null)
- `order_id` (FK → orders.id)
- `product_id` (FK → products.id)

## API Endpoints

| Method | URL                        | Description        |
|:-------|:---------------------------|:-------------------|
| GET    | /api/v1/products           | Get all products   |
| GET    | /api/v1/products/{id}      | Get product by ID  |
| POST   | /api/v1/products           | Create new product |
| PUT    | /api/v1/products/{id}      | Update product     |
| PATCH  | /api/v1/products/{id}      | Partial update     |
| DELETE | /api/v1/products/{id}      | Delete product     |
| GET    | /api/v1/products/filter    | Filter products    |
| GET    | /api/v1/categories         | Get all categories |
| POST   | /api/v1/categories         | Create a category  |

## Screenshots

### Database Table
![Database Table](screenshots/db-table.png)

### Browser Console
![Browser Console](screenshots/console1.png)
![Browser Console](screenshots/console2.png)

## Author
Gumatay, Estrada, Gallano