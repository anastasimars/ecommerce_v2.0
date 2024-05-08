package pl.akademiaspecjalistowit.ecommerce.security.authentication.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/catalog", "/register").permitAll()
                        .anyRequest().authenticated())
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/oauth2/authorization/login-client")
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(this.oauth2UserService())));

        return http.build();
    }

    private OAuth2UserService<OAuth2UserRequest, OAuth2User> oauth2UserService() {
        return new CustomOAuth2UserService();
    }

}
