package org.example.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an artist account in the music platform.
 * Extends the base Account class with artist-specific functionality
 * including song/album management and verification status.
 */
public class Artist extends Account {
    // List of songs created by the artist
    private List<Song> songs;

    // List of albums created by the artist
    private List<Album> albums;

    // Verification status of the artist
    private boolean isVerified;

    // List of notifications for the artist
    protected List<Notification> notifications = new ArrayList<>();

    /**
     * Constructs a new Artist account.
     *
     * @param name The artist's full name
     * @param age The artist's age
     * @param email The artist's email address
     * @param username The artist's username
     * @param password The artist's password (will be hashed)
     */
    public Artist(String name, int age, String email, String username, String password) {
        super(name, age, email, username, password);
        this.songs = new ArrayList<>();
        this.albums = new ArrayList<>();
        this.isVerified = false; // Artists start unverified by default
    }

    /**
     * Gets the artist's role.
     *
     * @return The string "Artist" representing this account type
     */
    @Override
    public String getRole() {
        return "Artist";
    }

    // -------------------- Music Management --------------------

    /**
     * Gets the artist's song catalog.
     *
     * @return List of songs by this artist
     */
    public List<Song> getSongs() {
        return songs;
    }

    /**
     * Gets the artist's album catalog.
     *
     * @return List of albums by this artist
     */
    public List<Album> getAlbums() {
        return albums;
    }

    /**
     * Adds a new song to the artist's catalog.
     *
     * @param song The song to add
     */
    public void addSong(Song song) {
        songs.add(song);
    }

    /**
     * Adds a new album to the artist's catalog.
     *
     * @param album The album to add
     */
    public void addAlbum(Album album) {
        albums.add(album);
    }

    // -------------------- Verification --------------------

    /**
     * Checks if the artist is verified.
     *
     * @return true if verified, false otherwise
     */
    public boolean isVerified() {
        return isVerified;
    }

    /**
     * Verifies the artist account.
     * Typically called by an admin during approval process.
     */
    public void verify() {
        this.isVerified = true;
    }

    // -------------------- Analytics --------------------

    /**
     * Gets the artist's most popular song by view count.
     *
     * @return Title of the most viewed song, or "No songs yet" if empty
     */
    public String getPopularSong() {
        if (songs.isEmpty()) return "No songs yet.";
        return songs.stream()
                .max((a, b) -> a.getViewCount() - b.getViewCount())
                .get()
                .getTitle();
    }

    /**
     * Generates a summary of the artist's profile.
     *
     * @return Formatted string with artist details and statistics
     */
    public String profileSummary() {
        return String.format("Artist: %s | Verified: %b | Songs: %d | Albums: %d | Top Song: %s",
                getUsername(), isVerified, songs.size(), albums.size(), getPopularSong());
    }

    // -------------------- Notifications --------------------

    /**
     * Adds a notification to the artist's notification list.
     *
     * @param notification The notification to add
     */
    public void addNotification(Notification notification) {
        notifications.add(notification);
    }

    /**
     * Displays all unread notifications and marks them as seen.
     * Shows "No notifications" if the list is empty.
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