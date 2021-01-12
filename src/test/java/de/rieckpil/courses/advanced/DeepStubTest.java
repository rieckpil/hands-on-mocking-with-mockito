package de.rieckpil.courses.advanced;

import de.rieckpil.courses.Address;
import de.rieckpil.courses.ContactInformation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DeepStubTest {

  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private ContactInformation contactInformation;

  @Test
  void withoutDeepStubs() {
    ContactInformation contactInformation = Mockito.mock(ContactInformation.class);
    Address address = Mockito.mock(Address.class);

    Mockito.when(contactInformation.getAddress()).thenReturn(address);
    Mockito.when(address.getCity()).thenReturn("Berlin");

    System.out.println(contactInformation.getAddress().getCity());
  }

  @Test
  void deepStubs() {
    Mockito.mock(ContactInformation.class, Answers.RETURNS_DEEP_STUBS);

    Mockito.when(contactInformation.getAddress().getCity()).thenReturn("Berlin");

    System.out.println(contactInformation.getAddress().getCity());
  }
}
