package de.rieckpil.courses.advanced;

import de.rieckpil.courses.BannedUsersClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class SpyTest {

  @Spy
  private BannedUsersClient bannedUsersClient;

  @Test
  void spies() {
  }

  @Test
  void spiesGotcha() {
  }
}
