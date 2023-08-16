package com.capstone.trip.config.oauth;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.capstone.trip.config.auth.PrincipalDetail;
import com.capstone.trip.config.oauth.provider.GoogleUserInfo;
import com.capstone.trip.config.oauth.provider.NaverUserInfo;
import com.capstone.trip.config.oauth.provider.OAuth2UserInfo;
import com.capstone.trip.domain.user.Role;
import com.capstone.trip.domain.user.User;
import com.capstone.trip.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User oAuth2User = super.loadUser(userRequest);
		OAuth2UserInfo oAuth2UserInfo =null;

		if(userRequest.getClientRegistration().getRegistrationId().equals("google")) {
			oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
		}
		else if(userRequest.getClientRegistration().getRegistrationId().equals("naver")) {
			oAuth2UserInfo = new NaverUserInfo((Map)oAuth2User.getAttributes().get("response"));
		}


		String provider = oAuth2UserInfo.getProvider();
		String providerId = oAuth2UserInfo.getProviderId();
		String username = provider + "_" + providerId;
		String password =  UUID.randomUUID().toString();
		String email = oAuth2UserInfo.getEmail();
		Role role = Role.USER;

		Optional<User> userOptional = userRepository.findByProviderAndProviderId(oAuth2UserInfo.getProvider(),
			oAuth2UserInfo.getProviderId());

		User user;

		if (userOptional.isPresent()) {
			user = userOptional.get();
			user.setEmail(oAuth2UserInfo.getEmail());
			userRepository.save(user);
		} else {
			user = User.builder()
				.username(username)
				.password(password)
				.email(email)
				.nickname(email)
				.role(role)
				.provider(provider)
				.providerId(providerId)
				.build();
			userRepository.save(user);
		}
		return new PrincipalDetail(user, oAuth2User.getAttributes());
	}
}