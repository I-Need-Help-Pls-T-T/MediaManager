# Testing Results for the "Media Manager" System

## Document Information
- **Project:** Media Manager
- **Version:** 1.0
- **Status:** Complete
- **Date:** [Current Date]

---

## 1. Executive Summary

### 1.1. Testing Summary
| Metric | Value | Note |
|:---|:---|:---|
| **Total Test Cases** | 40 | Functional, Integration, Security |
| **Passed** | 32 | Core collection and search functionality is stable |
| **Failed** | 5 | Critical defects in Security and Business Logic |
| **Blocked** | 3 | Tests dependent on correct Admin API operation |
| **Success Rate** | 80% | |

### 1.2. Key Findings
- **Critical Issues:** 1 (Admin API Authorization violation).
- **High-Priority Issues:** 1 (Error in **Average Rating** calculation during concurrent access).
- **Overall Assessment:** Core functionality (Collection Management, Statuses) is working. However, the presence of **critical defects** in security and key business logic (rating calculation) **blocks** the release.
---


## 2. Test Execution Overview

### 2.1. Test Environment Details
- **Backend Server:** Java 17, Spring Boot REST API
- **Frontend Application:** Node.js Web Interface
- **Database:** PostgreSQL 15+
- **Testing Tools:** Postman (API tests), JUnit 5 (Unit tests), JMeter (Load)

### 2.2. Test Coverage
| Category | Executed / Planned | Coverage Level |
|:---|:---|:---|
| Backend Unit Tests | 85% | High |
| API Functional (Postman) | 100% | Full |
| End-to-End UI | 70% | Medium (Admin API tests blocked) |
| Security | 90% | High (JWT and role checking) |

---

## 3. Testing Results by Category

### 3.1. Functional Testing

| ID | Test Case Description | Result | Notes |
|:---|:---|:---|:---|
| **F-001** | New User Registration. | ✅ Passed | Account created, password hashed. |
| **F-002** | User Login and **JWT Token** retrieval. | ✅ Passed | Token successfully issued and valid. |
| **F-003** | Adding media to Personal Collection. | ✅ Passed | `UserMediaStatus` record created with status **"Запланировано" (Planned)**. |
| **F-004** | Changing media status to **"Завершено" (Finished)**. | ✅ Passed | Status correctly updated. |
| **F-005** | Searching for media by title in the Catalog. | ✅ Passed | Search results are accurate. |
| **F-006** | Administrator adding/deleting media. | ❌ Failed | See Defect **DEF-003**. |

### 3.2. Non-Functional Testing

| ID | Test Case Description | Result | Notes |
|:---|:---|:---|:---|
| **NF-001** | **Security:** Access to Admin API (DELETE /media) without Admin role. | ❌ Failed | Critical defect **DEF-003**. |
| **NF-002** | **Performance:** **Average Rating** calculation with 100 concurrent requests. | ❌ Failed | High-priority defect **DEF-001**. Data race detected. |
| **NF-003** | **Reliability:** Handling of **JWT Token** expiration on Frontend. | ❌ Failed | Defect **DEF-002**. Frontend does not react to 401. |
| **NF-004** | **Reliability:** Adding a Comment with an invalid `MediaItem ID`. | ✅ Passed | Server returns 404. |

---

## 4. Defect Summary

| ID | Defect Title | Category | Priority | Status |
|:---|:---|:---|:---|:---|
| **DEF-001** | Data Race during Average Rating calculation. | Business Logic/Performance | **High** | Open |
| **DEF-002** | Frontend does not handle JWT Token expiration (401). | Frontend/Authentication | Medium | Open |
| **DEF-003** | **Critical** Authorization Violation: Regular User can delete Media via Admin API. | **Security** | **Critical** | Open |

### Details of Critical and High-Priority Defects

#### DEF-003: Admin API Authorization Violation
* **Steps to Reproduce:**
  1. Authenticate as a regular `User`. Obtain JWT.
  2. Execute request: `DELETE /api/v1/admin/media/{mediaId}` with the regular user's JWT.
  3. **Expected Result:** 403 Forbidden error.
  4. **Actual Result:** Request passes; media item is deleted from the Catalog (204 No Content).
* **Impact:** Critical. Complete bypass of Admin panel protection.

#### DEF-001: Data Race during Average Rating Calculation
* **Steps to Reproduce:**
  1. Use JMeter to simultaneously send 100 `POST /api/v1/rating` requests for a single `MediaItem` (e.g., 50 ratings of '10' and 50 ratings of '1').
  2. Request the **Average Rating** for this media item.
  3. **Expected Result:** Average Rating should be exactly 5.5.
  4. **Actual Result:** Rating fluctuates between 4.8 and 6.2. Logs in the `Service` layer revealed missed update persistence due to lack of locking/transaction.
* **Impact:** High. Incorrect display of key product metrics.

---

## 5. Recommendations

1. **Immediately fix DEF-003:** Implement mandatory role checking (`@Secured('ADMIN')` or similar mechanism) on all `/api/v1/admin/` endpoints.
2. **Fix DEF-001:** Review the **Average Rating** calculation logic in the `Service` layer, ensuring operation atomicity (e.g., using transactions or synchronization) to handle concurrent access.
3. **Refine DEF-002:** Implement Frontend logic to intercept the HTTP code **401 Unauthorized** and automatically redirect the user to the login page.

---

## 8. Appendices

### 8.1. Test Execution Log

| Time | Event | Result | Comment |
|:---|:---|:---|:---|
| 10:05:30 | Service layer Unit Tests launched. | ✅ Passed | 95% passed. |
| 10:15:45 | **NF-001** (Admin Auth Test) executed. | ❌ Failed | **DEF-003** discovered. Critical. |
| 10:20:00 | Load Test launched (100 threads, 100 ratings). | ❌ Failed | **DEF-001** discovered. Rating does not converge. |
| 10:30:10 | UC2 Testing (Collection Management) E2E. | ✅ Passed | Status change and adding works correctly. |
| 10:45:00 | Admin API Testing (blocked). | ⚠️ Blocked | Blocked due to DEF-003. |

---

## Document Control
- **Version:** 1.0
- **Creation Date:** [2025-11-30]
- **Test Lead:** I-Need_Help-Pls-T-T
- **Development:** DreamTeam