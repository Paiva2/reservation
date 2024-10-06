package org.com.reservation.infra.utils;

import lombok.AllArgsConstructor;
import org.com.reservation.domain.interfaces.EmailUtils;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@AllArgsConstructor
@Component
public class EmailUtilsImpl implements EmailUtils {
    private final Pattern emailPattern = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", Pattern.CASE_INSENSITIVE);

    @Override
    public boolean regexValidation(String email) {
        return emailPattern.matcher(email).find();
    }
}
