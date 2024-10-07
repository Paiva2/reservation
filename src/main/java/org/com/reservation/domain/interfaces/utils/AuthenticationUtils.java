package org.com.reservation.domain.interfaces.utils;

import com.nimbusds.jwt.SignedJWT;
import org.com.reservation.domain.entity.User;

public interface AuthenticationUtils {
    String sign(User user) throws Exception;

    SignedJWT verify(String token) throws Exception;
}
