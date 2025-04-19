package org.example.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents a music album in the system.
 * Contains album metadata and track listing.
 */
public class Album {
    // Unique identifier for the album
    private String id;

    // Title of the album
    private String title;

    // Album release date
    private LocalDate releaseDate;

    // Artist who created the album
    private Artist artist;

    // List of songs in the album
    private List<Song> trackList;

    /**
     * Constructs a new Album instance.
     *
     * @param title The title of the album
     * @param releaseDate The release date of the album
     * @param artist The artist who created the album
     */
    public Album(String title, LocalDate releaseDate, Artist artist) {
        this.id = UUID.randomUUID().toString(); // Generate unique ID
        this.title = title;
        this.releaseDate = releaseDate;
        this.artist = artist;
        this.trackList = new ArrayList<>(); // Initialize empty track list
    }

    /**
     * Adds a song to the album's track list.
     *
     * @param song The song to add to the album
     */
    public void addSong(Song song) {
        trackList.add(song);
    }

    // -------------------- Accessor Methods --------------------

    /**
     * Gets the album's track list.
     *
     * @return List of songs in the album
     */
    public List<Song> getTrackList() {
        return trackList;
    }

    /**
     * Gets the album title.
     *
     * @return The album title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the album release date.
     *
     * @return The release date
     */
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    /**
     * Gets the album artist.
     *
     * @return The artist who created the album
     */
    public Artist getArtist() {
        return artist;
    }

    /**
     * Returns a formatted string representation of the album.
     *
     * @return String in format: "Album: [title] | Artist: [artist] | Tracks: [count]"
     */
    @Override
    public String toString() {
        return String.format("Album: %s | Artist: %s | Tracks: %d",
                title, artist.getUsername(), trackList.size());
    }
}