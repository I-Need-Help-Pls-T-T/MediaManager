# Media Manager – Use Case Analysis

## Actors

### **Guest**
A user who is not logged in (not authenticated). Can view the **Public Catalog**, search for media, and register.

### **Registered User**
An authenticated user. Can manage their **Personal Collection**, change media status, set **Personal Ratings**, leave **Comments**, and view the **Average Rating**.

### **Admin**
A user with elevated privileges. Can manage the **Public Catalog** (adding, editing, deleting Media Items) and view system reports.

### **System**
Represents the internal business logic and services (e.g., rating calculation, JWT validation).

---

## Use Case Scenarios

### **UC1: User Registration**

**Actor:** Guest
**Precondition:** The user is not authenticated and is on the registration page.

**Flow of Events:**
1. The user opens the registration page.
2. The form contains fields: Email, Password, Password Confirmation, Registration Button.
3. The user fills out the form and clicks "Register".
4. The System **validates** the data:
    * All required fields are filled.
    * The Email has a correct format and is not already registered.
    * The Password meets security requirements (hashing).
    * The Password matches the confirmation.
5. If validation is successful, a new `User` record is created in the database.
6. The System returns a success message and redirects to the login page.

**Alternative Flows:**
* Invalid Email or already registered → The System displays an error: "This email is already associated with an account."
* Weak password → "The password must contain at least 8 characters, numbers, and letters."

**Postcondition:** A new user is created in the system and can log in.

---

### **UC2: User Login and Authentication**

**Actor:** Guest
**Precondition:** The user is on the login page.

**Flow of Events:**
1. The user enters their Email and Password.
2. Clicks "Log In".
3. The System **validates** the credentials using the hashed password from the DB.
4. If the data is correct, the System generates a **JWT token**.
5. The System sends the JWT token to the Client Interface.
6. The System redirects the user to the main page / their Collection page.

**Alternative Flows:**
* Incorrect login or password → The System displays the error "Incorrect login or password" and does not issue a JWT.

**Postcondition:** The user is authenticated; all subsequent requests are secured by JWT.

---

### **UC3: Adding Media to Personal Collection**

**Actor:** Registered User
**Precondition:** The user is authenticated and is viewing a Media Item page in the Public Catalog.

**Flow of Events:**
1. The user clicks the "Add to Collection" button.
2. The System checks: if the Media Item is already in the collection.
3. If not, the System creates a new **`UserMediaStatus`** record with the following attributes:
    * `mediaItemId` = ID of the current media item.
    * `userId` = ID of the authenticated user.
    * `status` = **"Запланировано" (Planned)**.
4. The System saves the record to the DB.
5. The System updates the interface, showing the new status and options for further management.

**Alternative Flows:**
* Media already in collection → The System displays: "Already in your collection. Current status: [Current Status]".

**Postcondition:** The Media Item is added to the user's Personal Collection with the status "Planned".

---

### **UC4: Changing Media Status in Collection**

**Actor:** Registered User
**Precondition:** The user is authenticated and is viewing their Personal Collection.

**Flow of Events:**
1. The user finds a Media Item in their collection.
2. The user selects a new status (e.g., "In Progress" or "Finished") from a list.
3. The System sends a request to update the **`UserMediaStatus`** in the DB.
4. If the new status is **"Завершено" (Finished)**, the System may prompt the user to input a **Personal Rating** (1-10).
5. The System updates the record in the DB and returns a confirmation.

**Postcondition:** The status of the Media Item in the user's collection is changed.

---

### **UC5: Leaving a Rating and Comment**

**Actor:** Registered User
**Precondition:** The user is authenticated and is viewing the Media Item page.

**Flow of Events:**
1. The user enters a **Personal Rating** (1 to 10) and/or a text **Comment**.
2. The user clicks "Submit/Update".
3. The System **validates** the input (rating range, comment length).
4. The System saves/updates the **Personal Rating** in the **`UserMediaStatus`** record.
5. The System creates/saves a new **`Comment`** record.
6. The System **recalculates the Average Rating** for this `MediaItem` based on all Personal Ratings in the DB.
7. The System displays the updated **Average Rating** and the new comment.

**Postcondition:** The Personal Rating and/or comment are saved, and the media item's Average Rating is updated.

---

### **UC6: Catalog Search and Filtering**

**Actor:** Guest, Registered User
**Precondition:** The user is on the Public Catalog page.

**Flow of Events:**
1. The user enters a query (e.g., title) in the search bar.
2. The user applies filters (e.g., Genre, Release Year, Media Type).
3. The System sends a request to the DB.
4. The System retrieves the corresponding `MediaItem`s and returns the results.
5. The System displays a list of media matching the search and filters.

**Postcondition:** The user sees a filtered list of Media Items.

---

### **UC7: Public Catalog Management (Admin)**

**Actor:** Admin
**Precondition:** The Admin is authenticated (Admin role) and is in the Admin Panel.

**Flow of Events:**
1. The Admin selects an action: **Add**, **Edit**, or **Delete** a Media Item.
2. *For Add/Edit:* The Admin fills/updates the `MediaItem` fields (Title, Description, Genre, etc.).
3. The Admin clicks "Save/Delete".
4. The System **validates** the data (only for Add/Edit).
5. The System performs the operation (INSERT, UPDATE, or DELETE) in the DB.
6. The System checks for **Authorization** (Admin role) to confirm the action.
7. The System returns a success message and updates the Catalog.

**Alternative Flows:**
* Unauthenticated user attempts access → The System returns a 403 Forbidden error.
* Deleting a Media Item with references → The System deletes associated `UserMediaStatus` and `Comment` records or blocks deletion (depending on business rules).

**Postcondition:** The Public Catalog is updated.