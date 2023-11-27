package com.example.springdemo.tools;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
public class SystemTools {

    private static final String COOKIE_TOKEN = "Authorization";

    /**
     * 使用UUID 產生TOKEN
     *
     * @param
     * @return java.lang.String
     * @author Lime
     */

    public static String uuidToken() {
        UUID randomUUID = UUID.randomUUID();
        String id = randomUUID.toString();
        String token = id.replaceAll("-", "");

        return token;
    }

    public static String md5Token(String tools) throws NoSuchAlgorithmException {
        String message = tools + LocalDateTime.now() + uuidToken();
        byte[] messageBytes = message.getBytes();

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashBytes = md.digest(messageBytes);

        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }
        String hash = sb.toString();
        return hash;
    }

    public static Cookie setCookie(final String token) {
        final Cookie cookie = new Cookie(COOKIE_TOKEN, token);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setMaxAge(60 * 5);
        return cookie;
    }

    public static void delCookie(final HttpServletResponse response) {
        Cookie cookie = new Cookie("login_", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
