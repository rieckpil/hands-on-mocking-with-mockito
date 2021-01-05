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
    BDDMockito.given(userRepository.findByUsername("mike")).willReturn(new User());

    BDDMockito.given(userRepository.save(any(User.class))).willAnswer(invocation -> {
      User userToStore = invocation.getArgument(0);
      userToStore.setId(42L);
      return userToStore;
    });


    System.out.println(userRepository.findByUsername("mike"));
    System.out.println(userRepository.save(new User()));
  }

  @Test
  void basicStubbingWithBDDThrowException() {
    BDDMockito.given(userRepository.findByUsername("admin")).willThrow(new RuntimeException("Don't request admin"));
    assertThrows(RuntimeException.class, () -> System.out.println(userRepository.findByUsername("admin")));
  }
}
