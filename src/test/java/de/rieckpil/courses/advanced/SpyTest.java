package de.rieckpil.courses.advanced;

import de.rieckpil.courses.BannedUsersClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
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
    Mockito.when(bannedUsersClient.amountOfBannedAccounts()).thenReturn(500);

    System.out.println(bannedUsersClient.amountOfBannedAccounts());
    System.out.println(bannedUsersClient.amountOfGloballyBannedAccounts());
    System.out.println(bannedUsersClient.banRate());

    Mockito.verify(bannedUsersClient).banRate();
    Mockito.verify(bannedUsersClient).amountOfGloballyBannedAccounts();
    Mockito.verify(bannedUsersClient).amountOfBannedAccounts();
  }

  @Test
  void spiesGotcha() {
    List spiedList = Mockito.spy(ArrayList.class);

    // Mockito.when(spy.get(0)).thenReturn("spy");

    Mockito.doReturn("spy").when(spiedList).get(0);

    System.out.println(spiedList.get(0));
  }
}
