package org.example.ui;

import org.example.model.*;
import org.example.seed.SeedData;
import java.util.List;
import java.util.Scanner;

/**
 * User interface and interaction controller.
 *
 * <p>Provides music fans with tools to:
 * <ul>
 *   <li>Discover and follow artists</li>
 *   <li>Interact with songs (comments, lyric suggestions)</li>
 *   <li>View content and engagement metrics</li>
 *   <li>Manage notifications</li>
 * </ul>
 */
public class UserStage {
    // Current user session
    private final User user;

    // Input handler for user interactions
    private final Scanner scanner;

    /**
     * Initializes a new user session.
     *
     * @param user The authenticated user account
     */
    public UserStage(User user) {
        this.user = user;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Main user control loop.
     *
     * <p>Displays menu and processes selections until logout.
     * Shows notifications on session start.
     */
    public void run() {
        user.showNotifications(); // Display new content alerts

        while (true) {
            System.out.println("  | ~~~~~~~User Menu~~~~~~~ |");
            System.out.println("1.|View Followed Artists    |");
            System.out.println("2.|View All Songs           |");
            System.out.println("3.|Comment on a Song        |");
            System.out.println("4.|Suggest Lyric Edit       |");
            System.out.println("5.|Check Notifications      |");
            System.out.println("6.|View Song Lyrics         |");
            System.out.println("7.|View Comments on a Song  |");
            System.out.println("0.|Logout                   |");
            System.out.print("Choose: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> viewFollowedArtists();
                case "2" -> viewAllSongs();
                case "3" -> commentOnSong();
                case "4" -> suggestLyricEdit();
                case "5" -> user.checkNotifications();
                case "6" -> viewLyrics();
                case "7" -> viewComments();
                case "0" -> {
                    System.out.println("Logging out please wait...");
                    return;
                }
                default -> System.out.println("❌Invalid choice❌");
            }
        }
    }

    /**
     * Displays the user's followed artists.
     * Shows empty state message if none followed.
     */
    private void viewFollowedArtists() {
        System.out.println("~~~~~~~Followed Artists~~~~~~~");
        if (user.getFollowedArtists().isEmpty()) {
            System.out.println("You are not following any artists.");
            return;
        }
        user.getFollowedArtists().forEach(artist ->
                System.out.println("- " + artist.getUsername()));
    }

    /**
     * Lists all available songs with view counts.
     */
    private void viewAllSongs() {
        System.out.println("~~~~~~~All Songs~~~~~~~");
        for (int i = 0; i < SeedData.songs.size(); i++) {
            Song song = SeedData.songs.get(i);
            System.out.printf("%d. %s (%d views)\n",
                    i + 1, song.getTitle(), song.getViewCount());
        }
    }

    /**
     * Guides user through song commenting process.
     *
     * <p>Steps:
     * <ol>
     *   <li>Select song from numbered list</li>
     *   <li>Enter comment text</li>
     * </ol>
     */
    private void commentOnSong() {
        viewAllSongs();
        System.out.print("Enter song number to comment on: ");
        int idx = Integer.parseInt(scanner.nextLine()) - 1;

        if (idx < 0 || idx >= SeedData.songs.size()) {
            System.out.println("❌Invalid index❌");
            return;
        }

        Song song = SeedData.songs.get(idx);
        System.out.print("Your comment: ");
        String content = scanner.nextLine();

        user.commentOnSong(song, content);
        System.out.println("Comment added✅");
    }

    /**
     * Handles lyric edit suggestion workflow.
     *
     * <p>Process:
     * <ol>
     *   <li>Select song</li>
     *   <li>View current lyrics</li>
     *   <li>Enter new lyrics (multi-line)</li>
     *   <li>Submit for review</li>
     * </ol>
     */
    private void suggestLyricEdit() {
        viewAllSongs();
        System.out.print("Enter song number to suggest edit: ");
        int idx = Integer.parseInt(scanner.nextLine()) - 1;

        if (idx < 0 || idx >= SeedData.songs.size()) {
            System.out.println("❌Invalid index❌");
            return;
        }

        Song song = SeedData.songs.get(idx);
        System.out.println("Current lyrics:\n" + song.getLyrics());
        System.out.print("Suggest new lyrics: ");
        System.out.println("Enter new lyrics suggestion (type 'END' to finish):");

        StringBuilder lyricsBuilder = new StringBuilder();
        String line;
        while (!(line = scanner.nextLine()).equals("END")) {
            lyricsBuilder.append(line).append("\n");
        }

        user.addEditRequest(new LyricEditRequest(
                user, song, lyricsBuilder.toString()));
        System.out.println("Your lyric edit request has been submitted.");
    }

    /**
     * Displays full lyrics for selected song.
     * Shows song metadata including view count.
     */
    private void viewLyrics() {
        viewAllSongs();
        System.out.print("Enter song number to view lyrics: ");
        int idx = Integer.parseInt(scanner.nextLine()) - 1;

        if (idx < 0 || idx >= SeedData.songs.size()) {
            System.out.println("❌Invalid index❌");
            return;
        }

        Song song = SeedData.songs.get(idx);
        System.out.println("\n🎵Title: " + song.getTitle());
        System.out.println("Lyrics:");
        System.out.println("----------------------------");
        System.out.println(song.getLyrics());
        System.out.println("----------------------------");
        System.out.println("Views: " + song.getViewCount());
    }

    /**
     * Displays all comments on selected song.
     * Shows empty state message if no comments exist.
     */
    private void viewComments() {
        viewAllSongs();
        System.out.print("Enter song number to see comments: ");
        int idx = Integer.parseInt(scanner.nextLine()) - 1;

        if (idx < 0 || idx >= SeedData.songs.size()) {
            System.out.println("❌Invalid index❌");
            return;
        }

        Song song = SeedData.songs.get(idx);
        List<Comment> comments = song.getComments();

        if (comments.isEmpty()) {
            System.out.println("No comments yet.");
        } else {
            System.out.println("💬 Comments on " + song.getTitle() + ":");
            comments.forEach(c -> System.out.println(
                    "- " + c.getText() + " (by " + c.getAuthor().getUsername() + ")"));
        }
    }
}