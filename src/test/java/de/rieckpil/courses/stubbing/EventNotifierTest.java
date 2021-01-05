package de.rieckpil.courses.stubbing;

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

  @Test
  void voidMethodStubbing() {

    // Mockito.when(eventNotifier.notifyNewUserCreation("mike")).thenReturn(null);
    Mockito.doThrow(new RuntimeException("Error!")).when(eventNotifier).notifyNewUserCreation("mike");
    Mockito.doNothing().doThrow(new RuntimeException("Error!")).when(eventNotifier).notifyNewUserCreation("mike");
  }

  @Test
  void voidMethodDoNothing() {
    Mockito.doNothing().doThrow(new RuntimeException("Error!")).when(eventNotifier).notifyNewUserCreation("mike");

    eventNotifier.notifyNewUserCreation("mike");

    assertThrows(RuntimeException.class, () -> eventNotifier.notifyNewUserCreation("mike"));
  }
}
