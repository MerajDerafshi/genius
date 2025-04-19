package org.example.ui;

import org.example.model.*;
import org.example.seed.SeedData;
import java.util.Scanner;

/**
 * Admin interface and workflow controller.
 *
 * <p>Handles all administrative operations including:
 * <ul>
 *   <li>Artist account approvals</li>
 *   <li>Lyric edit request reviews</li>
 *   <li>Notification management</li>
 *   <li>Content moderation</li>
 * </ul>
 */
public class AdminStage {
    // Current admin user session
    private final Admin admin;

    // Input scanner for user interactions
    private final Scanner scanner;

    /**
     * Initializes a new admin session.
     *
     * @param admin The authenticated admin account
     */
    public AdminStage(Admin admin) {
        this.admin = admin;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Main admin control loop.
     *
     * <p>Displays menu and processes user selections until logout.
     */
    public void run() {
        while (true) {
            System.out.println("  | ~~~~~~~Admin Menu~~~~~~~ |");
            System.out.println("1.|Approve Artists           |");
            System.out.println("2.|Review Lyric Edit Requests|");
            System.out.println("3.|Check Notifications       |");
            System.out.println("4.|View Song Lyrics          |");
            System.out.println("0.|Logout                    |");
            System.out.print("Choose: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> approveArtists();
                case "2" -> reviewEdits();
                case "3" -> admin.checkNotifications();
                case "4" -> viewLyrics();
                case "0" -> {
                    System.out.println("Logging out, please wait...");
                    return;
                }
                default -> System.out.println("❌Invalid choice❌");
            }
        }
    }

    /**
     * Handles artist verification workflow.
     *
     * <p>Displays pending artists and processes approval decisions.
     */
    private void approveArtists() {
        for (Artist artist : SeedData.artists) {
            if (!artist.isVerified()) {
                System.out.println("- " + artist.getUsername());
                System.out.print("Approve this artist? (yes/no): ");
                String ans = scanner.nextLine();
                if (ans.equalsIgnoreCase("yes")) {
                    admin.approveArtist(artist);
                }
            }
        }
    }

    /**
     * Manages lyric edit request reviews.
     *
     * <p>Displays pending edits and processes approval decisions,
     * notifying requesters of outcomes.
     */
    private void reviewEdits() {
        for (User user : SeedData.users) {
            for (LyricEditRequest req : user.getEditRequests()) {
                if (!req.isApproved()) {
                    System.out.println("- Song: " + req.getSong().getTitle());
                    System.out.println("Suggested by: " + req.getRequester().getUsername());
                    System.out.println("New Lyrics: " + req.getProposedLyrics());
                    System.out.print("Approve this edit? (yes/no): ");

                    boolean approved = scanner.nextLine().equalsIgnoreCase("yes");
                    admin.reviewLyricEdit(req, approved);

                    String msg = "Your lyric edit for \"" + req.getSong().getTitle() + "\" was " +
                            (approved ? "approved ✅" : "rejected ❌") + " by Admin.";
                    req.getRequester().addNotification(new Notification(msg));
                }
            }
        }
    }

    /**
     * Displays song lyrics viewer.
     *
     * <p>Shows all available songs and allows selection
     * for full lyrics viewing with metadata.
     */
    private void viewLyrics() {
        System.out.println("~~~~~~~All Songs~~~~~~~");
        for (int i = 0; i < SeedData.songs.size(); i++) {
            System.out.println((i + 1) + ". " + SeedData.songs.get(i).getTitle());
        }

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
}