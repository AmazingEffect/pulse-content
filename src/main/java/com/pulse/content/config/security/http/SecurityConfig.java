package com.pulse.content.config.security.http;


import com.pulse.content.config.security.http.exception.AuthJwtEntryPoint;
import com.pulse.content.config.security.http.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security 설정 클래스
 */
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter authJwtTokenFilter;
    private final AuthJwtEntryPoint authJwtEntryPoint;

    /**
     * @param http HttpSecurity 객체
     * @return SecurityFilterChain
     * @throws Exception 예외
     * @apiNote Actuator 경로에 대한 보안 필터 체인 설정
     */
    @Bean
    @Order(1)
    public SecurityFilterChain actuatorFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/actuator/**")
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }


    /**
     * @param http HttpSecurity 객체
     * @return SecurityFilterChain
     * @throws Exception 예외
     * @apiNote Spring Security 필터 체인 설정
     * Http 요청에 대한 보안 필터를 설정합니다. (gRPC는 HTTP가 아닌 다른 프로토콜을 사용하므로, 이 필터가 동작하지 않습니다.)
     */
    @Order(2)
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)                                     // CSRF 보호 비활성화
                .exceptionHandling(e -> e.authenticationEntryPoint(authJwtEntryPoint)) // 인증 예외 처리
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션 상태 비저장 설정
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/member/auth/**").permitAll()              // 인증 없이 접근 가능한 경로 설정
                            .anyRequest().authenticated();                                 // 나머지 요청은 인증 필요
                })
                .addFilterBefore(authJwtTokenFilter, UsernamePasswordAuthenticationFilter.class); // JWT 토큰 필터 설정

        return http.build();
    }

}