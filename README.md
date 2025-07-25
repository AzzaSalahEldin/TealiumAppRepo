# 🧪 Tealium E-commerce Automation Framework

This project is an end-to-end test automation framework built with **Java**, **Selenium WebDriver**, **TestNG**, and **Allure** for reporting. It validates core functionalities of an e-commerce site, focusing on **User Registration**, **Login**, and **Shopping Cart operations**.

---

## ✅ Features

- ✅ Page Object Model (POM)
- ✅ TestNG with Data-Driven Testing
- ✅ Selenium WebDriver
- ✅ Centralized Utility Methods (e.g., element wait & visibility)
- ✅ Allure Reporting Integration
- ✅ Extensible and modular codebase
- ✅ Parallel test execution support

---

## 🧪 Test Coverage

### 👤 User Registration

| Test Case | Description |
|----------|-------------|
| ✅ TC1 | Check successful registration with valid credentials |
| ✅ TC2 | Validate error messages for empty required fields |
| ✅ TC3 | Handle invalid email format in registration |
| ✅ TC4 | Detect mismatch between password and confirm password |
| ✅ TC5 | Prevent registration using an existing email address |

### 🔐 User Login

| Test Case | Description |
|----------|-------------|
| ✅ TC6 | Successful login with valid credentials |
| ✅ TC7 | Validate login with invalid email format |
| ✅ TC8 | Validate login with incorrect password |
| ✅ TC9 | Validate login with empty credentials |
| ✅ TC10 | Handle login attempts with non-existent accounts |

### 🛒 Shopping Cart (Accessories)

| Test Case | Description |
|----------|-------------|
| ✅ TC11 | Verify Accessories menu visibility |
| ✅ TC12 | Verify price sorting (Low to High) in Shoes subcategory |
| ✅ TC13 | Ensure Product Detail Page loads correctly |
| ✅ TC14 | Validate color and size selection functionality |
| ✅ TC15 | Confirm product is added to cart successfully |

### 🔚 Logout

| Test Case | Description |
|----------|-------------|
| ✅ TC16 | Verify successful logout and visibility of ‘Sign Out’ option |

---

## 🧰 Tech Stack

| Tool        | Purpose                   |
|-------------|---------------------------|
| Java        | Programming language      |
| Maven       | Build and dependency tool |
| Selenium    | Web automation            |
| TestNG      | Test execution framework  |
| Allure      | Reporting                 |
| WebDriverManager | Driver binaries mgmt |

---

## 🚀 How to Run Tests

1. **Clone the Repository**  
   ```bash
   git clone https://github.com/AzzaSalahEldin/TealiumAppRepo/.git
   cd your-repo
   ```

2. **Run via Maven**  
   ```bash
   mvn clean test
   ```

3. **Run Specific Suite**  
   ```bash
   mvn test -DsuiteXmlFile=testng.xml
   ```

---

## 📊 Allure Report

1. Generate Report  
   ```bash
   allure serve allure-results
   ```

2. OR  
   ```bash
   mvn allure:report
   ```

---

## 📁 Project Structure

```
src
├── main
│   └── java
│       └── pages          # Page Objects
│       └── utils          # Helpers (WaitUtils, ConfigReader)
├── test
│   └── java
│       └── tests          # Test classes
│       └── data           # CSV or Excel test data
testng.xml                 # TestNG suite
pom.xml                    # Maven config
