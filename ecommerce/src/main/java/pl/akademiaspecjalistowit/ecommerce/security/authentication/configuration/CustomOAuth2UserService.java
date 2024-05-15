package pl.akademiaspecjalistowit.ecommerce.security.authentication.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.domain.exception.CustomAuthenticationException;
import pl.akademiaspecjalistowit.ecommerce.security.authentication.entity.UserEntity;
import pl.akademiaspecjalistowit.ecommerce.security.authentication.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user = super.loadUser(userRequest);
        try {
            return processOAuth2User(user);
        } catch (CustomAuthenticationException e) {
            throw new RuntimeException(e);
        }
    }


    private OAuth2User processOAuth2User(OAuth2User oauthUser) throws CustomAuthenticationException {
        String username = oauthUser.getAttributes().get("login").toString();
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomAuthenticationException("No account found with username %s " + username));
        user.setAttributes(oauthUser.getAttributes());
        return user;
    }

}
