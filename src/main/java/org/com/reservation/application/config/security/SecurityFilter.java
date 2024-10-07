package org.com.reservation.application.config.security;

import com.nimbusds.jwt.SignedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.com.reservation.application.config.exception.MissingHeaderException;
import org.com.reservation.domain.entity.User;
import org.com.reservation.domain.entity.UserRole;
import org.com.reservation.domain.interfaces.dataprovider.UserDataProvider;
import org.com.reservation.domain.interfaces.dataprovider.UserRoleDataProvider;
import org.com.reservation.domain.interfaces.utils.AuthenticationUtils;
import org.com.reservation.domain.usecase.user.common.exception.UserNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@Component
public class SecurityFilter extends OncePerRequestFilter {
    private final UserDataProvider userDataProvider;
    private final UserRoleDataProvider userRoleDataProvider;

    private final AuthenticationUtils authenticationUtils;

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {
        String authorizationHeader = getAuthToken(request);

        if (authorizationHeader == null) {
            throw new MissingHeaderException("Authorization");
        } else {
            authorizationHeader = authorizationHeader.replaceAll("Bearer ", "");
        }

        try {
            SignedJWT signedJWT = authenticationUtils.verify(authorizationHeader);
            Long subjectId = Long.valueOf(signedJWT.getJWTClaimsSet().getSubject());

            User user = userDataProvider.findByUserId(subjectId).orElseThrow(UserNotFoundException::new);

            Boolean accountEnabled = user.getDisabledAt() != null;
            List<UserRole> userRoles = userRoleDataProvider.findByUserId(user.getId());
            UserDetailsImpl userDetails = new UserDetailsImpl(user.getPassword(), user.getId().toString(), accountEnabled, userRoles);

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                user.getId(),
                null,
                userDetails.getAuthorities()
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        boolean skipFilter = false;

        switch (request.getServletPath()) {
            case "/api/v1/user/login",
                 "/api/v1/user/register" -> skipFilter = true;
        }

        return skipFilter;
    }

    private String getAuthToken(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization");

        if (authToken == null || authToken.isEmpty()) return null;

        return authToken;
    }
}
