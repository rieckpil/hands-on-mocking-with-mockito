package de.rieckpil.courses.stubbing;

import de.rieckpil.courses.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class RegistrationServiceTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private BannedUsersClient bannedUsersClient;

  @InjectMocks
  private RegistrationService cut;

  @Test
  void defaultBehaviour() {
    System.out.println(userRepository.findByUsername("duke"));
    System.out.println(userRepository.save(new User()));
    System.out.println(bannedUsersClient.isBanned("duke", new Address()));
    System.out.println(bannedUsersClient.amountOfBannedAccounts());
    System.out.println(bannedUsersClient.amountOfGloballyBannedAccounts());
    System.out.println(bannedUsersClient.banRate());
  }

  @Test
  void basicStubbing() {
    when(bannedUsersClient.isBanned("duke", new Address())).thenReturn(true);

    System.out.println(bannedUsersClient.isBanned("duke", new Address()));

    System.out.println(new Address().equals(new Address()));
  }

  @Test
  void basicStubbingUsageOfAny() {
    // Mockito.when(bannedUsersClient.isBanned("duke", ArgumentMatchers.any(Address.class))).thenReturn(true);
    when(bannedUsersClient.isBanned(ArgumentMatchers.eq("duke"), any(Address.class))).thenReturn(true);

    System.out.println(bannedUsersClient.isBanned("duke", new Address()));
  }

  @Test
  void basicStubbingUsageThrow() {
    when(bannedUsersClient.isBanned(ArgumentMatchers.eq("duke"), any(Address.class))).thenThrow(new RuntimeException("Error"));

    assertThrows(RuntimeException.class, () -> System.out.println(bannedUsersClient.isBanned("duke", new Address())));
  }

  @Test
  void basicStubbingUsageCallRealMethod() {
    when(bannedUsersClient.isBanned(ArgumentMatchers.eq("duke"), any(Address.class))).thenCallRealMethod();
    when(bannedUsersClient.isBanned(ArgumentMatchers.eq("mike"), any(Address.class))).thenReturn(true);

    System.out.println(bannedUsersClient.isBanned("duke", new Address()));
    System.out.println(bannedUsersClient.isBanned("mike", new Address()));
  }

  @Test
  void basicStubbingUsageAnswer() {
    when(bannedUsersClient.isBanned(ArgumentMatchers.anyString(), any(Address.class))).thenAnswer(invocation -> {
      String username = invocation.getArgument(0);
      return username.length() <= 3;
    });

    System.out.println(bannedUsersClient.isBanned("duke", new Address()));
    System.out.println(bannedUsersClient.isBanned("jdk", new Address()));
  }

  @Test
  void shouldNotAllowRegistrationOfBannedUser() {
    when(bannedUsersClient.isBanned(eq("anton"), any(Address.class))).thenReturn(true);

    assertThrows(IllegalArgumentException.class, () -> cut.registerUser("anton", Utils.createContactInformation("anton@dev.biz")));
  }

  @Test
  void shouldAllowRegistrationOfNewUser() {

    when(bannedUsersClient.isBanned(eq("duke"), any(Address.class))).thenReturn(false);
    when(userRepository.findByUsername("duke")).thenReturn(null);
    when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
      User user = invocation.getArgument(0);
      user.setId(42L);
      return user;
    });

    User user = cut.registerUser("duke", Utils.createContactInformation("duke"));

    assertNotNull(user);
  }
}
