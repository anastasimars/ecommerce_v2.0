package pl.akademiaspecjalistowit.ecommerce.security.authentication.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.domain.exception.CustomAuthenticationException;
import pl.akademiaspecjalistowit.ecommerce.security.authentication.entity.AuthorityEntity;
import pl.akademiaspecjalistowit.ecommerce.security.authentication.entity.UserEntity;
import pl.akademiaspecjalistowit.ecommerce.security.authentication.repository.UserRepository;

import java.util.Set;

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
        String username = oauthUser.getAttributes().get("login").toString(); //use of login as username
        String email = oauthUser.getAttributes().get("email").toString();
        UserEntity user = userRepository.findByUsername(username)
                .orElse(null);
        if (user == null) {
            AuthorityEntity authority = new AuthorityEntity("ROLE_CLIENT");
            user = new UserEntity(Set.of(authority), email, username, "", oauthUser.getAttributes());
            userRepository.save(user);
        } else {
            user.setAttributes(oauthUser.getAttributes());
        }

        return user;
    }

}
