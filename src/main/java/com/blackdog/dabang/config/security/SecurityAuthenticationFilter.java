package com.blackdog.dabang.config.security;

import com.blackdog.dabang.common.response.error.ErrorCode;
import com.blackdog.dabang.common.response.error.ErrorResponse;
import com.blackdog.dabang.config.jwt.JwtProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
@Slf4j
@Component
public class SecurityAuthenticationFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;
    private final SecurityUserDetailsService securityUserDetailsService;
    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("==================================== Security Authentication Filter START ====================================");

        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.isBlank(authorization)) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String jwtToken = jwtProvider.getJwtToken(request);
            String userId = jwtProvider.getUserId(jwtToken);
            UserDetails userDetails = securityUserDetailsService.loadUserByUsername(userId);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch(Exception e) {
            // TODO : ExceptionHandlerMethodResolver 알아보기
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setCharacterEncoding("UTF-8");
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(objectMapper.writeValueAsString(ErrorResponse.of(ErrorCode.JWT_BAD_REQUEST)));
            e.printStackTrace();
            return;
        }

        filterChain.doFilter(request, response);
        log.info("==================================== Security Authentication Filter END ====================================");
    }
}
