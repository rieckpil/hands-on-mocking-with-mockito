package de.rieckpil.courses.stubbing;

import de.rieckpil.courses.BannedUsersClient;
import de.rieckpil.courses.RegistrationService;
import de.rieckpil.courses.User;
import de.rieckpil.courses.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class RegistrationServiceBDDTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private BannedUsersClient bannedUsersClient;

  @InjectMocks
  private RegistrationService cut;

  @Test
  void basicStubbingWithBDD() {

    BDDMockito
      .given(userRepository.findByUsername("duke"))
      .willReturn(new User());

    BDDMockito
      .given(userRepository.save(any(User.class)))
      .willAnswer(invocation -> {
        User user = invocation.getArgument(0);
        user.setId(42L);
        return user;
      });

    BDDMockito
      .given(userRepository.findByUsername("mike"))
      .willThrow(new RuntimeException("Error in DB"));

    System.out.println(userRepository.findByUsername("duke"));
    assertThrows(RuntimeException.class, () -> System.out.println(userRepository.findByUsername("mike")));
    System.out.println(userRepository.save(new User()).getId());
  }
}
