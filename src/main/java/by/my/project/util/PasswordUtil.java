package by.my.project.util;

import org.springframework.util.DigestUtils;

public class PasswordUtil {
    public static String convertPassToMD5(String password) {
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }
}
