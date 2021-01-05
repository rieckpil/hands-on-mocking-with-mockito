package de.rieckpil.courses;

import java.time.LocalDateTime;

public class RegistrationService {

  private final UserRepository userRepository;
  private final BannedUsersClient bannedUsersClient;

  public RegistrationService(UserRepository userRepository, BannedUsersClient bannedUsersClient) {
    this.userRepository = userRepository;
    this.bannedUsersClient = bannedUsersClient;
  }

  public User registerUser(String username, ContactInformation contactInformation) {

    if (bannedUsersClient.isBanned(username, contactInformation.getAddress())) {
      throw new IllegalArgumentException("This user is banned from our system");
    }

    User existingUser = userRepository.findByUsername(username);

    if (existingUser != null) {
      return existingUser;
    }

    User user = new User();
    user.setUsername(username);
    user.setEmail(contactInformation.getEmail() == null ? username + "myorg.io" : contactInformation.getEmail());
    user.setCreatedAt(LocalDateTime.now());

    return userRepository.save(user);
  }
}
