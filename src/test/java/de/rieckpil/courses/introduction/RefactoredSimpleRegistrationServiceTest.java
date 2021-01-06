package de.rieckpil.courses.introduction;

import de.rieckpil.courses.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RefactoredSimpleRegistrationServiceTest {

  @Test
  void shouldStoreNewUser() {

    RefactoredSimpleRegistrationService cut = new RefactoredSimpleRegistrationService(new FakeUserRepository());

    User user = cut.registerUser("duke");

  }

}
