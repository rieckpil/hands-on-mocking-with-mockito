package de.rieckpil.courses.advanced;

import de.rieckpil.courses.JpaUserRepository;
import de.rieckpil.courses.User;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mockConstruction;

class ConstructorMockTest {

  @Test
  void testConstructorMocking() {

    assertNull(new JpaUserRepository().findByUsername("duke"));

    try (MockedConstruction<JpaUserRepository> mocked = mockConstruction(JpaUserRepository.class)) {
      JpaUserRepository repository = new JpaUserRepository();

      Mockito.when(repository.findByUsername("duke")).thenReturn(new User());

      assertNotNull(repository.findByUsername("duke"));

      Mockito.verify(repository).findByUsername("duke");
    }

    assertNull(new JpaUserRepository().findByUsername("duke"));
  }
}
