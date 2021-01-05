package de.rieckpil.courses.introduction;

public class StringManipulator {

  public String manipulate(String username) {

    String input = username.substring(0, 3);

    if (input.toLowerCase().contains("a")) {
      return username.toLowerCase();
    } else {
      return username.toUpperCase();
    }
  }
}
