package org.example.model;

import java.util.UUID;
import org.example.utils.PasswordUtils;

/**
 * Abstract base class representing user accounts in the system.
 * Serves as the parent class for all account types (User, Artist, Admin).
 * Implements common account functionality and properties.
 */
public abstract class Account {
    // Unique identifier for the account
    protected String id;

    // User's full name
    protected String name;

    // User's age
    protected int age;

    // User's email address
    protected String email;

    // Unique username for login
    protected String username;

    // Hashed password (never stored in plain text)
    protected String password;

    /**
     * Constructs a new Account instance.
     *
     * @param name The user's full name
     * @param age The user's age
     * @param email The user's email address
     * @param username The account username (must be unique)
     * @param password The account password (will be hashed before storage)
     */
    public Account(String name, int age, String email, String username, String password) {
        this.id = UUID.randomUUID().toString(); // Generate unique ID
        this.name = name;
        this.age = age;
        this.email = email;
        this.username = username;
        this.password = PasswordUtils.hash(password); // Store only the hashed password
    }

    // -------------------- Accessor Methods --------------------

    /**
     * @return The account's unique identifier
     */
    public String getId() {
        return id;
    }

    /**
     * @return The user's full name
     */
    public String getName() {
        return name;
    }

    /**
     * @return The user's age
     */
    public int getAge() {
        return age;
    }

    /**
     * @return The user's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return The account username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return The hashed password (never returns plain text)
     */
    public String getPassword() {
        return password;
    }

    /**
     * Abstract method to get the account type/role.
     * Must be implemented by concrete subclasses.
     *
     * @return The account's role (e.g., "User", "Artist", "Admin")
     */
    public abstract String getRole();

    // -------------------- Overridden Methods --------------------

    /**
     * Returns a formatted string representation of the account.
     *
     * @return String in format: "[Role] Name (Username)"
     */
    @Override
    public String toString() {
        return String.format("[%s] %s (%s)", getRole(), name, username);
    }

    /**
     * Compares accounts based on username equality.
     *
     * @param obj The object to compare with
     * @return true if the usernames match, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Account)) {
            return false;
        }
        Account other = (Account) obj;
        return this.username.equals(other.username);
    }
}