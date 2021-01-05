package de.rieckpil.courses.introduction;

import de.rieckpil.courses.JpaUserRepository;
import de.rieckpil.courses.User;
import de.rieckpil.courses.UserRepository;

import java.time.LocalDateTime;

public class SimpleRegistrationService {

  public User registerUser(String username) {

    UserRepository userRepository = new JpaUserRepository();

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
