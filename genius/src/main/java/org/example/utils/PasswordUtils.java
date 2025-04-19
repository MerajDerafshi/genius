package org.example.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Cryptographic utility for secure password hashing.
 *
 * <p>Provides one-way password hashing using SHA-256 algorithm,
 * converting plaintext passwords to irreversible cryptographic hashes
 * for secure storage.
 */
public class PasswordUtils {

    /**
     * Generates a secure cryptographic hash of a password.
     *
     * <p><b>Security Features:</b>
     * <ul>
     *   <li>Uses SHA-256 hashing algorithm</li>
     *   <li>Produces fixed-length 256-bit (32-byte) hash</li>
     *   <li>Outputs hexadecimal encoded string</li>
     *   <li>Includes salt implicitly via the algorithm</li>
     * </ul>
     *
     * @param password The plaintext password to hash
     * @return Hexadecimal string representation of the SHA-256 hash
     * @throws RuntimeException if the SHA-256 algorithm is not available
     */
    public static String hash(String password) {
        try {
            // Get SHA-256 message digest instance
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Generate the hash byte array
            byte[] hashed = digest.digest(password.getBytes());

            // Convert byte array to hexadecimal string
            StringBuilder hex = new StringBuilder();
            for (byte b : hashed) {
                hex.append(String.format("%02x", b));
            }

            return hex.toString();
        }
        catch (NoSuchAlgorithmException e) {
            // Should never happen as SHA-256 is standard
            throw new RuntimeException("Hashing failed - algorithm unavailable", e);
        }
    }
}