package org.example.model;

import java.time.LocalDateTime;

/**
 * Represents a user comment in the system.
 * Contains comment content, author information, and timestamp.
 */
public class Comment {
    // Unique identifier for the comment
    private String id;

    // User who created the comment
    private User commenter;

    // Text content of the comment
    private String content;

    // Date and time when comment was created
    private LocalDateTime date;

    /**
     * Constructs a new Comment instance.
     *
     * @param commenter The user who is posting the comment
     * @param content The text content of the comment
     */
    public Comment(User commenter, String content) {
        this.id = java.util.UUID.randomUUID().toString(); // Generate unique ID
        this.commenter = commenter;
        this.content = content;
        this.date = LocalDateTime.now(); // Set to current time
    }

    // -------------------- Accessor Methods --------------------

    /**
     * Gets the username of the comment author.
     *
     * @return Commenter's username
     */
    public String getUsername() {
        return commenter.getUsername();
    }

    /**
     * Gets the comment text content.
     *
     * @return The comment content
     */
    public String getContent() {
        return content;
    }

    /**
     * Gets the comment creation timestamp.
     *
     * @return Date and time when comment was created
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Gets the comment text content (alias for getContent()).
     *
     * @return The comment content
     */
    public String getText() {
        return content;
    }

    /**
     * Gets the User object of the comment author.
     *
     * @return Comment author User object
     */
    public User getAuthor() {
        return commenter;
    }

    /**
     * Returns a formatted string representation of the comment.
     * Format: "[timestamp] username: comment text"
     *
     * @return Formatted comment string
     */
    @Override
    public String toString() {
        return String.format("[%s] %s: %s", date, getUsername(), content);
    }
}