# Media Manager - Glossary of Key Terms

This glossary presents the core concepts and terms used in the "Media Manager" project.

---

## Media Item

A general term for a unit of content cataloged in the system (e.g., Book, Movie, TV Show, Game). It has common attributes such as title, description, release year, and genres.

## Public Catalog

The section of the system containing all **Media Items** added by the **Administrator**. Available for search and filtering by all **Users**.

## Personal Collection

A list of **Media Items** that a **User** has added for personal tracking.

## Media Status

An indicator used by the **User** to track the progress of a **Media Item** in their **Personal Collection** (e.g., "Planned", "In Progress", "Finished").

## Rating

* **Personal Rating:** A value from 1 to 10 that a **User** assigns to a **Media Item** in their **Personal Collection**.
* **Average Rating:** The overall aggregated rating of a **Media Item**, calculated based on all users' **Personal Ratings**.

## Comment

A text message that a **User** can leave on the page of a **Media Item**.

## Account

The primary unit representing a user in the system. Can have the roles of **Registered User** or **Administrator**.

## Administrator (Admin)

A user with elevated privileges who is responsible for populating and moderating the **Public Catalog** (adding, editing, and deleting **Media Items**).

## Authentication

The process of verifying a **User's** identity, typically by matching a username (or email) and password. Successful authentication leads to the issuance of a session token (**JWT**).

## Authorization

The process of determining and ensuring what actions an **Authenticated User** is permitted to perform (e.g., adding media to a collection, or, for an **Administrator**, managing the catalog).

---

## Technical Terms

## Controller

A component that provides the **REST API** interface, which processes incoming client requests, calls the **Business Logic**, and returns responses.

## Service

A component that encapsulates the core **Business Logic** of the application (e.g., calculating the **Average Rating** or changing media status), working with entities and **DTOs**.

## DTO (Data Transfer Object) / Mapper

An object used to transfer data between subsystems (e.g., between the **Controller** and the **Service**). The **Mapper** is responsible for transforming between entity objects and **DTOs**.

## JWT (JSON Web Token)

A compact, URL-safe token, typically used to securely transmit information between parties as a JSON object, usually serving as an **Authentication** mechanism.