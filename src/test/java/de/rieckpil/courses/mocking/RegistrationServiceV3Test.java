package de.rieckpil.courses.mocking;

import de.rieckpil.courses.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceV3Test {

  @Mock
  private UserRepository userRepository;

  @Mock
  private BannedUsersClient bannedUsersClient;

  @InjectMocks
  private RegistrationService cut;

  @Test
  void shouldRegisterUnknownUser() {

    System.out.println(userRepository.toString());
    System.out.println(userRepository.getClass());

    System.out.println(bannedUsersClient.toString());
    System.out.println(bannedUsersClient.getClass());

    User user = cut.registerUser("duke", Utils.createContactInformation("duke"));
  }
}
