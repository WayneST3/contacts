package dev.wayne.contacts.util;

import dev.wayne.contacts.exception.ApiException;
import jakarta.servlet.http.HttpServletResponse;

public class PhoneCheck {

    public static String validatePhoneNumber(String phone) {
        if (phone == null) {
            throw new ApiException(HttpServletResponse.SC_BAD_REQUEST, "Номер телефона не задан");
        }
        phone = phone.replaceAll(" ", "");
        phone = phone.replaceAll("-", "");
        phone = phone.replaceAll("\\(", "");
        phone = phone.replaceAll("\\)", "");
        phone = phone.replaceAll("\\+", "");

        if (phone.isBlank() || phone.length() < 10 || phone.length() > 11 || !phone.matches("[0-9]+")) {
            throw new ApiException(HttpServletResponse.SC_BAD_REQUEST, "Неверный формат телефона");
        }
        if (phone.length() == 11) {
            if (phone.startsWith("7") || phone.startsWith("8")) {
                return "+7" + phone.substring(1);
            }
        }
        if (phone.length() == 10) {
            return "+7" + phone;
        }
        throw new ApiException(HttpServletResponse.SC_BAD_REQUEST, "Неверный формат телефона");
    }
}
