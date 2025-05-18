package util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.util.Base64;

public class PasswordHashUtil {

    // Configuration parameters for password hashing
    private static final int ITERATIONS = 2048;  // Number of iterations
    private static final int KEY_LENGTH = 256;   // Key length in bits
    private static final String ALGORITHM = "PBKDF2WithHmacSHA256";  // Hashing algorithm
    private static final int SALT_LENGTH = 16;   // Salt length in bytes

    /**
     * Hashes a password with a randomly generated salt
     * @param password The plain text password to hash
     * @return A string containing salt and hash separated by colon
     */
    public static String hashPassword(String password) {
        try {
            // Generate a secure random salt
            byte[] salt = new byte[SALT_LENGTH];
            SecureRandom random = new SecureRandom();
            random.nextBytes(salt);

            // Create the PBEKeySpec with the password, salt, iterations and key length
            PBEKeySpec spec = new PBEKeySpec(
                password.toCharArray(),
                salt,
                ITERATIONS,
                KEY_LENGTH
            );

            // Get the SecretKeyFactory instance
            SecretKeyFactory skf = SecretKeyFactory.getInstance(ALGORITHM);

            // Generate the hash
            byte[] hash = skf.generateSecret(spec).getEncoded();

            // Combine salt and hash with colon separator for storage
            return Base64.getEncoder().encodeToString(salt) + ":" + 
                   Base64.getEncoder().encodeToString(hash);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Error while hashing password", e);
        }
    }

    /**
     * Verifies a password against a stored hash
     * @param password The plain text password to verify
     * @param storedHash The stored hash (salt:hash)
     * @return true if password matches, false otherwise
     */
    public static boolean verifyPassword(String password, String storedHash) {
        try {
            // Split the stored hash into salt and hash components
            String[] parts = storedHash.split(":");
            if (parts.length != 2) {
                return false;
            }

            // Decode the salt and hash from Base64
            byte[] salt = Base64.getDecoder().decode(parts[0]);
            String storedPasswordHash = parts[1];

            // Create the same PBEKeySpec with the provided password
            PBEKeySpec spec = new PBEKeySpec(
                password.toCharArray(),
                salt,
                ITERATIONS,
                KEY_LENGTH
            );

            // Get the SecretKeyFactory instance
            SecretKeyFactory skf = SecretKeyFactory.getInstance(ALGORITHM);

            // Generate the hash to compare
            byte[] testHash = skf.generateSecret(spec).getEncoded();

            // Compare the generated hash with the stored hash
            return storedPasswordHash.equals(Base64.getEncoder().encodeToString(testHash));

        } catch (Exception e) {
            return false;
        }
    }
}