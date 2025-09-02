# 🚀 Automation_Excercise – Test Automation Framework  

![Java](https://img.shields.io/badge/Java-21-orange?logo=java&logoColor=white)  
![Selenium](https://img.shields.io/badge/Selenium-WebDriver-brightgreen?logo=selenium&logoColor=white)  
![TestNG](https://img.shields.io/badge/TestNG-Framework-blueviolet)  
![REST Assured](https://img.shields.io/badge/REST%20Assured-API%20Testing-yellowgreen)  
![Maven](https://img.shields.io/badge/Maven-Build%20Tool-red?logo=apachemaven)  
![Allure](https://img.shields.io/badge/Allure-Reports-ff69b4)  
---

I’m excited to share that I have successfully built a complete **Test Automation Framework** for the [Automation_Excercise website](https://automationexercise.com) 🎉  

---

### ✅ Features

- **Java + TestNG** based test execution  
- **Page Object Model (POM)** design pattern  
- **Explicit Waits Utility**  
- **Cross-browser support (Edge, Chrome, etc.)**  
- **Log4j2 logging**  
- **Allure Reporting**  
- **Data-driven testing using JSON & Excel**  
- **CI-ready with GitHub Actions**  
- **Scalable directory structure**  
- **Custom Hooks & Listeners for screenshots and logs**  
- **Environment-based execution (local, remote, headless)**  

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

# 🧪 Quality Engineering Projects Repository  

Welcome to the central hub of my Quality Assurance (QA) and Test Automation projects. This repository includes a set of structured and scalable frameworks that showcase real-world examples of modern testing practices using Java and various tools.  

## 📁 Repository Structure  

This repository contains the following projects:  

### 🧱 Project Structure
TAF/
├── .gitignore
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/automationexercices/
│   │   │       ├── FileUtils.java
│   │   │       ├── Builder.java
│   │   │       ├── UserManagementAPI.java
│   │   │       ├── apis/
│   │   │       ├── drivers/
│   │   │       │   ├── AbstractDriver.java
│   │   │       │   ├── Browser.java
│   │   │       │   ├── ChromeFactory.java
│   │   │       │   ├── EdgeFactory.java
│   │   │       │   ├── FirefoxFactory.java
│   │   │       │   ├── GUIDriver.java
│   │   │       │   ├── SafariFactory.java
│   │   │       │   ├── UITest.java
│   │   │       │   └── WebDriverProvider.java
│   │   │       ├── listeners/
│   │   │       │   └── TestNGListeners.java
│   │   │       ├── media/
│   │   │       │   ├── ScreenRecordManager.java
│   │   │       │   └── ScreenshotsManager.java
│   │   │       ├── pages/
│   │   │       │   ├── CartPage.java
│   │   │       │   ├── CheckoutPage.java
│   │   │       │   ├── ContactUsPage.java
│   │   │       │   ├── DeleteAccountPage.java
│   │   │       │   ├── LogoutPage.java
│   │   │       │   ├── PaymentPage.java
│   │   │       │   ├── ProductDetailsPage.java
│   │   │       │   ├── ProductsPage.java
│   │   │       │   ├── SignupLoginPage.java
│   │   │       │   ├── SignupPage.java
│   │   │       │   ├── TestCasesPage.java
│   │   │       │   └── components/
│   │   │       │       └── NavigationBarComponent.java
│   │   │       ├── utils/
│   │   │       │   ├── OSUtils.java
│   │   │       │   ├── TerminalUtils.java
│   │   │       │   ├── TimeManager.java
│   │   │       │   ├── WaitManager.java
│   │   │       │   ├── actions/
│   │   │       │   │   ├── AlertActions.java
│   │   │       │   │   ├── BrowserActions.java
│   │   │       │   │   ├── ElementActions.java
│   │   │       │   │   └── FrameActions.java
│   │   │       │   ├── dataReader/
│   │   │       │   │   ├── ExcelReader.java
│   │   │       │   │   ├── JsonReader.java
│   │   │       │   │   └── PropertyReader.java
│   │   │       │   ├── logs/
│   │   │       │   │   └── LogsManager.java
│   │   │       │   └── report/
│   │   │       │       ├── AllureAttachmentManager.java
│   │   │       │       ├── AllureBinaryManager.java
│   │   │       │       ├── AllureConstants.java
│   │   │       │       ├── AllureEnvironmentManager.java
│   │   │       │       └── AllureReportGenerator.java
│   │   │       └── validations/
│   │   │           ├── BaseAssertion.java
│   │   │           ├── Validation.java
│   │   │           └── Verification.java
│   ├── resources/
│   │   ├── META-INF/
│   │   │   └── services/
│   │   │       └── org.testng.ITestNGListener
│   │   ├── allure.properties
│   │   ├── db.properties
│   │   ├── environment.properties
│   │   ├── extensions/
│   │   │   └── HaramBlur.crx
│   │   ├── log4j2.properties
│   │   ├── seleniumGrid.properties
│   │   ├── video.properties
│   │   ├── waits.properties
│   │   └── webapp.properties
├── test/
│   ├── java/
│   │   └── com/automationexercices/tests/
│   │       ├── BaseTest.java
│   │       ├── api/
│   │       │   └── RegisterTestAPI.java
│   │       └── ui/
│   │           ├── CartTest.java
│   │           ├── CheckoutTest.java
│   │           ├── InvoiceTest.java
│   │           ├── LoginTest.java
│   │           ├── PaymentTest.java
│   │           ├── ProductDetailsTest.java
│   │           ├── ProductsTest.java
│   │           └── RegisterTest.java
│   └── resources/
│       └── test-data/
│           ├── cart-data.json
│           ├── checkout-data.json
│           ├── login-data.json
│           ├── product-details-data.json
│           ├── products-data.json
│           └── register-data.json


 ## Run the tests
 ``
 mvn test 
 ``
## 👨‍💻 Author ### Emad Maher
#### mid-level Test Automation Engineer 
| 📧 emad.maher.tester@gmail.com
🔗 LinkedIn: linkedin.com/in/emad-maher-5806b2254

