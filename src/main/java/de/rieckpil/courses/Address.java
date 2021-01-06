package de.rieckpil.courses;

import java.util.Objects;

public class Address {

  private String country;
  private String city;
  private Integer postalCode;

  public Address() {
  }

  public Address(String country, String city, Integer postalCode) {
    this.country = country;
    this.city = city;
    this.postalCode = postalCode;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public Integer getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(Integer postalCode) {
    this.postalCode = postalCode;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Address address = (Address) o;
    return Objects.equals(country, address.country) &&
      Objects.equals(city, address.city) &&
      Objects.equals(postalCode, address.postalCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(country, city, postalCode);
  }
}
