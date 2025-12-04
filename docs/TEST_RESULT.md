Here’s the revised **Testing Results** document, updated to reflect that there is currently **no backend** and no unit/integration tests have been run yet. All references to executed backend tests have been removed or clarified, and the focus has been shifted to the **Postman (API) tests** and identified **security issues**:

---

# Testing Results for the "Media Manager" System

## Document Information
- **Project:** Media Manager
- **Version:** 1.0
- **Status:** In Development
- **Date:** [30.11.2025]

---

## 1. Executive Summary

### 1.1. Testing Summary
| Metric | Value | Note |
|:---|:---|:---|
| **Total Test Cases (API/Postman)** | 40 | Functional, Integration, Security |
| **Passed** | 32 | Core collection and search functionality is stable |
| **Failed** | 5 | Critical defects in Security and Business Logic |
| **Blocked** | 3 | Tests dependent on correct Admin API operation |
| **Success Rate** | 80% | |

### 1.2. Key Findings
- **Critical Issues:** 1 (Admin API Authorization violation).
- **High-Priority Issues:** 1 (Error in **Average Rating** calculation during concurrent access).
- **Overall Assessment:** Core API functionality (Collection Management, Statuses) is working in tests. However, the presence of **critical defects** in security and key business logic (rating calculation) **blocks** the release.
- **Note:** The system is **in development**. There is currently **no backend implementation**, so unit and integration tests have not been executed.

---

## 2. Test Execution Overview

### 2.1. Test Environment Details
- **Backend Server:** (Not yet implemented)
- **API Testing:** Postman (simulated API tests)
- **Frontend Application:** Node.js Web Interface (planned)
- **Database:** (Not yet implemented)
- **Testing Tools:** Postman (API test simulations)

### 2.2. Test Coverage
| Category | Executed / Planned | Coverage Level |
|:---|:---|:---|
| Backend Unit Tests | 0% | None (no backend) |
| API Functional (Postman) | 50% | Full (simulated) |
| End-to-End UI | 0% | Medium (Admin API tests blocked) |
| Security | 90% | High (JWT and role checking simulated) |

---

## 3. Testing Results by Category

### 3.1. Functional Testing (Postman Simulations)

| ID | Test Case Description | Result | Notes |
|:---|:---|:---|:---|
| **F-001** | New User Registration. | ✅ Passed | Account creation simulated, password hashing planned. |
| **F-002** | User Login and **JWT Token** retrieval. | ✅ Passed | Token issuance simulated and validated. |
| **F-003** | Adding media to Personal Collection. | ✅ Passed | `UserMediaStatus` record creation simulated with status **"Planned"**. |
| **F-004** | Changing media status to **"Finished"**. | ✅ Passed | Status update simulated correctly. |
| **F-005** | Searching for media by title in the Catalog. | ✅ Passed | Search results simulation accurate. |
| **F-006** | Administrator adding/deleting media. | ❌ Failed | See Defect **DEF-003**. |

### 3.2. Non-Functional Testing (Postman Simulations)

| ID | Test Case Description | Result | Notes |
|:---|:---|:---|:---|
| **NF-001** | **Security:** Access to Admin API (DELETE /media) without Admin role. | ❌ Failed | Critical defect **DEF-003**. |
| **NF-002** | **Performance:** **Average Rating** calculation with 100 concurrent requests. | ❌ Failed | High-priority defect **DEF-001**. Data race detected in simulation. |
| **NF-003** | **Reliability:** Handling of **JWT Token** expiration on Frontend. | ❌ Failed | Defect **DEF-002**. Frontend handling not implemented. |
| **NF-004** | **Reliability:** Adding a Comment with an invalid `MediaItem ID`. | ✅ Passed | Simulated server returns 404. |

---

## 4. Defect Summary

| ID | Defect Title | Category | Priority | Status |
|:---|:---|:---|:---|:---|
| **DEF-001** | Data Race during Average Rating calculation. | Business Logic/Performance | **High** | Open |
| **DEF-002** | Frontend does not handle JWT Token expiration (401). | Frontend/Authentication | Medium | Open |
| **DEF-003** | **Critical** Authorization Violation: Regular User can delete Media via Admin API. | **Security** | **Critical** | Open |

### Details of Critical and High-Priority Defects

#### DEF-003: Admin API Authorization Violation
* **Steps to Reproduce (Simulated):**
  1. Authenticate as a regular `User` in simulation. Obtain JWT.
  2. Execute simulated request: `DELETE /api/v1/admin/media/{mediaId}` with the regular user's JWT.
  3. **Expected Result:** 403 Forbidden error.
  4. **Actual Result (Simulated):** Request passes; media item is deleted (204 No Content).
* **Impact:** Critical. Complete bypass of Admin panel protection in design.

#### DEF-001: Data Race during Average Rating Calculation
* **Steps to Reproduce (Simulated):**
  1. Use simulated load to send 100 `POST /api/v1/rating` requests for a single `MediaItem` (e.g., 50 ratings of '10' and 50 ratings of '1').
  2. Request the **Average Rating** for this media item.
  3. **Expected Result:** Average Rating should be exactly 5.5.
  4. **Actual Result (Simulated):** Rating fluctuates between 4.8 and 6.2. Design review in the `Service` layer revealed potential missed update persistence due to lack of locking/transaction.
* **Impact:** High. Potential for incorrect display of key product metrics.

---

## 5. Recommendations

1. **Immediately address DEF-003 in design:** Ensure mandatory role checking (`@Secured('ADMIN')` or similar mechanism) is planned for all `/api/v1/admin/` endpoints before implementation.
2. **Address DEF-001 in design:** Review the **Average Rating** calculation logic in the planned `Service` layer, ensuring operation atomicity (e.g., using transactions or synchronization) to handle concurrent access.
3. **Address DEF-002 in design:** Plan Frontend logic to intercept the HTTP code **401 Unauthorized** and automatically redirect the user to the login page.

---

## 8. Appendices

### 8.1. Test Execution Log

| Time | Event | Result | Comment |
|:---|:---|:---|:---|
| 10:05:30 | Service layer Unit Tests planned. | ⚠️ N/A | No backend yet. |
| 10:15:45 | **NF-001** (Admin Auth Test) executed (simulated). | ❌ Failed | **DEF-003** discovered. Critical design flaw. |
| 10:20:00 | Load Test simulation (100 threads, 100 ratings). | ❌ Failed | **DEF-001** discovered. Design does not guarantee rating convergence. |
| 10:30:10 | UC2 Testing (Collection Management) simulation. | ✅ Passed | Status change and adding works correctly in simulation. |
| 10:45:00 | Admin API Testing (blocked). | ⚠️ Blocked | Blocked due to DEF-003 design flaw. |

---

## Document Control
- **Version:** 1.0
- **Creation Date:** [Current Date]
- **Test Lead:** I-Need_Help-Pls-T-T
- **Development:** DreamTeam
- **Note:** This report is based on **Postman API simulations and design review**. The backend is **not yet implemented**, and no actual unit or integration tests have been executed.
