# E-Commerce Web Application | Full-Stack Project

A portfolio-ready full-stack e-commerce web application built with **Spring Boot, React, MySQL and JWT authentication**. Users can browse products, manage a cart, manage their profile, place orders, view order history, and complete a simulated payment flow.

## Features

- User registration and login with JWT authentication
- Product catalogue with category/search filtering
- Shopping cart: add, update quantity, remove items, clear cart
- Checkout and order creation
- Simulated payment flow (no real money is charged)
- Order history and order details
- User profile update
- REST APIs tested with Postman
- MySQL persistence using Spring Data JPA/Hibernate
- Responsive React UI
- CORS configuration for local development

## Tech Stack

| Layer | Technology |
|---|---|
| Frontend | React, Vite, React Router, Axios |
| Backend | Java 17+, Spring Boot, Spring Security |
| API | REST, JSON |
| Authentication | JWT |
| Database | MySQL, JPA/Hibernate |
| Testing/API Client | Postman |
| Build | Maven, npm |

## Project Structure

```text
ecommerce-fullstack/
├── backend/       # Spring Boot REST API
├── frontend/      # React + Vite client
├── database/      # SQL schema
├── postman/       # API collection
├── docker-compose.yml
└── README.md
```

## 1. Database

Start MySQL locally or use Docker:

```bash
docker compose up -d mysql
```

The database `ecommerce_db` is created automatically. The application uses Hibernate `ddl-auto=update`, so tables are created/updated on first startup.

## 2. Run the backend

Requirements: Java 17+ and Maven 3.9+.

```bash
cd backend
mvn spring-boot:run
```

Backend runs at:

```text
http://localhost:8080
```

Environment variables are optional for local development:

```text
DB_URL=jdbc:mysql://localhost:3306/ecommerce_db?createDatabaseIfNotExist=true&serverTimezone=UTC
DB_USERNAME=root
DB_PASSWORD=root
JWT_SECRET=change-this-development-secret-to-a-long-random-value
```

## 3. Run the frontend

```bash
cd frontend
npm install
npm run dev
```

Frontend runs at:

```text
http://localhost:5173
```

## Demo flow

1. Register a new account.
2. Browse products.
3. Add products to the cart.
4. Open Cart and proceed to checkout.
5. Enter a shipping address.
6. Use the simulated payment screen.
7. View the created order under **My Orders**.

## API overview

### Authentication

- `POST /api/auth/register`
- `POST /api/auth/login`
- `GET /api/users/me`
- `PUT /api/users/me`

### Products

- `GET /api/products`
- `GET /api/products/{id}`

### Cart

- `GET /api/cart`
- `POST /api/cart/items`
- `PUT /api/cart/items/{productId}`
- `DELETE /api/cart/items/{productId}`
- `DELETE /api/cart`

### Orders

- `POST /api/orders`
- `GET /api/orders`
- `GET /api/orders/{id}`

### Payment simulation

- `POST /api/payments/simulate`

See `postman/ecommerce-api.postman_collection.json` for an importable collection.

## GitHub setup

```bash
git init
git add .
git commit -m "Initial commit - full-stack e-commerce application"
git branch -M main
git remote add origin https://github.com/<YOUR_USERNAME>/ecommerce-fullstack.git
git push -u origin main
```

## Resume description

> Developed a full-stack E-Commerce web application using React, Spring Boot and MySQL, implementing JWT authentication, product browsing, cart management, order processing and simulated payments. Built REST APIs with Spring Boot, persisted data using JPA/Hibernate, and tested endpoints using Postman.

## Important

This project contains a **simulated payment gateway** for demonstration purposes. It does not process real card payments and should not be used for production financial transactions without a real payment provider and additional security controls.

## Windows quick start

Open two Command Prompt windows from the project folder:

**Terminal 1 – MySQL:**
```cmd
docker compose up -d mysql
```

**Terminal 2 – Backend:**
```cmd
run-backend.bat
```

**Terminal 3 – Frontend:**
```cmd
run-frontend.bat
```

If you already have MySQL installed instead of Docker, create `ecommerce_db`, then set `DB_USERNAME` and `DB_PASSWORD` to your MySQL credentials before starting Spring Boot.
