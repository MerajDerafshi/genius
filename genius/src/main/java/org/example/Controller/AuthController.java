package org.example.Controller;

import org.example.model.*;
import org.example.seed.SeedData;
import org.example.ui.UserStage;
import org.example.ui.ArtistStage;
import org.example.ui.AdminStage;
import org.example.utils.PasswordUtils;
import java.util.Scanner;

/**
 * Authentication controller for managing user registration, login, and logout
 */
public class AuthController
{
    // Currently logged-in user
    private static Account currentUser = null;
    // Scanner for reading user input
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Method for new user registration
     */
    public static void signUp()
    {
        System.out.println(".________________. ");
        System.out.println("|    Sign Up     |");
        System.out.println("|________________|");

        // Get user information
        System.out.print("Enter your Full Name: ");String name = scanner.nextLine();
        System.out.print("Enter your Age: ");int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter your Email address: ");String email = scanner.nextLine();
        System.out.print("Create a username: ");String username = scanner.nextLine();
        System.out.print("Create a password: ");String password = scanner.nextLine();
        System.out.print("Are you a user or an artist? (Artist/User): ");String roleInput = scanner.nextLine();

        // Create account based on user type
        if (roleInput.equalsIgnoreCase("Artist"))
        {
            // Create artist account
            Artist artist = new Artist(name, age, email, username, password);
            SeedData.artists.add(artist);
            System.out.println("Your artist account is registered✅");
            System.out.println("Please wait to pending approval.");

            // Send notification to admins for new artist approval
            for (Admin admin : SeedData.admins)
            {
                admin.addNotification(new Notification("New artist sign-up request: " + artist.getUsername()));
            }
        }
        else if (roleInput.equalsIgnoreCase("User"))
        {
            // Create regular user account
            User user = new User(name, age, email, username, password);
            SeedData.users.add(user);
            System.out.println("User account created successfully.✅");
        }
    }

    /**
     * Method for user login
     */
    public static void login()
    {
        System.out.println("#~~~~~~~Login~~~~~~~#");
        // Get username and password
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        // Hash password for comparison with stored passwords
        String hashed = PasswordUtils.hash(password);

        // Check admin login
        for (Admin admin : SeedData.admins)
        {
            if (admin.getUsername().equals(username) && admin.getPassword().equals(hashed))
            {
                currentUser = admin;
                System.out.println("Welcome admin: " + admin.getUsername());
                new AdminStage(admin).run(); // Launch admin panel
                return;
            }
        }

        // Check artist login
        for (Artist artist : SeedData.artists)
        {
            if (artist.getUsername().equals(username) && artist.getPassword().equals(hashed))
            {
                if (!artist.isVerified())
                {
                    System.out.println("❌ Artist not verified yet.       ❌");
                    System.out.println("❌ Please wait for admin approval.❌");
                    return;
                }
                currentUser = artist;
                System.out.println("Welcome artist: " + artist.getUsername());
                new ArtistStage(artist).run(); // Launch artist panel
                return;
            }
        }

        // Check regular user login
        for (User user : SeedData.users)
        {
            if (user.getUsername().equals(username) &&  user.getPassword().equals(hashed))
            {
                currentUser = user;
                System.out.println("Welcome user: " + user.getUsername());
                new UserStage(user).run(); // Launch user panel
                return;
            }
        }

        // Error message for invalid credentials
        System.out.println("❌ Oops                         ❌");
        System.out.println("❌ Invalid username or password.❌");
    }

    /**
     * Get the currently logged-in user
     * @return current user account
     */
    public static Account getCurrentUser()
    {
        return currentUser;
    }

    /**
     * Method for user logout
     */
    public static void logout()
    {
        currentUser = null;
        System.out.println("Logged out successfully.");
    }
}