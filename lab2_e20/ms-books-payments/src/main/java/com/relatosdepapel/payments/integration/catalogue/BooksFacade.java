package com.relatosdepapel.payments.integration.catalogue;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
@RequiredArgsConstructor
@Slf4j
public class BooksFacade {

 @Value("${catalogue.base-url}")
 private String catalogueBaseUrl;

 private final WebClient.Builder webClient;

 public Book getBook(String id) {
    String uri = catalogueBaseUrl + "/books/" + id;

  try {
   return webClient.build()
       .get()
       .uri(uri)
       .retrieve()
       .bodyToMono(Book.class)
       .timeout(Duration.ofSeconds(3))
       .onErrorResume(ex -> {
        log.error("Error consultando catálogo para bookId={}: {}", id, ex.getMessage());
        return Mono.empty();
       })
       .block();
  } catch (Exception e) {
   log.error("Fallo inesperado consultando catálogo para bookId={}: {}", id, e.getMessage());
   return null;
  }
 }
}
