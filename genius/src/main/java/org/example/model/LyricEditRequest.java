package org.example.model;

import org.example.seed.SeedData;

/**
 * Represents a request to edit song lyrics in the system.
 * Tracks the edit proposal, requester, approval status, and handles notifications.
 */
public class LyricEditRequest {
    // Unique identifier for the request
    private String id;

    // User who submitted the edit request
    private User requester;

    // Song being edited
    private Song song;

    // Proposed new lyrics
    private String proposedLyrics;

    // Approval status (defaults to false)
    private boolean isApproved;

    /**
     * Constructs a new lyric edit request and notifies relevant parties.
     *
     * @param requester User submitting the edit request
     * @param song Song being edited
     * @param proposedLyrics New lyrics being proposed
     */
    public LyricEditRequest(User requester, Song song, String proposedLyrics) {
        this.id = java.util.UUID.randomUUID().toString();
        this.requester = requester;
        this.song = song;
        this.proposedLyrics = proposedLyrics;
        this.isApproved = false;

        // Notify all admins about the new request
        for (Admin admin : SeedData.admins) {
            admin.addNotification(new Notification(
                    requester.getUsername() + " suggested lyric edit for song: " + song.getTitle()
            ));
        }

        // Notify the artist who owns the song (if available)
        Artist artist = song.getArtist();
        if (artist != null) {
            artist.addNotification(new Notification(
                    requester.getUsername() + " suggested lyric edit for your song: " + song.getTitle()
            ));
        }
    }

    // -------------------- Accessor Methods --------------------

    /**
     * Gets the user who requested the edit.
     *
     * @return Requesting user
     */
    public User getRequester() {
        return requester;
    }

    /**
     * Gets the song being edited.
     *
     * @return Song object
     */
    public Song getSong() {
        return song;
    }

    /**
     * Gets the proposed lyric changes.
     *
     * @return New lyrics text
     */
    public String getProposedLyrics() {
        return proposedLyrics;
    }

    /**
     * Checks if the request was approved.
     *
     * @return true if approved, false otherwise
     */
    public boolean isApproved() {
        return isApproved;
    }

    // -------------------- Approval Actions --------------------

    /**
     * Approves the lyric edit request.
     * Updates the song's lyrics and marks request as approved.
     */
    public void approve() {
        this.isApproved = true;
        song.setLyrics(proposedLyrics); // Apply the lyric changes
    }

    /**
     * Rejects the lyric edit request.
     * Marks request as not approved without changing lyrics.
     */
    public void reject() {
        this.isApproved = false;
    }

    // -------------------- Display --------------------

    /**
     * Returns a formatted string representation of the request.
     *
     * @return String in format: "Edit Request for [song] by [user]: [status]"
     */
    @Override
    public String toString() {
        return String.format("Edit Request for [%s] by %s: %s",
                song.getTitle(), requester.getUsername(),
                isApproved ? "Approved" : "Pending");
    }
}