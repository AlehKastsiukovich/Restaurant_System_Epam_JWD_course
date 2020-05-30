package by.epam.javatraining.restaurant.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordUtils {
    private static final Logger LOGGER = LogManager.getLogger(PasswordUtils.class);

    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;
    private static final byte[] SALT = {29, 49, -57, 117, -38, -26, -43, 118, -93, -117, 116, 45, 49, -99, 61, -72};

    public static String generateSecuredPassword(String password) {
        String securePassword;
        byte[] securePasswordBytes = hash(password.toCharArray());
        securePassword = Base64.getEncoder().encodeToString(securePasswordBytes);

        return securePassword;
    }

    public static boolean verifyUserPassword(String providedPassword, String securedPassword) {
        String newSecurePassword = generateSecuredPassword(providedPassword);

        return newSecurePassword.equalsIgnoreCase(securedPassword);
    }

    private static byte[] hash(char[] password) {
        PBEKeySpec spec = new PBEKeySpec(password, SALT, ITERATIONS, KEY_LENGTH);
        Arrays.fill(password, Character.MIN_VALUE);

        try {
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

            return keyFactory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            LOGGER.error(e);
            throw new AssertionError(e);
        } finally {
            spec.clearPassword();
        }
    }

    public static void main(String[] args) {
        String myPass = "kaffka123";

        String encode = PasswordUtils.generateSecuredPassword(myPass);

        System.out.println(verifyUserPassword(myPass, encode));
    }
}
