package classes;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import sun.misc.BASE64Encoder;

/**
 *
 * @author Seanjackson
 */
public class ShaHash {

    private String plaintext;
    private String hash;
    private String OTP_Hash_64;
    private String msg;
    private String H_of_K = "";
    private String otp_hash;
    private int hash_chain_length = 100000;

    public ShaHash() {
    }

    public synchronized String hash(String plaintext) {
        this.plaintext = plaintext;
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            md.update((this.plaintext).getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte raw[] = md.digest();
        this.hash = (new BASE64Encoder()).encode(raw);
        return this.hash;
    }

    public String OTP_Hash_64(String msg) {
        // Hashage SHA1 sur 160 bit par defaut
        msg = msg;
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            md.update((msg).getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] digest = md.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b : digest) {
            sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        otp_hash = sb.toString();
        long Hash_Hex0 = Long.parseLong(otp_hash.substring(0, 8), 16);
        long Hash_Hex1 = Long.parseLong(otp_hash.substring(8, 16), 16);
        long Hash_Hex2 = Long.parseLong(otp_hash.substring(16, 24), 16);
        long Hash_Hex3 = Long.parseLong(otp_hash.substring(24, 32), 16);
        long Hash_Hex4 = Long.parseLong(otp_hash.substring(32, 40), 16);
        //Fin du decoupage
        // Operations de XOR
        Hash_Hex0 ^= Hash_Hex2;
        Hash_Hex0 ^= Hash_Hex4;
        Hash_Hex1 ^= Hash_Hex3;
        // Fin Operations de XOR
        otp_hash = String.format("%08X%08X", Hash_Hex0, Hash_Hex1);
        return otp_hash;
    }

    public String H_Fonction(String H_K, int k) {
        for (int i = 1; i <= k; i++) {
            H_K = OTP_Hash_64(H_K);
        }
        return H_K;
    }

    // Convertir HEXADECIMAL vers DECIMAL
    public String hash_str_to_Low_High_Bytes(String hash_str) {
        long otp_hash_low_high0 = Long.parseLong(hash_str.substring(0, 8), 16);
        long otp_hash_low_high1 = Long.parseLong(hash_str.substring(8, 16), 16);
        String low_10 = String.format("%010d", otp_hash_low_high0);
        String high_10 = String.format("%010d", otp_hash_low_high1);
        String hash_dec_str = "0" + low_10 + high_10;
        return hash_dec_str;
    }

    /* Convertir HEXADECIMAL vers DECIMAL
     public static int hex2decimal(String toConvert) {
     int finalDecimal = 0;
     for (int i = 0; i < toConvert.length(); i++) {
     char c = toConvert.charAt(i);
     int num = (int) c;
     finalDecimal = 256 * finalDecimal + num;
     }
     return finalDecimal;
     }*/
    public String getPlaintext() {
        return plaintext;
    }

    public void setPlaintext(String plaintext) {
        this.plaintext = plaintext;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
