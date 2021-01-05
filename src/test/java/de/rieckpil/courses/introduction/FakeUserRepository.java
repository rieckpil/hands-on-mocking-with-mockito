package de.rieckpil.courses.introduction;

import de.rieckpil.courses.User;
import de.rieckpil.courses.UserRepository;

public class FakeUserRepository implements UserRepository {

  @Override
  public User save(User user) {
    System.out.println("# FakeUserRepository: Storing the user");
    return user;
  }

  @Override
  public User findByUsername(String username) {
    System.out.println("# FakeUserRepository: Retrieving a user by its username");
    return null;
  }
}
