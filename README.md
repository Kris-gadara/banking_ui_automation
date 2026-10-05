# XYZ Bank UI Automation

UI automation for the [Global SQA XYZ Bank demo](https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login), built with **Java 21**, Selenium WebDriver, TestNG, and Maven.

## Overview

A small Page Object–style framework automates three customer flows: login, balance verification, and deposit. Tests run from `testng.xml` via Maven Surefire. Screenshots and TestNG reports capture execution evidence.

## Objective

- Automate login, balance check, and deposit on the demo banking site
- Separate locators, page actions, helpers, and tests
- Support CI with GitHub Actions (Java 21 + Maven)

## Application Under Test

| Item | Detail |
|------|--------|
| **Application** | XYZ Bank (Global SQA Banking Project) |
| **URL** | `https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login` |
| **Config** | `src/main/resources/Configs/stage.properties` (`xyzbank`) |

## Tech Stack

| Component | Version |
|-----------|---------|
| **Java** | **21** (Maven compiler source/target) |
| **Selenium Java** | 4.18.1 |
| **TestNG** | 7.9.0 |
| **WebDriverManager** | 5.7.0 |
| **Apache Commons IO** | 2.15.1 |
| **Maven Surefire** | 3.2.5 |

## Framework Structure

| Layer | Purpose |
|-------|---------|
| `Helpers` | WebDriver lifecycle, properties, shared variables |
| `WebLocators` | Element locators (`XYZLocators`) |
| `WebMethods` | Page interactions (`PageMethods`) |
| `reusableMethods` | Waits, delays, screenshots |
| `listeners` | TestNG console logging |
| `Login_Functionality` | Test class `LoginTest` |

**WebDriver:** Chrome (default in `testng.xml`) uses `WebDriverManager.chromedriver().setup()`. Optional Firefox/Edge branches also use WebDriverManager—no bundled driver binaries in the repo.

## Project Structure

```
.
├── .github/workflows/github-ci.yml
├── pom.xml
├── testng.xml
├── src/main/java/Helpers/
├── src/main/resources/Configs/stage.properties
├── src/test/java/
│   ├── Login_Functionality/LoginTest.java
│   ├── WebLocators/XYZLocators.java
│   ├── WebMethods/PageMethods.java
│   ├── reusableMethods/ReusableMethods.java
│   └── listeners/Listeners.java
├── Screenshots/
├── test-output/
└── README.md
```

## Automated Test Scenarios

Single test class, three TestNG methods (`browserName=chrome` in `testng.xml`):

| # | Method | Module |
|---|--------|--------|
| 1 | `Login` | Customer login as **Harry Potter** |
| 2 | `BalanceCheck` | Account name, number, balance, currency |
| 3 | `Deposit` | Deposit when balance is `0` (amount `10000`), assert **Deposit Successful** |

## Test Execution

**Prerequisites:** JDK **21**, Apache Maven, Google Chrome.

```bash
mvn clean test -DtestngFile=testng.xml
```

PowerShell:

```powershell
mvn clean test "-DtestngFile=testng.xml"
```

Maven reports: `target/surefire-reports/`. Portfolio copies: `test-output/` (updated after local runs).

## Test Results

**Latest local run** (after Java 21 migration, 2026-10-05 ~14:19–14:20 IST):

| Metric | Value |
|--------|------:|
| Total | 3 |
| Passed | 3 |
| Failed | 0 |
| Skipped | 0 |
| Errors | 0 |
| Suite time | ~60.6 s |

Passed: `Login`, `BalanceCheck`, `Deposit`.

## Screenshots / Reports

- **Screenshots:** `Screenshots/screenshot*.png` during test steps
- **Reports:** `test-output/index.html`, `emailable-report.html`, `testng-results.xml`

## Configuration

| File | Role |
|------|------|
| `stage.properties` | Application URL |
| `testng.xml` | Suite, `browserName`, test class |
| `pom.xml` | Dependencies, Java 21, Surefire suite file |

## Known Limitations

- **Maven PATH:** Maven 3.9.9 may be installed but not on system `PATH`; add Maven `bin` to PATH for CLI use (see below).
- **CDP warning:** Selenium 4.18.1 may warn about Chrome DevTools version mismatch; tests still passed in the latest run.
- **Fixed sleeps:** `Thread.sleep` plus explicit waits lengthen runs and can flake on slow networks.
- **Deposit logic:** Amount entry only when balance string equals `"0"`.
- **Demo dependency:** Third-party site and user data can change.

### Maven PATH (Windows)

If `mvn` is not recognized, add Maven’s `bin` folder to your user **Path** environment variable, for example:

`C:\Users\<YourUser>\AppData\Local\Temp\apache-maven-3.9.9\bin`

For a permanent install, extract [Apache Maven 3.9.x](https://maven.apache.org/download.cgi) under `C:\Program Files\Apache\maven`, set **MAVEN_HOME** to that folder, and add `%MAVEN_HOME%\bin` to **Path**. Restart the terminal after saving.

## Author

**Kriskumar Gadara**  
GitHub: [https://github.com/Kris-gadara](https://github.com/Kris-gadara)
