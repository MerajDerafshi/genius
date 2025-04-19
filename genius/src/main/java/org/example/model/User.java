package org.example.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a standard user account in the music platform.
 * Extends the base Account class with user-specific functionality
 * including artist following, song viewing history, and lyric edit requests.
 */
public class User extends Account {
    // List of artists this user follows
    private List<Artist> followedArtists;

    // List of lyric edit requests submitted by this user
    private List<LyricEditRequest> editRequests;

    // History of songs viewed by this user
    private List<Song> viewedHistory;

    // List of notifications for the user
    protected List<Notification> notifications = new ArrayList<>();

    /**
     * Constructs a new User account.
     *
     * @param name The user's full name
     * @param age The user's age
     * @param email The user's email address
     * @param username The user's username
     * @param password The user's password (will be hashed)
     */
    public User(String name, int age, String email, String username, String password) {
        super(name, age, email, username, password);
        this.followedArtists = new ArrayList<>();
        this.editRequests = new ArrayList<>();
        this.viewedHistory = new ArrayList<>();
    }

    /**
     * Gets the user's role.
     *
     * @return The string "User" representing this account type
     */
    @Override
    public String getRole() {
        return "User";
    }

    // -------------------- Artist Following --------------------

    /**
     * Gets the list of artists this user follows.
     *
     * @return List of followed artists
     */
    public List<Artist> getFollowedArtists() {
        return followedArtists;
    }

    /**
     * Follows a new artist if not already following.
     *
     * @param artist The artist to follow
     */
    public void followArtist(Artist artist) {
        if (!followedArtists.contains(artist)) {
            followedArtists.add(artist);
        }
    }

    /**
     * Unfollows an artist.
     *
     * @param artist The artist to unfollow
     */
    public void unfollowArtist(Artist artist) {
        followedArtists.remove(artist);
    }

    /**
     * Checks if the user is following a specific artist.
     *
     * @param artist The artist to check
     * @return true if following, false otherwise
     */
    public boolean isFollowing(Artist artist) {
        return followedArtists.contains(artist);
    }

    // -------------------- Lyric Edit Requests --------------------

    /**
     * Gets all lyric edit requests submitted by this user.
     *
     * @return List of edit requests
     */
    public List<LyricEditRequest> getEditRequests() {
        return editRequests;
    }

    /**
     * Adds a new lyric edit request.
     *
     * @param request The edit request to add
     */
    public void addEditRequest(LyricEditRequest request) {
        editRequests.add(request);
    }

    // -------------------- View History --------------------

    /**
     * Gets the user's song viewing history.
     *
     * @return List of viewed songs
     */
    public List<Song> getViewedHistory() {
        return viewedHistory;
    }

    /**
     * Adds a song to the viewing history.
     *
     * @param song The song to add to history
     */
    public void addToViewedHistory(Song song) {
        viewedHistory.add(song);
    }

    /**
     * Gets the most recently viewed songs.
     *
     * @param count Number of recent songs to retrieve
     * @return List of recently viewed songs (empty if no history)
     */
    public List<Song> getRecentViews(int count) {
        int size = viewedHistory.size();
        if (size == 0) return Collections.emptyList();
        return viewedHistory.subList(Math.max(size - count, 0), size);
    }

    // -------------------- Song Interaction --------------------

    /**
     * Adds a comment to a song.
     *
     * @param song The song to comment on
     * @param content The comment text
     */
    public void commentOnSong(Song song, String content) {
        Comment comment = new Comment(this, content);
        song.addComment(comment);
    }

    // -------------------- Notifications --------------------

    /**
     * Adds a notification to the user's notification list.
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

    /**
     * Shows new releases from followed artists.
     */
    public void showNotifications() {
        for (Artist artist : followedArtists) {
            List<Song> songs = artist.getSongs();
            if (!songs.isEmpty()) {
                Song last = songs.get(songs.size() - 1);
                System.out.println("- " + artist.getUsername() + " released: " + last.getTitle());
            }
        }
    }

    // -------------------- User Summary --------------------

    /**
     * Generates a summary of the user's activity.
     *
     * @return Formatted string with username, following count, and view count
     */
    public String summary() {
        return String.format("User: %s | Following: %d | Viewed: %d",
                getUsername(), followedArtists.size(), viewedHistory.size());
    }
}