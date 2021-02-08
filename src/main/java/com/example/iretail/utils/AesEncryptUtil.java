package com.example.iretail.utils;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * key必须16位
 * encrypt()
 * decrypt()
 */
@Component
public class AesEncryptUtil {
    private AesEncryptUtil() {
    }

    /**
     * 加密
     */
    public final String encrypt(String data, String privateKey) {
        try {
            return byte2hex(encrypt(data.getBytes(), privateKey));
        } catch (Exception var3) {
            return null;
        }
    }

    /**
     * 解密
     */
    public final String decrypt(String data, String privateKey) {
        try {
            return new String(decrypt(hex2byte(data.getBytes()), privateKey));
        } catch (Exception var3) {
            return null;
        }
    }

    private byte[] encrypt(byte[] src, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec secureKey = new SecretKeySpec(key.getBytes(), "AES");
        cipher.init(1, secureKey);
        return cipher.doFinal(src);
    }

    private byte[] decrypt(byte[] src, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec secureKey = new SecretKeySpec(key.getBytes(), "AES");
        cipher.init(2, secureKey);
        return cipher.doFinal(src);
    }

    private String byte2hex(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;

        for (byte value : b) {
            stmp = Integer.toHexString(value & 255);
            if (stmp.length() == 1) {
                hs.append("0").append(stmp);
            } else {
                hs.append(stmp);
            }
        }

        return hs.toString().toUpperCase();
    }

    private byte[] hex2byte(byte[] b) {
        if (b.length % 2 != 0) {
            throw new IllegalArgumentException("长度不是偶数");
        } else {
            byte[] b2 = new byte[b.length / 2];

            for (int n = 0; n < b.length; n += 2) {
                String item = new String(b, n, 2);
                b2[n / 2] = (byte) Integer.parseInt(item, 16);
            }

            return b2;
        }
    }

}
