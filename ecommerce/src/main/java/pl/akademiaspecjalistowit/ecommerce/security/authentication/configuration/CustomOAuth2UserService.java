package pl.akademiaspecjalistowit.ecommerce.security.authentication.configuration;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User user = super.loadUser(userRequest);

        OAuth2AccessToken accessToken = userRequest.getAccessToken();
        System.out.println("Access Token: " + accessToken.getTokenValue());

        Map<String, Object> attributes = user.getAttributes();
        System.out.println("User Attributes: " + attributes);

        Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));


        return new DefaultOAuth2User(authorities, attributes, "name");
    }
}
