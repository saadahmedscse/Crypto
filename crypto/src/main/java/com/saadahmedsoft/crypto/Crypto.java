package com.saadahmedsoft.crypto;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class Crypto {
    public static String createHmacHash(String text, String key, String algorithm) {
        try {
            byte[] bytes = getMacBytes("Hmac" + algorithm, key.getBytes(), text.getBytes());
            return bytesToHex(bytes);
        } catch (Exception ignored) {
        }
        return "";
    }

    public static String createHash(String text, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA256");
        byte[] hash = digest.digest(text.getBytes());
        return bytesToHex(hash);
    }

    public static String getStringFromHmac(String HmacString, String algorithm) {return "";}

    public static String getStringFromHash(String hashString, String algorithm) {return "";}

    private static byte[] getMacBytes(String algorithm, byte[] key, byte[] password) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance(algorithm);
        mac.init(new SecretKeySpec(key, algorithm));
        return mac.doFinal(password);
    }

    private static String bytesToHex(byte[] bytes) {
        final char[] hexArray = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        for (int i = 0, v; i < bytes.length; i++) {
            v = bytes[i] & 0xFF;
            hexChars[i * 2] = hexArray[v >>> 4];
            hexChars[i * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}