package de.rieckpil.courses;

public class Utils {

  public static ContactInformation createContactInformation(String email) {
    return createContactInformation(email, "Germany", "Berlin", 10119);
  }

  public static ContactInformation createContactInformation(String email, String country, String city, Integer postalCode) {
    Address address = new Address();
    address.setCity(city);
    address.setCountry(country);
    address.setPostalCode(postalCode);


    ContactInformation contactInformation = new ContactInformation();
    contactInformation.setEmail(email);
    contactInformation.setAddress(address);

    return contactInformation;
  }
}
