package de.rieckpil.courses.advanced;

import de.rieckpil.courses.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

// since 3.4.0
@ExtendWith(MockitoExtension.class)
class StaticMethodMockTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private BannedUsersClient bannedUsersClient;

  @InjectMocks
  private RegistrationService cut;

  private LocalDateTime defaultLocalDateTime = LocalDateTime.of(2020, 1, 1, 12, 0);

  @Test
  void mockStaticMethod() {

    System.out.println(LocalDateTime.now());

    try (MockedStatic<LocalDateTime> mockedLocalDateTime = Mockito.mockStatic(LocalDateTime.class)) {

      mockedLocalDateTime.when(LocalDateTime::now).thenReturn(defaultLocalDateTime);

      when(bannedUsersClient.isBanned(eq("duke"), any(Address.class))).thenReturn(false);
      when(userRepository.findByUsername("duke")).thenReturn(null);
      when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
        User user = invocation.getArgument(0);
        user.setId(42L);
        return user;
      });

      User user = cut.registerUser("duke", Utils.createContactInformation("duke@mockito.org"));

      System.out.println(user.getCreatedAt());

      System.out.println(LocalDateTime.now());
      System.out.println(LocalDateTime.now());
      System.out.println(LocalDateTime.now());

      assertEquals(defaultLocalDateTime, user.getCreatedAt());
    }

    System.out.println(LocalDateTime.now());
  }
}
