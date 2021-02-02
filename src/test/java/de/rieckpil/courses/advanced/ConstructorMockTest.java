package de.rieckpil.courses.advanced;

import de.rieckpil.courses.JpaUserRepository;
import de.rieckpil.courses.User;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNotNull;

// since 3.5.0
class ConstructorMockTest {

  @Test
  void constructorMocking() {

    System.out.println(new JpaUserRepository().findByUsername("mike"));

    try (MockedConstruction<JpaUserRepository> mocked = Mockito.mockConstruction(JpaUserRepository.class)) {

      JpaUserRepository jpaUserRepository = new JpaUserRepository();

      Mockito.when(jpaUserRepository.findByUsername("duke")).thenReturn(new User());

      assertNotNull(jpaUserRepository.findByUsername("duke"));

      Mockito.verify(jpaUserRepository).findByUsername("duke");
    }

    System.out.println(new JpaUserRepository().findByUsername("duke"));
  }

  @Test
  void constructorMockingWithDirectStubbing() {

    try (MockedConstruction<JpaUserRepository> mocked = Mockito.mockConstruction(JpaUserRepository.class,
      (mock, context) -> Mockito.when(mock.findByUsername("duke")).thenReturn(new User()))) {

      JpaUserRepository jpaUserRepository = new JpaUserRepository();

      assertNotNull(jpaUserRepository.findByUsername("duke"));

      Mockito.verify(jpaUserRepository).findByUsername("duke");
    }

  }
}
