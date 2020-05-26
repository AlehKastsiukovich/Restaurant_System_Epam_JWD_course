package by.epam.javatraining.restaurant.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.SecretKeyFactory;
import java.security.AlgorithmParameters;
import javax.crypto.spec.IvParameterSpec;

public class PasswordService {
    private Cipher cipher;
    private SecretKey key;
    private byte[] bytesIV;
    private static PasswordService instance;

    private static final int ITERATION_COUNT = 1024;
    private static final int KEY_STRENGTH = 256;
    private static final String PASSWORD_PHRASE = "ABCDEFGHIJKL";
    private static final String KEY_FACTORY_ALGORITHM = "PBKDF2WithHmacSHA1";
    private static final Logger LOGGER = LogManager.getLogger(PasswordService.class);
    
    private PasswordService() throws Exception {
        SecretKeyFactory factory = SecretKeyFactory.getInstance(KEY_FACTORY_ALGORITHM);
        KeySpec spec = new PBEKeySpec(PASSWORD_PHRASE.toCharArray(), getSalt(), ITERATION_COUNT, KEY_STRENGTH);
        SecretKey tmp = factory.generateSecret(spec);
        
        key = new SecretKeySpec(tmp.getEncoded(), "AES");
        cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    }

    public static synchronized PasswordService getInstance() {
        if (instance == null) {
            try {
                instance = new PasswordService();
            } catch (Exception e) {
                LOGGER.error(e);
            }
        }

        return instance;
    }

    public String encryptPassword(String password) throws Exception {
        cipher.init(Cipher.ENCRYPT_MODE, key);
        AlgorithmParameters params = cipher.getParameters();
        bytesIV = params.getParameterSpec(IvParameterSpec.class).getIV();
        
        byte[] utf8EncryptedPassword = cipher.doFinal(password.getBytes());
        String base64EncryptedPassword = new sun.misc.BASE64Encoder().encodeBuffer(utf8EncryptedPassword);
        
        return base64EncryptedPassword;
    }

    public String decryptPassword(String base64EncryptedPassword) throws Exception {
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(bytesIV));
        AlgorithmParameters params = cipher.getParameters();
        bytesIV = params.getParameterSpec(IvParameterSpec.class).getIV();
        
        byte[] decryptedPassword = new sun.misc.BASE64Decoder().decodeBuffer(base64EncryptedPassword);
        byte[] utf8DecryptedPassword = cipher.doFinal(decryptedPassword);
        
        return new String(utf8DecryptedPassword, StandardCharsets.UTF_8);
    }

    private byte[] getSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        return salt;
    }
}