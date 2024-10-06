package org.com.reservation.domain.interfaces.utils;

public interface PasswordUtils {
    String encode(CharSequence rawPassword);

    boolean matches(CharSequence rawPassword, String encodedPassword);
}
