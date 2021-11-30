package de.rieckpil.courses.verification;

import de.rieckpil.courses.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.WARN)
public class RegistrationServiceVerificationTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private BannedUsersClient bannedUsersClient;

  @Captor
  private ArgumentCaptor<User> userArgumentCaptor;

  @Captor
  private ArgumentCaptor<String> stringArgumentCaptor;

  @Captor
  private ArgumentCaptor<Address> addressArgumentCaptor;

  @InjectMocks
  private RegistrationService cut;

  @Test
  void basicVerification() {
  }

  @Test
  void additionalVerificationOptions() {
  }

  @Test
  void argumentCaptorsWhenVerifying() {
  }
}
