package org.example;

import org.example.seed.SeedData;
import org.example.Controller.AuthController;
import java.util.Scanner;

/**
 * Main entry point for the Genius music application.
 *
 * <p>This class handles:
 * <ul>
 *   <li>Application initialization</li>
 *   <li>Primary menu navigation</li>
 *   <li>User flow coordination</li>
 * </ul>
 */
public class Main {

    /**
     * Application entry point.
     *
     * <p>Execution flow:
     * <ol>
     *   <li>Initializes sample data</li>
     *   <li>Displays main authentication menu</li>
     *   <li>Routes to appropriate controllers</li>
     *   <li>Handles application termination</li>
     * </ol>
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Initialize sample data
        SeedData.generate();

        // Create input scanner
        Scanner scanner = new Scanner(System.in);

        // Main application loop
        while (true) {
            System.out.println("  |~~~~~~Genius~~~~~~|");
            System.out.println("1.|      Sign Up     |");
            System.out.println("2.|       Login      |");
            System.out.println("0.|       Exit       |");
            System.out.print("Choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" ->
                    // Route to sign up flow
                        AuthController.signUp();

                case "2" ->
                    // Route to login flow
                        AuthController.login();

                case "0" -> {
                    // Graceful exit
                    System.out.println("Bye!");
                    return;
                }

                default ->
                    // Handle invalid input
                        System.out.println("❌Invalid option❌");
            }
        }
    }
}