package pl.akademiaspecjalistowit.ecommerce.security.authentication.configuration;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import pl.akademiaspecjalistowit.ecommerce.domain.exceptionhandler.CustomAuthenticationFailureHandler;
import pl.akademiaspecjalistowit.ecommerce.security.authentication.CustomOAuth2UserService;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/products").permitAll()
                        .requestMatchers(HttpMethod.POST, "/register").permitAll()
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                        .anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable) //for testing in postman
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuth2UserService))
                        .loginPage("/oauth2/authorization/github")
                        .failureHandler(customAuthenticationFailureHandler())
                );

        return http.build();
    }

    @Bean
    public CustomAuthenticationFailureHandler customAuthenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
