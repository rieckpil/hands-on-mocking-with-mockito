package de.rieckpil.courses.introduction;

import de.rieckpil.courses.User;
import org.junit.jupiter.api.Test;

class SimpleRegistrationServiceTest {

  @Test
  void shouldRegisterUnknownUser() {

    SimpleRegistrationService cut = new SimpleRegistrationService();

    // how to control the behavior of the collaborators?
    User user = cut.registerUser("duke");
  }

}
