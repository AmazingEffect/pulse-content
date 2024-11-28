package com.pulse.content.config.security.http.filter;

import com.pulse.content.config.jwt.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;

/**
 * HTTP 요청을 필터링하여 JWT 토큰을 확인하고, 유효한 토큰인 경우 사용자 인증 정보를 설정합니다.
 * gRPC는 HTTP가 아닌 다른 프로토콜을 사용하므로, 이 필터가 동작하지 않습니다.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    // 특정 경로를 필터링에서 제외
    private static final Set<String> EXCLUDE_URLS = Set.of(
            "/actuator/info",
            "/actuator/health",
            "/actuator/env",
            "/actuator/metrics"
    );

    /**
     * HTTP 요청을 필터링하여 JWT 토큰을 확인하고, 유효한 토큰인 경우 사용자 인증 정보를 설정합니다.
     *
     * @param request     HTTP 요청 객체
     * @param response    HTTP 응답 객체
     * @param filterChain 필터 체인 객체
     * @throws ServletException 서블릿 예외
     * @throws IOException      입출력 예외
     */
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            String requestURI = request.getRequestURI();

            // 특정 경로를 필터링에서 제외 (config에서 제외 불가능하기에 여기서 처리)
            if (EXCLUDE_URLS.contains(requestURI)) {
                // 엑츄에이터 엔드포인트는 인증 없이 접근 가능합니다.
                filterChain.doFilter(request, response);
                return;
            }

            // 1. JWT 토큰을 요청에서 추출
            String jwt = parseJwt(request);

            // 2. JWT 토큰이 없는 경우 예외 처리
            if (notHasJwtToken(jwt)) {
                throw new JwtAuthenticationException("JWT token is missing");
            }

            // 3. JWT 토큰이 유효하지 않은 경우 예외 처리
            if (notValidJwtToken(jwt)) {
                throw new JwtAuthenticationException("Invalid JWT token");
            }

            // JWT 토큰이 유효한 경우, 사용자 인증 정보를 설정합니다.
            // todo: 지금은 db조회를 안하고 토큰에서 꺼내서 쓰는데 db조회해서 검증 해야할지 고민..
            if (StringUtils.hasText(jwt) && jwtTokenProvider.validateJwtToken(jwt)) {
                Authentication authentication = jwtTokenProvider.getAuthenticationFromGrpcToken(jwt);

                // 인증 객체를 설정합니다.
                if (authentication != null) {
                    // 세부 정보를 설정하려면 UsernamePasswordAuthenticationToken으로 캐스팅해야 합니다.
                    UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) authentication;
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        } catch (Exception ex) {
            logger.error("Could not set user authentication in security context", ex);
        }
        filterChain.doFilter(request, response);
    }


    /**
     * @param request HttpServletRequest 객체
     * @return 추출된 JWT 토큰 문자열, 없으면 null
     * @apiNote HTTP 요청에서 JWT 토큰을 추출하는 메서드
     */
    private String parseJwt(HttpServletRequest request) {
        // 1. Authorization 헤더에서 JWT 토큰을 추출
        String bearerToken = request.getHeader("Authorization");

        // 2. "Bearer " 접두사를 제거하고 JWT 토큰 반환
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        // 3. JWT 토큰이 없는 경우 null 반환
        return null;
    }

    /**
     * @param jwt JWT 토큰 문자열
     * @return JWT 토큰이 없는 경우 true, 있는 경우 false
     * @apiNote JWT 토큰이 없는 경우를 확인하는 메서드
     */
    private boolean notHasJwtToken(String jwt) {
        return !StringUtils.hasText(jwt);
    }


    /**
     * @param jwt JWT 토큰 문자열
     * @return JWT 토큰이 유효하지 않은 경우 true, 유효한 경우 false
     * @apiNote JWT 토큰이 유효하지 않은 경우를 확인하는 메서드
     */
    private boolean notValidJwtToken(String jwt) {
        return !jwtTokenProvider.validateJwtToken(jwt);
    }

}
