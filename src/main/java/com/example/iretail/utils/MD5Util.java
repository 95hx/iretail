package com.example.iretail.utils;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

/**
 * MD5 加密md5()
 * MD5 验证verify()
 */
@Component
public class MD5Util {
    /**
     * MD5方法
     *
     * @param text 明文
     * @param key  密钥
     * @return 密文
     */
    public String md5(String text, String key) {
        //加密后的字符串
        String encodeStr = DigestUtils.md5DigestAsHex((text + key).getBytes());
        return encodeStr;
    }

    /**
     * MD5验证方法
     *
     * @param text 明文
     * @param key  密钥
     * @param md5  密文
     * @return true/false
     * @throws Exception
     */
    public boolean verify(String text, String key, String md5) throws Exception {
        //根据传入的密钥进行验证
        String md5Text = md5(text, key);
        if (md5Text.equalsIgnoreCase(md5)) {
            return true;
        }
        return false;
    }
}
