package com.example.springdemo.tools;

import com.example.springdemo.dao.domain.HsUser;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
public class SystemTools {


    /**
     *  1.使用者
     *  2.要判斷的字串
     *  3.判斷的內容
     *
     *  判斷字串是否為空字串
     *
     * @author Lime
     * @param hsUser, s, remark
     * @return java.lang.Boolean
     */
    public static Boolean isNullStringTools(HsUser hsUser, String s, String remark){
        if (s.trim() == "" || s.equals("") || s == null){
            log.error("[ERROR] User : [ " + hsUser.getUsername() + " ] "+ remark + " 命中isNull 輸入內容 : { "+s+" }");
            return true;
        };
        return  false;
    }

    /**
     *  使用UUID 產生TOKEN
     *
     * @author Lime
     * @param
     * @return java.lang.String
     */

    public static String uuidToken(){
        UUID randomUUID =UUID.randomUUID();
        String id =  randomUUID.toString();
        String token = id.replaceAll("-","");

        return token;
    }

    public static String md5Token(String tools) throws NoSuchAlgorithmException {
        String message = tools + LocalDateTime.now()+uuidToken();
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

    public static Cookie setCookie(final HsUser hsUser) {
        final Cookie cookie = new Cookie("login_",hsUser.getToken());
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setMaxAge(60*5);
        return cookie;
    }
    public static void delCookie(final HttpServletResponse response) {
        Cookie cookie = new Cookie("login_", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
