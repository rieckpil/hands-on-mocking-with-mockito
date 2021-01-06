package de.rieckpil.courses.mocking;

import de.rieckpil.courses.BannedUsersClient;
import de.rieckpil.courses.RegistrationService;
import de.rieckpil.courses.UserRepository;
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
  void setUp() {
    MockitoAnnotations.openMocks(this);
    // this.cut = new RegistrationService(userRepository, bannedUsersClient);
  }

  @Test
  void shouldRegisterUnknownUser() {
    System.out.println(userRepository.getClass());
    System.out.println(bannedUsersClient.getClass());

    System.out.println(cut.toString());
  }
}
