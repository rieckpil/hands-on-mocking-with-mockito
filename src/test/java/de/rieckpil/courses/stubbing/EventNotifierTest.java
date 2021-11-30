package de.rieckpil.courses.stubbing;

import de.rieckpil.courses.BannedUsersClient;
import de.rieckpil.courses.EventNotifier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class EventNotifierTest {

  @Mock
  private EventNotifier eventNotifier;

  @Mock
  private BannedUsersClient bannedUsersClient;

  @Test
  void voidMethodStubbing() {
  }

  @Test
  void doReturnExample() {
  }
}
