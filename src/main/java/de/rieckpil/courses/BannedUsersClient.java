package de.rieckpil.courses;

public class BannedUsersClient {

  public boolean isBanned(String username, Address address) {
    // request information from a remote system
    System.out.println("Checking if " + username + " at given address is banned");
    return false;
  }

  public int amountOfBannedAccounts() {
    return 42;
  }

  public long amountOfGloballyBannedAccounts() {
    return 42;
  }

  public double banRate() {
    return 42.42;
  }
}
