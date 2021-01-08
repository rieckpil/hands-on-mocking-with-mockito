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

    when(bannedUsersClient.isBanned(eq("duke"), any(Address.class))).thenReturn(true);
    // when(bannedUsersClient.amountOfGloballyBannedAccounts()).thenReturn(42L);

    assertThrows(IllegalArgumentException.class,
      () -> cut.registerUser("duke", Utils.createContactInformation("duke@mockito.org")));

    Mockito.verify(bannedUsersClient).isBanned(eq("duke"), argThat(address -> address.getCity().equals("Berlin")));
    Mockito.verify(bannedUsersClient, times(1)).isBanned(eq("duke"), any(Address.class));
    Mockito.verify(bannedUsersClient, atLeastOnce()).isBanned(eq("duke"), any(Address.class));
    Mockito.verify(bannedUsersClient, atMost(1)).isBanned(eq("duke"), any(Address.class));
    Mockito.verify(bannedUsersClient, never()).bannedUserId();

    Mockito.verifyNoMoreInteractions(bannedUsersClient, userRepository);

    // Mockito.verify(bannedUsersClient, description("Nobody checked for mike")).isBanned(eq("mike"), any(Address.class));
  }

  @Test
  void additionalVerificationOptions() {

    when(bannedUsersClient.isBanned(eq("duke"), any(Address.class))).thenReturn(false);
    when(userRepository.findByUsername("duke")).thenReturn(null);
    when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
      User user = invocation.getArgument(0);
      user.setId(42L);
      return user;
    });

    User user = cut.registerUser("duke", Utils.createContactInformation("duke@mockito.org"));

    assertNotNull(user);

    Mockito.verify(userRepository).save(any(User.class));
    Mockito.verify(userRepository).findByUsername("duke");
    Mockito.verify(bannedUsersClient).isBanned(eq("duke"), any(Address.class));

    InOrder inOrder = Mockito.inOrder(userRepository, bannedUsersClient);

    inOrder.verify(bannedUsersClient).isBanned(eq("duke"), any(Address.class));
    inOrder.verify(userRepository).findByUsername("duke");
    inOrder.verify(userRepository).save(any(User.class));
  }

  @Test
  void argumentCaptorsWhenVerifying() {

    when(bannedUsersClient.isBanned(eq("duke"), any(Address.class))).thenReturn(false);
    when(userRepository.findByUsername("duke")).thenReturn(null);
    when(userRepository.save(any(User.class))).thenReturn(new User());

    User user = cut.registerUser("duke", Utils.createContactInformation());

    assertNotNull(user);

    Mockito.verify(userRepository).save(userArgumentCaptor.capture());
    Mockito.verify(bannedUsersClient).isBanned(eq("duke"), addressArgumentCaptor.capture());

    System.out.println(addressArgumentCaptor.getValue());

    User userToStore = userArgumentCaptor.getValue();

    System.out.println(userToStore);
    assertNotNull(userToStore.getUsername());
    assertNotNull(userToStore.getCreatedAt());
    assertTrue(userToStore.getEmail().contains("@myorg.io"));
    assertNull(userToStore.getId());
  }
}
