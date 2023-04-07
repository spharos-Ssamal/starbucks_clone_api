package com.ssamal.starbucks_clone_api.global.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringUtils {
    private StringUtils() {
        throw new IllegalStateException("Utiliy Class");
    }

    public static String generatePurchaseCode() {
        return "D" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSSS"));
    }
}
