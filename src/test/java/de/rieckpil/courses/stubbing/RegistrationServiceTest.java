package de.rieckpil.courses.stubbing;

import de.rieckpil.courses.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
    System.out.println(userRepository.findByUsername("mike"));
    System.out.println(userRepository.save(new User()));
    System.out.println(bannedUsersClient.isBanned("mike", new Address()));
    System.out.println(bannedUsersClient.amountOfBannedAccounts());
    System.out.println(bannedUsersClient.amountOfGloballyBannedAccounts());
    System.out.println(bannedUsersClient.banRate());
    System.out.println(bannedUsersClient.bannedUserId());
  }

  @Test
  void basicStubbing() {
    Mockito.when(bannedUsersClient.isBanned("duke", new Address())).thenReturn(true);

    System.out.println(bannedUsersClient.isBanned("duke", new Address()));
    System.out.println(bannedUsersClient.isBanned("duke", null));
    System.out.println(bannedUsersClient.isBanned("mike", new Address()));
  }

  @Test
  void basicStubbingWithArgumentMatchers() {
    Mockito.when(bannedUsersClient
      .isBanned(ArgumentMatchers.eq("duke"), ArgumentMatchers.any(Address.class))).thenReturn(true);

    Mockito.when(bannedUsersClient
      .isBanned(ArgumentMatchers.anyString(), ArgumentMatchers.isNull())).thenReturn(true);

    Mockito.when(bannedUsersClient
      .isBanned(ArgumentMatchers.argThat(s -> s.length() <= 3), ArgumentMatchers.isNull())).thenReturn(false);

    System.out.println(bannedUsersClient.isBanned("duke", new Address()));
    System.out.println(bannedUsersClient.isBanned("shdshfhsdlf", null));
    System.out.println(bannedUsersClient.isBanned("foo", null));
  }

  @Test
  void basicStubbingUsageThrows() {

    when(bannedUsersClient.isBanned(eq("duke"), any())).thenThrow(new RuntimeException("Remote system is down!"));

    System.out.println(bannedUsersClient.isBanned("mike", null));

    assertThrows(RuntimeException.class, () ->
      System.out.println(bannedUsersClient.isBanned("duke", new Address())));
  }

  @Test
  void basicStubbingUsageCallRealMethod() {
    when(bannedUsersClient.isBanned(eq("duke"), any(Address.class))).thenCallRealMethod();

    System.out.println(bannedUsersClient.isBanned("duke", new Address()));
  }

  @Test
  void basicStubbingUsageThenAnswer() {
    when(bannedUsersClient.isBanned(eq("duke"), any(Address.class))).thenAnswer(invocation -> {
      String username = invocation.getArgument(0);
      Address address = invocation.getArgument(1);
      return username.contains("d") && address.getCity().contains("d");
    });

    Address address = new Address();
    address.setCity("Berlin");

    System.out.println(bannedUsersClient.isBanned("duke", address));

    Address addressTwo = new Address();
    addressTwo.setCity("London");

    System.out.println(bannedUsersClient.isBanned("duke", addressTwo));

    // User returnedUser = new User();
    // returnedUser.setId(42L);
    // when(userRepository.save(any(User.class))).thenReturn(returnedUser);

    when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
      User user = invocation.getArgument(0);
      user.setId(42L);
      return user;
    });

    System.out.println(userRepository.save(new User()).getId());
  }

  @Test
  void shouldNotAllowRegistrationOfBannedUsers() {

    when(bannedUsersClient.isBanned(eq("duke"), any(Address.class))).thenReturn(true);

    assertThrows(IllegalArgumentException.class,
      () -> cut.registerUser("duke", Utils.createContactInformation("duke@mockito.org")));

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

    User user = cut.registerUser("duke", Utils.createContactInformation("duke@mockito.org"));

    assertNotNull(user);
  }
}
