package de.rieckpil.courses;

import java.time.LocalDateTime;

public class RegistrationService {

  private final UserRepository userRepository;
  private final BannedUserClient bannedUserClient;

  public RegistrationService(UserRepository userRepository, BannedUserClient bannedUserClient) {
    this.userRepository = userRepository;
    this.bannedUserClient = bannedUserClient;
  }

  public Long registerUser(String username) {

    if (username.isBlank()) {
      throw new IllegalArgumentException("Username must not be blank");
    }

    if (bannedUserClient.isBanned(username)) {
      // banned user
      return 42L;
    }

    if (userRepository.findByUsername(username.toUpperCase()) != null) {
      // already registered
      return 32L;
    }

    User user = new User();
    user.setUsername(username);
    user.setCreatedAt(LocalDateTime.now());

    User savedUser = userRepository.save(user);

    return savedUser.getId();
  }
}
