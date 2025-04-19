package org.example.seed;

import org.example.model.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Seed data generator for initializing the application with sample data.
 * Contains static collections of sample users, artists, admins, and songs.
 */
public class SeedData {
    // Collection of sample user accounts
    public static List<User> users = new ArrayList<>();

    // Collection of sample artist accounts
    public static List<Artist> artists = new ArrayList<>();

    // Collection of sample admin accounts
    public static List<Admin> admins = new ArrayList<>();

    // Collection of sample songs
    public static List<Song> songs = new ArrayList<>();

    // Collection of sample albums
    public static List<Album> albums = new ArrayList<>();

    /**
     * Generates initial sample data for the application.
     * Creates:
     * - 1 admin account
     * - 2 artist accounts
     * - 2 user accounts
     * - 2 songs (1 per artist)
     */
    public static void generate() {
        // Create admin account
        Admin admin1 = new Admin("Meraj", 19, "meraj@gmail.com", "meraj", "meraj121301");
        admins.add(admin1);

        // Create artist accounts
        Artist artist1 = new Artist("Ed Sheeran", 28, "sheeran@gmail.com", "sheeran", "sheeran123");
        Artist artist2 = new Artist("Queen", 24, "queen@gmail.com", "queen", "queen123");
        artists.add(artist1);
        artists.add(artist2);

        // Create user accounts
        User user1 = new User("Navid", 21, "navid@gmail.com", "navid", "navid123");
        User user2 = new User("Seyed", 22, "seyed@gmail.com", "seyed", "seyed123");
        users.add(user1);
        users.add(user2);

        // Create songs and associate with artists
        Song song1 = new Song(
                "Shape of You",
                "The club isn't the best place to find a lover\nSo the bar is where I go\nMe and my friends at the table doing shots\nDrinking fast and then we talk slow...",
                LocalDate.of(2017, 1, 6),
                artist1
        );

        Song song2 = new Song(
                "Bohemian Rhapsody",
                "Is this the real life?\nIs this just fantasy?\nCaught in a landslide...",
                LocalDate.of(1971, 5, 31),
                artist2
        );

        // Add songs to artists' catalogs
        artist1.getSongs().add(song1);
        artist2.getSongs().add(song2);

        // Add songs to global collection
        songs.add(song1);
        songs.add(song2);
    }
}