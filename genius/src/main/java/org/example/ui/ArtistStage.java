package org.example.ui;

import org.example.model.*;
import org.example.seed.SeedData;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Artist interface and workflow controller.
 *
 * <p>Provides artists with tools to manage their content including:
 * <ul>
 *   <li>Song and album creation</li>
 *   <li>Content viewing and editing</li>
 *   <li>Lyric edit request management</li>
 *   <li>Fan interaction tracking</li>
 * </ul>
 */
public class ArtistStage {
    // Current artist session
    private final Artist artist;

    // Input handler for user interactions
    private final Scanner scanner;

    /**
     * Initializes a new artist session.
     *
     * @param artist The authenticated artist account
     */
    public ArtistStage(Artist artist) {
        this.artist = artist;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Main artist control loop.
     *
     * <p>Displays menu and processes user selections until logout.
     * Handles all artist-specific operations.
     */
    public void run() {
        while (true) {
            System.out.println("  | ~~~~~~Artist Menu~~~~~~ |");
            System.out.println("1.|View My Songs            |");
            System.out.println("2.|View My Albums           |");
            System.out.println("3.|Create Song              |");
            System.out.println("4.|Create Album             |");
            System.out.println("5.|Review Edit Requests     |");
            System.out.println("6.|Check Notifications      |");
            System.out.println("7.|View Song Lyrics         |");
            System.out.println("8.|View Comments on a Song  |");
            System.out.println("0.|Logout                   |");
            System.out.print("Choose: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> viewSongs();
                case "2" -> viewAlbums();
                case "3" -> createSong();
                case "4" -> createAlbum();
                case "5" -> reviewEdits();
                case "6" -> artist.checkNotifications();
                case "7" -> viewLyrics();
                case "8" -> viewComments();
                case "0" -> {
                    System.out.println("Logging out please wait...");
                    return;
                }
                default -> System.out.println("❌Invalid option❌");
            }
        }
    }

    /**
     * Displays the artist's song catalog.
     */
    private void viewSongs() {
        System.out.println("~~~~~~~My Songs~~~~~~~");
        artist.getSongs().forEach(song ->
                System.out.println("- " + song.getTitle()));
    }

    /**
     * Displays the artist's album catalog.
     */
    private void viewAlbums() {
        System.out.println("~~~~~~~My Albums~~~~~~~");
        artist.getAlbums().forEach(album ->
                System.out.println("- " + album.getTitle()));
    }

    /**
     * Guides artist through new song creation.
     *
     * <p>Collects:
     * <ol>
     *   <li>Song title</li>
     *   <li>Lyrics (multi-line input)</li>
     * </ol>
     * Auto-sets release date to current date.
     */
    private void createSong() {
        System.out.print("Song Title: ");
        String title = scanner.nextLine();

        System.out.print("Lyrics: ");
        System.out.println("Enter lyrics (type 'END' in a new line to finish):");

        StringBuilder lyricsBuilder = new StringBuilder();
        String line;
        while (!(line = scanner.nextLine()).equals("END")) {
            lyricsBuilder.append(line).append("\n");
        }

        Song song = new Song(title, lyricsBuilder.toString(),
                LocalDate.now(), artist);
        song.incrementView();
        artist.addSong(song);
        SeedData.songs.add(song);
        System.out.println("✅ Song created successfully.");
    }

    /**
     * Guides artist through new album creation.
     *
     * <p>Collects album title and sets current date as release date.
     * Note: Song addition to album must be done separately.
     */
    private void createAlbum() {
        System.out.print("Album Title: ");
        String title = scanner.nextLine();

        Album album = new Album(title, LocalDate.now(), artist);
        artist.addAlbum(album);
        SeedData.albums.add(album);
        System.out.println("✅ Album created. Add songs to it manually for now.");
    }

    /**
     * Manages lyric edit requests for artist's songs.
     *
     * <p>Displays pending requests and allows approval/rejection,
     * with automatic notification to requesters.
     */
    private void reviewEdits() {
        System.out.println("~~~~~~~Review Edit Requests~~~~~~~");
        SeedData.users.stream()
                .flatMap(user -> user.getEditRequests().stream())
                .filter(req -> req.getSong() != null)
                .filter(req -> artist.getSongs().contains(req.getSong()))
                .filter(req -> !req.isApproved())
                .forEach(req -> {
                    System.out.println("- Song: " + req.getSong().getTitle());
                    System.out.println("Suggested by: " + req.getRequester().getUsername());
                    System.out.println("New Lyrics: " + req.getProposedLyrics());
                    System.out.print("Approve this edit? (yes/no): ");

                    boolean approved = scanner.nextLine().equalsIgnoreCase("yes");
                    if (approved) {
                        req.approve();
                        System.out.println("✅ Approved.");
                    } else {
                        req.reject();
                        System.out.println("❌Rejected❌");
                    }

                    String msg = "Your lyric edit for \"" + req.getSong().getTitle() +
                            "\" was " + (approved ? "approved ✅" : "rejected ❌");
                    req.getRequester().addNotification(new Notification(msg));
                });
    }

    /**
     * Displays full lyrics for selected song.
     *
     * <p>Shows song metadata including view count.
     */
    private void viewLyrics() {
        viewSongs();
        System.out.print("Enter song number to view lyrics: ");
        int idx = Integer.parseInt(scanner.nextLine()) - 1;

        List<Song> songs = artist.getSongs();
        if (idx < 0 || idx >= songs.size()) {
            System.out.println("Invalid index.");
            return;
        }

        Song song = songs.get(idx);
        System.out.println("\n🎵Title: " + song.getTitle());
        System.out.println("Lyrics:");
        System.out.println("----------------------------");
        System.out.println(song.getLyrics());
        System.out.println("----------------------------");
        System.out.println("Views: " + song.getViewCount());
    }

    /**
     * Displays fan comments for selected song.
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
            comments.forEach(c ->
                    System.out.println("- " + c.getText() +
                            " (by " + c.getAuthor().getUsername() + ")"));
        }
    }

    /**
     * Displays numbered list of artist's songs.
     */
    private void viewAllSongs() {
        List<Song> songs = artist.getSongs();

        if (songs.isEmpty()) {
            System.out.println("You have no songs.");
            return;
        }

        System.out.println("🎵Your Songs:");
        for (int i = 0; i < songs.size(); i++) {
            System.out.println((i + 1) + ". " + songs.get(i).getTitle());
        }
    }
}