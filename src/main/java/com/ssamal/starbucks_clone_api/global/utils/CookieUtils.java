package com.ssamal.starbucks_clone_api.global.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseCookie;

public class CookieUtils {
    private CookieUtils() {
        throw new IllegalStateException("Utility class");
    }
    public static ResponseCookie createCookie(String cookieName, String value, Long validTime) {
        return ResponseCookie.from(cookieName, value)
                .path("/")
                .sameSite("None")
                .httpOnly(true)
                .maxAge(validTime / 1000)
                .build();
    }

    public static Cookie getCookie(HttpServletRequest request, String cookieName) {
        if (request.getCookies().length == 0) {
            return null;
        } else {
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    public static boolean deleteCookie(HttpServletRequest request, HttpServletResponse response, String cookieName) {
        Cookie cookie = getCookie(request, cookieName);
        if (cookie != null) {
            Cookie myCookie = new Cookie(cookieName, null);
            myCookie.setMaxAge(0);
            myCookie.setPath("/");
            response.addCookie(myCookie);
            return true;
        } else {
            return false;
        }
    }
}
