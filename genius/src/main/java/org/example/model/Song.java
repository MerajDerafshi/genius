package org.example.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents a song in the music system.
 * Contains song metadata, lyrics, view statistics, and comments.
 */
public class Song {
    // Unique identifier for the song
    private String id;

    // Title of the song
    private String title;

    // Lyrics content
    private String lyrics;

    // Artist who created the song
    private Artist artist;

    // List of user comments on this song
    private List<Comment> comments;

    // Total view count
    private int viewCount;

    // Release date of the song
    private LocalDate releaseDate;

    /**
     * Constructs a new Song instance.
     *
     * @param title The song title
     * @param lyrics The song lyrics
     * @param releaseDate The release date
     * @param artist The artist who created the song
     */
    public Song(String title, String lyrics, LocalDate releaseDate, Artist artist) {
        this.id = UUID.randomUUID().toString(); // Generate unique ID
        this.title = title;
        this.lyrics = lyrics;
        this.releaseDate = releaseDate;
        this.artist = artist;
        this.comments = new ArrayList<>(); // Initialize empty comments list
        this.viewCount = 0; // New songs start with 0 views
    }

    // -------------------- Comment Management --------------------

    /**
     * Adds a new comment to this song.
     *
     * @param comment The comment to add
     */
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    /**
     * Gets all comments on this song.
     *
     * @return List of comments
     */
    public List<Comment> getComments() {
        return comments;
    }

    // -------------------- View Statistics --------------------

    /**
     * Increments the view count by 1.
     * Called each time the song is played/viewed.
     */
    public void incrementView() {
        viewCount++;
    }

    /**
     * Gets the total view count.
     *
     * @return Number of times the song has been viewed
     */
    public int getViewCount() {
        return viewCount;
    }

    // -------------------- Accessors --------------------

    /**
     * Gets the song title.
     *
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the song lyrics.
     *
     * @return The lyrics text
     */
    public String getLyrics() {
        return lyrics;
    }

    /**
     * Gets the artist who created this song.
     *
     * @return The artist
     */
    public Artist getArtist() {
        return artist;
    }

    // -------------------- Mutators --------------------

    /**
     * Updates the song lyrics.
     *
     * @param lyrics The new lyrics
     */
    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    // -------------------- Display --------------------

    /**
     * Returns a formatted string representation of the song.
     *
     * @return String in format: "🎵 Title | Released: Date | Views: Count"
     */
    @Override
    public String toString() {
        return "🎵 " + title + " | Released: " + releaseDate + " | Views: " + viewCount;
    }
}