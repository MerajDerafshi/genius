package org.example.model;

import java.time.LocalDateTime;

/**
 * Represents a system notification message with read status tracking.
 * Notifications can be marked as seen and include timestamp information.
 */
public class Notification {
    // The notification message content (immutable)
    private final String message;

    // When the notification was created (immutable)
    private final LocalDateTime createdAt;

    // Whether the notification has been viewed
    private boolean seen;

    /**
     * Creates a new notification with the given message.
     * Automatically sets creation time and initializes as unseen.
     *
     * @param message The notification content text
     */
    public Notification(String message) {
        this.message = message;
        this.createdAt = LocalDateTime.now(); // Set to current time
        this.seen = false; // New notifications start as unread
    }

    // -------------------- Accessor Methods --------------------

    /**
     * Gets the notification message content.
     *
     * @return The notification text
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets when the notification was created.
     *
     * @return Creation timestamp
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Checks if the notification has been viewed.
     *
     * @return true if marked as seen, false otherwise
     */
    public boolean isSeen() {
        return seen;
    }

    // -------------------- State Modification --------------------

    /**
     * Marks the notification as having been viewed.
     * Cannot be undone - notifications are permanently marked as seen.
     */
    public void markAsSeen() {
        this.seen = true;
    }

    // -------------------- Display --------------------

    /**
     * Returns a formatted string representation of the notification.
     * Includes visual indicator (✓/🔔) based on seen status and timestamp.
     *
     * @return Formatted string in format: "[indicator] message [time]"
     */
    @Override
    public String toString() {
        return (seen ? "✓ " : "🔔 ") + message + "  [" + createdAt.toLocalTime() + "]";
    }
}