package org.example.model;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents an administrator account in the system.
 * Extends the base Account class with admin-specific functionality.
 * Handles artist approvals, lyric edit reviews, and notifications.
 */
public class Admin extends Account {

    // List to store admin notifications
    protected List<Notification> notifications = new ArrayList<>();

    /**
     * Constructs a new Admin account.
     *
     * @param name The admin's full name
     * @param age The admin's age
     * @param email The admin's email address
     * @param username The admin's username
     * @param password The admin's password (will be hashed)
     */
    public Admin(String name, int age, String email, String username, String password) {
        super(name, age, email, username, password);
    }

    /**
     * Returns the role of this account ("Admin").
     *
     * @return The string "Admin" representing this account type
     */
    @Override
    public String getRole() {
        return "Admin";
    }

    /**
     * Approves an artist account, marking them as verified.
     *
     * @param artist The artist account to approve
     */
    public void approveArtist(Artist artist) {
        artist.verify();
        System.out.println("✅ Artist approved: " + artist.getUsername());
    }

    /**
     * Reviews and approves/rejects a lyric edit request.
     *
     * @param request The lyric edit request to review
     * @param approve True to approve, false to reject the request
     */
    public void reviewLyricEdit(LyricEditRequest request, boolean approve) {
        if (approve) {
            request.approve();
            System.out.println("✅ Edit request approved for: " + request.getSong().getTitle());
        } else {
            request.reject();
            System.out.println("❌ Edit request rejected for: " + request.getSong().getTitle());
        }
    }

    /**
     * Adds a new notification to the admin's notification list.
     *
     * @param notification The notification to add
     */
    public void addNotification(Notification notification) {
        notifications.add(notification);
    }

    /**
     * Displays all unread notifications and marks them as seen.
     * Shows "No notifications" message if the list is empty.
     */
    public void checkNotifications() {
        System.out.println("🔔 Notifications:");
        if (notifications.isEmpty()) {
            System.out.println("No notifications.");
        } else {
            for (Notification n : notifications) {
                System.out.println(n);
                n.markAsSeen();
            }
        }
    }
}