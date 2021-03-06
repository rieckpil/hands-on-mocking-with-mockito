package de.rieckpil.courses.introduction;

import de.rieckpil.courses.User;
import de.rieckpil.courses.UserRepository;

import java.time.LocalDateTime;

public class RefactoredSimpleRegistrationService {

  private final UserRepository userRepository;

  public RefactoredSimpleRegistrationService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User registerUser(String username) {
    User existingUser = userRepository.findByUsername(username);

    if (existingUser != null) {
      return existingUser;
    }

    User user = new User();
    user.setUsername(username);
    user.setCreatedAt(LocalDateTime.now());

    return userRepository.save(user);
  }
}
