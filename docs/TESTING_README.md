# Testing Documentation: Media Manager Project

This document contains key information and recommendations for testing the "Media Manager" web application.

## Document Overview

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

## Quick Start Guide

### Launching Backend Services (REST API)

The Backend is implemented using **Java 17, Spring Boot**.

```bash
cd backend
# Command to run Spring Boot
./mvnw spring-boot:run
```

### Launching Frontend Application (Web Interface)

The Frontend is implemented using **Node.js**.

```bash
cd frontend
npm install
npm run dev
```

## Test Environment Setup

* **Java**: 17+
* **Node.js**: 18+
* **DBMS**: PostgreSQL
* **Containerization**: Docker & Docker Compose (recommended for quick DB deployment)
* **Unit Testing Tools**: JUnit 5 (for Java/Backend)
* **API Testing Tools**: Postman / REST Assured (for REST API testing)

## Test Categories

### Functional Testing

| Area | Description |
|:---|:---|
| ✅ **User Management** | Registration, Login, Authentication (JWT), Logout |
| ✅ **Collection Management** | Adding media to the collection, changing status (Planned, In Progress, Finished) |
| ✅ **Rating and Commenting** | Setting a personal rating (1-10), leaving/deleting a comment |
| ✅ **Catalog and Search** | Searching and filtering media by title, genre, release year |
| ✅ **Administrator Role** | Adding, editing, and deleting media in the Public Catalog |

### Integration Testing

| Area | Description |
|:---|:---|
| ✅ **API Integration** | `Controller` ↔ `Service` ↔ `Repository` ↔ `Database` interaction |
| ✅ **Data Integrity** | Checking relationships between entities (`User`, `MediaItem`, `UserMediaStatus`) |
| ✅ **Calculations** | Correctness of **Average Rating** calculation |

### Non-Functional Testing

| Area | Requirement |
|:---|:---|
| ✅ **Security** | Checking API endpoint protection, password encryption, **JWT token** validity |
| ✅ **Reliability** | System availability 99.9% of the time, server error rate (5xx) not exceeding 0.1% |
| ✅ **Usability** | Intuitive web interface |
| ✅ **Scalability** | Checking performance under load, especially during rating calculations |

## Example Test Cases

| ID | Name | Scenario / Instructions | Expected Result |
|:---|:---|:---|:---|
| **TC001** | User Registration | 1. Open the registration form. 2. Enter a unique email and password. 3. Submit. | User is successfully registered and receives a token. |
| **TC002** | Adding Media to Collection | 1. Authenticate. 2. Find a Media Item. 3. Click "Add to Collection". | A `UserMediaStatus` record is created with the status "Planned". |
| **TC003** | Status Change | 1. Go to Personal Collection. 2. Change media status to "Finished". | Media status is updated; system may prompt for a rating. |
| **TC004** | Admin: Add Media | 1. Log in with Administrator role. 2. Use the Admin Panel. 3. Add a new `MediaItem` to the Catalog. | Media Item is successfully added to the Public Catalog. |
| **TC005** | Invalid Authentication | 1. Attempt to log in with an incorrect password. | System returns an error (e.g., 401 Unauthorized) and does not issue a JWT token. |

## Known Issues / Risks

* **Data Race during Rating Calculation:** Risk of incorrect **Average Rating** calculation if multiple users submit ratings simultaneously. Operation atomicity must be ensured.
* **Relationship Integrity:** Violation of referential integrity when deleting a `MediaItem` that is referenced by `UserMediaStatus` and `Comment`.
* **JWT Expiration:** Incorrect handling of expired **JWT token** on the Frontend, which may lead to the necessity of re-login.

## Next Steps

* **Address Identified Issues:** Resolve data integrity and rating calculation problems.
* **Complete Remaining Tests:** Perform full E2E (End-to-End) testing of the user interface.
* **Update Documentation:** Add detailed testing results to `TEST_RESULTS.md`.
