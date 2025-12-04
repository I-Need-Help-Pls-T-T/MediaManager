# Domain Model Class Diagram: Media Manager

## Class Diagram Description

The core logic of the **"Media Manager"** domain is centered around **media items** (catalog), **users** (authentication and collections), and the **relationships** between them (status tracking, comments, and creator information).

### Main Entities

* **`User`**: Represents a system user with account credentials.
* **`MediaItem`**: An abstract entity that serves as the basis for all content types in the catalog.
* **`Movie`, `TVShow`, `Book`, `Game`**: Concrete content types that inherit common attributes from `MediaItem`.
* **`Person`**: Represents a person associated with media items (actor, director, author, etc.).
* **`UserMediaStatus`**: A Junction entity for tracking a user's personal statuses and ratings for a specific media item.
* **`Comment`**: Represents a text comment left by a user on a media item.
* **`Credit`**: A Junction entity for detailing roles, linking `Person` with `MediaItem`.

### Key Relationships Explained

1.  **MediaItem — Inheritance:**

    * `Movie`, `TVShow`, `Book`, `Game` **inherit** attributes and relationships from `MediaItem`.

2.  **User — UserMediaStatus — MediaItem (Many-to-Many):**

    * `User` **(1) -- (0..\*)** `UserMediaStatus`: Each user can track many media items.
    * `MediaItem` **(1) -- (0..\*)** `UserMediaStatus`: Each media item can be tracked by many users.
    * *The relationship is implemented via a composite key in the `user_media_status` table.*

3.  **User — Comment — MediaItem (Many-to-Many):**

    * `User` **(1) -- (0..\*)** `Comment`: A user can leave many comments.
    * `MediaItem` **(1) -- (0..\*)** `Comment`: A media item can have many comments.

4.  **Person — Credit — MediaItem (Many-to-Many):**

    * `Person` **(1) -- (0..\*)** `Credit`: A person can be associated with many media items.
    * `MediaItem` **(1) -- (0..\*)** `Credit`: A media item can be associated with many persons (through their roles).
    * *The relationship is implemented via the junction table `credit`.*

### Attributes

* **`User`**: `id` (PK), `username`, `email`, `password` (Hashed).
* **`MediaItem` (Base)**: `id` (PK), `title`, `description`, `releaseYear`, `rating` (Avg), `mediaType`, `genres`, `language`, `coverUrl`.
* **`Movie` (Specific)**: `country`, `runtime`, `budget`.
* **`UserMediaStatus`**: `id` (Composite PK/FK), `status` (Enum), `rating` (Personal rating), `notes`, `addedDate`, `isFavorite`.
* **`Person`**: `id` (PK), `name`, `rating` (Avg. Person's rating).
* **`Comment`**: `id` (PK), `text`, `createdAt`, `user_id` (FK to User), `media_item_id` (FK to MediaItem).
* **`Credit`**: `id` (PK), `role`, `media_item_id` (FK), `person_id` (FK).