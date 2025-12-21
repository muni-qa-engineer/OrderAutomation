# OrderAutomation – Selenium Test Automation Framework

This repository contains a **Selenium Java Test Automation Framework** built using the **Page Object Model (POM)** and **TestNG**, designed to automate an end-to-end e-commerce order flow.

The project demonstrates real-world automation concepts such as dynamic element handling, explicit waits, modular page objects, and clean test design, following industry-standard best practices.

---

## 🚀 Tech Stack
- Java
- Selenium WebDriver
- TestNG
- Maven
- Page Object Model (POM)
- Git & GitHub

---

## 📌 Application Under Test
- https://rahulshettyacademy.com/client

---

## 🧪 Test Scenario Covered
- Login to the application
- Verify product availability
- Add product to cart
- Checkout and place order
- Validate order confirmation message

---🗂 Project Structure

```text
OrderAutomation
│
├── src
│   ├── main
│   │   └── java
│   │       ├── baseTestComponent
│   │       │   └── BaseTest.java
│   │       ├── AbstractComponent
│   │       │   └── AbstaractPage.java
│   │       └── OwnProject
│   │           └── pageObjects
│   │               ├── LoginPage.java
│   │               ├── ProductCataloguePage.java
│   │               ├── CartPage.java
│   │               ├── CheckoutPage.java
│   │               └── ConfirmationPage.java
│   │
│   └── test
│       └── java
│           └── OwnProject
│               └── OrderAutmation
│                   └── SubmitOrderTest.java
│
├── pom.xml
└── README.md