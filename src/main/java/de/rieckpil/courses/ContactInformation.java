package de.rieckpil.courses;

public class ContactInformation {

  private String email;
  private Address address;

  public ContactInformation() {
  }

  public ContactInformation(String email, Address address) {
    this.email = email;
    this.address = address;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }
}
