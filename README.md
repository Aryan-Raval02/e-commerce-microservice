# 🛒 Java E-Commerce Microservices Project

> A learning project to understand **microservices architecture** using Java and Spring Boot.

---

## 🚀 Overview
This project is a basic **E-Commerce system** built with microservices.  
It is created for **learning purposes**, so the architecture is simple and focused on understanding how multiple services interact in a microservice environment.

---

## 🏗 Services Built
Currently, the project contains the following microservices:

- **User Service** – Manage user registration, login, and profiles  
- **Product Service** – Manage product catalog and details  
- **Cart Service** – Handle user's shopping cart and items  
- **Order Service** – Manage orders and order history  
- **Payment Service** – Handle payment processing (basic simulation)

---

## 💻 Technologies Used
- **Java** – Core programming language  
- **Spring Boot** – For building REST APIs and microservices  
- **Spring Data JPA** – Database interactions  
- **PostgreSQL** – Relational database  

> Future updates may include **Spring Security**, **RabbitMQ/Kafka**, and **Docker** for better microservice practice.

---

## ⚙️ Features
- REST APIs for each service  
- Basic CRUD operations for products, users, and orders  
- Service interaction through REST calls  
- Persistent data storage with PostgreSQL  

---

## 🔧 Project Structure
```
ecommerce-microservices/
│
├── user-service/
│ ├── src/main/java/
│ ├── src/main/resources/
│ └── pom.xml
│
├── product-service/
│ ├── src/main/java/
│ ├── src/main/resources/
│ └── pom.xml
│
├── cart-service/
│ ├── src/main/java/
│ ├── src/main/resources/
│ └── pom.xml
│
├── order-service/
│ ├── src/main/java/
│ ├── src/main/resources/
│ └── pom.xml
│
└── payment-service/
├── src/main/java/
├── src/main/resources/
└── pom.xml
```


---

## 📚 Learning Goals
- Understand **microservices architecture**  
- Learn **service-to-service communication**  
- Practice **Spring Boot REST APIs** and **PostgreSQL integration**  
- Explore **transactional workflows** in an e-commerce system  

---

## ⚠️ Note
This project is **for learning purposes only** and not production-ready.  
Some features and architecture patterns are simplified for easier understanding.

---

## 🔗 How to Run
1. Clone the repository:  
```bash
git clone https://github.com/Aryan-Raval02/e-commerce-microservice.git

