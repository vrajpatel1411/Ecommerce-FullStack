# Tradify - E-Commerce Application

An end-to-end full-stack eCommerce web application enabling users to browse products, manage their cart, checkout using Stripe, and view orders. Built with a robust Spring Boot backend and a responsive React.js frontend.

---

## 📂 Project Structure

```
ecommerce-project/
├── backend/         # Spring Boot backend API
└── frontend/        # React.js frontend UI
```

---

## ⚙️ Features

### Backend (Spring Boot)

* User authentication (login functionality)
* Product catalog management
* Cart operations (add, remove, update)
* Checkout process
* Stripe payment integration
* Order tracking

### Frontend (React.js + TailwindCSS)

* Product catalog UI
* Cart and checkout pages
* Stripe Elements for payment input
* Order history and confirmation page
* Responsive design using TailwindCSS

---

## ☁️ Deployment

Deployed on **Google Cloud Platform** using:

* **Cloud Build**: CI/CD pipeline for automated build and deployment
* **Google Kubernetes Engine (GKE)**: For container orchestration and scaling

---

## 🚀 Running Locally

### Prerequisites

* Java 17+
* Node.js + npm
* Docker (optional)

### Backend

```bash
cd backend
./mvnw spring-boot:run
```

### Frontend

```bash
cd frontend
npm install
npm start
```

---

## 💳 Stripe Integration

To use Stripe payments:

1. Create a Stripe account and get your API keys.
2. Add the keys to backend configuration (`application.properties`).
3. The frontend uses Stripe Elements to securely handle payment information.

---

## 📊 Tech Stack

* **Backend:** Java, Spring Boot, Spring Security, Stripe SDK
* **Frontend:** React.js, TailwindCSS, Axios, Stripe.js
* **DevOps:** Docker, Cloud Build (CI/CD), GKE

## 👨‍💼 Author

**Vraj Patel**
Full Stack Developer | Cloud Enthusiast
