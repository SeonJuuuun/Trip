package com.capstone.trip.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.capstone.trip.config.auth.PrincipalDetailService;
import com.capstone.trip.config.oauth.PrincipalOauth2UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final PrincipalDetailService principalDetailService;
	private final PrincipalOauth2UserService principalOauth2UserService;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
		throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.rememberMe().tokenValiditySeconds(60 * 60 * 7)
			.userDetailsService(principalDetailService)
			.and()
			.cors().and().csrf().disable()
			.authorizeHttpRequests()
			.requestMatchers("/ws/chat/**").permitAll()
			.requestMatchers("/chat/**").permitAll()
			.requestMatchers("ws://localhost:8080/ws/chat").permitAll()
			.requestMatchers("/user/username/exists").permitAll() // 허용할 경로 설정
			.requestMatchers("/user/email/exists").permitAll() // 허용할 경로 설정
			.requestMatchers("/user/nickname/exists").permitAll() // 허용할 경로 설정
			.requestMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/auth/user/login")
			.loginProcessingUrl("/auth/api/v1/user/login")
			.defaultSuccessUrl("/")
			.and()
			.oauth2Login()
			.loginPage("/auth/user/login")
			.defaultSuccessUrl("/")            // 로그인 성공하면 "/" 으로 이동
			.userInfoEndpoint()
			.userService(principalOauth2UserService);

		http.headers().frameOptions().sameOrigin();

		return http.build();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers("/images/**", "/js/**", "/webjars/**");
	}

}
