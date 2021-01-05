package de.rieckpil.courses.mocking;

import de.rieckpil.courses.*;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

class RegistrationServiceV1Test {

  private UserRepository userRepository = mock(UserRepository.class);
  private BannedUsersClient bannedUsersClient = mock(BannedUsersClient.class);
  private RegistrationService cut = new RegistrationService(userRepository, bannedUsersClient);

  @Test
  void shouldRegisterUnknownUser() {

    UserRepository realRepository = new JpaUserRepository();

    System.out.println(realRepository.toString());
    System.out.println(realRepository.getClass());

    System.out.println(userRepository.toString());
    System.out.println(userRepository.getClass());

    System.out.println(bannedUsersClient.toString());
    System.out.println(bannedUsersClient.getClass());

    User user = cut.registerUser("duke", Utils.createContactInformation("duke"));
  }

}
