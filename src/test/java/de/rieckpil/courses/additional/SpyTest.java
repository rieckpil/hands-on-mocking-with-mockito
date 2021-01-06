package de.rieckpil.courses.additional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class SpyTest {

  @Test
  void spies() {
    List<String> spy = Mockito.spy(new LinkedList<>());

    // when(spy.get(0)).thenReturn("foo");

    doReturn("foo").when(spy).get(0);

    System.out.println(spy.get(0));
  }
}
