package org.com.reservation.domain.interfaces;

public interface PasswordUtils {
    String encode(CharSequence rawPassword);

    boolean matches(CharSequence rawPassword, String encodedPassword);
}
