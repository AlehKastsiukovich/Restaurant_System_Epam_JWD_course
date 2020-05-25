package by.epam.javatraining.restaurant.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.KeySpec;

public class PasswordHashGenerator {
    private static final Logger LOGGER = LogManager.getLogger(PasswordHashGenerator.class);
    private static final int ITERATION_COUNT = 1024;
    private static final int KEY_STRENGTH = 256;

    private Cipher cipher;
    private SecretKey key;
    private byte[] bytesIv;

    private PasswordHashGenerator() {
    }

    private static class PasswordHashGeneratorHolder {
        private static final PasswordHashGenerator INSTANCE = new PasswordHashGenerator();
    }

    public static PasswordHashGenerator getInstance() {
        return PasswordHashGeneratorHolder.INSTANCE;
    }

    public String encryptPassword(String password) {
        String base64EncryptedData = null;

        try {
            initializeSecretKey(password);

            cipher.init(Cipher.ENCRYPT_MODE, key);
            AlgorithmParameters params = cipher.getParameters();
            bytesIv = params.getParameterSpec(IvParameterSpec.class).getIV();
            byte[] utf8EncryptedData = cipher.doFinal(password.getBytes());
            base64EncryptedData = new sun.misc.BASE64Encoder().encodeBuffer(utf8EncryptedData);

        } catch (InvalidKeyException | InvalidParameterSpecException
                | BadPaddingException | IllegalBlockSizeException
                | InvalidKeySpecException | NoSuchAlgorithmException | NoSuchPaddingException e) {
            LOGGER.error(e);
        }

        return base64EncryptedData;
    }

    public String decryptPassword(String base64EncryptedPassword) throws Exception {
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(bytesIv));
        byte[] decryptedData = new sun.misc.BASE64Decoder().decodeBuffer(base64EncryptedPassword);
        byte[] utf8 = cipher.doFinal(decryptedData);
        return new String(utf8, StandardCharsets.UTF_8);
    }

    private byte[] getSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        return salt;
    }

    private void initializeSecretKey(String password)
            throws InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException {
        cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        KeySpec keySpec = new PBEKeySpec(password.toCharArray(), getSalt(), ITERATION_COUNT, KEY_STRENGTH);
        SecretKey secretKey = factory.generateSecret(keySpec);

        key = new SecretKeySpec(secretKey.getEncoded(), "AES");
    }
}
