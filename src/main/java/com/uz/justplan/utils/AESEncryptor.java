package com.uz.justplan.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESEncryptor {
    private static final String SECRET = "MySecretKey12345"; // must be 16/24/32 bytes for AES

    public static String encrypt(String plainText) throws Exception {
        SecretKeySpec key = new SecretKeySpec(SECRET.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");

        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());

        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encrypted) throws Exception {
        SecretKeySpec key = new SecretKeySpec(SECRET.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");

        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encrypted));

        return new String(decryptedBytes);
    }

    public static void main(String[] args) throws Exception {
        String password = "MySecurePassword123!";
        String encrypted = encrypt(password);
        String decrypted = decrypt(encrypted);

        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
        //Encrypted: +zwtD3jldYbCD2tS8QAXYcLwConifv9uaGqyjnkc/1I=
        //Decrypted: MySecurePassword123!
    }
}
