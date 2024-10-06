package org.com.reservation.infra.utils;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import org.apache.commons.lang3.time.DateUtils;
import org.com.reservation.domain.entity.User;
import org.com.reservation.domain.interfaces.utils.AuthenticationUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

@Component
public class AuthenticationUtilsImpl implements AuthenticationUtils {
    @Value("${jwt.priv-rsa}")
    private String privateKeyPath;

    @Value("${jwt.pub-rsa}")
    private String publicKeyPath;

    @Value("${jwt.issuer}")
    private String issuer;

    private RSAKey pubRsaKey;
    private RSAKey privRsaKey;
    private JWSSigner signer;
    private RSASSAVerifier verifier;

    private final static Integer EXPIRATION_TIME_HOURS = 7; // 7h

    @PostConstruct
    @SneakyThrows
    private void init() {
        this.pubRsaKey = getKeyFromPemFile(publicKeyPath);
        this.privRsaKey = getKeyFromPemFile(privateKeyPath);
        this.signer = signer(privRsaKey);
        this.verifier = verifier(pubRsaKey);
    }

    @Override
    public String sign(User user) throws JOSEException {
        JWTClaimsSet claims = new JWTClaimsSet.Builder()
            .subject(user.getId().toString())
            .issuer(issuer)
            .expirationTime(defineExpDate())
            .build();

        JWSHeader headers = new JWSHeader
            .Builder(JWSAlgorithm.RS256)
            .type(JOSEObjectType.JWT)
            .keyID(pubRsaKey.getKeyID())
            .build();

        SignedJWT signedJWT = new SignedJWT(headers, claims);

        signedJWT.sign(signer);
        return signedJWT.serialize();
    }

    private RSAKey getKeyFromPemFile(String path) throws IOException, JOSEException {
        String keyPEM = new String(Files.readAllBytes(Paths.get(path)));
        RSAKey rsaKey = RSAKey.parseFromPEMEncodedObjects(keyPEM).toRSAKey();

        return rsaKey;
    }

    private JWSSigner signer(RSAKey privateRSAKey) throws JOSEException {
        return new RSASSASigner(privateRSAKey);
    }

    private RSASSAVerifier verifier(RSAKey pubRSAKey) throws JOSEException {
        return new RSASSAVerifier(pubRSAKey);
    }

    private Date defineExpDate() {
        Date exp = new Date();
        exp = DateUtils.addHours(exp, EXPIRATION_TIME_HOURS);

        return exp;
    }
}
