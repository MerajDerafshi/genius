package org.example.Controller;

import org.example.model.Comment;
import org.example.model.Song;
import org.example.model.User;
import java.util.List;

/**
 * Controller for managing song-related operations including:
 * - Displaying song details
 * - Showing recent/top songs
 * - Handling song viewing statistics
 */
public class SongController {

    /**
     * Displays comprehensive details of a song including metadata and comments.
     *
     * @param song The song object to display
     * @implNote Formats output with visual separators for better readability
     */
    public static void viewSongDetails(Song song) {
        System.out.println("\n~~~~🎵Song Details🎵~~~~");
        System.out.println("Title: " + song.getTitle());
        System.out.println("Lyrics:\n" + song.getLyrics());
        System.out.println("Views: " + song.getViewCount());

        // Comment section with visual separation
        System.out.println("\n~~~ Comments ~~~");
        for (Comment comment : song.getComments()) {
            System.out.println(comment); // Uses Comment.toString()
        }
    }

    /**
     * Displays the most recently added songs (FIFO order).
     *
     * @param songs The complete song list
     * @param limit Maximum number of songs to display
     * @apiNote Preserves original list order, shows newest entries first
     */
    public static void showRecentSongs(List<Song> songs, int limit) {
        System.out.println("\n🎧 Recently Added Songs:");
        int count = Math.min(songs.size(), limit); // Safe boundary check
        for (int i = 0; i < count; i++) {
            System.out.println("- " + songs.get(i).getTitle());
        }
    }

    /**
     * Displays top songs sorted by view count in descending order.
     *
     * @param songs The complete song list
     * @param limit Maximum number of songs to display
     * @implNote Uses Java Stream API for efficient sorting and limiting
     */
    public static void showTopSongs(List<Song> songs, int limit) {
        System.out.println("\n📈 Top Songs by Views:");
        songs.stream()
                .sorted((a, b) -> b.getViewCount() - a.getViewCount()) // Descending sort
                .limit(limit) // Apply user-requested limit
                .forEach(song -> System.out.printf("- %s (%d views)\n",
                        song.getTitle(), song.getViewCount()));
    }

    /**
     * Handles song viewing workflow including:
     * - Incrementing view counter
     * - Updating user history
     * - Displaying song details
     *
     * @param song The song being viewed
     * @param viewer The user watching the song
     * @implNote Combines business logic with presentation layer
     */
    public static void viewSong(Song song, User viewer) {
        // Update statistics
        song.incrementView();
        viewer.addToViewedHistory(song);

        // Show details
        viewSongDetails(song);
    }
}