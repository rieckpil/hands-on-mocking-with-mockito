package de.rieckpil.courses.stubbing;

import de.rieckpil.courses.EventNotifier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class EventNotifierTest {

  @Mock
  private EventNotifier eventNotifier;

  @Test
  void voidMethodStubbing() {
  }

}
