package org.example.Controller;

import org.example.model.Album;
import org.example.model.Artist;
import org.example.model.Song;
import java.util.List;

/**
 * Controller for handling search operations across different music entities.
 * Provides case-insensitive search functionality for songs, artists, and albums.
 */
public class SearchController {

    /**
     * Searches for songs containing the given keyword in their title.
     *
     * @param songs   List of songs to search through
     * @param keyword Search term to match against song titles
     * @apiNote Performs case-insensitive partial matching
     */
    public static void searchSongs(List<Song> songs, String keyword) {
        System.out.println("🔍 Matching Songs 🔍");
        for (Song song : songs) {
            if (song.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println("- " + song.getTitle());
            }
        }
    }

    /**
     * Searches for artists whose username or name contains the given keyword.
     *
     * @param artists List of artists to search through
     * @param keyword Search term to match against artist names/usernames
     * @apiNote Checks both username and display name fields
     */
    public static void searchArtists(List<Artist> artists, String keyword) {
        System.out.println("🎤 Matching Artists:");
        for (Artist artist : artists) {
            if (artist.getUsername().toLowerCase().contains(keyword.toLowerCase()) ||
                    artist.getName().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println("- " + artist.getUsername());
            }
        }
    }

    /**
     * Searches for albums containing the given keyword in their title.
     *
     * @param albums  List of albums to search through
     * @param keyword Search term to match against album titles
     * @apiNote Case-insensitive matching with partial string support
     */
    public static void searchAlbums(List<Album> albums, String keyword) {
        System.out.println("\n💿 Matching Albums:");
        for (Album album : albums) {
            if (album.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println("- " + album.getTitle());
            }
        }
    }
}