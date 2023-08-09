package com.capstone.trip.domain.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);

	Optional<User> findByProviderAndProviderId(String provider, String providerId);

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

	boolean existsByNickname(String nickname);
}
