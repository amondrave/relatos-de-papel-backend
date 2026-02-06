package com.relatosdepapel.payments.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "payments")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Payment {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 /** Lista de IDs de libros comprados (referencia lógica al catálogo). */
 @ElementCollection
 @CollectionTable(name = "payment_books", joinColumns = @JoinColumn(name = "payment_id"))
 @Column(name = "book_id", nullable = false)
 private List<Long> books;

 /** Identificador simple de comprador (opcional, útil para trazabilidad). */
 @Column(length = 120)
 private String customer;

 @Column(nullable = false, updatable = false)
 private Instant createdAt;

 @PrePersist
 void onCreate() {
  createdAt = Instant.now();
 }
}
