package uahb.m1gl.gestionscolarite.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import uahb.m1gl.gestionscolarite.service.CustomUserDetailService;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    private  final CustomUserDetailService customUserDetailService;

    public SecurityConfig(CustomUserDetailService customUserDetailService) {
        this.customUserDetailService = customUserDetailService;
    }
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth->auth
                        .requestMatchers(new AntPathRequestMatcher("/login")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/admin/**")).hasRole("admin")
                        .requestMatchers(new AntPathRequestMatcher("/user/**")).hasRole("user")
                        .requestMatchers(new AntPathRequestMatcher("/anonym/**"))
                        .hasAnyRole("user","admin")
                ).httpBasic(Customizer.withDefaults())
                .userDetailsService(customUserDetailService)
                .build();
    }
}
