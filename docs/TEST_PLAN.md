## 1\. Testing Results for the "Media Manager" System (TEST\_RESULT.md)

### Document Information

- **Project:** Media Manager
- **Version:** 1.0
- **Status:** Complete
- **Date:** [Current Date]

-----

### 1\. Executive Summary

#### 1.1. Testing Summary

| Metric | Value | Note |
|:---|:---|:---|
| **Total Test Cases** | 40 | Functional, Integration, Security |
| **Passed** | 32 | Core collection and search functionality is stable |
| **Failed** | 5 | Critical defects in Security and Business Logic |
| **Blocked** | 3 | Tests dependent on correct Admin API operation |
| **Success Rate** | 80% | |

#### 1.2. Key Findings

- **Critical Issues:** 1 (Admin API Authorization violation).
- **High-Priority Issues:** 1 (Error in **Average Rating** calculation during concurrent access).
- **Overall Assessment:** Core functionality (Collection Management, Statuses) works. However, the presence of **critical defects** in security and key business logic (rating calculation) **blocks** the release.

-----

### 2\. Test Execution Overview

#### 2.1. Test Environment Details

- **Backend Server:** Java 17, Spring Boot REST API
- **Frontend Application:** Node.js Web Interface
- **Database:** PostgreSQL 15+
- **Testing Tools:** Postman (API tests), JUnit 5 (Unit tests), JMeter (Load)

#### 2.2. Test Coverage

| Category | Executed / Planned | Coverage Level |
|:---|:---|:---|
| Backend Unit Tests | 85% | High |
| API Functional (Postman) | 100% | Full |
| End-to-End UI | 70% | Medium (Admin API tests blocked) |
| Security | 90% | High (JWT and role checking) |

-----

### 3\. Testing Results by Category

#### 3.1. Functional Testing

| ID | Test Case Description | Result | Notes |
|:---|:---|:---|:---|
| **F-001** | New User Registration. | ✅ Passed | Account created, password hashed. |
| **F-002** | User Login and **JWT Token** retrieval. | ✅ Passed | Token successfully issued and valid. |
| **F-003** | Adding media to Personal Collection. | ✅ Passed | `UserMediaStatus` record created with status **"Запланировано" (Planned)**. |
| **F-004** | Changing media status to **"Завершено" (Finished)**. | ✅ Passed | Status correctly updated. |
| **F-005** | Searching for media by title in the Catalog. | ✅ Passed | Search results are accurate. |
| **F-006** | Administrator adding/deleting media. | ❌ Failed | See Defect **DEF-003**. |

#### 3.2. Non-Functional Testing

| ID | Test Case Description | Result | Notes |
|:---|:---|:---|:---|
| **NF-001** | **Security:** Access to Admin API (DELETE /media) without Admin role. | ❌ Failed | Critical defect **DEF-003**. |
| **NF-002** | **Performance:** **Average Rating** calculation with 100 concurrent requests. | ❌ Failed | High-priority defect **DEF-001**. Data race detected. |
| **NF-003** | **Reliability:** Handling of **JWT Token** expiration on Frontend. | ❌ Failed | Defect **DEF-002**. Frontend does not react to 401. |
| **NF-004** | **Reliability:** Adding a Comment with an invalid `MediaItem ID`. | ✅ Passed | Server returns 404. |

-----

### 4\. Defect Summary

| ID | Defect Title | Category | Priority | Status |
|:---|:---|:---|:---|:---|
| **DEF-001** | Data Race during Average Rating calculation. | Business Logic/Performance | **High** | Open |
| **DEF-002** | Frontend does not handle JWT Token expiration (401). | Frontend/Authentication | Medium | Open |
| **DEF-003** | **Critical** Authorization Violation: Regular User can delete Media via Admin API. | **Security** | **Critical** | Open |

#### Details of Critical and High-Priority Defects

**DEF-003: Admin API Authorization Violation**

* **Steps to Reproduce:**
    1.  Authenticate as a regular `User`. Obtain JWT.
    2.  Execute request: `DELETE /api/v1/admin/media/{mediaId}` with the regular user's JWT.
    3.  **Expected Result:** 403 Forbidden error.
    4.  **Actual Result:** Request passes; media item is deleted from the Catalog (204 No Content).
* **Impact:** Critical. Complete bypass of Admin panel protection.

**DEF-001: Data Race during Average Rating Calculation**

* **Steps to Reproduce:**
    1.  Use JMeter to simultaneously send 100 `POST /api/v1/rating` requests for a single `MediaItem` (e.g., 50 ratings of '10' and 50 ratings of '1').
    2.  Request the **Average Rating** for this media item.
    3.  **Expected Result:** Average Rating should be exactly 5.5.
    4.  **Actual Result:** Rating fluctuates between 4.8 and 6.2. Logs in the `Service` layer revealed missed update persistence due to lack of locking/transaction.
* **Impact:** High. Incorrect display of key product metrics.

-----

### 5\. Recommendations

1.  **Immediately fix DEF-003:** Implement mandatory role checking (`@Secured('ADMIN')` or similar mechanism) on all `/api/v1/admin/` endpoints.
2.  **Fix DEF-001:** Review the **Average Rating** calculation logic in the `Service` layer, ensuring operation atomicity (e.g., using transactions or synchronization) to handle concurrent access.
3.  **Refine DEF-002:** Implement Frontend logic to intercept the HTTP code **401 Unauthorized** and automatically redirect the user to the login page.

-----

### 8\. Appendices

#### 8.1. Test Execution Log

| Time | Event | Result | Comment |
|:---|:---|:---|:---|
| 10:05:30 | Service layer Unit Tests launched. | ✅ Passed | 95% passed. |
| 10:15:45 | **NF-001** (Admin Auth Test) executed. | ❌ Failed | **DEF-003** discovered. Critical. |
| 10:20:00 | Load Test launched (100 threads, 100 ratings). | ❌ Failed | **DEF-001** discovered. Rating does not converge. |
| 10:30:10 | UC2 Testing (Collection Management) E2E. | ✅ Passed | Status change and adding works correctly. |
| 10:45:00 | Admin API Testing (blocked). | ⚠️ Blocked | Blocked due to DEF-003. |

-----

### Document Control

- **Version:** 1.0
- **Creation Date:** [2025-11-30]
- **Test Lead:** I-Need\_Help-Pls-T-T
- **Development:** DreamTeam

-----

## 2\. Testing Documentation: Media Manager Project (TESTING\_README.md)

This document contains key information and recommendations for testing the "Media Manager" web application.

### Document Overview

### 📋 TEST\_PLAN.md

The Test Plan should include:

* Objectives and scope of testing.
* Testing strategy and approaches.
* Quality criteria (e.g., Reliability, Security).
* Risk assessment and mitigation measures.

### 📊 TEST\_RESULTS.md

Should contain detailed results of executed test cases:

* Pass / Fail metrics.
* Testing results for each component.
* Defect tracking and analysis.

### 📝 TESTING\_SUMMARY.md

Provides a high-level overview:

* Key findings for functional and non-functional testing.
* Identified defects.
* Recommendations and conclusions.

### Quick Start Guide

#### Launching Backend Services (REST API)

The Backend is implemented using **Java 17, Spring Boot**.

```bash
cd backend
# Command to run Spring Boot
./mvnw spring-boot:run
```

#### Launching Frontend Application (Web Interface)

The Frontend is implemented using **Node.js**.

```bash
cd frontend
npm install
npm run dev
```

### Test Environment Setup

* **Java**: 17+
* **Node.js**: 18+
* **DBMS**: PostgreSQL
* **Containerization**: Docker & Docker Compose (recommended for quick DB deployment)
* **Unit Testing Tools**: JUnit 5 (for Java/Backend)
* **API Testing Tools**: Postman / REST Assured (for REST API testing)

### Test Categories

#### Functional Testing

| Area | Description |
|:---|:---|
| ✅ **User Management** | Registration, Login, Authentication (JWT), Logout |
| ✅ **Collection Management** | Adding media to the collection, changing status (Planned, In Progress, Finished) |
| ✅ **Rating and Commenting** | Setting a personal rating (1-10), leaving/deleting a comment |
| ✅ **Catalog and Search** | Searching and filtering media by title, genre, release year |
| ✅ **Administrator Role** | Adding, editing, and deleting media in the Public Catalog |

#### Integration Testing

| Area | Description |
|:---|:---|
| ✅ **API Integration** | `Controller` ↔ `Service` ↔ `Repository` ↔ `Database` interaction |
| ✅ **Data Integrity** | Checking relationships between entities (`User`, `MediaItem`, `UserMediaStatus`) |
| ✅ **Calculations** | Correctness of **Average Rating** calculation |

#### Non-Functional Testing

| Area | Requirement |
|:---|:---|
| ✅ **Security** | Checking API endpoint protection, password encryption, **JWT token** validity |
| ✅ **Reliability** | System availability 99.9% of the time, server error rate (5xx) not exceeding 0.1% |
| ✅ **Usability** | Intuitive web interface |
| ✅ **Scalability** | Checking performance under load, especially during rating calculations |

### Example Test Cases

| ID | Name | Scenario / Instructions | Expected Result |
|:---|:---|:---|:---|
| **TC001** | User Registration | 1. Open the registration form. 2. Enter a unique email and password. 3. Submit. | User is successfully registered and receives a token. |
| **TC002** | Adding Media to Collection | 1. Authenticate. 2. Find a Media Item. 3. Click "Add to Collection". | A `UserMediaStatus` record is created with the status "Planned". |
| **TC003** | Status Change | 1. Go to Personal Collection. 2. Change media status to "Finished". | Media status is updated; system may prompt for a rating. |
| **TC004** | Admin: Add Media | 1. Log in with Administrator role. 2. Use the Admin Panel. 3. Add a new `MediaItem` to the Catalog. | Media Item is successfully added to the Public Catalog. |
| **TC005** | Invalid Authentication | 1. Attempt to log in with an incorrect password. | System returns an error (e.g., 401 Unauthorized) and does not issue a JWT token. |

### Known Issues / Risks

* **Data Race during Rating Calculation:** Risk of incorrect **Average Rating** calculation if multiple users submit ratings simultaneously. Operation atomicity must be ensured.
* **Relationship Integrity:** Violation of referential integrity when deleting a `MediaItem` that is referenced by `UserMediaStatus` and `Comment`.
* **JWT Expiration:** Incorrect handling of expired **JWT token** on the Frontend, which may lead to the necessity of re-login.

### Next Steps

* **Address Identified Issues:** Resolve data integrity and rating calculation problems.
* **Complete Remaining Tests:** Perform full E2E (End-to-End) testing of the user interface.
* **Update Documentation:** Add detailed testing results to `TEST_RESULTS.md`.

-----

## 3\. Testing Plan for the "Media Manager" System (TEST\_PLAN.md)

### Document Information

- **Project:** Media Manager
- **Version:** 1.0
- **Status:** Approved
- **Date of last update:** [Current Date]

-----

### 1\. Introduction

#### 1.1. Purpose

This Testing Plan describes the strategy, goals, scope, and methodologies that will be used for the comprehensive verification of the **"Media Manager"** web application. The objective is to ensure that all functional and non-functional requirements comply with the Requirements Specification.

#### 1.2. Scope of Application

The testing plan covers the entire "Media Manager" system, including:

* Backend REST API, developed with **Java Spring Boot**.
* Frontend web interface, developed with **Node.js**.
* Interaction with the **PostgreSQL** database.
* Authentication (JWT) and Authorization (Role **User** and **Admin**) mechanisms.
* All Use Cases described in the documentation (Collection Management, Commenting, Rating).

#### 1.3. References

* Requirements Specification (Requirements\_Document\_-\_rus.docx)
* Domain Model Class Diagram (DOMAIN\_MODEL.md)
* Activity and Sequence Diagrams (ACTIVITY\_DIAGRAMS.md, SEQUENCE\_DIAGRAMS.md)
* Glossary of key terms (CLOSSARY.md)

-----

### 2\. Testing Objectives

#### 2.1. Primary Objectives

* Verify that key **functional requirements** (Collection Management, Commenting, Rating) are implemented according to the requirements.
* Confirm that the system provides the required level of **Security** (API protection, password hashing, **JWT token** validity).
* Validate the correctness of the **business logic**, especially the Average Rating Calculation and Media Item Status Management.

#### 2.2. Secondary Objectives

* Assess the **Reliability** and **Performance** of the system under expected load.
* Ensure an intuitive **Web Interface** and compliance with **Usability** requirements.

-----

### 3\. Testing Scope

| Component / Scenario | Included (In Scope) | Excluded (Out of Scope) |
|:---|:---|:---|
| **Collection Management** | Adding, deleting, changing media status. | Integration with external APIs for media data retrieval. |
| **Authentication/Authorization** | Registration, Login, JWT validation, **User** and **Admin** Roles. | Two-Factor Authentication (2FA). |
| **Rating Calculation** | Calculation of **Average Rating** and **Personal Rating** (1-10). | Moderation of comments and ratings. |
| **Catalog** | Search and filtering of media by title, genre, release year, type. | Media content streaming (video/book streaming). |

-----

### 4\. Testing Strategy

Testing will be based on **Risk (Risk-Based Testing)**, giving the highest priority to critical areas: Security (Authorization) and Business Logic (Rating Calculation).

#### 4.1. Testing Levels

| Level | Object of Testing | Method | Tools |
|:---|:---|:---|:---|
| **Unit Testing** | Backend (Service, Repository logic). | Automated, "white box". | JUnit 5 |
| **Integration Testing** | `Controller` ↔ `Service` ↔ `Database` interaction. | Automated, "grey box". | Postman, REST Assured |
| **System Testing** | API End-to-End scenarios (e.g., UC1: Adding Media to Collection). | "Black box" / Scenarios. | Postman, Cypress (for E2E) |
| **User Acceptance Testing (UAT)** | Frontend and key business scenarios. | Manual, "black box". | User testers. |

-----

### 5\. Test Types

#### 5.1. Functional Testing

* **Role and Access Testing:** Strict verification that only the Administrator can manage the Catalog.
* **Collection Testing:** Verification of all status transitions (Planned -\> In Progress -\> Finished).
* **Business Logic Testing:** Validation of the accuracy of **Average Rating** calculation (special attention to load and concurrency).
* **Input Validation Testing:** Checking all forms (Registration, Comment) for correct handling of invalid data (SQL injection, XSS).

#### 5.2. Non-Functional Testing

* **Security Testing:** Checking API protection, forced password hashing, testing **JWT token** expiration and revocation.
* **Reliability Testing:** Simulating DB and API failures to verify recovery mechanisms and ensure 99.9% availability.
* **Performance Testing:**
    * **Load Testing:** Checking API response under concurrent operation of 100+ users.
    * **Stress Testing:** Determining the system's breaking point when calculating complex aggregations (Average Rating, Filtering by multiple parameters).

-----

### 6\. Test Environment and Tools

#### 6.1. Test Environment

| Component | Specification | Note |
|:---|:---|:---|
| **Backend** | Java 17, Spring Boot | REST API |
| **Frontend** | Node.js, Web Browser (Chrome, Firefox) | Web Interface |
| **Database** | PostgreSQL 15+ | **Must be isolated** from the Production environment. |
| **Deployment** | Docker/Docker Compose | Used for deploying test components (DB, Backend). |

#### 6.2. Testing Tools

* **Unit/Integration:** JUnit 5, Mockito, REST Assured.
* **API/Functional:** Postman (for manual and automated API testing).
* **Performance:** JMeter or LoadRunner.
* **E2E (UI):** Cypress or Selenium (for automation of End-to-End scenarios).
* **Defect Management:** [Your tracking system (JIRA, Redmine, etc.)].

-----

### 7\. Risks and Mitigation Measures

| Risk | Impact | Probability | Mitigation Measures |
|:---|:---|:---|:---|
| **R1: Incorrect Average Rating calculation** | High (distortion of a key metric). | Medium | Implement Unit Tests for Service logic; Conduct stress testing for concurrent access. |
| **R2: Authorization Violation (Admin Access)** | Critical (threat to Catalog integrity). | High | Implement mandatory Security Testing on all Admin endpoints. |
| **R3: DB Scalability issues** | High (slowdown with growth in media/users). | Medium | Conduct Performance Testing; Optimize PostgreSQL queries. |
| **R4: Data Integrity issues** | Medium (broken relationships upon deletion). | Low | Implement cascading deletion or relationship checking logic in the `Service` layer. |

-----

### 8\. Exit Criteria

The Test Phase is considered complete, and the product can be handed over to the next stage (e.g., Production), only when the following conditions are met:

1.  **Coverage:** All **Critical** and **High-Priority** test cases are executed and successfully passed.
2.  **Defects:**
    * **Critical** and **High-Priority** defects are absent (0 Open Defects).
    * All **Medium-Priority** defects are either fixed or have an acceptable workaround documented in **TESTING\_SUMMARY.md**.
3.  **Performance:** Compliance with stated performance requirements (e.g., API response \< 500ms for 95% of requests).
4.  **UAT:** User Acceptance Testing (UAT) is successfully completed and signed off by the Client/Sponsor.
5.  **Documentation:** All test artifacts (**TEST\_RESULTS.md**, **TESTING\_SUMMARY.md**) are up-to-date.