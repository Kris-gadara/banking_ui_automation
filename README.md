# XYZ Bank UI Automation

Portfolio UI automation project for the [Global SQA XYZ Bank demo](https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login), using Java, Selenium WebDriver, TestNG, and Maven.

## Overview

A compact Page Object–style framework covers customer login, account details validation, and a deposit flow. Tests are driven by `testng.xml` and executed with Maven Surefire. Screenshots are saved during runs; TestNG HTML/XML reports document results.

## Objective

- Automate core customer journeys on the public demo banking application
- Keep locators, page actions, helpers, and tests in separate layers
- Capture screenshots and TestNG reports as execution evidence
- Support CI via GitHub Actions (Maven + TestNG suite)

## Application Under Test

| Item | Detail |
|------|--------|
| **Application** | XYZ Bank (Global SQA Banking Project demo) |
| **URL** | `https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login` |
| **Configuration** | `src/main/resources/Configs/stage.properties` (`xyzbank` key) |

## Tech Stack

| Component | Version / notes |
|-----------|-----------------|
| **JDK (local verification)** | OpenJDK **21** (Temurin) |
| **Maven compiler (bytecode)** | Source/target **8** in `pom.xml` |
| **Selenium Java** | 4.18.1 |
| **TestNG** | 7.9.0 |
| **WebDriverManager** | 5.7.0 — used for **Chrome** in `WebDriverHelper` |
| **Apache Commons IO** | 2.15.1 |
| **Maven Surefire** | 3.2.5 — suite file via `-DtestngFile=testng.xml` |

## Framework Structure

| Package / folder | Responsibility |
|------------------|----------------|
| `Helpers` | `WebDriverHelper`, `ReadProperties`, `GlobalVariables` |
| `WebLocators` | `XYZLocators` — centralized `By` definitions |
| `WebMethods` | `PageMethods` — click, select, navigate, text entry, getText |
| `reusableMethods` | `ReusableMethods` — explicit waits, fixed delays, screenshots |
| `listeners` | `Listeners` — TestNG `ITestListener` console logging |
| `Login_Functionality` | `LoginTest` — end-to-end scenarios |

**WebDriver behavior**

- **Chrome:** `WebDriverManager.chromedriver().setup()` then `ChromeDriver`
- **Firefox / Edge:** local executables under `Tools/FirefoxDrivers` and `Tools/MsEdgeDrivers`

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
├── Tools/                 # geckodriver / msedgedriver (Chrome via WebDriverManager)
├── Screenshots/           # PNG captures from test runs
├── test-output/           # TestNG HTML/XML reports (synced from latest Maven run)
└── README.md
```

## Automated Test Scenarios

Implemented in `Login_Functionality.LoginTest` (`testng.xml` → `browserName=chrome`):

| Priority | Test method | What it validates |
|----------|-------------|-------------------|
| 0 | `Login` | Open app → Customer Login → select **Harry Potter** → login; screenshots |
| 1 | `BalanceCheck` | Account name **Harry Potter**; reads account number, balance, currency; logs when number contains `004` |
| 2 | `Deposit` | Deposit flow; if balance is `"0"`, enters `10000`; asserts **Deposit Successful**; re-runs balance checks |

## Test Execution

**Prerequisites:** JDK (8+ bytecode; verified on **JDK 21**), **Apache Maven** on `PATH`, Google Chrome installed.

From the project root:

```bash
mvn clean test -DtestngFile=testng.xml
```

On **PowerShell**, quote the property:

```powershell
mvn clean test "-DtestngFile=testng.xml"
```

**Latest verified run (local):**

| Field | Value |
|-------|--------|
| **Date/time** | 2026-10-05, ~14:03–14:04 IST |
| **Command** | `mvn clean test "-DtestngFile=testng.xml"` |
| **JDK** | 21.0.12.1 (Temurin) |
| **Maven** | 3.9.9 |
| **Chrome** | 154.0.8037.93 |
| **Browser driver** | WebDriverManager (Chrome) |

Maven writes Surefire reports under `target/surefire-reports/`; copies of the latest run are also kept in `test-output/` for portfolio viewing.

## Test Results

Results from the **latest execution** above (`target/surefire-reports/testng-results.xml`):

| Metric | Count |
|--------|------:|
| **Total tests** | 3 |
| **Passed** | 3 |
| **Failed** | 0 |
| **Skipped** | 0 |
| **Errors** | 0 |
| **Suite duration** | ~60.1 s (60,061 ms) |

All three methods passed: `Login`, `BalanceCheck`, `Deposit`.

## Screenshots / Reports

**Screenshots (`Screenshots/`):**

- Latest run (2026-10-05 ~14:03–14:04 IST): `screenshot0.png` … `screenshot4.png` (overwritten/created during that run)
- Older files from a prior run: `ScreenshotNum0.png`, `ScreenshotNum1.png` (2026-10-05 ~13:38 IST)

**Reports:**

| Location | Purpose |
|----------|---------|
| `test-output/index.html` | TestNG dashboard (latest Maven run copied here) |
| `test-output/emailable-report.html` | Summary table |
| `test-output/testng-results.xml` | Machine-readable results with timestamps |
| `target/surefire-reports/` | Primary output from `mvn test` (regenerated each build) |

The `test-output/Default suite/` folder is leftover from an earlier IDE-style run and is **not** from the latest Maven execution.

## Configuration

| File | Role |
|------|------|
| `stage.properties` | Application URL |
| `testng.xml` | Suite, parallelism, `browserName` parameter |
| `pom.xml` | Dependencies; Surefire `${testngFile}` |

## Known Limitations

- **Maven on PATH:** Maven may be installed but not on the system `PATH`; use full path to `mvn` or add Maven to `PATH` for CLI/GitHub Actions consistency.
- **Report locations:** `mvn test` writes to `target/surefire-reports/`; IDE runs may update project-root `test-output/` directly.
- **CDP warning:** Selenium 4.18.1 may log a Chrome DevTools Protocol version warning for Chrome 154; tests still passed in the latest run.
- **Waits:** Heavy use of `Thread.sleep` alongside explicit waits increases duration and can cause flakiness on slow networks.
- **Deposit logic:** Amount is entered only when balance equals `"0"`; other balances follow a different path.
- **Static `@Test` methods:** Unusual TestNG pattern; works today but is non-idiomatic.
- **CI JDK:** `.github/workflows/github-ci.yml` uses JDK **11** while local verification used **21**; POM bytecode remains **8**.
- **Third-party demo:** Depends on Global SQA hosting and demo user data (e.g. Harry Potter account state).

## Author

**Kriskumar Gadara**  
GitHub: [https://github.com/Kris-gadara](https://github.com/Kris-gadara)
