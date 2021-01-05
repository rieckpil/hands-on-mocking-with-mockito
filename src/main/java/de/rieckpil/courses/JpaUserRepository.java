package de.rieckpil.courses;

public class JpaUserRepository implements UserRepository {

  @Override
  public User save(User user) {
    System.out.println("Storing user in the database");
    return user;
  }

  @Override
  public User findByUsername(String username) {
    System.out.println("Retrieving a given user from the database");
    return null;
  }
}
