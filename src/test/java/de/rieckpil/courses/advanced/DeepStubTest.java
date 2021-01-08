package de.rieckpil.courses.advanced;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.URI;
import java.net.http.HttpRequest;

@ExtendWith(MockitoExtension.class)
public class DeepStubTest {

  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private HttpRequest.Builder mockHttpRequestBuilder;

  @Test
  void withoutDeepStubs() {
    HttpRequest request = HttpRequest.newBuilder()
      .uri(URI.create("http://localhost:8080"))
      .header("Accept", "application/json")
      .GET()
      .build();
  }

  @Test
  void deepStubs() {
  }
}
