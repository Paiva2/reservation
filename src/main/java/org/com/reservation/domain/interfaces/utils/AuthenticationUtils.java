package org.com.reservation.domain.interfaces.utils;

import org.com.reservation.domain.entity.User;

public interface AuthenticationUtils {
    String sign(User user) throws Exception;
}
