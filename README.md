# 🚀 Automation Exercise – Test Automation Framework  

![Java](https://img.shields.io/badge/Java-17%2F21-orange?logo=java&logoColor=white)  
![Selenium](https://img.shields.io/badge/Selenium-WebDriver-brightgreen?logo=selenium&logoColor=white)  
![TestNG](https://img.shields.io/badge/TestNG-Framework-blueviolet)  
![REST Assured](https://img.shields.io/badge/REST%20Assured-API%20Testing-yellowgreen)  
![Maven](https://img.shields.io/badge/Maven-Build%20Tool-red?logo=apachemaven)  
![Allure](https://img.shields.io/badge/Allure-Reports-ff69b4)  
![Docker](https://img.shields.io/badge/Docker-Optional-blue?logo=docker)  

---

I’m excited to share that I have successfully built a complete **Test Automation Framework** for the [Automation Exercise website](https://automationexercise.com) 🎉  

---

## 🔧 Tech Stack & Tools Used
- **Java 17 / 21** – Core language for automation  
- **Selenium WebDriver** – UI automation & cross-browser testing  
- **TestNG** – Test management, assertions, parallel execution, listeners  
- **REST Assured** – API testing & integration with UI flows  
- **Allure Reports** – Interactive test reports with environment details & trends  
- **Maven** – Dependency management & build automation  
- **Log4j2** – Structured logging & debugging  
- **MySQL** – Sync test results with DB  
- **Docker / Selenium Grid (Optional)** – Remote & parallel execution  

---

## 📂 Framework Design Highlights
- **Page Object Model (POM)** with component-based design → clean separation of Pages & reusable UI components  
- **Driver Management** → ThreadLocal WebDriver for safe parallel tests  
- **BaseTest** → unified setup/teardown for UI & API tests  
- **Utils Layer** → WaitManager, PropertyReader, JsonReader, TerminalUtils  
- **Assertions Layer** → Hard (Verification) & Soft (Validation)  
- **Reports & Logs** → Allure Reports + Log4j2 + video recording support  
- **CI/CD Ready** → Easy integration with Jenkins / GitHub Actions  

---

## 🧪 Test Coverage Examples
✔ Register/Login user (API + UI)  
✔ Add product to cart & validate cart  
✔ Checkout & validate delivery/billing addresses  
✔ Payment flow with card information  

---

## ▶️ How to Run Tests
1. Clone the repo  
   ```bash
   git clone https://github.com/QualityAssuranceEmad/Automation_Excercise.git
   cd Automation_Excercise
