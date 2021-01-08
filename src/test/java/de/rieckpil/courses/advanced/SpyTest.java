package de.rieckpil.courses.advanced;

import de.rieckpil.courses.BannedUsersClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SpyTest {

  @Spy
  private BannedUsersClient bannedUsersClient;

  @Test
  void spies() {
    Mockito.when(bannedUsersClient.amountOfBannedAccounts()).thenReturn(500);

    System.out.println(bannedUsersClient.amountOfBannedAccounts());
    System.out.println(bannedUsersClient.amountOfGloballyBannedAccounts());
    System.out.println(bannedUsersClient.banRate());

    Mockito.verify(bannedUsersClient).banRate();
    Mockito.verify(bannedUsersClient).amountOfGloballyBannedAccounts();
    Mockito.verify(bannedUsersClient).amountOfBannedAccounts();

  }
}
