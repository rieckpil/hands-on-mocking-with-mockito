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
  }
}
