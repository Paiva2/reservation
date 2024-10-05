package org.com.reservation.infra.utils;

import lombok.AllArgsConstructor;
import org.com.reservation.domain.interfaces.EmailUtils;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@AllArgsConstructor
@Component
public class EmailUtilsImpl implements EmailUtils {
    private final Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Override
    public boolean regexValidation(String email) {
        return emailPattern.pattern().matches(email);
    }
}
