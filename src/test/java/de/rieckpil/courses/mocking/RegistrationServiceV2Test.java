package de.rieckpil.courses.mocking;

import de.rieckpil.courses.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class RegistrationServiceV2Test {

  @Mock
  private UserRepository userRepository;

  @Mock
  private BannedUsersClient bannedUsersClient;

  @InjectMocks
  private RegistrationService cut;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    this.cut = new RegistrationService(userRepository, bannedUsersClient);
  }

  @Test
  void shouldRegisterUnknownUser() {

    System.out.println(userRepository.toString());
    System.out.println(userRepository.getClass());

    System.out.println(bannedUsersClient.toString());
    System.out.println(bannedUsersClient.getClass());

    User user = cut.registerUser("duke", Utils.createContactInformation("duke"));
  }
}
