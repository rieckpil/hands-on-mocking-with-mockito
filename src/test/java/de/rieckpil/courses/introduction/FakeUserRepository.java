package de.rieckpil.courses.introduction;

import de.rieckpil.courses.User;
import de.rieckpil.courses.UserRepository;

public class FakeUserRepository implements UserRepository {

  @Override
  public User save(User user) {
    System.out.println("# FakeUserRepository save");
    return null;
  }

  @Override
  public User findByUsername(String username) {
    if (username.equals("duke")) {
      return null;
    } else if (username.equals("mike")) {
      throw new RuntimeException("Error in DB");
    }

    System.out.println("# FakeUserRepository findByUsername");
    return new User();
  }
}
