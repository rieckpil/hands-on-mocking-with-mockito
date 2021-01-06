package de.rieckpil.courses.introduction;

import de.rieckpil.courses.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleRegistrationServiceTest {

  @Test
  void shouldStoreNewUser() {

    SimpleRegistrationService cut = new SimpleRegistrationService();

    User user = cut.registerUser("duke");

  }

}
