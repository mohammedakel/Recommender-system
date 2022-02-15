package edu.brown.cs.student.main.BloomFilterr;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * A class that genrates hash functions based on SHA-1
 */

public class HashGenerator {
    private static MessageDigest HASH_FUNCTION = null;
    static {
        try {
            HASH_FUNCTION = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    private static Charset CHARSET = StandardCharsets.UTF_8;

    /**
    *
    * @param data      input data.
    * @param numHashes number of hashes/BigIntegers to produce
    * @return array of BigInteger hashes
  */
    public static BigInteger[] createHashes(byte[] data, int numHashes) {
        BigInteger[] result = new BigInteger[numHashes];
        int k = 0;
        BigInteger salt = BigInteger.valueOf(0);
        while (k < numHashes) {
            HASH_FUNCTION.update(salt.toByteArray());
            salt = salt.add(BigInteger.valueOf(1));
            byte[] hash = HASH_FUNCTION.digest(data);
            HASH_FUNCTION.reset();

            // convert hash byte array to hex string, then to BigInteger
            String hexHash = bytesToHex(hash);
            result[k] = new BigInteger(hexHash, 16);
            k++;
        }
        return result;
    }

    /**
     * Converts a byte array to a hex string.
     * Source: https://stackoverflow.com/a/9855338
     *
     * @param bytes the byte array to convert
     * @return the hex string
     */
    private static String bytesToHex(byte[] bytes) {
        byte[] hexArray = "0123456789ABCDEF".getBytes(CHARSET);
        byte[] hexChars = new byte[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars, CHARSET);
    }

}
